// FrontEnd Plus GUI for JAD
// DeCompiled : SMGPWriter.class

package com.huawei.insa2.comm.smgp;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PWriter;
import com.huawei.insa2.comm.smgp.message.SMGPMessage;
import java.io.IOException;
import java.io.OutputStream;

public class SMGPWriter extends PWriter
{

    protected OutputStream out;

    public SMGPWriter(OutputStream out)
    {
        this.out = out;
    }

    public void write(PMessage message)
        throws IOException
    {
        SMGPMessage msg = (SMGPMessage)message;
        out.write(msg.getBytes());
    }

    public void writeHeartbeat()
        throws IOException
    {
    }
}
