// FrontEnd Plus GUI for JAD
// DeCompiled : SGIPBindRepMessage.class

package com.huawei.insa2.comm.sgip.message;

import com.huawei.insa2.comm.sgip.SGIPConstant;
import com.huawei.insa2.util.TypeConvert;

// Referenced classes of package com.huawei.insa2.comm.sgip.message:
//            SGIPMessage

public class SGIPBindRepMessage extends SGIPMessage
{

    private String outStr;

    public SGIPBindRepMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[21];
        if(buf.length != 21)
        {
            throw new IllegalArgumentException(SGIPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, buf.length);
            src_node_Id = TypeConvert.byte2int(super.buf, 0);
            time_Stamp = TypeConvert.byte2int(super.buf, 4);
            sequence_Id = TypeConvert.byte2int(super.buf, 8);
            return;
        }
    }

    public SGIPBindRepMessage(int result)
        throws IllegalArgumentException
    {
        if(result < 0 || result > 255)
        {
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(SGIPConstant.DELIVER_REPINPUT_ERROR)))).append(":result").append(SGIPConstant.INT_SCOPE_ERROR))));
        } else
        {
            int len = 29;
            buf = new byte[len];
            TypeConvert.int2byte(len, buf, 0);
            TypeConvert.int2byte(0x80000001, buf, 4);
            buf[20] = (byte)result;
            outStr = ",result=".concat(String.valueOf(String.valueOf(result)));
            return;
        }
    }

    public int getResult()
    {
        return buf[12];
    }

    public String toString()
    {
        String tmpStr = "SGIP_BIND_REP: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",Result=").append(getResult())));
        return tmpStr;
    }

    public int getCommandId()
    {
        return 0x80000001;
    }
}
