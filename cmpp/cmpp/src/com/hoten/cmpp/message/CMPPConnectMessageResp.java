package com.hoten.cmpp.message;

/**
 * <p>Title: 连接回复信息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPConnectMessageResp extends CMPPHead{

    public CMPPConnectMessageResp() {
    }

    public int nStatus;                 //状态 0：正确 1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5：其他错误
    public String sISMG;                //ISMG认证码。认证出错时，此项为空
    public int nVersion;                //服务器支持的最高版本号
}