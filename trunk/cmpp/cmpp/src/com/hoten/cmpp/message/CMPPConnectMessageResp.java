package com.hoten.cmpp.message;

/**
 * <p>Title: ���ӻظ���Ϣ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPConnectMessageResp extends CMPPHead{

    public CMPPConnectMessageResp() {
    }

    public int nStatus;                 //״̬ 0����ȷ 1����Ϣ�ṹ�� 2���Ƿ�Դ��ַ 3����֤�� 4���汾̫�� 5����������
    public String sISMG;                //ISMG��֤�롣��֤����ʱ������Ϊ��
    public int nVersion;                //������֧�ֵ���߰汾��
}