package com.hoten.cmpp.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SubmitMsg {
    public int nNeedReply;         //�Ƿ�Ҫ�󷵻�״̬ȷ�ϱ��棺 0������Ҫ 1����Ҫ 2������SMC����
    public String sServiceId;      //ҵ������
    public int nFeeUserType;       //�Ʒ��û����ͣ�0����Ŀ���ն�MSISDN�Ʒ� 1����Դ�ն�MSISDN�Ʒ� 2����SP�Ʒ� 3�����ֶ���Ч
    public String sFeeMobile;         //���Ʒ��û����룬��nFeeUserType����
    public int nMsgFormat;         //��Ϣ��ʽ��0��ASCII�� 3������Ϣд������ 4����������Ϣ 8��UCS2���� 15����GB����
    public String sMsgSrc;         //��Ϣ������Դ
    public String sFeeType;        //�ʷ����01���ԡ��Ʒ��û����롱��� 02����������Ϣ�� 03����������ȡ��Ϣ�� 04����Ϣ�ѷⶥ 05���շ���SPʵ��
    public String sFeeCode;        //�ʷѴ��루�Է�Ϊ��λ��
    public String sSrcId;          //Դ���룬SP�ı䳤�ط���
    public int nDestUsrTl;         //������Ϣ���û�������<100����
    public String sDestTerminalId; //���ܶ��ŵ�MSISDN����
    public int nMsgLength;         //��Ϣ���ȣ�nMsgFormatΪ0ʱ<160�ֽڣ�����<=140�ֽڣ�
    public String sMsgContent;     //��Ϣ����

    public byte picAndRing[];
}