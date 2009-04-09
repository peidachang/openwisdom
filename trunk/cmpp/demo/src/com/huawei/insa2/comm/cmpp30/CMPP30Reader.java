// FrontEnd Plus GUI for JAD
// DeCompiled : CMPP30Reader.class

package com.huawei.insa2.comm.cmpp30;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PReader;
import com.huawei.insa2.comm.cmpp.message.*;
import com.huawei.insa2.comm.cmpp30.message.CMPP30ConnectRepMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30DeliverMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30SubmitRepMessage;
import java.io.*;

public class CMPP30Reader extends PReader
{

    protected DataInputStream in;

    public CMPP30Reader(InputStream is)
    {
        in = new DataInputStream(is);
    }

    public PMessage read()
        throws IOException
    {
        int total_Length = in.readInt();
        int command_Id = in.readInt();
        System.out.println("COMMAND ID="+command_Id);
        byte buf[] = new byte[total_Length - 8];
        in.readFully(buf);
        if(command_Id == 0x80000001)
        	{
        	System.out.println("connection command");
        	 return new CMPP30ConnectRepMessage(buf);
        	}
           
        if(command_Id == 5)
            return new CMPP30DeliverMessage(buf);
        if(command_Id == 0x80000004)
            return new CMPP30SubmitRepMessage(buf);
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
