package com.hoten.cmpp.type;
import com.hoten.cmpp.message.*;
import com.hoten.cmpp.*;
import com.hoten.cmpp.socket.*;
import com.hoten.cmpp.util.*;
import java.util.*;
import java.io.*;
import java.net.*;

/**
 * <p>Title: 通信主类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPP_HW implements CMPP_Service{
    private CMPP_InitMessage initMsg=CMPP_InitMessage.getInstance();
    private SocketManager spm=null;
    private String logFile=initMsg.logFile;
    private Socket s=null;
    private String poolName=null;
    private int flag=-1;
    private long time=initMsg.timeout;

    private Vector deliverMsgList=null;
    private DataInputStream in=null;
    private DataOutputStream out=null;
    private int C=initMsg.C;
    private int N=initMsg.N;

    private int sendOut=1;
    private int activeNum=1;

    private String icp=null;

    private int checkTime=(int)(System.currentTimeMillis()/1000);

    public CMPP_HW() {
    }
    //初始化CMPP
    public int initCMPP(String poolName,String host,String icp_ID,String user,String auth,String timeStamp,int version,int port,String loginFlag,Vector list){
        int stat=-1;
        int timeout=1000*10;
        try {
            icp=icp_ID;
            spm=new SocketManager(host,user,auth,timeStamp,version,port,timeout);
            this.poolName=poolName;
            deliverMsgList=list;
            stat = initCMPP();
        }
        catch (Exception ex) {
            spm.freeSocket();
            s=null;
            stat=-1;
        }
        return stat;
    }
    private int initCMPP()throws Exception{
        sendOut=1;
        activeNum=1;
        int stat = CMPP_SUCCESS;
        try {
            CMPPConnectMessage cMsg=new CMPPConnectMessage();
            sendOut=cMsg.setSequenceID(sendOut);
            byte[] auth =getAuthString();
            MD5 md5 = new MD5();
            byte md5Msg[]=md5.getMD5ofBytes(auth,auth.length);
            stat=login(cMsg,md5Msg);
            if(stat!=CMPP_SUCCESS){
                Log.printEvent("初使化HW_CMPP发生错误,resp stat:"+stat,logFile);
            }else{
                Log.printEvent("初使化HW_CMPP成功"+Thread.currentThread().getName(),logFile);
            }
        }
        catch (Exception ex){
            stat=CONNECT_INIT_ERROR;
            Log.printError(ex,"初使化HW_CMPP发生错误"+Thread.currentThread().getName(),logFile);
        }
        try {
            Thread.currentThread().sleep(1000);
        }
        catch (Exception ex) {
        }
        return stat;
    }
    private byte[] getAuthString()throws Exception{//取得登录认证码（转换前）
        byte[] user=spm.getUSER();
        byte[] auth = spm.getAuth();
        byte[] timeStamp = "0008080808".getBytes();
        if(user==null||auth==null)
            return null;
        byte abyte2[] = new byte[100];
        System.arraycopy(user, 0, abyte2, 0, user.length);//icp
        int k = user.length+ 9;
        System.arraycopy(auth, 0, abyte2, k, auth.length);//keys
        k += auth.length;
        System.arraycopy(timeStamp, 0, abyte2, k, 10);//keys
        k += 10;
        byte auths[] = new byte[k];
        System.arraycopy(abyte2, 0,auths, 0, k);//keys
        return auths;
    }

    private int login(CMPPConnectMessage cMsg,byte[] auth)throws IOException,Exception{
        int stat = CMPP_SUCCESS;
        int length=0;
        byte[] inMsg = new byte[39];
        System.arraycopy(Tools.int2byte(cMsg.nCommandID),0,inMsg,4,4);
        System.arraycopy(Tools.int2byte(cMsg.nSequenceID),0,inMsg,8,4);
        length=12;
        System.arraycopy(icp.getBytes(),0,inMsg,12,icp.getBytes().length);
        length+=icp.getBytes().length;
        System.arraycopy(auth,0,inMsg,length,auth.length);
        length+=auth.length;
        inMsg[34]=(byte)spm.getVersion();
        System.arraycopy(Tools.int2byte(8080808),0,inMsg,35,4);
        System.arraycopy(Tools.int2byte(39),0,inMsg,0,4);
        s = spm.getSocket();
        ///发送认证码
        send(inMsg);
        //接收认证码
        CMPPHead head= Deliver();
        if(head.nCommandID==this.COMMAND_CMPP_CONNECT_RESP){
            stat=head.stat;
        }else{
            Log.printEvent("HW:登陆连接反馈发生错误！",logFile);
            stat=CONNECT_MSG_RESP_NOT_FOUNT_ERROR;
        }
        //关闭接收发送通道
        if(stat!=CMPP_SUCCESS){
            close();
        }
        return stat;
    }
    //关闭与网关连接的SOCKET在调用了logout后调用

    //发送断开连接消息给网关
    public void quit(){
        CMPPHead logout = new CMPPHead();
         sendOut=logout.setSequenceID(sendOut);
         logout.nCommandID=2;
         logout.nMsgSize=12;
         try {
             sendHead(logout);
         }
         catch (Exception ex) {
         }
        close();
    }
    //信息提交
    public int submit(SubmitMsg msg)throws IOException{
        try {
            if(msg==null){
                if(!activeTest("auto")){
                    throw new IOException();
                }
                return 0;
            }
            CMPPSubmitMessage submitMessage = new CMPPSubmitMessage(msg);
            String time = CTime.getTime(CTime.YYMMDDhhmmssxxx);
            submitMessage.sValidTime=time;
            submitMessage.sAtTime=time;
            boolean splitFlag=true;
            Vector vMsg=null;
            try {
                if(submitMessage.picAndRing!=null){
                    submitMessage.sMsgContent="1";
                    splitFlag=false;
                }
                vMsg = SplitMsg.split(submitMessage.sMsgContent,splitFlag);             //信息分割队列
            }
            catch (Exception ex) {
                return -1;
            }
            int size=vMsg.size();
            int stat[] = new int[size];
            int i;
            for(i=0;i<size;i++){
                sendOut=submitMessage.setSequenceID(sendOut);
                submitMessage.sMsgContent = (String)(vMsg.remove(0));  //信息内容
                submitMessage.nPkTotal=size;
                submitMessage.nPkNumber=i+1;
                stat[i]=sendMsg(submitMessage);
                if(stat[i]!=0) return stat[i];
            }
        }
        catch (IOException ex) {
            close();
            throw ex;
        }
        return 0;
    }
    private int sendMsg(CMPPSubmitMessage msg)throws IOException{
        int stat = CMPP_SUCCESS;
        byte[] sendMsg=null;
        try {
            sendMsg = getSendMsg(msg);
        }
        catch (Exception ex) {
            sendOut--;
            return SUBMIT_MSG_FORMAT_ERROR;
        }
        int i=N;//重新发送次数
        while(i!=0){
            try {
                sendPacket(msg,sendMsg);
            }
            catch (IOException ex) {
                sendOut--;
                throw ex;
            }
            CMPPHead back= Deliver();
            if(back.stat==DELIVER_MSG_TIME_OUT){
                i--;
                if(i==0)
                    stat=SUBMIT_MSG_TIME_OUT;
                continue;
            }
            if(back.nCommandID==this.COMMAND_CMPP_SUBMIT_RESP){
                return back.stat;
            }
            if(back.nCommandID==this.COMMAND_CMPP_TERMINATE){
                throw new IOException();
            }
            if(back.nCommandID==this.COMMAND_CMPP_DELIVER){
                deliverMsgList.addElement(((CMPPDeliverMessage)back).getDeliverMsg());
                continue;
            }
            else
                return this.CMPP_UNKNOWN_PACKAGE_ERROR;
        }
        return stat;
    }

    private byte[] getSendMsg(CMPPSubmitMessage msg)throws Exception{
        byte send[]= new byte[1024*3];
        send[8]=(byte)msg.nPkTotal;
        send[9]=(byte)msg.nPkNumber;
        send[10]=(byte)msg.nNeedReply;
        send[11]=(byte)msg.nMsgLevel;
        if(msg.sServiceId!=null)
            System.arraycopy(msg.sServiceId.getBytes(),0,send,12,msg.sServiceId.getBytes().length);
        send[22]=(byte)msg.nFeeUserType;
        if(msg.sFeeMobile!=null){
            System.arraycopy(msg.sFeeMobile.getBytes(),0,send,23,msg.sFeeMobile.getBytes().length);
        }else{
            System.arraycopy("".getBytes(),0,send,23,"".getBytes().length);
        }
        send[44]=(byte)msg.nTPpId;
        send[45]=(byte)msg.nTPudhi;
        send[46]=(byte)msg.nMsgFormat;
        System.arraycopy(icp.getBytes(),0,send,47,icp.getBytes().length);
        msg.sFeeType="0"+Integer.parseInt(msg.sFeeType);
        System.arraycopy(msg.sFeeType.getBytes(),0,send,53,msg.sFeeType.getBytes().length);
        System.arraycopy(msg.sFeeCode.getBytes(),0,send,55,msg.sFeeCode.getBytes().length);
        if(msg.sValidTime!=null)
            System.arraycopy(msg.sValidTime.getBytes(),0,send,61,15);
        else
            System.arraycopy(CTime.getTime(CTime.YYMMDDhhmmssxxx).getBytes(),0,send,61,15);
        if(msg.sAtTime!=null)
            System.arraycopy(msg.sAtTime.getBytes(),0,send,78,15);
        else
            System.arraycopy(CTime.getTime(CTime.YYMMDDhhmmssxxx).getBytes(),0,send,61,15);
        if(msg.sSrcId!=null)
            System.arraycopy(msg.sSrcId.getBytes(),0,send,95,msg.sSrcId.getBytes().length);
        System.arraycopy(msg.getDestBytes(),0,send,117,msg.nDestUsrTl*21);
        send[116]=(byte)msg.nDestUsrTl;
        int size=117+msg.nDestUsrTl*21;

        if(msg.picAndRing!=null){
            send[size]=(byte)msg.picAndRing.length;
            size++;
            System.arraycopy(msg.picAndRing,0,send,size,msg.picAndRing.length);
            size=size+msg.picAndRing.length;
        }else{
            byte[] sMsgContent=null;
            if(msg.sMsgContent==null && msg.nNeedReply==2) msg.sMsgContent="JF";
            try {
                if(msg.nMsgFormat==8)
                    sMsgContent=msg.sMsgContent.getBytes("iso-10646-ucs-2");
                else if(msg.nMsgFormat==15)
                    sMsgContent=msg.sMsgContent.getBytes("gb2312");
                else
                    sMsgContent=msg.sMsgContent.getBytes("iso-8859-1");
            }
            catch (Exception ex) {
            }
            send[size]=(byte)sMsgContent.length;
            size++;
            System.arraycopy(sMsgContent,0,send,size,sMsgContent.length);
            size+=sMsgContent.length;
        }
        System.arraycopy("".getBytes(),0,send,size,"".length());
        size+=8;
        byte[] msgBytes= new byte[size];
        System.arraycopy(send,0,msgBytes,0,size);
        return msgBytes;
    }
    //信息删除
    public int Cancel(){
        CMPPCancelMessage cancelMessage = new CMPPCancelMessage();
        CMPPCancelMessageResp cancelResp = new CMPPCancelMessageResp();

        return 1;
    }
    private CMPPHead dealMsg(CMPPHead head,byte[] msg)throws IOException,Exception{
        CMPPHead backMsg = new CMPPHead();
        int stat=head.stat;
        switch (head.nCommandID){
            case COMMAND_CMPP_NACK_RESP:
                break;
            case COMMAND_CMPP_CONNECT_RESP:
                CMPPConnectMessageResp cResp = new CMPPConnectMessageResp();
                if(msg==null) stat=CONNECT_MSG_NULL_ERROR;
                else{
                    cResp.nStatus=msg[0];
                    try {
                        stat= msg[0];
                        if(stat!=3)
                            cResp.sISMG=new String(msg,1,16);
                        cResp.nVersion=msg[msg.length-1];
                    }
                    catch (Exception ex) {
                        stat=CONNECT_MSG_FORMAT_ERROR;
                    }
                }
                cResp.nStatus=stat;
                backMsg=cResp;
                break;
            case COMMAND_CMPP_DELIVER:
                CMPPDeliverMessage back=new CMPPDeliverMessage();
                stat = getDeliverMessage(back,msg);
                CMPPDeliverMessageResp resp = new CMPPDeliverMessageResp();
                resp.nMsgId=back.msgID;
                resp.nSequenceID=back.nSequenceID;
                resp.nMsgSize=21;
                resp.nResult=stat;
                byte[] send = new byte[9];
                System.arraycopy(Tools.long2byte(resp.nMsgId),0,send,0,8);
                send[8]=(byte)resp.nResult;
                try {
                    sendPacket(resp,send);
                }
                catch (IOException ex) {
                    throw ex;
                }
                back.stat=stat;
                backMsg=back;
                break;
            case COMMAND_CMPP_SUBMIT_RESP:
                CMPPSubmitMessageResp sResp = new CMPPSubmitMessageResp();
                if(msg==null) stat=SUBMIT_MSG_NULL_ERROR;
                else{
                    try {
                        byte id[]= new byte[8];
                        System.arraycopy(msg,0,id,0,8);
                        sResp.setMsgID(id);
                        sResp.nResult= msg[8];
                        stat=msg[8];
                    }
                    catch (Exception ex) {
                        stat=SUBMIT_MSG_FORMAT_ERROR;
                    }
                }
                sResp.stat=stat;
                backMsg=sResp;
                break;
            case COMMAND_CMPP_ACTIVE_TEST:
                CMPPActiveTestMessageResp respMsg=new CMPPActiveTestMessageResp();
                respMsg.nSequenceID=head.nSequenceID;
                try {
                    sendHead(respMsg);
                }
                catch (IOException ex) {
                    throw ex;
                }
                break;
            case COMMAND_CMPP_ACTIVE_TEST_REP:
                break;
            case COMMAND_CMPP_TERMINATE:
                CMPPHead out= new CMPPHead();
                out.nMsgSize=12;
                out.nCommandID=COMMAND_CMPP_TERMINATE_RESP;
                out.nSequenceID=head.nSequenceID;
                try {
                    sendHead(out);
                }
                catch (IOException ex) {
                    throw ex;
                }
                break;
            case COMMAND_CMPP_TERMINATE_RESP:
                break;
            default:
                stat=CMPP_UNKNOWN_PACKAGE_ERROR;
        }
        backMsg.nCommandID=head.nCommandID;
        backMsg.nMsgSize=head.nMsgSize;
        backMsg.nSequenceID=head.nSequenceID;
        backMsg.stat=stat;
        return backMsg;
    }
    //收取网关下发信息

    public void deliver()throws IOException{
        try {
            if(!activeTest("auto"))
                throw new IOException();
            CMPPHead back = Deliver();
            if(back.nCommandID==this.COMMAND_CMPP_DELIVER){
                deliverMsgList.addElement(((CMPPDeliverMessage)back).getDeliverMsg());
                return;
            }
            if(back.nCommandID==this.COMMAND_CMPP_TERMINATE){
                close();
                throw new IOException();
            }
            if(back.stat==DELIVER_MSG_TIME_OUT){
                return;
            }
        }
        catch (IOException ex) {
            close();
            throw ex;
        }
    }
    private CMPPHead Deliver()throws IOException{
        CMPPHead head = new CMPPHead();
        byte[] bMsg=null;
        int i=0;
        while(true){
            try {
                bMsg=read(head);
            }
            catch (InterruptedIOException ex) {
                if(i==2){
                    head.stat=DELIVER_MSG_TIME_OUT;//由于没有消息而退出－－stat=1
                    break;
                }else{
                    i++;
                     continue;
                }
            }
            catch (IOException ex) {
                throw ex;
            }
            try {
                head = dealMsg(head,bMsg);
            }
            catch(IOException ex){
                throw ex;
            }
            catch (Exception ex) {
                Log.printBytes(bMsg,logFile);
                Log.printError(ex,"",logFile);
            }
            if(head.nCommandID==this.COMMAND_CMPP_ACTIVE_TEST){//||head.nCommandID==this.COMMAND_CMPP_NACK_RESP){
                continue;
            }
            break;
        }
        return head;
    }

    //向网关查询信息流量
    public CMPPQueryMessageResp Query(){
        CMPPQueryMessage queryMessage = new CMPPQueryMessage();
        CMPPQueryMessageResp queryResp = new CMPPQueryMessageResp();

        return queryResp;
    }

    //发送测试信息

    private boolean activeTest(String flag)throws IOException{
        boolean testFlag=false;
        if(flag==null||(int)(System.currentTimeMillis()/1000)-checkTime>10)
            testFlag=true;
        if(testFlag){
            CMPPActiveTestMessage test = new CMPPActiveTestMessage();
            activeNum=test.setSequenceID(activeNum);
            test.nMsgSize=12;
            try {
                sendHead(test);
            }
            catch(IOException e){
                activeNum--;
                return false;
            }
            try {
                while(true){
                    CMPPHead back = Deliver();
                    if(back.nCommandID==this.COMMAND_CMPP_DELIVER){
                        deliverMsgList.addElement(((CMPPDeliverMessage)back).getDeliverMsg());
                        continue;
                    }
                    if(back.stat==this.DELIVER_MSG_TIME_OUT){
                        throw new IOException();
                    }
                    if(back.nCommandID==this.COMMAND_CMPP_ACTIVE_TEST_REP){
                        checkTime=(int)(System.currentTimeMillis()/1000);
                        break;
                    }
                }
            }
            catch (IOException ex) {
                return false;
            }
            return true;
        }
        return true;
    }
    public boolean activeTest()throws IOException{
        return activeTest(null);
    }
    private int getDeliverMessage(CMPPDeliverMessage msg,byte[] content)throws Exception{
        if(msg==null)
            return DELIVER_MSG_NULL_ERROR;
        int stat=CMPP_SUCCESS;
        //把content解析成所需信息
        byte msgID[] = new byte[8];
        byte sDestTermID[] = new byte[21];
        byte sServiceID[] = new byte[10];
        byte cTpPid=0;
        byte cTpUdhi=0;
        byte nMsgFormat=0;
        byte sSrcTermID[] = new byte[21];
        byte nIsReply=0;
        byte nMsgLen=0;
        byte sMsgContent[]=null;
        try {
            System.arraycopy(content,0,msgID,0,8);
            System.arraycopy(content,8,sDestTermID,0,21);
            System.arraycopy(content,29,sServiceID,0,10);
            cTpPid = content[39];
            cTpUdhi = content[40];
            nMsgFormat = content[41];
            System.arraycopy(content,42,sSrcTermID,0,21);
            nIsReply = content[63];
            nMsgLen = content[64];
            if(nMsgLen< 0)
                nMsgLen += 256;
            sMsgContent= new byte[nMsgLen];
            System.arraycopy(content,65,sMsgContent,0,nMsgLen);
        }
        catch (Exception ex) {
            return DELIVER_MSG_FORMAT_ERROR;
        }
        /////////////////////////////////////////封装正常消息
        msg.setMsgID(msgID);
        msg.msgID=Tools.byte2long(msgID);
        msg.nIsDelivery=nIsReply;
        msg.nMsgFormat=nMsgFormat;
        msg.nMsgLength=nMsgLen;
        msg.nTPpid=cTpPid;
        msg.nTPudhi=cTpUdhi;
        msg.sDestId=new String(sDestTermID).trim();
        msg.sServiceId=new String(sServiceID).trim();
        msg.sSrcTerminalId=new String(sSrcTermID).trim();
        try {
            if(nIsReply==0){
                if(nMsgFormat == 8){//ucs2编码格式
                    msg.sMsgContent = new String(sMsgContent,"iso-10646-ucs-2");

                }else{
                    msg.sMsgContent=new String(sMsgContent,"gb2312");
                }
            }
        }
        catch (Exception ex) {
            return DELIVER_MSG_FORMAT_DECODE_ERROR;
        }
        /////////////////////////////状态报告中所需字段
        if(nIsReply==1){
            try {
                byte msgReportID[]=new byte[8];
                System.arraycopy(sMsgContent,0,msgReportID,0,8);
                msg.setReportMsgId(msgReportID);
                //msg.nReportMsgId=Tools.byte2long(sMsgContent,0);
                msg.sStat=new String(sMsgContent,8,7).trim();
                msg.sSubmitTime=new String(sMsgContent,15,10).trim();
                msg.sDoneTime=new String(sMsgContent,25,10).trim();
                msg.sDestTerminalId=new String(sMsgContent,35,21).trim();
                msg.nSMSCSequence=Tools.byte2int(sMsgContent,56);
            }
            catch (Exception ex) {
                stat=DELIVER_MSG_FORMAT_REPORT_ERROR;
            }
        }
        return stat;
    }
    private void sendHead(CMPPHead head)throws IOException{
        byte[] send = new byte[12];
        System.arraycopy(Tools.int2byte(head.nMsgSize),0,send,0,4);
        System.arraycopy(Tools.int2byte(head.nCommandID),0,send,4,4);
        System.arraycopy(Tools.int2byte(head.nSequenceID),0,send,8,4);
        send(send);
    }
    private void sendPacket(CMPPHead head,byte[] msg)throws IOException{
        byte[] send = new byte[12+msg.length];
        head.nMsgSize=12+msg.length;
        System.arraycopy(Tools.int2byte(head.nMsgSize),0,send,0,4);
        System.arraycopy(Tools.int2byte(head.nCommandID),0,send,4,4);
        System.arraycopy(Tools.int2byte(head.nSequenceID),0,send,8,4);
        System.arraycopy(msg,0,send,12,msg.length);
        send(send);
    }
    private void send(byte[] msg)throws IOException{
        DataOutputStream out = creatOutputStream();
        out.write(msg);
        out.flush();
    }

    private byte[] read(CMPPHead head)throws InterruptedIOException,IOException{
        DataInputStream in =creatInputStream();
        head.nMsgSize=in.readInt();
        head.nCommandID=in.readInt();
        head.nSequenceID=in.readInt();
        byte[] resp = null;
        if(head.nMsgSize<=12){
            return null;
        }else{
            resp=new byte[head.nMsgSize-12];
            in.read(resp,0,head.nMsgSize-12);
        }
        return resp;
    }
    private DataInputStream creatInputStream()throws IOException{
        return new DataInputStream(s.getInputStream());
    }
    private DataOutputStream creatOutputStream()throws IOException{
        return new DataOutputStream(s.getOutputStream());
    }
    public void close(){
        spm.freeSocket();
        s=null;
    }

    private static final int CMPP_SUCCESS=0;
    private static final int CMPP_UNKNOWN_PACKAGE_ERROR=-9;
    private static final int CMPP_LOGOUT_HAPPEND=-2;

    private static final int SUBMIT_MSG_NULL_ERROR=-4;
    private static final int SUBMIT_MSG_FORMAT_ERROR=-5;
    private static final int SUBMIT_MSG_TIME_OUT=-6;

    private static final int DELIVER_MSG_FORMAT_REPORT_ERROR=-7;
    private static final int DELIVER_MSG_NULL_ERROR=-8;
    private static final int DELIVER_MSG_TIME_OUT=-9;
    private static final int DELIVER_MSG_FORMAT_ERROR=-10;
    private static final int DELIVER_MSG_FORMAT_DECODE_ERROR=-11;

    private static final int CONNECT_MSG_NULL_ERROR=-12;
    private static final int CONNECT_MSG_RESP_NOT_FOUNT_ERROR=-13;
    private static final int CONNECT_MSG_FORMAT_ERROR=-14;
    private static final int CONNECT_INIT_ERROR=-15;

    private static final int CONNECT_HEAD_LEN = 12;
    private static final int CONNECT_PACKET_LEN = 27;
    private static final int QUERY_PACKET_LEN = 27;
    private static final int COMMAND_CMPP_SUBMIT = 4;
    private static final int COMMAND_CMPP_QUERY = 6;
    private static final int COMMAND_CMPP_TERMINATE = 2;
    private static final int COMMAND_CMPP_TERMINATE_RESP = 0x80000002;
    private static final int COMMAND_CMPP_QUERY_RESP = 0x80000006;
    private static final int COMMAND_CMPP_SUBMIT_RESP = 0x80000004;
    private static final int COMMAND_CMPP_CONNECT = 1;
    private static final int COMMAND_CMPP_CONNECT_RESP = 0x80000001;
    private static final int COMMAND_CMPP_ACTIVE_TEST = 8;
    private static final int COMMAND_CMPP_ACTIVE_TEST_REP = 0x80000008;
    private static final int COMMAND_CMPP_DELIVER = 5;
    private static final int COMMAND_CMPP_NACK_RESP = 0x80000000;
    private static final int COMMAND_CMPP_DELIVER_RESP = 0x80000005;
    private static final int COMMAND_CMPP_SET_KEY = 4098;
    private static final int COMMAND_CMPP_SET_KEY_RESP = 0x80001002;
}
