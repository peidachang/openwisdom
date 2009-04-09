// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPDeliverRespMessage.class

package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.cngp.CNGPConstant;

// Referenced classes of package com.huawei.insa2.comm.cngp.message:
//            CNGPMessage

public class CNGPDeliverRespMessage extends CNGPMessage
{

    private StringBuffer strBuf;

    public CNGPDeliverRespMessage(byte msgId[], int congestionState)
        throws IllegalArgumentException
    {
        if(msgId.length > 10)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.DELIVER_REPINPUT_ERROR)))).append(":msg_Id").append(CNGPConstant.STRING_LENGTH_GREAT).append("10"))));
        if(congestionState < 0 || congestionState > 255)
        {
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.DELIVER_REPINPUT_ERROR)))).append(":congestionState ").append(CNGPConstant.INT_SCOPE_ERROR))));
        } else
        {
            int len = 31;
            buf = new byte[31];
            setMsgLength(len);
            setRequestId(0x80000003);
            System.arraycopy(msgId, 0, buf, 16, msgId.length);
            buf[30] = (byte)congestionState;
            strBuf = new StringBuffer(100);
            strBuf.append(",MsgId=".concat(String.valueOf(String.valueOf(msgId))));
            strBuf.append(",congestionState=".concat(String.valueOf(String.valueOf(congestionState))));
            return;
        }
    }

    public String toString()
    {
        StringBuffer outStr = new StringBuffer(100);
        outStr.append("CNGPDeliverRespMessage:");
        strBuf.append("PacketLength=".concat(String.valueOf(String.valueOf(getMsgLength()))));
        strBuf.append(",RequestID=".concat(String.valueOf(String.valueOf(getRequestId()))));
        strBuf.append(",Status=".concat(String.valueOf(String.valueOf(getStatus()))));
        outStr.append(",SequenceId=".concat(String.valueOf(String.valueOf(getSequenceId()))));
        if(strBuf != null)
            outStr.append(strBuf.toString());
        return outStr.toString();
    }
}
