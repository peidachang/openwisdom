package com.hoten.communicate.info;
import java.sql.*;
/**
 * <p>Title:MTInfo </p>
 * <p>Description:MT��Ϣ����Ҫ��װ��һ����Ҫ���͵���Ϣ����������ݽṹ
 * �ͱ�WAITINFO,UI_WAITINFO����ͬ�����������ַ�����getter��setter
 * ��Ҫ�Ƕ���������Խ��в��� </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company:Hoten</p>
 * @version 1.0
 */


public class MTInfo {
    private String _sXH;       //��Ϣ���
    private String _sXXLY;     //��Ϣ��Դ
    private String _sJTLY;     //������Դ
    private String _sXXLX;     //��Ϣ����
    private String _sQQFSSJ;   //������ʱ��
    private String _sFCSJ;     //����ʱ��
    private String _sSJHM;     //�ֻ�����
    private String _sXXNR;     //��Ϣ����
    private String _sNRXH;     //�������
    private String _sFee;      //��Ϣ����
    private String _sBZ1;      //��ע1
    private String _sBZ2;      //��ע2
    private String _sBZ3;      //��ע3
    private String _sFlagFee;  //���ñ�־
    private String _sYWDM;     //ҵ�����
    private String _sArea;     //��Ϣ��Դ����

    private static int i=0;


    public MTInfo(){
    }
    /** ����_sXH */
    public void setXH(String sXH){
        this._sXH = sXH;
    }
    /** ����_sXH */
    public String getXH(){
        return this._sXH;
    }
    /** ����_sXXLY */
    public void setXXLY(String sXXLY){
        this._sXXLY = sXXLY;
    }
    /** ����_sXXLY */
    public String getXXLY(){
        return this._sXXLY;
    }
    /** ����_sJTLY */
    public void setJTLY(String sJTLY){
        this._sJTLY = sJTLY;
    }
    /** ����_sJTLY */
    public String getJTLY(){
        return this._sJTLY;
    }
    /** ����_sXXLX */
    public void setXXLX(String sXXLX){
        this._sXXLX = sXXLX;
    }
    /** ����_sXXLX */
    public String getXXLX(){
        return this._sXXLX;
    }
    /** ����_sQQFSSJ */
    public void setQQFSSJ(String sQQFSSJ){
        this._sQQFSSJ = sQQFSSJ;
    }
    /** ����_sQQFSSJ */
    public String getQQFSSJ(){
        return this._sQQFSSJ;
    }
    /** ����_sFCSJ */
    public void setFCSJ(String sFCSJ){
        this._sFCSJ = sFCSJ;
    }
    /** ����_sFCSJ */
    public String getFCSJ(){
        return this._sFCSJ;
    }
    /** ����_sSJHM */
    public void setSJHM(String sSJHM){
        this._sSJHM = sSJHM;
    }
    /** ����_sSJHM */
    public String getSJHM(){
        return this._sSJHM;
    }
    /** ����_sXXNR */
    public void setXXNR(String sXXNR){
        this._sXXNR= sXXNR;
    }
    /** ����_sXXNR */
    public String getXXNR(){
        return this._sXXNR;
    }
    /** ����_sNRXH */
    public void setNRXH(String sNRXH){
        this._sNRXH = sNRXH;
    }
    /** ����_sNRXH */
    public String getNRXH(){
        return this._sNRXH;
    }
    /** ����_sFee */
    public void setFee(String sFee){
        this._sFee = sFee;
    }
    /** ����_sFee */
    public String getFee(){
        return this._sFee;
    }
    /** ����_sBZ1 */
    public void setBZ1(String sBZ1){
        this._sBZ1 = sBZ1;
    }
    /** ����_sBZ1 */
    public String getBZ1(){
        return this._sBZ1;
    }
    /** ����_sBZ2 */
    public void setBZ2(String sBZ2){
        this._sBZ2 = sBZ2;
    }
    /** ����_sBZ2 */
    public String getBZ2(){
        return this._sBZ2;
    }
    /** ����_sBZ3 */
    public void setBZ3(String sBZ3){
        this._sBZ3 = sBZ3;
    }
    /** ����_sBZ3 */
    public String getBZ3(){
        return this._sBZ3;
    }
    /** ����_sFlagFee */
    public void setFlagFee(String sFlagFee){
        this._sFlagFee = sFlagFee;
    }
    /** ����_sFlagFee */
    public String getFlagFee(){
        return this._sFlagFee;
    }
    /** ����_sYWDM */
    public void setYWDM(String sYWDM){
        this._sYWDM = sYWDM;
    }
    /** ����_sYWDM */
    public String getYWDM(){
        return this._sYWDM;
    }
    /** ����_sArea */
    public void setArea(String area){
        this._sArea = area;
    }
    /** ����_sArea */
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
        _sXXLX=rs.getString("XXLX");//��Ϣ����
        _sFee=rs.getString("Fee");
        _sXXNR=rs.getString("XXNR");
        if(_sXXNR!=null){
            _sXXNR=com.hoten.util.Chinese.fromDatabase(_sXXNR.trim());
            _sXXNR=_sXXNR.replace('��',',');
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