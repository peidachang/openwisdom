package com.hoten.communicate.mo;
import com.hoten.cmpp.*;
import com.hoten.cmpp.message.*;
import com.hoten.communicate.info.*;
import com.hoten.util.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MO_Get_YD implements Runnable{
    private static boolean flag=true;
    private MOInfoList moList = MOInfoList.getInstance();
    private String logFile=MOParam.getInstance().logFile;
    private int sleepTime = 1000;
    private com.hoten.cmpp.CMPP_Shell cmpp = null;

    private String shellName = null;
    private String separator=null;
    private String stateFile = null;
    private String area = null;
    private String table = null;
    private String pid = null;
    private String msg=null;

    public MO_Get_YD(String shell,String statFile,String sArea,String pid,String areaName) {
        separator=System.getProperty("file.separator");
        shellName=shell.toUpperCase();
        if(statFile!=null)
            stateFile=statFile;
        else
            stateFile="."+separator+"log"+separator+"state"+separator+shellName+"_state.log";
        area=sArea.toUpperCase();
        if(area==null) area="JSYD";
        if(area.equals("JSYD")||area.equals("ZJYD"))
            table="ui_mo2";
        else if(area.equals("AHYD"))
            table="ui_mo_ah";
        else
            table="ui_mo";
        msg = areaName;
        if(pid==null) pid="0";
        this.pid=pid;
        try {
            initCMPP();
        }
        catch (Exception ex) {
            stopThread();
        }
    }
    public static void stopThread(){
        flag=false;
    }
    public void run(){
        Log.printEvent("------"+msg+"通信MO_Get启动－－－－－－",logFile);
        try {
            while(flag){
                try {
                    com.hoten.cmpp.message.DeliverMsg msg = null;
                    msg = cmpp.deliver();
                    if(msg==null){
                        Thread.currentThread().sleep(1000);
                        continue;
                    }
                    if(msg.nIsDelivery==1){
                        saveState(msg);
                    }else{
                        MOInfo moInfo = new MOInfo();
                        setMOInfoMsg(moInfo,msg);
                        moList.add(moInfo);
                    }
                }
                catch (Exception ex) {
                    Log.printError(ex,msg+"通信MO_Get接收网关短信发生错误!",logFile);
                }
                Thread.currentThread().sleep(sleepTime);
            }
        }
        catch (InterruptedException ex) {
        }
        quitCMPP();
        Log.printEvent("------"+msg+"通信MO_Get关闭－－－－－－",logFile);
    }
    private void quitCMPP(){
        cmpp.quit();
        if(cmpp.DeliverListNum()!=0){
            doMsg(cmpp);
        }
    }
    private void doMsg(CMPP_Shell cmpp){
        com.hoten.cmpp.message.DeliverMsg msg = null;
        boolean flag=true;
        while(flag){
            msg = cmpp.deliver();
            if(msg==null){
                flag=false;
            }
            if(msg.nReportMsgId!=null){
                saveState(msg);
            }else{
                MOInfo moInfo = new MOInfo();
                setMOInfoMsg(moInfo,msg);
                moList.add(moInfo);
            }
        }
    }

    private void initCMPP()throws Exception{
        cmpp = new CMPP_Shell(shellName);
        cmpp.initCMPP(MOParam.getInstance().initPath+shellName+".properties");
    }

    private void setMOInfoMsg(MOInfo moInfo,DeliverMsg msg){
        String mobiles = msg.sSrcTerminalId;
        if(table.equals("ui_mo")){
            if(!mobiles.startsWith("86")){
                mobiles="86"+mobiles;
            }
        }
        moInfo.setMobile(mobiles);
        moInfo.setTime(pid);
        moInfo.setBZ(msg.sDestId);
        moInfo.setArea(area);
        moInfo.setTable(table);
        moInfo.setMsg(msg.sMsgContent);
    }
    private void saveState(DeliverMsg cdr){
        Log.printState(cdr.sSubmitTime+"\t"+cdr.sDestTerminalId+"\t"+cdr.sStat,stateFile);
    }
}