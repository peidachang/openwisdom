// FrontEnd Plus GUI for JAD
// DeCompiled : CMPPConnectMessage.class

package com.huawei.insa2.comm.cmpp.message;

import com.huawei.insa2.comm.cmpp.CMPPConstant;
import com.huawei.insa2.util.SecurityTools;
import com.huawei.insa2.util.TypeConvert;
import java.util.Date;

// Referenced classes of package com.huawei.insa2.comm.cmpp.message:
//            CMPPMessage

public class CMPPConnectMessage extends CMPPMessage
{

    private String outStr;

    public CMPPConnectMessage(String source_Addr, int version, String shared_Secret, Date timestamp)
        throws IllegalArgumentException
    {
        if(source_Addr == null)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CMPPConstant.CONNECT_INPUT_ERROR)))).append(":source_Addr").append(CMPPConstant.STRING_NULL))));
        if(source_Addr.length() > 6)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CMPPConstant.CONNECT_INPUT_ERROR)))).append(":source_Addr").append(CMPPConstant.STRING_LENGTH_GREAT).append("6"))));
        if(version < 0 || version > 255)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CMPPConstant.CONNECT_INPUT_ERROR)))).append(":version").append(CMPPConstant.INT_SCOPE_ERROR))));
        int len = 39;
        buf = new byte[len];
        TypeConvert.int2byte(len, buf, 0);
        TypeConvert.int2byte(1, buf, 4);
        System.arraycopy(source_Addr.getBytes(), 0, buf, 12, source_Addr.length());
        if(shared_Secret != null)
            len = source_Addr.length() + 19 + shared_Secret.length();
        else
            len = source_Addr.length() + 19;
        byte tmpbuf[] = new byte[len];
        int tmploc = 0;
        System.arraycopy(source_Addr.getBytes(), 0, tmpbuf, 0, source_Addr.length());
        tmploc = source_Addr.length() + 9;
        if(shared_Secret != null)
        {
            System.arraycopy(shared_Secret.getBytes(), 0, tmpbuf, tmploc, shared_Secret.length());
            tmploc += shared_Secret.length();
        }
        String tmptime = "0008080808";
        System.arraycopy(tmptime.getBytes(), 0, tmpbuf, tmploc, 10);
        SecurityTools.md5(tmpbuf, 0, len, buf, 18);
        buf[34] = (byte)version;
        TypeConvert.int2byte(0x7b4da8, buf, 35);
        outStr = ",source_Addr=".concat(String.valueOf(String.valueOf(source_Addr)));
        outStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(outStr)))).append(",version=").append(version)));
        outStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(outStr)))).append(",shared_Secret=").append(shared_Secret)));
        outStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(outStr)))).append(",timeStamp=").append(tmptime)));
    }

    public String toString()
    {
        String tmpStr = "CMPP_Connect: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        tmpStr = String.valueOf(tmpStr) + String.valueOf(outStr);
        return tmpStr;
    }

    public int getCommandId()
    {
        return 1;
    }
}
