package com.hoten.cmpp.message;

/**
 * <p>Title: 查询信息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPQueryMessage extends CMPPHead{

    public CMPPQueryMessage() {
    }
    public String sTime;                 //时间YYYYMMDD（精确到日）
    public int nQueryType;               //查询类别 0：总数查询 1：按业务类型查询
    public String sQueryCode;            //查询码  当nQueryType=0时，无效。当nQueryType=1时，填业务类型sServiceId
    public String sReserve;              //保留
}