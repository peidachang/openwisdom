package com.hoten.cmpp.message;

/**
 * <p>Title:≤‚ ‘∑µªÿ–≈œ¢ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPActiveTestMessageResp  extends CMPPHead{

    public CMPPActiveTestMessageResp() {
        super();
        this.nMsgSize=12;
        this.nCommandID=0x80000008;
    }
    public byte bReserved;
}