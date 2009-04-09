package com.hoten.cmpp.message;

/**
 * <p>Title:下发返回信息 </p>
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
    public long nMsgId;                 //消息标识
    public int nResult;                //结果 0：正确 其他错误
}