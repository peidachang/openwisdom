package com.hoten.cmpp.message;
import java.util.*;
import com.hoten.cmpp.util.Log;
/**
 * <p>Title:提交信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPSubmitMessage extends CMPPHead{

    public CMPPSubmitMessage(SubmitMsg msg) {
        this.nCommandID=4;
        sMsgSrc=msg.sMsgSrc;
        nMsgID=0;
        nNeedReply=msg.nNeedReply;
        sServiceId=msg.sServiceId;
        nFeeUserType=msg.nFeeUserType;
        sFeeMobile=msg.sFeeMobile;
        nMsgFormat=msg.nMsgFormat;
        sFeeType=msg.sFeeType;
        sFeeCode=msg.sFeeCode;
        sSrcId=msg.sSrcId;
        nDestUsrTl=msg.nDestUsrTl;
        sDestTerminalId=msg.sDestTerminalId; //接受短信的MSISDN号码
        nMsgLength=msg.nMsgLength;         //信息长度（nMsgFormat为0时<160字节，其他<=140字节）
        sMsgContent=msg.sMsgContent;     //
        picAndRing=msg.picAndRing;
        if(picAndRing!=null){
            nTPudhi=0x40;
            nMsgFormat=4;
        }
    }
    public long nMsgID;             //消息标识
    public int nPkTotal;           //相同Msg_Id的信息总条数，从1开始
    public int nPkNumber;          //相同Msg_Id的信息序号，从1开始
    public int nNeedReply;         //是否要求返回状态确认报告： 0：不需要 1：需要 2：产生SMC话单
    public int nMsgLevel=1;          //信息级别
    public String sServiceId;      //业务类型
    public int nFeeUserType;       //计费用户类型：0：对目的终端MSISDN计费 1：对源终端MSISDN计费 2：对SP计费 3：本字段无效
    public String sFeeMobile;         //被计费用户号码，与nFeeUserType互斥
    public int nTPpId=0;             //GSM协议类型
    public int nTPudhi=0;            //GSM协议类型
    public int nMsgFormat;         //信息格式：0：ASCII串 3：短信息写卡操作 4：二进制信息 8：UCS2编码 15：含GB汉字
    public String sMsgSrc;         //信息内容来源
    public String sFeeType;        //资费类别：01：对“计费用户号码”免费 02：按条计信息费 03：按包月收取信息费 04：信息费封顶 05：收费由SP实现
    public String sFeeCode;        //资费代码（以分为单位）
    public String sValidTime=null;      //存活有效期
    public String sAtTime=null;         //定时发送时间
    public String sSrcId;          //源号码，SP的变长特服号
    public int nDestUsrTl;         //接受信息的用户数量（<100个）
    public String sDestTerminalId; //接受短信的MSISDN号码
    public int nMsgLength;         //信息长度（nMsgFormat为0时<160字节，其他<=140字节）
    public String sMsgContent;     //信息内容
    public String sReserve;        //保留

    private String mobileFile=com.hoten.cmpp.CMPP_InitMessage.getInstance().mobileFile;

    public byte[] picAndRing;//图片铃声

    public byte[] getDestBytes(){
        byte[] desc = new byte[nDestUsrTl*21];
        int i=0;
        StringTokenizer sDescList = new StringTokenizer(sDestTerminalId,",");
        while(sDescList.hasMoreTokens()){
            String mobile=sDescList.nextToken();
            if(com.hoten.cmpp.util.Tools.checkMobile(mobile)){
                System.arraycopy(mobile.getBytes(),0,desc,i*21,mobile.getBytes().length);
                i++;
            }else{
                Log.printEvent(mobile+"手机号码错误",mobileFile);
            }
        }
        nDestUsrTl=i;
        return desc;
    }
    public byte[] getDestBytes_DR(){
        byte[] desc = new byte[nDestUsrTl*21];
        int i=0;
        int length=0;
        StringTokenizer sDescList = new StringTokenizer(sDestTerminalId,",");
        while(sDescList.hasMoreTokens()){
            String mobile=sDescList.nextToken();
            if(com.hoten.cmpp.util.Tools.checkMobile(mobile)){
                System.arraycopy(mobile.getBytes(),0,desc,length,mobile.getBytes().length);
                length+=mobile.getBytes().length+1;
                i++;
            }else{
                Log.printEvent(mobile+"手机号码错误",mobileFile);
            }
        }
        if(length!=0){
            byte[] mobile = new byte[length];
            System.arraycopy(desc,0,mobile,0,length);
            nDestUsrTl=i;
            return mobile;
        }else{
            return new byte[1];
        }
    }
}