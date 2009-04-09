// FrontEnd Plus GUI for JAD
// DeCompiled : CMPPReader.class

package com.huawei.insa2.comm.cmpp;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PReader;
import com.huawei.insa2.comm.cmpp.message.CMPPActiveMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPActiveRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPCancelRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPConnectRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPDeliverMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPQueryRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPTerminateMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPTerminateRepMessage;
import java.io.*;

public class CMPPReader extends PReader
{

    protected DataInputStream in;

    public CMPPReader(InputStream is)
    {
        in = new DataInputStream(is);
    }

    public PMessage read()
        throws IOException
    {
        int total_Length = in.readInt();
        int command_Id = in.readInt();
        byte buf[] = new byte[total_Length - 8];
        in.readFully(buf);
        if(command_Id == 0x80000001)
            return new CMPPConnectRepMessage(buf);
        if(command_Id == 5)
            return new CMPPDeliverMessage(buf);
        if(command_Id == 0x80000004)
            return new CMPPSubmitRepMessage(buf);
        if(command_Id == 0x80000006)
            return new CMPPQueryRepMessage(buf);
        if(command_Id == 0x80000007)
            return new CMPPCancelRepMessage(buf);
        if(command_Id == 0x80000008)
            return new CMPPActiveRepMessage(buf);
        if(command_Id == 8)
            return new CMPPActiveMessage(buf);
        if(command_Id == 2)
            return new CMPPTerminateMessage(buf);
        if(command_Id == 0x80000002)
            return new CMPPTerminateRepMessage(buf);
        else
            return null;
    }
}
