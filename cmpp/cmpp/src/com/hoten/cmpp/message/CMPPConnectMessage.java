package com.hoten.cmpp.message;

/**
 * <p>Title:连接信息 </p>
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
    public String sSP_ID;                  //SP企业代码
    public String sSP_AUTH;                //源地址鉴别码
    public int nVersion;                   //版本号
    public int nTimestamp;                 //时间戳
}
