// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPMessage.class

package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.util.TypeConvert;

public abstract class CNGPMessage extends PMessage
    implements Cloneable
{

    protected byte buf[];

    public CNGPMessage()
    {
    }

    public Object clone()
    {
        try
        {
            CNGPMessage m = (CNGPMessage)super.clone();
            m.buf = (byte[])buf.clone();
            CNGPMessage cngpmessage = m;
            return cngpmessage;
        }
        catch(CloneNotSupportedException ex)
        {
            ex.printStackTrace();
        }
        Object obj = null;
        return obj;
    }

    public abstract String toString();

    public int getMsgLength()
    {
        int msgLength = TypeConvert.byte2int(buf, 0);
        return msgLength;
    }

    public void setMsgLength(int msgLength)
    {
        TypeConvert.int2byte(msgLength, buf, 0);
    }

    public int getRequestId()
    {
        int requestId = TypeConvert.byte2int(buf, 4);
        return requestId;
    }

    public void setRequestId(int requestId)
    {
        TypeConvert.int2byte(requestId, buf, 4);
    }

    public int getStatus()
    {
        int status = TypeConvert.byte2int(buf, 8);
        return status;
    }

    public void setStatus(int status)
    {
        TypeConvert.int2byte(status, buf, 8);
    }

    public int getSequenceId()
    {
        int sequenceId = TypeConvert.byte2int(buf, 12);
        return sequenceId;
    }

    public void setSequenceId(int sequenceId)
    {
        TypeConvert.int2byte(sequenceId, buf, 12);
    }

    public byte[] getBytes()
    {
        return buf;
    }
}
