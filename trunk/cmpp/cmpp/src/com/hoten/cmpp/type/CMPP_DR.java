package com.hoten.cmpp.type;
import com.hoten.cmpp.message.*;
import com.hoten.cmpp.socket.*;
import com.hoten.cmpp.util.*;
import java.util.*;
import java.io.*;
import java.net.*;
import com.hoten.cmpp.*;

/**
 * <p>Title: 通信主类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPP_DR implements CMPP_Service{
    private CMPP_InitMessage initMsg=CMPP_InitMessage.getInstance();
    private SocketManager socketManger=null;
    private String logFile=initMsg.logFile;
    private Socket s=null;
    private String poolName=null;
    private int flag=-1;
    private long time=initMsg.timeout;

    private Vector deliverMsgList=null;
    private DataInputStream in=null;
    private DataOutputStream out=null;
    private String user = null;
    private int C=initMsg.C;
    private int N=initMsg.N;

    private int sendOut=1;
    private String icp=null;

    private int checkTime=(int)(System.currentTimeMillis()/1000);

    public CMPP_DR() {
    }
    //初始化CMPP
    public int initCMPP(String poolName,String host,String icp_ID,String user,String auth,String timeStamp,int version,int port,String loginFlag,Vector list){
        int stat=-1;
        int timeout = 1000*10;
        icp=icp_ID;
        this.user=user;
        try {
            socketManger=new SocketManager(host,user,auth,timeStamp,version,port,timeout);
            if(loginFlag==null)
                flag=2;
            else{
                if(loginFlag.equals("MO"))
                    flag=1;
                if(loginFlag.equals("MT"))
                    flag=0;
                if(loginFlag.equals("MOMT"))
                    flag=2;
            }
            this.poolName=poolName;
            deliverMsgList=list;
            stat = initCMPP();
        }
        catch (Exception ex) {
            socketManger.freeSocket();
            s=null;
            stat=-1;
        }
        return stat;
    }
    private int initCMPP()throws Exception{
        sendOut=1;
        int stat = CMPP_SUCCESS;
        try {
            CMPPConnectMessage cMsg=new CMPPConnectMessage();
            sendOut=cMsg.setSequenceID(sendOut);
            int timeStamp = (int)(System.currentTimeMillis()/1000);
            byte[] auth =getAuthString(timeStamp);
            MD5 md5 = new MD5();
            byte md5Msg[]=md5.getMD5ofBytes(auth,auth.length);
            stat=login(cMsg,md5Msg,flag,timeStamp);
            if(stat!=CMPP_SUCCESS){
                Log.printEvent(Thread.currentThread().getName()+"初使化DR_CMPP发生错误,resp stat:"+stat,logFile);
            }else{
                Log.printEvent("初使化DR_CMPP成功:"+Thread.currentThread().getName(),logFile);
            }
        }
        catch (Exception ex){
            stat=CONNECT_INIT_ERROR;
            Log.printError(ex,"初使化DR_CMPP发生错误:"+Thread.currentThread().getName(),logFile);
        }
        try {
            Thread.currentThread().sleep(1000);
        }
        catch (Exception ex) {
        }
        return stat;
    }
    private byte[] getAuthString(int timeStamp)throws Exception{//取得登录认证码（转换前）
        byte[] user=socketManger.getUSER();
        byte[] auth = socketManger.getAuth();
        if(user==null||auth==null)
            return null;
        byte abyte2[] = new byte[100];
        System.arraycopy(user, 0, abyte2, 0, user.length);//icp
        int k = user.length+ 9;
        System.arraycopy(auth, 0, abyte2, k, auth.length);//keys
        k += auth.length;
        System.arraycopy(Tools.int2byte(timeStamp), 0, abyte2, k, 4);//keys
        k += 4;
        byte auths[] = new byte[k];
        System.arraycopy(abyte2, 0,auths, 0, k);//keys
        return auths;
    }

    private int login(CMPPConnectMessage cMsg,byte[] auth,int loginFlag,int timeStamp)throws IOException,Exception{
        int stat = CMPP_SUCCESS;
        int length=0;
        byte[] inMsg = new byte[100];
        System.arraycopy(Tools.int2byte(cMsg.nCommandID),0,inMsg,4,4);
        System.arraycopy(Tools.int2byte(cMsg.nSequenceID),0,inMsg,12,4);
        length=16;
        System.arraycopy(icp.getBytes(),0,inMsg,length,icp.getBytes().length);
        length+=icp.getBytes().length+1;
        System.arraycopy(auth,0,inMsg,length,auth.length);
        length+=auth.length;
        inMsg[length]=(byte)loginFlag;
        length++;
        inMsg[length]=(byte)socketManger.getVersion();
        length++;
        System.arraycopy(Tools.int2byte(timeStamp),0,inMsg,length,4);
        length+=4;
        cMsg.nMsgSize=length;
        System.arraycopy(Tools.int2byte(cMsg.nMsgSize),0,inMsg,0,4);
        byte[] send = new byte[length];
        System.arraycopy(inMsg,0,send,0,length);
        s = socketManger.getSocket();
        ///发送认证码
        send(send);
        //接收认证码
        CMPPHead head= Deliver();
        if(head.nCommandID==this.COMMAND_CMPP_CONNECT_RESP){
            stat=head.stat;
        }else{
            Log.printEvent("DR:登陆连接反馈发生错误！",logFile);
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
        logout.nMsgSize=16;
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
        int length=0;
        byte send[]= new byte[1024*3];
        System.arraycopy(icp.getBytes(),0,send,0,icp.getBytes().length);
        length+=icp.getBytes().length+1;
        if(msg.sServiceId!=null){
            System.arraycopy(msg.sServiceId.getBytes(),0,send,length,msg.sServiceId.getBytes().length);
            length+=msg.sServiceId.getBytes().length+1;
        }else
            length++;
        send[length]=(byte)Integer.parseInt(msg.sFeeType);
        length++;
        System.arraycopy(Tools.int2byte(Integer.parseInt(msg.sFeeCode)),0,send,length,4);
        length+=4;
        send[length]=(byte)msg.nTPpId;
        length++;
        send[length]=(byte)msg.nNeedReply;
        length++;
        send[length]=(byte)msg.nMsgLevel;
        length++;
        if(msg.sValidTime!=null){
            System.arraycopy(msg.sValidTime.getBytes(),0,send,length,msg.sValidTime.getBytes().length);
            length+=msg.sValidTime.getBytes().length+1;
        }else{
            length++;
        }
        if(msg.sAtTime!=null){
            System.arraycopy(msg.sAtTime.getBytes(),0,send,length,msg.sAtTime.getBytes().length);
            length+=msg.sAtTime.getBytes().length+1;
        }else{
            length++;
        }
        send[length]=(byte)msg.nFeeUserType;
        length++;
        if(msg.sFeeMobile!=null){
            System.arraycopy(msg.sFeeMobile.getBytes(),0,send,length,msg.sFeeMobile.getBytes().length);
            length+=msg.sFeeMobile.getBytes().length+1;
        }else{
            length++;
        }
        System.arraycopy(msg.sSrcId.getBytes(),0,send,length,msg.sSrcId.getBytes().length);
        length+=msg.sSrcId.getBytes().length+1;

        length++;
        System.arraycopy(msg.getDestBytes_DR(),0,send,length,msg.getDestBytes_DR().length);
        send[length-1]=(byte)msg.nDestUsrTl;
        length+=msg.getDestBytes_DR().length;
        send[length]=(byte)msg.nMsgFormat;
        length++;

        if(msg.sMsgContent==null && msg.nNeedReply==2) msg.sMsgContent="JF";

        if(msg.picAndRing!=null){
            send[length]=(byte)msg.picAndRing.length;
            length++;
            System.arraycopy(msg.picAndRing,0,send,length,msg.picAndRing.length);
            length+=msg.sMsgContent.getBytes().length;
        }else{
            byte[] sMsgContent=null;
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
            send[length]=(byte)sMsgContent.length;
            length++;
            System.arraycopy(sMsgContent,0,send,length,sMsgContent.length);
            length+=sMsgContent.length+1;
        }
        byte[] msgs = new byte[length];
        System.arraycopy(send,0,msgs,0,length);
        return msgs;
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
                if(msg==null){
                    cResp.nStatus=stat;
                    backMsg=cResp;
                    break;
                }
                try {
                    cResp.sISMG=new String(msg,0,16);
                    cResp.nVersion=msg[16];
                }
                catch (Exception ex) {
                    stat=CONNECT_MSG_FORMAT_ERROR;
                }
                cResp.nStatus=stat;
                backMsg=cResp;
                break;
            case COMMAND_CMPP_DELIVER:
                CMPPDeliverMessage back=new CMPPDeliverMessage();
                stat = getDeliverMessage(back,msg);
                CMPPDeliverMessageResp resp = new CMPPDeliverMessageResp();
                resp.nSequenceID=back.nSequenceID;
                resp.stat=stat;
                resp.nMsgSize=16;
                try {
                    sendHead(resp);
                }
                catch (IOException ex) {
                    throw ex;
                }
                back.stat=stat;
                backMsg=back;
                break;
            case COMMAND_CMPP_SUBMIT_RESP:
                CMPPSubmitMessageResp sResp = new CMPPSubmitMessageResp();
                if(msg!=null){
                    try {
                        sResp.un_count= msg[0];
                        if(sResp.un_count!=0){
                            byte[] count = new byte[msg.length-1];
                            System.arraycopy(msg,1,count,0,msg.length-1);
                            sResp.fail_Index=count;
                        }
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
                respMsg.nMsgSize=16;
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
                out.nMsgSize=16;
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
        }
        catch (IOException ex) {
            close();
            throw ex;
        }
    }
    //收取网关下发信息
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
            if(head.nCommandID==this.COMMAND_CMPP_ACTIVE_TEST){
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
            sendOut=test.setSequenceID(sendOut);
            test.nMsgSize=16;
            try {
                sendHead(test);
            }
            catch(IOException e){
                sendOut--;
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
            int length = content.length;
            int i=0;
            for(;i<length;i++){
                if(content[i]==0){
                    break;
                }
            }
            System.arraycopy(content,0,sSrcTermID,0,i+1);
            byte[] msgs=new byte[length];
            System.arraycopy(content,i+1,msgs,0,length-i-1);
            content=msgs;

            i=0;
            length=content.length;
            for(;i<length;i++){
                if(content[i]==0){
                    break;
                }
            }
            System.arraycopy(content,0,sDestTermID,0,i+1);
            msgs=new byte[length];
            System.arraycopy(content,i+1,msgs,0,length-i-1);
            content=msgs;

            i=0;
            length=content.length;
            for(;i<length;i++){
                if(content[i]==0){
                    break;
                }
            }
            System.arraycopy(content,0,sServiceID,0,i+1);
            msgs=new byte[length];
            System.arraycopy(content,i+1,msgs,0,length-i-1);
            content=msgs;
            cTpPid=content[0];
            nIsReply=content[1];
            cTpUdhi=content[2];
            nMsgFormat=content[3];
            nMsgLen=content[4];
            if(nMsgLen<0)
                nMsgLen+=256;
            sMsgContent= new byte[nMsgLen];
            System.arraycopy(content,5,sMsgContent,0,nMsgLen);
        }
        catch (Exception ex) {
            return DELIVER_MSG_FORMAT_ERROR;
        }
        /////////////////////////////////////////封装正常消息
        msg.nIsDelivery=nIsReply;
        msg.nMsgFormat=nMsgFormat;
        msg.nMsgLength=nMsgLen;
        msg.nTPpid=cTpPid;//协议标识
        msg.nTPudhi=cTpUdhi;//优先级别
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
                //byte[] msgID = new byte[8];
                //byte[] sStat = new byte[3];
                //byte[] sSubmitTime = new byte[12];
                //byte[] sDoneTime = new byte[12];

                int length = sMsgContent.length;
                int i=0;
                for(;i<length;i++){
                    if(sMsgContent[i]==9){
                        break;
                    }
                }
                 byte[] msgID = new byte[i];
                System.arraycopy(sMsgContent,0,msgID,0,i);
                byte[] msgs=new byte[length-i-1];
                System.arraycopy(sMsgContent,i+1,msgs,0,length-i-1);
                sMsgContent=msgs;

                i=0;
                length=sMsgContent.length;
                for(;i<length;i++){
                    if(sMsgContent[i]==9){
                        break;
                    }
                }
                byte[] sStat = new byte[i];
                System.arraycopy(sMsgContent,0,sStat,0,i);
                msgs=new byte[length-i-1];
                System.arraycopy(sMsgContent,i+1,msgs,0,length-i-1);
                sMsgContent=msgs;

                i=0;
                length=sMsgContent.length;
                for(;i<length;i++){
                    if(sMsgContent[i]==9){
                        break;
                    }
                }
                byte[] sSubmitTime = new byte[i];
                System.arraycopy(sMsgContent,0,sSubmitTime,0,i);
                msgs=new byte[length-i-1];
                System.arraycopy(sMsgContent,i+1,msgs,0,length-i-1);
                sMsgContent=msgs;

                i=0;
                length=sMsgContent.length;
                for(;i<length;i++){
                    if(sMsgContent[i]==9){
                        break;
                    }
                }
                byte[] sDoneTime = new byte[i];
                System.arraycopy(sMsgContent,0,sDoneTime,0,i);
                msg.setReportMsgId(msgID);
                msg.sStat=new String(sStat).trim();
                msg.sStat=String.valueOf(Integer.parseInt(msg.sStat));
                msg.sSubmitTime=new String(sSubmitTime).trim();
                msg.sDoneTime=new String(sDoneTime).trim();
            }
            catch (Exception ex) {
                stat=DELIVER_MSG_FORMAT_REPORT_ERROR;
            }
        }
        return stat;

    }

    private void sendHead(CMPPHead head)throws IOException{
        byte[] send = new byte[16];
        System.arraycopy(Tools.int2byte(head.nMsgSize),0,send,0,4);
        System.arraycopy(Tools.int2byte(head.nCommandID),0,send,4,4);
        System.arraycopy(Tools.int2byte(head.nSequenceID),0,send,12,4);
        send(send);
    }
    private void sendPacket(CMPPHead head,byte[] msg)throws IOException{
        byte[] send = new byte[16+msg.length];
        head.nMsgSize=16+msg.length;
        System.arraycopy(Tools.int2byte(head.nMsgSize),0,send,0,4);
        System.arraycopy(Tools.int2byte(head.nCommandID),0,send,4,4);
        System.arraycopy(Tools.int2byte(head.nSequenceID),0,send,12,4);
        System.arraycopy(msg,0,send,16,msg.length);
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
        head.stat=in.readInt();
        head.nSequenceID=in.readInt();
        byte[] resp = null;
        if(head.nMsgSize<=16){
            return null;
        }else{
            resp=new byte[head.nMsgSize-16];
            in.read(resp,0,head.nMsgSize-16);
        }
        return resp;
    }
    private DataInputStream creatInputStream()throws IOException{
        return new DataInputStream(s.getInputStream());
    }
    private DataOutputStream creatOutputStream()throws IOException{
        return new DataOutputStream(s.getOutputStream());
    }
    private void close(){
        socketManger.freeSocket();
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