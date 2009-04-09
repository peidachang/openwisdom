// FrontEnd Plus GUI for JAD
// DeCompiled : SGIPBindMessage.class

package com.huawei.insa2.comm.sgip.message;

import com.huawei.insa2.comm.sgip.SGIPConstant;
import com.huawei.insa2.util.TypeConvert;

// Referenced classes of package com.huawei.insa2.comm.sgip.message:
//            SGIPMessage

public class SGIPBindMessage extends SGIPMessage
{

    private String outStr;

    public SGIPBindMessage(int login_Type, String login_Name, String login_Password)
        throws IllegalArgumentException
    {
        if(login_Name == null)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(SGIPConstant.CONNECT_INPUT_ERROR)))).append(":login_Name").append(SGIPConstant.STRING_NULL))));
        if(login_Name.length() > 16)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(SGIPConstant.CONNECT_INPUT_ERROR)))).append(":login_Name").append(SGIPConstant.STRING_LENGTH_GREAT).append("16"))));
        if(login_Password == null)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(SGIPConstant.CONNECT_INPUT_ERROR)))).append(":login_Password").append(SGIPConstant.STRING_NULL))));
        if(login_Password.length() > 16)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(SGIPConstant.CONNECT_INPUT_ERROR)))).append(":login_Password").append(SGIPConstant.STRING_LENGTH_GREAT).append("16"))));
        if(login_Type < 0 || login_Type > 255)
        {
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(SGIPConstant.CONNECT_INPUT_ERROR)))).append(":login_Type").append(SGIPConstant.INT_SCOPE_ERROR))));
        } else
        {
            int len = 61;
            buf = new byte[len];
            TypeConvert.int2byte(len, buf, 0);
            TypeConvert.int2byte(1, buf, 4);
            buf[20] = (byte)login_Type;
            System.arraycopy(login_Name.getBytes(), 0, buf, 21, login_Name.length());
            System.arraycopy(login_Password.getBytes(), 0, buf, 37, login_Password.length());
            outStr = ",login_Type=".concat(String.valueOf(String.valueOf(login_Type)));
            outStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(outStr)))).append(",login_Name=").append(login_Name)));
            outStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(outStr)))).append(",login_Password=").append(login_Password)));
            return;
        }
    }

    public SGIPBindMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[53];
        if(buf.length != 53)
        {
            throw new IllegalArgumentException(SGIPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, 53);
            src_node_Id = TypeConvert.byte2int(super.buf, 0);
            time_Stamp = TypeConvert.byte2int(super.buf, 4);
            sequence_Id = TypeConvert.byte2int(super.buf, 8);
            return;
        }
    }

    public int getLoginType()
    {
        int tmpId = buf[12];
        return tmpId;
    }

    public String getLoginName()
    {
        byte tmpId[] = new byte[16];
        System.arraycopy(buf, 13, tmpId, 0, 16);
        String tmpStr = (new String(tmpId)).trim();
        if(tmpStr.indexOf(0) >= 0)
            return tmpStr.substring(0, tmpStr.indexOf(0));
        else
            return tmpStr;
    }

    public String getLoginPass()
    {
        byte tmpId[] = new byte[16];
        System.arraycopy(buf, 29, tmpId, 0, 16);
        String tmpStr = (new String(tmpId)).trim();
        if(tmpStr.indexOf(0) >= 0)
            return tmpStr.substring(0, tmpStr.indexOf(0));
        else
            return tmpStr;
    }

    public String toString()
    {
        String tmpStr = "SGIP_BIND: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        tmpStr = String.valueOf(tmpStr) + String.valueOf(outStr);
        return tmpStr;
    }

    public int getCommandId()
    {
        return 1;
    }
}
