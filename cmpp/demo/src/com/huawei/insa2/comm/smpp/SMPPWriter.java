// FrontEnd Plus GUI for JAD
// DeCompiled : SMPPWriter.class

package com.huawei.insa2.comm.smpp;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PWriter;
import com.huawei.insa2.comm.smpp.message.SMPPMessage;
import java.io.IOException;
import java.io.OutputStream;

public class SMPPWriter extends PWriter
{

    protected OutputStream out;

    public SMPPWriter(OutputStream out)
    {
        this.out = out;
    }

    public void write(PMessage message)
        throws IOException
    {
        SMPPMessage msg = (SMPPMessage)message;
        out.write(msg.getBytes());
    }

    public void writeHeartbeat()
        throws IOException
    {
    }
}
