package com.hoten.cmpp;
/**
 * <p>Title: SaveMOList</p>
 * <p>Description: </p>
 * 存于ws_mo、ws_mo_ui的消息队列
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Hoten</p>
 * @version 1.0
 */
import java.util.*;
import com.hoten.cmpp.util.*;
import com.hoten.cmpp.message.*;
import com.hoten.cmpp.type.*;
import java.io.*;


public class CMPP_Pool {
    CMPP_InitMessage initMsg = CMPP_InitMessage.getInstance();
    /** SaveMO消息模块实例 */
    static private CMPP_Pool _instance;
    /** SaveMO消息队列 */
    private Hashtable _hList;

    private Vector pathList=new Vector();

    /** 线程同步控制确保模块仅有一个实例 */
    static synchronized public CMPP_Pool getInstance() {
        if (_instance == null) {
            _instance = new CMPP_Pool();
        }
        return _instance;
    }
    /** 构造器，默认消息队列长度为50 */
    private CMPP_Pool() {
        _hList=new Hashtable();
    }
    /** 向消息队列添加消息 */
    public synchronized boolean add(String name,String path){
        boolean flag=true;
        if(!pathList.contains(path)){
            CMPPInfo info=initPool(name,path);
            if(info==null){
                flag=false;
            }else{
                _hList.put(info.getYDName(),info);
                pathList.addElement(path);
            }
        }
        return flag;
    }
    /** 返回并删除消息队列起始处消息，若消息队列为空，返回空 */
    public CMPPInfo get(String name){
        if(!_hList.containsKey(name))return null;
        return (CMPPInfo)_hList.get(name);
    }
    /** 返回消息队列长度 */
    public int getSize(){
        return _hList.size();
    }
    private CMPPInfo initPool(String name,String path){
        CMPPInfo info=null;
        FileInputStream is=null;
        try{
            is= new FileInputStream(path);
        }catch(FileNotFoundException e){
            System.out.println("初始化路径："+path+"没有发现");
            Log.printEvent("初始化路径："+path+"没有发现",CMPP_InitMessage.getInstance().logFile);
            return null;
        }
        Properties props = new Properties();
        try {
            props.load(is);
        }
        catch (Exception e) {
            System.out.println("初始化路径："+path+"没有发现");
            Log.printEvent("初始化路径："+path+"没有发现",CMPP_InitMessage.getInstance().logFile);
            return null;
        }
        String YD_Name=props.getProperty("YD_NAME");
        if(YD_Name==null||!YD_Name.toUpperCase().equals(name)){
            Log.printEvent("初始化路径："+path+"中YD_NAME没有设置好",CMPP_InitMessage.getInstance().logFile);
            return null;
        }
        String ISMG_Flag=props.getProperty(YD_Name+ ".ISMG_FLAG","CMPP");
        String icp_ID=props.getProperty(YD_Name+ ".ICP_ID");
        String host = props.getProperty(YD_Name + ".HOST");
        String user = props.getProperty(YD_Name + ".USER");
        if(user==null) user=icp_ID;
        String auth = props.getProperty(YD_Name + ".AUTH");
        String version = props.getProperty(YD_Name + ".VERSION", "18");
        String timeStamp = props.getProperty(YD_Name + ".TIMESTAMP","0000000000");
        String port = props.getProperty(YD_Name + ".PORT","7890");
        if(icp_ID==null||host==null||auth==null){
            Log.printEvent("初始化路径："+path+"中ICP_ID或HOST或AUTH没有设置",CMPP_InitMessage.getInstance().logFile);
            return null;
        }
        String mo_Num=props.getProperty(YD_Name + ".MO_NUM", "0");
        String mt_Num=props.getProperty(YD_Name + ".MT_NUM", "0");
        String momt_Num=props.getProperty(YD_Name + ".MOMT_NUM","0");
        info = new CMPPInfo(YD_Name.toUpperCase(),ISMG_Flag,Integer.parseInt(mo_Num),Integer.parseInt(mt_Num),
                            Integer.parseInt(momt_Num),host,auth,icp_ID,timeStamp,Integer.parseInt(port),
                            Integer.parseInt(version),user);
        try {
            if(info.CMPPInit()){
                return info;
            }else
                return null;
        }
        catch (Exception ex) {
            return null;
        }
    }

}