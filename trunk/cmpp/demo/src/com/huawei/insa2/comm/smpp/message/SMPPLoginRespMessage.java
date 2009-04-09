// FrontEnd Plus GUI for JAD
// DeCompiled : SMPPLoginRespMessage.class

package com.huawei.insa2.comm.smpp.message;

import com.huawei.insa2.comm.smpp.SMPPConstant;

// Referenced classes of package com.huawei.insa2.comm.smpp.message:
//            SMPPMessage

public class SMPPLoginRespMessage extends SMPPMessage
{

    public SMPPLoginRespMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[buf.length];
        if(buf.length < 17 || buf.length > 32)
        {
            throw new IllegalArgumentException(SMPPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, buf.length);
            return;
        }
    }

    public String getSystemId()
    {
        byte tmpbuf[] = new byte[buf.length - 16 - 1];
        System.arraycopy(buf, 16, tmpbuf, 0, buf.length - 17);
        return new String(tmpbuf);
    }

    public String toString()
    {
        StringBuffer strBuf = new StringBuffer(300);
        strBuf.append("SMPPLoginRespMessage: ");
        strBuf.append("PacketLength=".concat(String.valueOf(String.valueOf(buf.length))));
        strBuf.append(",CommandID=".concat(String.valueOf(String.valueOf(getCommandId()))));
        strBuf.append(",Status=".concat(String.valueOf(String.valueOf(getStatus()))));
        strBuf.append(",SequenceId=".concat(String.valueOf(String.valueOf(getSequenceId()))));
        strBuf.append(",SystemId=".concat(String.valueOf(String.valueOf(getSystemId()))));
        return strBuf.toString();
    }
}
