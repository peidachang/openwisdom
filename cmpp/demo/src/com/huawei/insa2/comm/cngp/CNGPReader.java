// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPReader.class

package com.huawei.insa2.comm.cngp;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PReader;
import com.huawei.insa2.comm.cngp.message.CNGPDeliverMessage;
import com.huawei.insa2.comm.cngp.message.CNGPLoginRespMessage;
import com.huawei.insa2.comm.cngp.message.CNGPSubmitRespMessage;
import com.huawei.insa2.util.TypeConvert;
import java.io.*;

public class CNGPReader extends PReader
{

    protected DataInputStream in;

    public CNGPReader(InputStream is)
    {
        in = new DataInputStream(is);
    }

    public PMessage read()
        throws IOException
    {
        int totalLength = in.readInt();
        int commandId = in.readInt();
        byte tmp[] = new byte[totalLength - 8];
        in.readFully(tmp);
        byte buf[] = new byte[totalLength];
        TypeConvert.int2byte(totalLength, buf, 0);
        TypeConvert.int2byte(commandId, buf, 4);
        System.arraycopy(tmp, 0, buf, 8, totalLength - 8);
        if(commandId == 0x80000001)
            return new CNGPLoginRespMessage(buf);
        if(commandId == 3)
            return new CNGPDeliverMessage(buf);
        if(commandId == 0x80000002)
            return new CNGPSubmitRespMessage(buf);
        else
            return null;
    }
}
