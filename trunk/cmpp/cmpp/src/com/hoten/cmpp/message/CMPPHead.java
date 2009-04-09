package com.hoten.cmpp.message;

/**
 * <p>Title: CMPP信息头</p>
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
    public int nMsgSize=12;             //消息总长度
    public int nCommandID;           //命令或响应类型
    public int nSequenceID=0;          //消息流水号
    public int stat=0;       //返回状态
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