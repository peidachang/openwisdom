// FrontEnd Plus GUI for JAD
// DeCompiled : SGIPWriter.class

package com.huawei.insa2.comm.sgip;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.PWriter;
import com.huawei.insa2.comm.sgip.message.SGIPMessage;
import java.io.IOException;
import java.io.OutputStream;

public class SGIPWriter extends PWriter
{

    protected OutputStream out;

    public SGIPWriter(OutputStream out)
    {
        this.out = out;
    }

    public void write(PMessage message)
        throws IOException
    {
        SGIPMessage msg = (SGIPMessage)message;
        out.write(msg.getBytes());
    }

    public void writeHeartbeat()
        throws IOException
    {
    }
}
