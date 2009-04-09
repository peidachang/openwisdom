package com.hoten.communicate.info;
import java.sql.*;
/**
 * <p>Title:MTInfo </p>
 * <p>Description:MT消息类主要封装了一个将要发送的消息，里面的数据结构
 * 和表WAITINFO,UI_WAITINFO表相同。此类有两种方法，getter和setter
 * 主要是对里面的属性进行操作 </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company:Hoten</p>
 * @version 1.0
 */


public class MTInfo {
    private String _sXH;       //消息序号
    private String _sXXLY;     //信息来源
    private String _sJTLY;     //具体来源
    private String _sXXLX;     //信息类型
    private String _sQQFSSJ;   //请求发送时间
    private String _sFCSJ;     //发出时间
    private String _sSJHM;     //手机号码
    private String _sXXNR;     //信息内容
    private String _sNRXH;     //内容序号
    private String _sFee;      //信息费用
    private String _sBZ1;      //备注1
    private String _sBZ2;      //备注2
    private String _sBZ3;      //备注3
    private String _sFlagFee;  //费用标志
    private String _sYWDM;     //业务代码
    private String _sArea;     //信息来源地区

    private static int i=0;


    public MTInfo(){
    }
    /** 设置_sXH */
    public void setXH(String sXH){
        this._sXH = sXH;
    }
    /** 返回_sXH */
    public String getXH(){
        return this._sXH;
    }
    /** 设置_sXXLY */
    public void setXXLY(String sXXLY){
        this._sXXLY = sXXLY;
    }
    /** 返回_sXXLY */
    public String getXXLY(){
        return this._sXXLY;
    }
    /** 设置_sJTLY */
    public void setJTLY(String sJTLY){
        this._sJTLY = sJTLY;
    }
    /** 返回_sJTLY */
    public String getJTLY(){
        return this._sJTLY;
    }
    /** 设置_sXXLX */
    public void setXXLX(String sXXLX){
        this._sXXLX = sXXLX;
    }
    /** 返回_sXXLX */
    public String getXXLX(){
        return this._sXXLX;
    }
    /** 设置_sQQFSSJ */
    public void setQQFSSJ(String sQQFSSJ){
        this._sQQFSSJ = sQQFSSJ;
    }
    /** 返回_sQQFSSJ */
    public String getQQFSSJ(){
        return this._sQQFSSJ;
    }
    /** 设置_sFCSJ */
    public void setFCSJ(String sFCSJ){
        this._sFCSJ = sFCSJ;
    }
    /** 返回_sFCSJ */
    public String getFCSJ(){
        return this._sFCSJ;
    }
    /** 设置_sSJHM */
    public void setSJHM(String sSJHM){
        this._sSJHM = sSJHM;
    }
    /** 返回_sSJHM */
    public String getSJHM(){
        return this._sSJHM;
    }
    /** 设置_sXXNR */
    public void setXXNR(String sXXNR){
        this._sXXNR= sXXNR;
    }
    /** 返回_sXXNR */
    public String getXXNR(){
        return this._sXXNR;
    }
    /** 设置_sNRXH */
    public void setNRXH(String sNRXH){
        this._sNRXH = sNRXH;
    }
    /** 返回_sNRXH */
    public String getNRXH(){
        return this._sNRXH;
    }
    /** 设置_sFee */
    public void setFee(String sFee){
        this._sFee = sFee;
    }
    /** 返回_sFee */
    public String getFee(){
        return this._sFee;
    }
    /** 设置_sBZ1 */
    public void setBZ1(String sBZ1){
        this._sBZ1 = sBZ1;
    }
    /** 返回_sBZ1 */
    public String getBZ1(){
        return this._sBZ1;
    }
    /** 设置_sBZ2 */
    public void setBZ2(String sBZ2){
        this._sBZ2 = sBZ2;
    }
    /** 返回_sBZ2 */
    public String getBZ2(){
        return this._sBZ2;
    }
    /** 设置_sBZ3 */
    public void setBZ3(String sBZ3){
        this._sBZ3 = sBZ3;
    }
    /** 返回_sBZ3 */
    public String getBZ3(){
        return this._sBZ3;
    }
    /** 设置_sFlagFee */
    public void setFlagFee(String sFlagFee){
        this._sFlagFee = sFlagFee;
    }
    /** 返回_sFlagFee */
    public String getFlagFee(){
        return this._sFlagFee;
    }
    /** 设置_sYWDM */
    public void setYWDM(String sYWDM){
        this._sYWDM = sYWDM;
    }
    /** 返回_sYWDM */
    public String getYWDM(){
        return this._sYWDM;
    }
    /** 设置_sArea */
    public void setArea(String area){
        this._sArea = area;
    }
    /** 返回_sArea */
    public String getArea(){
        return this._sArea;
    }
    public MTInfo getCopy(){
        MTInfo mtInfo = new MTInfo();
        mtInfo.setArea(this._sArea);
        mtInfo.setBZ1(this._sBZ1);
        mtInfo.setBZ2(this._sBZ2);
        mtInfo.setBZ3(this._sBZ3);
        mtInfo.setFCSJ(this._sFCSJ);
        mtInfo.setFee(this._sFee);
        mtInfo.setFlagFee(this._sFlagFee);
        mtInfo.setJTLY(this._sJTLY);
        mtInfo.setNRXH(this._sNRXH);
        mtInfo.setQQFSSJ(this._sQQFSSJ);
        mtInfo.setSJHM(this._sJTLY);
        mtInfo.setXH('A'+this._sXH.substring(1));
        mtInfo.setXXLX(this._sXXLX);
        mtInfo.setXXLY(this._sXXLY);
        mtInfo.setYWDM(this._sYWDM);
        return mtInfo;
    }

    public void setMTInfoMsg(ResultSet rs)throws Exception{
        _sXH=rs.getString("xh").trim();
        _sXXLY=rs.getString("xxly");
        _sJTLY=rs.getString("JTLY");
        _sFCSJ=rs.getString("FCSJ");
        _sQQFSSJ=rs.getString("QQFSSJ");
        _sSJHM=rs.getString("SJHM");
        _sNRXH=rs.getString("NRXH");
        _sXXLX=rs.getString("XXLX");//信息类型
        _sFee=rs.getString("Fee");
        _sXXNR=rs.getString("XXNR");
        if(_sXXNR!=null){
            _sXXNR=com.hoten.util.Chinese.fromDatabase(_sXXNR.trim());
            _sXXNR=_sXXNR.replace('，',',');
        }
        _sYWDM=rs.getString("YWDM");
        _sFlagFee=rs.getString("FlagFee");
        _sBZ1=rs.getString("BZ1");
        _sArea=rs.getString("area");
        _sBZ3=rs.getString("BZ3");
        _sBZ2=rs.getString("BZ2");
        if(_sXXLY!=null) _sXXLY=_sXXLY.trim();
        if(_sJTLY!=null) _sJTLY=_sJTLY.trim();
        if(_sFCSJ!=null) _sFCSJ=_sFCSJ.trim();
        if(_sQQFSSJ!=null) _sQQFSSJ=_sQQFSSJ.trim();
        if(_sNRXH!=null) _sNRXH=_sNRXH.trim();
        if(_sXXLX!=null) _sXXLX=_sXXLX.trim();
        if(_sFee!=null) _sFee=_sFee.trim();
        if(_sYWDM!=null) _sYWDM=_sYWDM.trim();
        if(_sFlagFee!=null) _sFlagFee=_sFlagFee.trim();
        if(_sBZ1!=null) _sBZ1=_sBZ1.trim();
        if(_sArea!=null) _sArea=_sArea.trim();
        if(_sBZ3!=null) _sBZ3=_sBZ3.trim();
        if(_sBZ2!=null) _sBZ2=_sBZ2.trim();
    }
}