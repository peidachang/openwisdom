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

public class MT_Get_JF implements Runnable{
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private DBAccess dbm = new DBAccess();
    private static boolean flag = true;
    private int sleepTime =1000;

    private String errorFile = MOParam.getInstance().errorFile;
    private String logFile = MOParam.getInstance().logFile;

    private int rowNum = 100;
    private Vector xhList = new Vector(100);
    private String areaList = null;

    private MTInfoList mtList= MTInfoList.getInstance();

    public static void stopThread(){
        flag=false;
    }
    public MT_Get_JF(String[] sAreaList) {
        if(sAreaList!=null){
            areaList="('";
            for(int i=0;i<sAreaList.length;i++){
                mtList.regedit(sAreaList[i]);
                if(i==sAreaList.length-1){
                    areaList+=sAreaList[i]+"')";
                }
                else
                    areaList+=sAreaList[i]+"','";
            }
        }
        if(areaList==null){
            System.out.println("Format ERROR! JF_Area must format \"('JSYD','AHYD','SDYD')\"! ");
            System.exit(1);
        }
    }
    public void run(){
        Log.printEvent("------MT_Get_JF启动－－－－－－",logFile);
        String sql = "select * from waitinfo_jf where area in "+areaList+" and rownum<="+rowNum;
        try {
            String area= null;
            while(flag){
                try {
                    con = dbm.getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(sql);
                    while(rs.next()){
                        MTInfo mtInfo = new MTInfo();
                        try {
                            mtInfo.setMTInfoMsg(rs);
                            area=mtInfo.getArea();
                            mtInfo.setXXNR("JF");
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
                            stmt.addBatch("delete from waitinfo_jf where xh='"+(String)xhList.remove(0)+"'");
                    }
                    stmt.executeBatch();
                }
                catch (Exception ex) {
                    Log.printError(ex,"MTGETJF　发生错误",errorFile);
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
                area=null;
                Thread.currentThread().sleep(sleepTime);
            }
        }
        catch (InterruptedException ex) {
            Log.printError(ex,"sleep error",errorFile);
        }
        dbm.release();
        Log.printEvent("------MT_Get_JF关闭－－－－－－",logFile);
    }
}