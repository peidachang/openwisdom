package com.hoten.cmpp.message;

/**
 * <p>Title:������Ϣ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public final class CMPPConnectMessage extends CMPPHead {
    public CMPPConnectMessage ()
    {
        super();
        this.nCommandID=1;
    }
    public String sSP_ID;                  //SP��ҵ����
    public String sSP_AUTH;                //Դ��ַ������
    public int nVersion;                   //�汾��
    public int nTimestamp;                 //ʱ���
}
