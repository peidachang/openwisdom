// FrontEnd Plus GUI for JAD
// DeCompiled : CMPPCancelRepMessage.class

package com.huawei.insa2.comm.cmpp.message;

import com.huawei.insa2.comm.cmpp.CMPPConstant;
import com.huawei.insa2.util.TypeConvert;

// Referenced classes of package com.huawei.insa2.comm.cmpp.message:
//            CMPPMessage

public class CMPPCancelRepMessage extends CMPPMessage
{

    public CMPPCancelRepMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[5];
        if(buf.length != 5)
        {
            throw new IllegalArgumentException(CMPPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, 5);
            sequence_Id = TypeConvert.byte2int(super.buf, 0);
            return;
        }
    }

    public int getSuccessId()
    {
        return buf[4];
    }

    public String toString()
    {
        String tmpStr = "CMPP_Cancel_REP: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",SuccessId=").append(String.valueOf(buf[4]))));
        return tmpStr;
    }

    public int getCommandId()
    {
        return 0x80000007;
    }
}
