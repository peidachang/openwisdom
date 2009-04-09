package com.hoten.cmpp.message;

/**
 * <p>Title:连接测试信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPActiveTestMessage extends CMPPHead {

    public CMPPActiveTestMessage() {
        super();
        this.nMsgSize=12;
        this.nCommandID=8;
    }
}