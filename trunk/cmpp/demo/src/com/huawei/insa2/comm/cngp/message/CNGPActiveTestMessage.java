// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPActiveTestMessage.class

package com.huawei.insa2.comm.cngp.message;


// Referenced classes of package com.huawei.insa2.comm.cngp.message:
//            CNGPMessage

public class CNGPActiveTestMessage extends CNGPMessage
{

    public CNGPActiveTestMessage()
    {
        int len = 16;
        buf = new byte[len];
        setMsgLength(len);
        setRequestId(4);
    }

    public String toString()
    {
        StringBuffer strBuf = new StringBuffer(100);
        strBuf.append("CNGPActiveTestMessage: ");
        strBuf.append("PacketLength=".concat(String.valueOf(String.valueOf(getMsgLength()))));
        strBuf.append(",RequestID=".concat(String.valueOf(String.valueOf(getRequestId()))));
        strBuf.append(",Status=".concat(String.valueOf(String.valueOf(getStatus()))));
        strBuf.append(",SequenceId=".concat(String.valueOf(String.valueOf(getSequenceId()))));
        return strBuf.toString();
    }
}
