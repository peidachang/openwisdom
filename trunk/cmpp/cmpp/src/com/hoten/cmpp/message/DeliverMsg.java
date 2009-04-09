package com.hoten.cmpp.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class DeliverMsg {

    public DeliverMsg() {
    }
    public String sDestId;             //目的号码 SP服务代码
    public String sServiceId;          //业务类型
    public int nMsgFormat;             //信息格式
    public String sSrcTerminalId;      //源终端MSISDN号码
    public int nIsDelivery;            //是否为状态报告 0：非状态报告 1：状态报告
    public int nMsgLength;             //消息长度
    public String sMsgContent;         //消息内容
    /////////////////////////////////////////////////////////////////////////////////////////
    public String nReportMsgId;           //信息标识 （状态报告是有用）
    public String sStat;               //发送短信的应答结果 （状态报告是有用）
    public String sSubmitTime;         //消息提交时间 YYMMDDHHMM（状态报告是有用）
    public String sDoneTime;           //YYMMDDHHMM（状态报告是有用）
    public String sDestTerminalId;     //目的终端MSISDN号码（状态报告是有用）
    public int nSMSCSequence;          //取自SMSC发送状态报告的消息体中的消息标识（状态报告是有用）
}