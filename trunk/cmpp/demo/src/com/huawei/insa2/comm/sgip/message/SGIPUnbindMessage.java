// FrontEnd Plus GUI for JAD
// DeCompiled : SGIPUnbindMessage.class

package com.huawei.insa2.comm.sgip.message;

import com.huawei.insa2.comm.sgip.SGIPConstant;
import com.huawei.insa2.util.TypeConvert;

// Referenced classes of package com.huawei.insa2.comm.sgip.message:
//            SGIPMessage

public class SGIPUnbindMessage extends SGIPMessage
{

    public SGIPUnbindMessage()
    {
        int len = 20;
        buf = new byte[len];
        TypeConvert.int2byte(len, buf, 0);
        TypeConvert.int2byte(2, buf, 4);
    }

    public SGIPUnbindMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[12];
        if(buf.length != 12)
        {
            throw new IllegalArgumentException(SGIPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, 12);
            src_node_Id = TypeConvert.byte2int(super.buf, 0);
            time_Stamp = TypeConvert.byte2int(super.buf, 4);
            sequence_Id = TypeConvert.byte2int(super.buf, 8);
            return;
        }
    }

    public String toString()
    {
        String tmpStr = "SGIP_UNBIND: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        return tmpStr;
    }

    public int getCommandId()
    {
        return 2;
    }
}
