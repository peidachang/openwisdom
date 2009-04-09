// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPWriter.class

package com.huawei.insa2.comm.cngp;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PWriter;
import com.huawei.insa2.comm.cngp.message.CNGPMessage;
import java.io.IOException;
import java.io.OutputStream;

public class CNGPWriter extends PWriter
{

    protected OutputStream out;

    public CNGPWriter(OutputStream out)
    {
        this.out = out;
    }

    public void write(PMessage message)
        throws IOException
    {
        out.write(((CNGPMessage)message).getBytes());
    }

    public void writeHeartbeat()
        throws IOException
    {
    }
}
