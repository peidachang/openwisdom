package com.hoten.cmpp.message;

/**
 * <p>Title:��ѯ������Ϣ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPQueryMessageResp  extends CMPPHead{

    public CMPPQueryMessageResp() {
    }
    public String sTime;                 //ʱ��YYYYMMDD����ȷ���գ�
    public int nQueryType;               //��ѯ��� 0��������ѯ 1����ҵ�����Ͳ�ѯ
    public String sQueryCode;            //��ѯ��  ��nQueryType=0ʱΪ�ա���nQueryType=1ʱΪҵ������sServiceId
    public int nsMTTotalMsg;             //��SP������Ϣ����
    public int nMTTotalUser;             //��SP�����û�����
    public int nMTSucess;                //�ɹ�ת������
    public int nMTWait;                  //��ת������
    public int nMTFalse;                 //ת��ʧ������
    public int nMOSucess;                //��SP�ʹ�ɹ�����
    public int nMOWait;                  //��SP���ʹ�����
    public int nMOFalse;                 //��SP�ʹ�ʧ������
}