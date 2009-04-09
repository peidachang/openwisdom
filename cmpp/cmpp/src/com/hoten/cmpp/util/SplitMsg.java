package com.hoten.cmpp.util;
import java.util.Vector;
/**
 * <p>Title: 短信超长分割</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SplitMsg {
    private final static  int step=58;

    public static Vector split(String msg,boolean splitFlag){
        if(msg==null) return null;
        Vector v = new Vector();
        if(splitFlag||msg.length()<=70){
            v.addElement(msg);
            return v;
        }else{
            int length=msg.length();
            int splitNum = (int)(length/step)+1;
            boolean flag=true;
            if(splitNum>10){
                splitNum=10;
                flag=false;
            }
            int len = 0;
            for(int i=0;i<splitNum;i++){
                if(i==0){
                    String spMsg=msg.substring(0,step)+getFirstEnd(splitNum);
                    v.addElement(spMsg);
                    spMsg=null;
                    len=step;
                    continue;
                }
                if(i==splitNum-1){
                    String spMsg=null;
                    if(flag==false)
                        spMsg=getSecondFirst(i,splitNum)+msg.substring(len,len+step);
                    v.addElement(spMsg);
                    spMsg=null;
                    continue;
                }
                String spMsg=null;
                spMsg=getSecondFirst(i,splitNum)+msg.substring(len,len+step)+getSecondEnd(i,splitNum);
                v.addElement(spMsg);
                spMsg=null;
            }
            return v;
        }
    }
    private static String getFirstEnd(int spNum){
        return "(1/"+spNum+")";
    }
    private static String getSecondFirst(int n,int spNum){
        return"(接"+n+"/"+spNum+")";
    }
    private static String getSecondEnd(int n,int spNum){
        return"("+(n+1)+"/"+spNum+")";
    }
}