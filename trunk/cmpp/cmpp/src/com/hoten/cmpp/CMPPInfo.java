package com.hoten.cmpp;
import java.util.*;
import com.hoten.cmpp.message.*;
import com.hoten.cmpp.type.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPInfo {
    private String YD_Name;//��Ӫ������
    private String cmppFlag;//cmpp����



    private int mo_Num;//ֻ����cmpp����
    private int mt_Num;//ֻ����cmpp��
    private int momt_Num;//���շ���cmpp��

    private Vector moList;//ֻ����cmpp����
    private Vector mtList;//ֻ����cmpp����
    private Vector momtList;//���շ���cmpp����

    private String host;
    private int port;
    private String auth;
    private String icp_ID;
    private String user;
    private int version;
    private String timeStamp;

    private int timeout = 1000;
    private Vector error=new Vector();

    private Vector deliverMsgList=null;//������Ϣ����
    public CMPPInfo(String name,String cmppFlag,int mo_Num,int mt_Num,int momt_Num,String host,String auth,String icp_ID,String timeStamp,int port,int version,String user) {
        YD_Name=name;
        this.cmppFlag=cmppFlag;
        this.mo_Num=mo_Num;
        this.mt_Num=mt_Num;
        this.momt_Num=momt_Num;
        if(mo_Num!=0) moList=new Vector();
        if(mt_Num!=0) mtList=new Vector();
        if(momt_Num!=0) momtList=new Vector();
        this.host=host;
        this.port=port;
        this.auth=auth;
        this.version=version;
        this.timeStamp=timeStamp;
        this.icp_ID=icp_ID;
        this.user=user;
        deliverMsgList=new Vector();

    }
    public String getYDName(){
        return YD_Name;
    }

    public String getFlag(String flag){
        if(flag.equals("DELIVER")){
            if(mo_Num!=0)
                return "MO";
            if(momt_Num!=0){
                return "MOMT";
            }
            return null;
        }
        if(flag.equals("SUBMIT")){
            if(mt_Num!=0)
                return "MT";
            if(momt_Num!=0){
                return "MOMT";
            }
            return null;
        }
        return null;
    }
    public  synchronized CMPP_Service getCMPP(String flag,long timeOut)throws Exception{
        long startTime = new Date().getTime();
        CMPP_Service cmpp;
        while ((cmpp = getCMPP(flag)) == null) {
            try {
                wait(timeout);                                         //��notifyAll()���ѻ�ʱ�䵽�Զ�����
            }
            catch (InterruptedException e) {}
            if ((new Date().getTime() - startTime) >= timeout) {
                  return null;
              }
        }
        return cmpp;
    }
    public  synchronized CMPP_Service getCMPP(String flag)throws Exception{
        Vector list = getList(flag);
        if(list.size()!=0){
            return (CMPP_Service)list.remove(0);
        }else{
            return null;
        }
    }
    public synchronized void freeCMPP(String flag,CMPP_Service cmpp){
        Vector list = getList(flag);
        if(cmpp!=null){
            list.addElement(cmpp);
        }
        notifyAll();
    }
    private Vector getList(String flag){
        if(flag.equals("MO")){
            return moList;
        }
        if(flag.equals("MT")){
            return mtList;
        }
        if(flag.equals("MOMT")){
            return momtList;
        }
        return null;
    }
    public void quit(){
        if(mo_Num!=0){
            while(moList.size()!=0){
                ((CMPP_Service)moList.remove(0)).quit();
            }
        }
        if(mt_Num!=0){
            while(mtList.size()!=0){
                ((CMPP_Service)mtList.remove(0)).quit();
            }
        }
        if(momt_Num!=0){
            while(momtList.size()!=0){
                ((CMPP_Service)momtList.remove(0)).quit();
            }
        }
    }
    private boolean init(Vector list,int num,String flag)throws Exception{
        if(num!=0){
            for(int i=0;i<num;i++){
                Thread.currentThread().sleep(1000);
                CMPP_Service cmpp=null;
                if(cmppFlag.equals("DR")){
                    cmpp = new CMPP_DR();
                    if(cmpp.initCMPP(YD_Name,host,icp_ID,user,auth,timeStamp,version,port,flag,deliverMsgList)!=0){
                        return false;
                    }
                }else
                if(cmppFlag.equals("YX")){
                    cmpp = new CMPP_YX();
                    if(cmpp.initCMPP(YD_Name,host,icp_ID,user,auth,timeStamp,version,port,flag,deliverMsgList)!=0){
                        return false;
                    }
                }else
                if(cmppFlag.equals("HW")){
                    cmpp = new CMPP_HW();
                    if(cmpp.initCMPP(YD_Name,host,icp_ID,user,auth,timeStamp,version,port,flag,deliverMsgList)!=0){
                        return false;
                    }
                }else
                if(cmppFlag.equals("SX")){
                    cmpp = new CMPP_SX();
                    if(cmpp.initCMPP(YD_Name,host,icp_ID,user,auth,timeStamp,version,port,flag,deliverMsgList)!=0){
                        return false;
                    }
                }else{
                    cmpp = new CMPP();
                    if(cmpp.initCMPP(YD_Name,host,icp_ID,user,auth,timeStamp,version,port,flag,deliverMsgList)!=0){
                        return false;
                    }
                }
                list.addElement(cmpp);
            }
        }
        return true;
    }
    public boolean CMPPInit(String flags)throws Exception{
        boolean flag=true;
        if(flags.equals("MO")){
            mo_Num--;
            flag=init(moList,1,"MO");
            if(!flag) return flag;
            mo_Num++;
        }
        if(flags.equals("MT")){
            mt_Num--;
            flag=init(mtList,1,"MT");
            if(!flag) return flag;
            mt_Num++;
        }
        if(flags.equals("MOMT")){
            momt_Num--;
            flag=init(momtList,1,"MOMT");
            if(!flag) return flag;
            momt_Num++;
        }
        return true;
    }
    public boolean CMPPInit()throws Exception{
        boolean flag=true;
        if(mo_Num!=0){
            flag=init(moList,mo_Num,"MO");
            if(!flag) return flag;
        }
        if(mt_Num!=0){
            flag=init(mtList,mt_Num,"MT");
            if(!flag) return flag;
        }
        if(momt_Num!=0){
            flag=init(momtList,momt_Num,"MOMT");
            if(!flag) return flag;
        }
        return true;
    }

    public Vector getMsgList(){
        return deliverMsgList;
    }
    public Vector getErrorList(){
        return error;
    }
}
