// FrontEnd Plus GUI for JAD
// DeCompiled : CMPPCancelMessage.class

package com.huawei.insa2.comm.cmpp.message;

import com.huawei.insa2.comm.cmpp.CMPPConstant;
import com.huawei.insa2.util.TypeConvert;

// Referenced classes of package com.huawei.insa2.comm.cmpp.message:
//            CMPPMessage

public class CMPPCancelMessage extends CMPPMessage
{

    private String outStr;

    public CMPPCancelMessage(byte msg_Id[])
        throws IllegalArgumentException
    {
        if(msg_Id.length > 8)
        {
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CMPPConstant.CONNECT_INPUT_ERROR)))).append(":msg_Id").append(CMPPConstant.STRING_LENGTH_GREAT).append("8"))));
        } else
        {
            int len = 20;
            buf = new byte[len];
            TypeConvert.int2byte(len, buf, 0);
            TypeConvert.int2byte(7, buf, 4);
            System.arraycopy(msg_Id, 0, buf, 12, msg_Id.length);
            return;
        }
    }

    public String toString()
    {
        String tmpStr = "CMPP_Cancel: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        return tmpStr;
    }

    public int getCommandId()
    {
        return 7;
    }
}
