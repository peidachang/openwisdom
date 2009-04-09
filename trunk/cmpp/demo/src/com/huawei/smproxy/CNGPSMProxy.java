// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPSMProxy.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.PLayer;
import com.huawei.insa2.comm.PSocketConnection;
import com.huawei.insa2.comm.cngp.CNGPConnection;
import com.huawei.insa2.comm.cngp.CNGPTransaction;
import com.huawei.insa2.comm.cngp.message.*;
import com.huawei.insa2.util.Args;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package com.huawei.smproxy:
//            CNGPEventAdapter

public class CNGPSMProxy
{

    private CNGPConnection conn;

    public CNGPSMProxy(Map args)
    {
        this(new Args(args));
    }

    public CNGPSMProxy(Args args)
    {
        conn = new CNGPConnection(args);
        conn.addEventListener(new CNGPEventAdapter(this));
        conn.waitAvailable();
        if(!conn.available())
            throw new IllegalStateException(conn.getError());
        else
            return;
    }

    public CNGPMessage send(CNGPMessage message)
        throws IOException
    {
        if(message == null)
            return null;
        CNGPTransaction t = (CNGPTransaction)conn.createChild();
        try
        {
            t.send(message);
            t.waitResponse();
            CNGPMessage rsp = t.getResponse();
            CNGPMessage cngpmessage = rsp;
            return cngpmessage;
        }
        finally
        {
            t.close();
        }
    }

    public void onTerminate()
    {
    }

    public CNGPMessage onDeliver(CNGPDeliverMessage msg)
    {
        return new CNGPDeliverRespMessage(msg.getMsgId(), 0);
    }

    public void close()
    {
        conn.close();
    }

    public CNGPConnection getConn()
    {
        return conn;
    }

    public String getConnState()
    {
        return conn.getError();
    }
}
