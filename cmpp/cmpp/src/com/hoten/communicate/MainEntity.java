package com.hoten.communicate;
import com.hoten.util.*;
import com.hoten.communicate.mo.*;
import com.hoten.communicate.mt.*;
import com.hoten.communicate.quit.*;
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

public class MainEntity {
    private String separator=System.getProperty("file.separator");
    private String shellName="hn";
    private String area="HNYD";
    private String areaName="湖南";
    private String pid="A";
    private String path="."+separator+"sys.properties";
    public MainEntity() {
        initMsg(path);
    }
    public static void main(String[] args){
        new MainEntity().startSystem();
    }

    private void initMsg(String path){
       FileInputStream is=null;
       try{
           is= new FileInputStream(path);
       }catch(FileNotFoundException e){
           System.out.println("初始化路径："+path+"没有发现");
           return;
       }
       Properties props = new Properties();
       try {
           props.load(is);
       }
       catch (Exception e) {
           System.out.println("初始化路径："+path+"没有发现");
           return ;
       }
       shellName=props.getProperty("shell");
       area=props.getProperty("area");
       areaName = props.getProperty("areaName");
       path = props.getProperty("initPath",path);
       try {
           areaName = new String(areaName.getBytes("iso-8859-1"),"gb2312");
       }
       catch (Exception ex) {
       }
       pid = props.getProperty("pid","0");
    }
    public void startSystem(){
        Log.printEvent("------通信系统启动－－－－",MOParam.getInstance().logFile);
        System.out.println(areaName+"通信系统启动.....");
        Thread control  = new Thread(new Syste_Quit(shellName),"control");
        control.start();
        int size = MOParam.getInstance().sendNum;
        //发送
        for(int i=0;i<size;i++){
            Thread mtSend = new Thread(new MT_Send(shellName,area,areaName),"Mt_Send"+i);
            mtSend.start();
        }
        Thread mtGet  = new Thread(new MT_Get_YD(area,areaName),"Mt_Get");
        mtGet.start();
        String[] areaList = {area};
        Thread mtGet_JF  = new Thread(new MT_Get_JF(areaList),"mtGet_JF");
        mtGet_JF.start();

        Thread moSave  = new Thread(new MO_Save(),"Mo_Save");
        moSave.start();
        size = MOParam.getInstance().getNum;
        //接收
        for(int i=0;i<size;i++){
            Thread moGet  = new Thread(new MO_Get_YD(shellName,null,area,pid,areaName),"Mo_Get"+i);
            moGet.start();
        }
    }
}