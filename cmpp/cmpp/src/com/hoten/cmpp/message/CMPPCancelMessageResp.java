package com.hoten.cmpp.message;

/**
 * <p>Title:取消返回信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPCancelMessageResp extends CMPPHead{

    public CMPPCancelMessageResp() {
    }
    public int nSuccessId;                 //成功标识 0：成功 1：失败
}