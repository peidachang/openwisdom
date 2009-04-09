// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPSubmitRespMessage.class

package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.cngp.CNGPConstant;

// Referenced classes of package com.huawei.insa2.comm.cngp.message:
//            CNGPMessage

public class CNGPSubmitRespMessage extends CNGPMessage
{

    public CNGPSubmitRespMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[31];
        if(buf.length != 31)
        {
            throw new IllegalArgumentException(CNGPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, 31);
            return;
        }
    }

    public byte[] getMsgId()
    {
        byte tmpMsgId[] = new byte[10];
        System.arraycopy(buf, 16, tmpMsgId, 0, 10);
        return tmpMsgId;
    }

    public int getCongestionState()
    {
        return buf[30];
    }

    public String toString()
    {
        StringBuffer strBuf = new StringBuffer(200);
        strBuf.append("CNGPSubmitRespMessage: ");
        strBuf.append("PacketLength=".concat(String.valueOf(String.valueOf(getMsgLength()))));
        strBuf.append(",RequestID=".concat(String.valueOf(String.valueOf(getRequestId()))));
        strBuf.append(",Status=".concat(String.valueOf(String.valueOf(getStatus()))));
        strBuf.append(",SequenceId=".concat(String.valueOf(String.valueOf(getSequenceId()))));
        strBuf.append(",MsgId=".concat(String.valueOf(String.valueOf(new String(getMsgId())))));
        strBuf.append(",CongestionState=".concat(String.valueOf(String.valueOf(getCongestionState()))));
        return strBuf.toString();
    }
}
