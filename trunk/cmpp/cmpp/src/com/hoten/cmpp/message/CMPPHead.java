package com.hoten.cmpp.message;

/**
 * <p>Title: CMPP��Ϣͷ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPHead {
    private static int i=1;
    public CMPPHead() {
    }
    public int nMsgSize=12;             //��Ϣ�ܳ���
    public int nCommandID;           //�������Ӧ����
    public int nSequenceID=0;          //��Ϣ��ˮ��
    public int stat=0;       //����״̬
    public void setSequenceID(){
        nSequenceID=i;
        i++;
        if(i == 0x7fffffff)
            i = 1;
    }
    public int setSequenceID(int p){
        if(p >= 0x7fffffff)
            p = 1;
        nSequenceID=p;
        p++;
        return p;
    }
    public static void initSequenceID(int num){
        i=num;
    }
}