package com.hoten.cmpp.message;

/**
 * <p>Title:�·�������Ϣ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPDeliverMessageResp extends CMPPHead{

    public CMPPDeliverMessageResp() {
        super();
        this.nCommandID=0x80000005;
    }
    public long nMsgId;                 //��Ϣ��ʶ
    public int nResult;                //��� 0����ȷ ��������
}