package com.hoten.communicate.info;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MOInfo {
    private String  msg;            //消息内容
    private String  mobile;         //手机号码
    private String  area;           //所在地区
    private String  time;           //发送的时间
    private String  bz;             //特服号
    private String  table;          //所属表(与地区相对应)

    private static int i=0;

    public MOInfo() {
        setTime();
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public String getMsg(){
        return msg;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    public String getMobile(){
        return mobile;
    }
    public void setArea(String area){
        this.area=area;
    }
    public String getArea(){
        return area;
    }
    private void setTime(){
        time = com.hoten.util.CTime.getTime(com.hoten.util.CTime.YYMMDDhhmmss)+getXXX();
    }
    public void setTime(String pid){
        this.time = pid+time.substring(1);
    }
    public String getTime(){
        return time;
    }
    public void setBZ(String bz){
        this.bz=bz;
    }
    public String getBZ(){
        return bz;
    }
    public void setTable(String table){
        this.table=table;
    }
    public String getTable(){
        return table;
    }
    private String getXXX(){
        synchronized(this.getClass()){
            i++;
            if(i>999) i=1;
        }
        return getFormatTime(i,3);
    }
    private String getFormatTime(int time,int format){
        StringBuffer numm=new StringBuffer();
        int length=String.valueOf(time).length();

        if(format<length) return null;

        for(int i=0 ;i<format-length ;i++){
            numm.append("0");
        }
        numm.append(time);
        return numm.toString().trim();
    }
}