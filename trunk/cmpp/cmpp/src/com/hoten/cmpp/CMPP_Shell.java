package com.hoten.cmpp;
import com.hoten.cmpp.message.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPP_Shell {
    CMPP_Pool pool = CMPP_Pool.getInstance();
    private String name;//运营商名称
    private String flag;
    private int timeout = 100;
    private Vector error = null;
    public CMPP_Shell(String YD_Name){
        if(YD_Name!=null)
            name=YD_Name.toUpperCase();
    }
    public boolean initCMPP(String initPath){
        return pool.add(name,initPath);
    }

    public void quit(){
        CMPPInfo info=pool.get(name);
        info.quit();
    }

    public int DeliverListNum(){
        CMPPInfo info=pool.get(name);
        return info.getMsgList().size();
    }
    public int submit(SubmitMsg msg){
        int stat=0;
        CMPPInfo info=pool.get(name);
        if(info==null) return -1;
        String flag = info.getFlag("SUBMIT");
        if(flag==null) return -1;
        if(error==null)
            error = info.getErrorList();
        CMPP_Service cmpp=null;
        try {
            while(true){
                cmpp = info.getCMPP(flag,timeout);
                if(cmpp==null){
                    int i=-1;
                    if((i=error.indexOf(flag))!=-1){
                        try {
                            if(info.CMPPInit(flag)){
                                error.remove(i);
                            }
                        }
                        catch (Exception ex) {
                            break;
                        }
                    }
                    continue;
                }
                try {
                    stat= cmpp.submit(msg);
                }
                catch (Exception ex) {
                    cmpp=null;
                    error.addElement(flag);
                }
                break;
            }
        }
        catch (Exception ex) {
            stat=-1;
        }finally{
            info.freeCMPP(flag,cmpp);
            flag=null;
        }
        return stat;
    }
    public DeliverMsg deliver(){
        CMPPInfo info=pool.get(name);
        if(info==null) return null;
        if(info.getMsgList().size()!=0){
             return (DeliverMsg)info.getMsgList().remove(0);
        }
        String flag = info.getFlag("DELIVER");
        if(flag==null) return null;
        if(error==null)
            error = info.getErrorList();
        CMPP_Service cmpp=null;
        try {
            int p=0;
            while(true){
                cmpp = info.getCMPP(flag,timeout);
                if(cmpp==null){
                    int i=-1;
                    if((i=error.indexOf(flag))!=-1){
                        try {
                            if(info.CMPPInit(flag)){
                                error.remove(i);
                            }
                        }
                        catch (Exception ex) {
                            break;
                        }
                    }
                    continue;
                }
                p++;
                try {
                    cmpp.deliver();
                }
                catch (Exception ex) {
                    cmpp=null;
                    error.addElement(flag);
                }
                break;
            }
        }
        catch (Exception ex) {
        }finally{
            info.freeCMPP(flag,cmpp);
            flag=null;
        }
        if(info.getMsgList().size()!=0){
            return (DeliverMsg)info.getMsgList().remove(0);
        }else
            return null;
    }
}