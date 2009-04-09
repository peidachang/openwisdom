package com.hoten.cmpp;
/**
 * <p>Title: SaveMOList</p>
 * <p>Description: </p>
 * ����ws_mo��ws_mo_ui����Ϣ����
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
    /** SaveMO��Ϣģ��ʵ�� */
    static private CMPP_Pool _instance;
    /** SaveMO��Ϣ���� */
    private Hashtable _hList;

    private Vector pathList=new Vector();

    /** �߳�ͬ������ȷ��ģ�����һ��ʵ�� */
    static synchronized public CMPP_Pool getInstance() {
        if (_instance == null) {
            _instance = new CMPP_Pool();
        }
        return _instance;
    }
    /** ��������Ĭ����Ϣ���г���Ϊ50 */
    private CMPP_Pool() {
        _hList=new Hashtable();
    }
    /** ����Ϣ���������Ϣ */
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
    /** ���ز�ɾ����Ϣ������ʼ����Ϣ������Ϣ����Ϊ�գ����ؿ� */
    public CMPPInfo get(String name){
        if(!_hList.containsKey(name))return null;
        return (CMPPInfo)_hList.get(name);
    }
    /** ������Ϣ���г��� */
    public int getSize(){
        return _hList.size();
    }
    private CMPPInfo initPool(String name,String path){
        CMPPInfo info=null;
        FileInputStream is=null;
        try{
            is= new FileInputStream(path);
        }catch(FileNotFoundException e){
            System.out.println("��ʼ��·����"+path+"û�з���");
            Log.printEvent("��ʼ��·����"+path+"û�з���",CMPP_InitMessage.getInstance().logFile);
            return null;
        }
        Properties props = new Properties();
        try {
            props.load(is);
        }
        catch (Exception e) {
            System.out.println("��ʼ��·����"+path+"û�з���");
            Log.printEvent("��ʼ��·����"+path+"û�з���",CMPP_InitMessage.getInstance().logFile);
            return null;
        }
        String YD_Name=props.getProperty("YD_NAME");
        if(YD_Name==null||!YD_Name.toUpperCase().equals(name)){
            Log.printEvent("��ʼ��·����"+path+"��YD_NAMEû�����ú�",CMPP_InitMessage.getInstance().logFile);
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
            Log.printEvent("��ʼ��·����"+path+"��ICP_ID��HOST��AUTHû������",CMPP_InitMessage.getInstance().logFile);
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