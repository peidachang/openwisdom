package com.hoten.cmpp.message;

/**
 * <p>Title:取消信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPCancelMessage  extends CMPPHead {

    public CMPPCancelMessage() {
    }
    public long nMsgId;              //信息标识
}