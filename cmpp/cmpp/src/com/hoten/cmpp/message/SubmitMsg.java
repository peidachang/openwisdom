package com.hoten.cmpp.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SubmitMsg {
    public int nNeedReply;         //是否要求返回状态确认报告： 0：不需要 1：需要 2：产生SMC话单
    public String sServiceId;      //业务类型
    public int nFeeUserType;       //计费用户类型：0：对目的终端MSISDN计费 1：对源终端MSISDN计费 2：对SP计费 3：本字段无效
    public String sFeeMobile;         //被计费用户号码，与nFeeUserType互斥
    public int nMsgFormat;         //信息格式：0：ASCII串 3：短信息写卡操作 4：二进制信息 8：UCS2编码 15：含GB汉字
    public String sMsgSrc;         //信息内容来源
    public String sFeeType;        //资费类别：01：对“计费用户号码”免费 02：按条计信息费 03：按包月收取信息费 04：信息费封顶 05：收费由SP实现
    public String sFeeCode;        //资费代码（以分为单位）
    public String sSrcId;          //源号码，SP的变长特服号
    public int nDestUsrTl;         //接受信息的用户数量（<100个）
    public String sDestTerminalId; //接受短信的MSISDN号码
    public int nMsgLength;         //信息长度（nMsgFormat为0时<160字节，其他<=140字节）
    public String sMsgContent;     //信息内容

    public byte picAndRing[];
}