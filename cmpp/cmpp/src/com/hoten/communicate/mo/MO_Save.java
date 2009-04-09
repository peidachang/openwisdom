package com.hoten.communicate.mo;
import com.hoten.communicate.info.*;
import com.hoten.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MO_Save implements Runnable{
    private static boolean flag = true;
    private int sleepTime = 1000;
    private MOInfoList moList = MOInfoList.getInstance();
    private String errorFile = MOParam.getInstance().errorFile;
    private String logFile = MOParam.getInstance().logFile;
    private DBAccess dba = new DBAccess();
    public MO_Save() {
    }
    public static void stopThread(){
        flag=false;
    }
    public void run(){
        Log.printEvent("------MO_Save∆Ù∂Ø£≠£≠£≠£≠£≠£≠",logFile);
        try {
            while(flag){
                MOInfo moInfo = moList.remove();
                if(moInfo==null) {
                    Thread.currentThread().sleep(sleepTime*10);
                    continue;
                }
                try {
                    DBCommon.SaveMO(moInfo,dba);
                }
                catch (Exception ex) {
                    Log.printError(ex,moInfo.getMobile(),moInfo.getMsg(),errorFile);
                }
                Thread.currentThread().sleep(sleepTime);
            }
        }
        catch (InterruptedException ex) {
        }
        dba.release();
        Log.printEvent("------MO_Saveπÿ±’£≠£≠£≠£≠£≠£≠",logFile);
    }
}