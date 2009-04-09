package com.hoten.cmpp.message;
import java.util.*;
import com.hoten.cmpp.util.Log;
/**
 * <p>Title:�ύ��Ϣ </p>
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
        sDestTerminalId=msg.sDestTerminalId; //���ܶ��ŵ�MSISDN����
        nMsgLength=msg.nMsgLength;         //��Ϣ���ȣ�nMsgFormatΪ0ʱ<160�ֽڣ�����<=140�ֽڣ�
        sMsgContent=msg.sMsgContent;     //
        picAndRing=msg.picAndRing;
        if(picAndRing!=null){
            nTPudhi=0x40;
            nMsgFormat=4;
        }
    }
    public long nMsgID;             //��Ϣ��ʶ
    public int nPkTotal;           //��ͬMsg_Id����Ϣ����������1��ʼ
    public int nPkNumber;          //��ͬMsg_Id����Ϣ��ţ���1��ʼ
    public int nNeedReply;         //�Ƿ�Ҫ�󷵻�״̬ȷ�ϱ��棺 0������Ҫ 1����Ҫ 2������SMC����
    public int nMsgLevel=1;          //��Ϣ����
    public String sServiceId;      //ҵ������
    public int nFeeUserType;       //�Ʒ��û����ͣ�0����Ŀ���ն�MSISDN�Ʒ� 1����Դ�ն�MSISDN�Ʒ� 2����SP�Ʒ� 3�����ֶ���Ч
    public String sFeeMobile;         //���Ʒ��û����룬��nFeeUserType����
    public int nTPpId=0;             //GSMЭ������
    public int nTPudhi=0;            //GSMЭ������
    public int nMsgFormat;         //��Ϣ��ʽ��0��ASCII�� 3������Ϣд������ 4����������Ϣ 8��UCS2���� 15����GB����
    public String sMsgSrc;         //��Ϣ������Դ
    public String sFeeType;        //�ʷ����01���ԡ��Ʒ��û����롱��� 02����������Ϣ�� 03����������ȡ��Ϣ�� 04����Ϣ�ѷⶥ 05���շ���SPʵ��
    public String sFeeCode;        //�ʷѴ��루�Է�Ϊ��λ��
    public String sValidTime=null;      //�����Ч��
    public String sAtTime=null;         //��ʱ����ʱ��
    public String sSrcId;          //Դ���룬SP�ı䳤�ط���
    public int nDestUsrTl;         //������Ϣ���û�������<100����
    public String sDestTerminalId; //���ܶ��ŵ�MSISDN����
    public int nMsgLength;         //��Ϣ���ȣ�nMsgFormatΪ0ʱ<160�ֽڣ�����<=140�ֽڣ�
    public String sMsgContent;     //��Ϣ����
    public String sReserve;        //����

    private String mobileFile=com.hoten.cmpp.CMPP_InitMessage.getInstance().mobileFile;

    public byte[] picAndRing;//ͼƬ����

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
                Log.printEvent(mobile+"�ֻ��������",mobileFile);
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
                Log.printEvent(mobile+"�ֻ��������",mobileFile);
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