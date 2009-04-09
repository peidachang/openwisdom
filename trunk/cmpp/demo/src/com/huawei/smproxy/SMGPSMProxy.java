// FrontEnd Plus GUI for JAD
// DeCompiled : SMGPSMProxy.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.PLayer;
import com.huawei.insa2.comm.PSocketConnection;
import com.huawei.insa2.comm.smgp.SMGPConnection;
import com.huawei.insa2.comm.smgp.SMGPTransaction;
import com.huawei.insa2.comm.smgp.message.*;
import com.huawei.insa2.util.Args;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package com.huawei.smproxy:
//            SMGPEventAdapter

public class SMGPSMProxy
{

    private SMGPConnection conn;

    public SMGPSMProxy(Map args)
    {
        this(new Args(args));
    }

    public SMGPSMProxy(Args args)
    {
        conn = new SMGPConnection(args);
        conn.addEventListener(new SMGPEventAdapter(this));
        conn.waitAvailable();
        if(!conn.available())
            throw new IllegalStateException(conn.getError());
        else
            return;
    }

    public SMGPMessage send(SMGPMessage message)
        throws IOException
    {
        if(message == null)
            return null;
        SMGPTransaction t = (SMGPTransaction)conn.createChild();
        try
        {
            t.send(message);
            t.waitResponse();
            SMGPMessage rsp = t.getResponse();
            SMGPMessage smgpmessage = rsp;
            return smgpmessage;
        }
        finally
        {
            t.close();
        }
    }

    public void onTerminate()
    {
    }

    public SMGPMessage onDeliver(SMGPDeliverMessage msg)
    {
        return new SMGPDeliverRespMessage(msg.getMsgId(), 0);
    }

    public void close()
    {
        conn.close();
    }

    public SMGPConnection getConn()
    {
        return conn;
    }

    public String getConnState()
    {
        return conn.getError();
    }
}
