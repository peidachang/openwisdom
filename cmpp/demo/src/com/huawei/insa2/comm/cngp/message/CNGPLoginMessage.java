// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPLoginMessage.class

package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.SecurityTools;
import com.huawei.insa2.util.TypeConvert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package com.huawei.insa2.comm.cngp.message:
//            CNGPMessage

public class CNGPLoginMessage extends CNGPMessage
{

    private StringBuffer strBuf;

    public CNGPLoginMessage(String clientId, String shared_Secret, int loginMode, Date timeStamp, int version)
        throws IllegalArgumentException
    {
        if(clientId == null)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.CONNECT_INPUT_ERROR)))).append(":clientId ").append(CNGPConstant.STRING_NULL))));
        if(clientId.length() > 10)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.CONNECT_INPUT_ERROR)))).append(":clientId ").append(CNGPConstant.STRING_LENGTH_GREAT).append("8"))));
        if(shared_Secret.length() > 15)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.CONNECT_INPUT_ERROR)))).append(":shared_Secret ").append(CNGPConstant.STRING_LENGTH_GREAT).append("8"))));
        if(loginMode < 0 || loginMode > 255)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.CONNECT_INPUT_ERROR)))).append(":loginMode ").append(CNGPConstant.INT_SCOPE_ERROR))));
        if(version < 0 || version > 255)
            throw new IllegalArgumentException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(CNGPConstant.CONNECT_INPUT_ERROR)))).append(":version ").append(CNGPConstant.INT_SCOPE_ERROR))));
        int len = 48;
        buf = new byte[len];
        setMsgLength(len);
        setRequestId(1);
        System.arraycopy(clientId.getBytes(), 0, buf, 16, clientId.length());
        if(shared_Secret != null)
            len = clientId.length() + 17 + shared_Secret.length();
        else
            len = clientId.length() + 17;
        byte tmpbuf[] = new byte[len];
        int tmploc = 0;
        System.arraycopy(clientId.getBytes(), 0, tmpbuf, 0, clientId.length());
        tmploc = clientId.length() + 7;
        if(shared_Secret != null)
        {
            System.arraycopy(shared_Secret.getBytes(), 0, tmpbuf, tmploc, shared_Secret.length());
            tmploc += shared_Secret.length();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String strTimeStamp = dateFormat.format(timeStamp).substring(2);
        System.arraycopy(strTimeStamp.toString().getBytes(), 0, tmpbuf, tmploc, 10);
        SecurityTools.md5(tmpbuf, 0, len, buf, 26);
        buf[42] = (byte)loginMode;
        TypeConvert.int2byte(Integer.parseInt(strTimeStamp), buf, 43);
        buf[47] = (byte)version;
        strBuf = new StringBuffer(300);
        strBuf.append(",clientId=".concat(String.valueOf(String.valueOf(clientId))));
        strBuf.append(",shared_Secret=".concat(String.valueOf(String.valueOf(shared_Secret))));
        strBuf.append(",loginMode=".concat(String.valueOf(String.valueOf(loginMode))));
        strBuf.append(",timeStamp=".concat(String.valueOf(String.valueOf(timeStamp))));
        strBuf.append(",version=".concat(String.valueOf(String.valueOf(version))));
    }

    public String toString()
    {
        StringBuffer outStr = new StringBuffer(300);
        outStr.append("CNGPLoginMessage:");
        outStr.append("PacketLength=".concat(String.valueOf(String.valueOf(buf.length))));
        outStr.append(",RequestID=1");
        outStr.append(",SequenceId=".concat(String.valueOf(String.valueOf(getSequenceId()))));
        if(strBuf != null)
            outStr.append(strBuf.toString());
        return outStr.toString();
    }
}
