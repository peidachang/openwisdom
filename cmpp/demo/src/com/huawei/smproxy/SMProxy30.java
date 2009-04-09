// FrontEnd Plus GUI for JAD
// DeCompiled : SMProxy30.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.PLayer;
import com.huawei.insa2.comm.PSocketConnection;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp30.CMPP30Connection;
import com.huawei.insa2.comm.cmpp30.CMPP30Transaction;
import com.huawei.insa2.comm.cmpp30.message.CMPP30DeliverMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30DeliverRepMessage;
import com.huawei.insa2.util.Args;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package com.huawei.smproxy:
//            CMPP30EventAdapter

public class SMProxy30
{

    private CMPP30Connection conn;

    public SMProxy30(Map args)
    {
        this(new Args(args));
    }

    public SMProxy30(Args args)
    {
        conn = new CMPP30Connection(args);
        System.out.println("conn="+conn);
        conn.addEventListener(new CMPP30EventAdapter(this));
        conn.waitAvailable();
        if(!conn.available())
            throw new IllegalStateException(conn.getError());
        else
            return;
    }

    public CMPPMessage send(CMPPMessage message)
        throws IOException
    {
        if(message == null)
            return null;
        CMPP30Transaction t = (CMPP30Transaction)conn.createChild();
        try
        {
            t.send(message);
            t.waitResponse();
            CMPPMessage rsp = t.getResponse();
            CMPPMessage cmppmessage = rsp;
            return cmppmessage;
        }
        finally
        {
            t.close();
        }
    }

    public void onTerminate()
    {
    }

    public CMPPMessage onDeliver(CMPP30DeliverMessage msg)
    {
        return new CMPP30DeliverRepMessage(msg.getMsgId(), 0);
    }

    public void close()
    {
        conn.close();
    }

    public CMPP30Connection getConn()
    {
        return conn;
    }

    public String getConnState()
    {
        return conn.getError();
    }
}
