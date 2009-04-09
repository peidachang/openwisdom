package com.hoten.cmpp.message;

/**
 * <p>Title: �������·���Ϣ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPDeliverMessage  extends CMPPHead{

    public CMPPDeliverMessage() {
        this.nCommandID=5;
    }
    public DeliverMsg getDeliverMsg(){
        DeliverMsg msg = new DeliverMsg();
        msg.nIsDelivery=this.nIsDelivery;
        msg.nMsgFormat=this.nMsgFormat;
        msg.nMsgLength=this.nMsgLength;
        msg.nReportMsgId=this.nReportMsgId;
        msg.nSMSCSequence=this.nSMSCSequence;
        msg.sDestId=this.sDestId;
        msg.sDestTerminalId=this.sDestTerminalId;
        msg.sDoneTime=this.sDoneTime;
        msg.sMsgContent=this.sMsgContent;
        msg.sServiceId=this.sServiceId;
        msg.sSrcTerminalId=this.sSrcTerminalId;
        msg.sStat=this.sStat;
        msg.sSubmitTime=this.sSubmitTime;
        return msg;
    }
    public void setMsgID(byte id[]){

    }
    public void setReportMsgId(byte id[]){

    }
    public String nMsgId;                 //��Ϣ��ʶ
    public long msgID;
    public String sDestId;             //Ŀ�ĺ��� SP�������
    public String sServiceId;          //ҵ������
    public int nTPpid;
    public int nTPudhi;
    public int nMsgFormat;             //��Ϣ��ʽ
    public String sSrcTerminalId;      //Դ�ն�MSISDN����
    public int nIsDelivery;            //�Ƿ�Ϊ״̬���� 0����״̬���� 1��״̬����
    public int nMsgLength;             //��Ϣ����
    public String sMsgContent;         //��Ϣ����
    public String sReserved;           //����
    /////////////////////////////////////////////////////////////////////////////////////////
    public String nReportMsgId;           //��Ϣ��ʶ ��״̬���������ã�
    public String sStat;               //���Ͷ��ŵ�Ӧ���� ��״̬���������ã�
    public String sSubmitTime;         //��Ϣ�ύʱ�� YYMMDDHHMM��״̬���������ã�
    public String sDoneTime;           //YYMMDDHHMM��״̬���������ã�
    public String sDestTerminalId;     //Ŀ���ն�MSISDN���루״̬���������ã�
    public int nSMSCSequence;          //ȡ��SMSC����״̬�������Ϣ���е���Ϣ��ʶ��״̬���������ã�
}