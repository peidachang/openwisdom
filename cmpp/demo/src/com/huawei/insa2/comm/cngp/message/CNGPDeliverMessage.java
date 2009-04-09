// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPDeliverMessage.class

package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.huawei.insa2.comm.cngp.message:
//            CNGPMessage

public class CNGPDeliverMessage extends CNGPMessage
{

    public CNGPDeliverMessage(byte buf[])
        throws IllegalArgumentException
    {
        int len = 90 + (buf[84] & 0xff);
        if(buf.length != len)
        {
            throw new IllegalArgumentException(CNGPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            super.buf = new byte[len];
            System.arraycopy(buf, 0, super.buf, 0, buf.length);
            return;
        }
    }

    public byte[] getMsgId()
    {
        byte msgId[] = new byte[10];
        System.arraycopy(buf, 16, msgId, 0, 10);
        return msgId;
    }

    public int getIsReport()
    {
        return buf[26];
    }

    public int getMsgFormat()
    {
        return buf[27];
    }

    public Date getRecvTime()
    {
        Date date;
        try
        {
            int tmpYear = TypeConvert.byte2int(buf, 27);
            byte tmpbyte[] = new byte[2];
            System.arraycopy(buf, 31, tmpbyte, 0, 2);
            String tmpstr = new String(tmpbyte);
            int tmpMonth = Integer.parseInt(tmpstr);
            System.arraycopy(buf, 33, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            int tmpDay = Integer.parseInt(tmpstr);
            System.arraycopy(buf, 35, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            int tmpHour = Integer.parseInt(tmpstr);
            System.arraycopy(buf, 37, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            int tmpMinute = Integer.parseInt(tmpstr);
            System.arraycopy(buf, 39, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            int tmpSecond = Integer.parseInt(tmpstr);
            Calendar calendar = Calendar.getInstance();
            calendar.set(tmpYear, tmpMonth, tmpDay, tmpHour, tmpMinute, tmpSecond);
            Date date1 = calendar.getTime();
            return date1;
        }
        catch(Exception e)
        {
            date = null;
        }
        return date;
    }

    public String getSrcTermID()
    {
        byte srcTermId[] = new byte[21];
        System.arraycopy(buf, 41, srcTermId, 0, 21);
        return (new String(srcTermId)).trim();
    }

    public String getDestTermID()
    {
        byte destTermId[] = new byte[21];
        System.arraycopy(buf, 62, destTermId, 0, 21);
        return (new String(destTermId)).trim();
    }

    public int getMsgLength()
    {
        return buf[84] & 0xff;
    }

    public String getMsgContent()
    {
        int len = buf[84] & 0xff;
        byte content[] = new byte[len];
        System.arraycopy(buf, 85, content, 0, len);
        return (new String(content)).trim();
    }

    public int getCongestionState()
    {
        int pos = 89 + (buf[84] & 0xff);
        return buf[pos];
    }

    public String toString()
    {
        StringBuffer strBuf = new StringBuffer(600);
        strBuf.append("CNGPDeliverMessage: ");
        strBuf.append("PacketLength=".concat(String.valueOf(String.valueOf(getMsgLength()))));
        strBuf.append(",RequestID=".concat(String.valueOf(String.valueOf(getRequestId()))));
        strBuf.append(",Status=".concat(String.valueOf(String.valueOf(getStatus()))));
        strBuf.append(",Sequence_Id=".concat(String.valueOf(String.valueOf(getSequenceId()))));
        strBuf.append(",MsgID=".concat(String.valueOf(String.valueOf(new String(getMsgId())))));
        strBuf.append(",IsReport=".concat(String.valueOf(String.valueOf(getIsReport()))));
        strBuf.append(",MsgFormat=".concat(String.valueOf(String.valueOf(getMsgFormat()))));
        strBuf.append(",RecvTime=".concat(String.valueOf(String.valueOf(getRecvTime()))));
        strBuf.append(",SrcTermID=".concat(String.valueOf(String.valueOf(getSrcTermID()))));
        strBuf.append(",DestTermID=".concat(String.valueOf(String.valueOf(getDestTermID()))));
        strBuf.append(",MsgLength=".concat(String.valueOf(String.valueOf(getMsgLength()))));
        strBuf.append(",MsgContent=".concat(String.valueOf(String.valueOf(new String(getMsgContent())))));
        strBuf.append(",CongestionState=".concat(String.valueOf(String.valueOf(getCongestionState()))));
        return strBuf.toString();
    }
}
