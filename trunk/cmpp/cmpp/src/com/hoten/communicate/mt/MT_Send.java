package com.hoten.communicate.mt;
import com.hoten.cmpp.*;
import com.hoten.cmpp.message.*;
import com.hoten.communicate.info.*;
import com.hoten.util.*;
import java.io.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MT_Send implements Runnable{
    private static boolean flag = true;
    private MTInfoList mtList = MTInfoList.getInstance();
    private com.hoten.cmpp.CMPP_Shell cmpp =null;
    private static int i=0;

    private String logFile = MOParam.getInstance().logFile;
    private String errorFile = MOParam.getInstance().errorFile;
    private DBAccess dbm = new DBAccess();

    private String shellName = null;
    private String area = null;
    private String table = null;
    private String pid = null;
    private String msg=null;


    private int sleepTime = 10;

    public MT_Send(String shell,String sArea,String areaName) {
        shellName=shell.toUpperCase();
        area=sArea.toUpperCase();
        if(area==null) area="JSYD";
        if(area.equals("JSYD")||area.equals("ZJYD"))
            table="sendInfo";
        else if(area.equals("AHYD"))
            table="sendinfo_ah";
        else
            table="ui_sendinfo";
        msg = areaName;
        this.pid=shellName;
        mtList.regedit(area);
        try {
            initSetI();
            initCMPP();//初始化连接
        }
        catch (Exception ex) {
            Log.printError(ex,"ERROR",logFile);
            stopThread();//不成功停止线程的启动
        }
    }

    public static void stopThread(){
        flag=false;
    }
    private void initSetI()throws Exception{
        String sql = "select max(xh) from "+table+" where substr(xh,1,"+pid.length()+")='"+pid+"'";
        String xh = dbm.select(sql);
        if(xh==null) i=0;
        else{
            if(xh.equals("")){
                i=0;
            }else{
                i=Integer.parseInt(xh.trim().substring(pid.length()));
            }
        }
    }

    public void run(){
        Log.printEvent("------"+msg+"通信MT_Send启动－－－－－－",logFile);
        try {
            while(flag){
                MTInfo mtInfo = mtList.remove(area);//取得一条数据
                if(mtInfo == null||mtInfo.getXXNR()==null){
                    try {
                        cmpp.submit(null);
                    }
                    catch (Exception ex) {
                    }
                    Thread.currentThread().sleep(sleepTime*10);
                    continue;
                }
                try {
                    sendMT(mtInfo);//发生消息
                }
                catch (Exception ex) {
                    Log.printError(ex,"",errorFile);
                }
                mtInfo=null;
                Thread.currentThread().sleep(sleepTime);
            }
        }
        catch (InterruptedException ex) {
        }
        quitCMPP();
        Log.printEvent("------"+msg+"通信MT_Send关闭－－－－－－",logFile);
    }

    private void sendMT(MTInfo mtInfo) throws Exception{
        com.hoten.cmpp.message.SubmitMsg cs = new com.hoten.cmpp.message.SubmitMsg();//下发消息
        setSubmitMsg(cs,mtInfo);//封装ｍｔ消息
        int stat=-1;
        try {
            stat=cmpp.submit(cs);//提交下发的消息
        }
        catch (Exception ex) {
            Log.printError(ex,mtInfo.getJTLY(),mtInfo.getXXNR()+" ERROR: submit error",errorFile);
        }
        if(mtInfo.getBZ2()!=null&&mtInfo.getBZ2().equals("JF")&&stat!=0)
            saveState(stat,mtInfo);//记录下发的消息的状态
        else
            saveState(stat,mtInfo);//记录下发的消息的状态
    }

    private void quitCMPP(){
        cmpp.quit();
    }

    private void initCMPP()throws Exception{
        cmpp = new CMPP_Shell(shellName);
        cmpp.initCMPP(MOParam.getInstance().initPath+shellName+".properties");
    }

    private void setSubmitMsg(com.hoten.cmpp.message.SubmitMsg msg2,MTInfo mtInfo) throws Exception{
        msg2.nDestUsrTl=1;
        msg2.nFeeUserType=0;
        msg2.nMsgFormat=15;
        msg2.nMsgLength=mtInfo.getXXNR().length();
        msg2.nNeedReply=0;
        msg2.sDestTerminalId=mtInfo.getSJHM();
        msg2.sFeeCode=mtInfo.getFee();
        msg2.sFeeMobile=mtInfo.getJTLY();
        if(mtInfo.getFlagFee().equals("1"))
            msg2.sFeeType="02";
        if(mtInfo.getFlagFee().equals("2"))
            msg2.sFeeType="03";
        if(mtInfo.getFlagFee().equals("3"))
            msg2.sFeeType="01";
        msg2.sMsgContent=mtInfo.getXXNR();
        if(mtInfo.getXXLX()!=null && !mtInfo.getXXLX().equals("1")){
            msg2.picAndRing=getPicOrRing(mtInfo.getXXNR());
            msg2.sMsgContent=null;
            msg2.nMsgFormat=4;
        }
        msg2.sServiceId=mtInfo.getYWDM();
        msg2.sSrcId=mtInfo.getBZ3();
        if(mtInfo.getArea()==null)
            mtInfo.setArea("JSYD");
        if(mtInfo.getBZ2()!=null&&mtInfo.getBZ2().equals("JF"))
            msg2.nNeedReply=2;
    }
    private void saveState(int stat,MTInfo mtInfo){
        mtInfo.setXH(getXH());
        try {
            DBCommon.RecodeMT(stat,mtInfo,dbm,table);
        }
        catch (Exception ex) {
            Log.printError(ex,mtInfo.getJTLY(),mtInfo.getXXNR()+" ERROR: 数据库操作发生错误",errorFile);
        }
    }
    private String getXH(){
        synchronized(this.getClass()){
            i++;
            if(i==100000000) i=0;
        }
        return pid+getFormatTime(i,9);
    }
    private String getFormatTime(int time,int format){
        StringBuffer numm=new StringBuffer();
        int length=String.valueOf(time).length();

        if(format<length) return null;

        for(int i=0 ;i<format-length ;i++){
            numm.append("0");
        }
        numm.append(time);
        return numm.toString().trim();
    }
    private byte[] getPicOrRing(String msg){
        return msg.getBytes();
    }
}