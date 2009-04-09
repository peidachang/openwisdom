package com.hoten.communicate.quit;
import com.hoten.db.*;
import java.sql.*;
import com.hoten.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Syste_Quit implements Runnable{
    private static boolean stopFlag = true;
    private MOParam mo_param = MOParam.getInstance();
    private DBAccess _dbm = new DBAccess();
    private String _logFile = null;
    private String _dbPool = null;
    private int _dbTime = 0;
    private static final int sleepTime = 1000*30;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String shellName=null;

    private String flag = null;

    public static void stopThread(){
        stopFlag=false;
    }
    public Syste_Quit(String shellName) {
        _logFile=mo_param.logFile;
        _dbPool = mo_param.dbPool;
        _dbTime = mo_param.dbTime;
        this.shellName=shellName.toUpperCase();
    }
    public void run(){
        Log.printEvent("------通信控制系统启动－－－－－－",_logFile);
        try {
            while(stopFlag){
                try {
                    flag=null;
                    getFlag();
                    if(flag==null) flag="";
                }
                catch (Exception ex) {
                    if(flag==null) flag="";
                    Log.printError(ex,"",_logFile);
                }
                if(flag.equals("Q")){
                    try {
                        setFlag();
                    }
                    catch (Exception ex) {
                        Log.printError(ex,"",_logFile);
                    }
                    com.hoten.communicate.mo.MO_Get_YD.stopThread();
                    Thread.currentThread().sleep(1000*10);
                    while(com.hoten.communicate.info.MOInfoList.getInstance().getSize()!=0){
                        Thread.currentThread().sleep(100);
                    }
                    com.hoten.communicate.mo.MO_Save.stopThread();
                    com.hoten.communicate.mt.MT_Get_YD.stopThread();
                    com.hoten.communicate.mt.MT_Get_JF.stopThread();
                    while(com.hoten.communicate.info.MOInfoList.getInstance().getSize()!=0){
                        Thread.currentThread().sleep(100);
                    }
                    com.hoten.communicate.mt.MT_Send.stopThread();
                    this.stopThread();
                }
                Thread.currentThread().sleep(sleepTime);
            }
        }
        catch (InterruptedException ex) {
        }
        _dbm.release();
        Log.printEvent("------通信控制系统关闭－－－－－－",_logFile);
    }
    private void setFlag() throws Exception{
        String sql = "update ws_QuitControl set controlFlag='E' where serviceName='"+shellName+"MOMT'";
        _dbm.update(sql);
    }
    private void getFlag() throws Exception{
        String sql = "select controlFlag from ws_QuitControl where serviceName = '"+shellName+"MOMT'";
        flag= _dbm.select(sql);
    }
}