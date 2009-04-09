package com.hoten.communicate.mt;
import java.sql.*;
import java.util.*;
import com.hoten.db.DBConnectionManager;
import com.hoten.util.*;
import com.hoten.communicate.info.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MT_Get_YD implements Runnable{
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private DBAccess dbm = new DBAccess();
    private static boolean flag = true;
    private int sleepTime =1000;

    private String errorFile = MOParam.getInstance().errorFile;
    private String logFile = MOParam.getInstance().logFile;

    private String area=null;
    private String table=null;
    private String sql=null;
    private String areaName=null;

    private int rowNum = 100;
    private Vector xhList = new Vector(100);

    private MTInfoList mtList= MTInfoList.getInstance();

    public static void stopThread(){
        flag=false;
    }
    public MT_Get_YD(String sArea,String sAreaName) {
        area=sArea.toUpperCase();
        mtList.regedit(area);
        areaName=sAreaName;
        if(area.equals("JSYD")){
            table="waitinfo";
            sql="select * from waitinfo where (area='JSYD' or area is null) and rownum<="+rowNum;
        }
        else{
            if(area.equals("AHYD")){
                table="waitinfo_ah";
                sql="select * from waitinfo_ah where (area='AHYD' or area is null) and rownum<="+rowNum;
            }
            else{
                if(area.equals("ZJYD")){
                    table="waitinfo";
                    sql="select * from waitinfo where area='"+area+"' and rownum<="+rowNum;

                }else{
                    table="ui_waitinfo";
                    sql="select * from ui_waitinfo where area='"+area+"' and rownum<="+rowNum;
                }
            }
        }
    }
    public void run(){
        Log.printEvent("------"+areaName+"通信MT_Get_YD启动－－－－－－",logFile);
        try {
            while(flag){
                try {
                    con = dbm.getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(sql);
                    while(rs.next()){
                        MTInfo mtInfo = new MTInfo();
                        try {
                            mtInfo.setMTInfoMsg(rs);
                            if(mtInfo.getXXNR().length()>70){
                                MTInfo mtInfo2 = mtInfo.getCopy();
                                int size=getSize(mtInfo.getXXNR());
                                String msg1=mtInfo.getXXNR().substring(0,size);
                                String msg2=mtInfo.getXXNR().substring(size);
                                mtInfo.setXXNR(msg1);
                                mtInfo2.setXXNR(msg2);
                                mtList.add(mtInfo,area);
                                mtList.add(mtInfo2,area);
                            }else
                                mtList.add(mtInfo,area);
                        }
                        catch (Exception ex) {
                            Log.printError(ex,mtInfo.getJTLY(),mtInfo.getXXNR(),errorFile);
                        }
                        xhList.addElement(mtInfo.getXH());
                    }
                    rs.close();
                    int length=xhList.size();
                    for(int i=0;i<length;i++){
                            stmt.addBatch("delete from "+table+" where xh='"+(String)xhList.remove(0)+"'");
                    }
                    stmt.executeBatch();
                }
                catch (Exception ex) {
                    Log.printError(ex,areaName+"MTGET　发生错误",errorFile);
                }
                finally {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex) {
                    }
                    dbm.freeConnection(con);
                }
                while(mtList.getSize(area)>100){
                    Thread.currentThread().sleep(5);
                }
                Thread.currentThread().sleep(sleepTime);
            }
        }
        catch (InterruptedException ex) {
            Log.printError(ex,"sleep error",errorFile);
        }
        dbm.release();
        Log.printEvent("------"+areaName+"通信MT_Get_YD关闭－－－－－－",logFile);
    }
    private int getSize(String msg){
        int length = msg.length();
        if(length>138){
            return 69;
        }else
            return (int)(length/2);
    }
}