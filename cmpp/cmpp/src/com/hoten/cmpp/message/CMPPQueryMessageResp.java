package com.hoten.cmpp.message;

/**
 * <p>Title:查询返回信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPQueryMessageResp  extends CMPPHead{

    public CMPPQueryMessageResp() {
    }
    public String sTime;                 //时间YYYYMMDD（精确到日）
    public int nQueryType;               //查询类别 0：总数查询 1：按业务类型查询
    public String sQueryCode;            //查询码  当nQueryType=0时为空。当nQueryType=1时为业务类型sServiceId
    public int nsMTTotalMsg;             //从SP接受信息总数
    public int nMTTotalUser;             //从SP接受用户总数
    public int nMTSucess;                //成功转发数量
    public int nMTWait;                  //待转发数量
    public int nMTFalse;                 //转发失败数量
    public int nMOSucess;                //向SP送达成功数量
    public int nMOWait;                  //向SP待送达数量
    public int nMOFalse;                 //向SP送达失败数量
}