package com.hoten.cmpp.message;

/**
 * <p>Title: 服务器下发信息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPDeliverMessage  extends CMPPHead{

    public CMPPDeliverMessage() {
        this.nCommandID=5;
    }
    public DeliverMsg getDeliverMsg(){
        DeliverMsg msg = new DeliverMsg();
        msg.nIsDelivery=this.nIsDelivery;
        msg.nMsgFormat=this.nMsgFormat;
        msg.nMsgLength=this.nMsgLength;
        msg.nReportMsgId=this.nReportMsgId;
        msg.nSMSCSequence=this.nSMSCSequence;
        msg.sDestId=this.sDestId;
        msg.sDestTerminalId=this.sDestTerminalId;
        msg.sDoneTime=this.sDoneTime;
        msg.sMsgContent=this.sMsgContent;
        msg.sServiceId=this.sServiceId;
        msg.sSrcTerminalId=this.sSrcTerminalId;
        msg.sStat=this.sStat;
        msg.sSubmitTime=this.sSubmitTime;
        return msg;
    }
    public void setMsgID(byte id[]){

    }
    public void setReportMsgId(byte id[]){

    }
    public String nMsgId;                 //消息标识
    public long msgID;
    public String sDestId;             //目的号码 SP服务代码
    public String sServiceId;          //业务类型
    public int nTPpid;
    public int nTPudhi;
    public int nMsgFormat;             //信息格式
    public String sSrcTerminalId;      //源终端MSISDN号码
    public int nIsDelivery;            //是否为状态报告 0：非状态报告 1：状态报告
    public int nMsgLength;             //消息长度
    public String sMsgContent;         //消息内容
    public String sReserved;           //保留
    /////////////////////////////////////////////////////////////////////////////////////////
    public String nReportMsgId;           //信息标识 （状态报告是有用）
    public String sStat;               //发送短信的应答结果 （状态报告是有用）
    public String sSubmitTime;         //消息提交时间 YYMMDDHHMM（状态报告是有用）
    public String sDoneTime;           //YYMMDDHHMM（状态报告是有用）
    public String sDestTerminalId;     //目的终端MSISDN号码（状态报告是有用）
    public int nSMSCSequence;          //取自SMSC发送状态报告的消息体中的消息标识（状态报告是有用）
}