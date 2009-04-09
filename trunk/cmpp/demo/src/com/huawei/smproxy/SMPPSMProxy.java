// FrontEnd Plus GUI for JAD
// DeCompiled : SMPPSMProxy.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.PLayer;
import com.huawei.insa2.comm.PSocketConnection;
import com.huawei.insa2.comm.smpp.SMPPConnection;
import com.huawei.insa2.comm.smpp.SMPPTransaction;
import com.huawei.insa2.comm.smpp.message.*;
import com.huawei.insa2.util.Args;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package com.huawei.smproxy:
//            SMPPEventAdapter

public class SMPPSMProxy
{

    private SMPPConnection conn;

    public SMPPSMProxy(Map args)
    {
        this(new Args(args));
    }

    public SMPPSMProxy(Args args)
    {
        conn = new SMPPConnection(args);
        conn.addEventListener(new SMPPEventAdapter(this));
        conn.waitAvailable();
        if(!conn.available())
            throw new IllegalStateException(conn.getError());
        else
            return;
    }

    public SMPPMessage send(SMPPMessage message)
        throws IOException
    {
        if(message == null)
            return null;
        SMPPTransaction t = (SMPPTransaction)conn.createChild();
        try
        {
            t.send(message);
            t.waitResponse();
            SMPPMessage rsp = t.getResponse();
            SMPPMessage smppmessage = rsp;
            return smppmessage;
        }
        finally
        {
            t.close();
        }
    }

    public void onTerminate()
    {
    }

    public SMPPMessage onDeliver(SMPPDeliverMessage msg)
    {
        return new SMPPDeliverRespMessage(0);
    }

    public void close()
    {
        conn.close();
    }

    public SMPPConnection getConn()
    {
        return conn;
    }

    public String getConnState()
    {
        return conn.getError();
    }
}
