package com.hoten.cmpp.message;

/**
 * <p>Title: ��ѯ��Ϣ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPQueryMessage extends CMPPHead{

    public CMPPQueryMessage() {
    }
    public String sTime;                 //ʱ��YYYYMMDD����ȷ���գ�
    public int nQueryType;               //��ѯ��� 0��������ѯ 1����ҵ�����Ͳ�ѯ
    public String sQueryCode;            //��ѯ��  ��nQueryType=0ʱ����Ч����nQueryType=1ʱ����ҵ������sServiceId
    public String sReserve;              //����
}