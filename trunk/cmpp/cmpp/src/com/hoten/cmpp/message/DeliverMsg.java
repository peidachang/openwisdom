package com.hoten.cmpp.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class DeliverMsg {

    public DeliverMsg() {
    }
    public String sDestId;             //Ŀ�ĺ��� SP�������
    public String sServiceId;          //ҵ������
    public int nMsgFormat;             //��Ϣ��ʽ
    public String sSrcTerminalId;      //Դ�ն�MSISDN����
    public int nIsDelivery;            //�Ƿ�Ϊ״̬���� 0����״̬���� 1��״̬����
    public int nMsgLength;             //��Ϣ����
    public String sMsgContent;         //��Ϣ����
    /////////////////////////////////////////////////////////////////////////////////////////
    public String nReportMsgId;           //��Ϣ��ʶ ��״̬���������ã�
    public String sStat;               //���Ͷ��ŵ�Ӧ���� ��״̬���������ã�
    public String sSubmitTime;         //��Ϣ�ύʱ�� YYMMDDHHMM��״̬���������ã�
    public String sDoneTime;           //YYMMDDHHMM��״̬���������ã�
    public String sDestTerminalId;     //Ŀ���ն�MSISDN���루״̬���������ã�
    public int nSMSCSequence;          //ȡ��SMSC����״̬�������Ϣ���е���Ϣ��ʶ��״̬���������ã�
}