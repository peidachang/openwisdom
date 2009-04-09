// FrontEnd Plus GUI for JAD
// DeCompiled : SMPPEventAdapter.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.smpp.SMPPConnection;
import com.huawei.insa2.comm.smpp.SMPPTransaction;
import com.huawei.insa2.comm.smpp.message.*;

// Referenced classes of package com.huawei.smproxy:
//            SMPPSMProxy

class SMPPEventAdapter extends PEventAdapter
{

    private SMPPSMProxy smProxy;
    private SMPPConnection conn;

    public SMPPEventAdapter(SMPPSMProxy smProxy)
    {
        this.smProxy = null;
        conn = null;
        this.smProxy = smProxy;
        conn = smProxy.getConn();
    }

    public void childCreated(PLayer child)
    {
        SMPPTransaction t = (SMPPTransaction)child;
        SMPPMessage msg = t.getResponse();
        SMPPMessage resmsg = null;
        if(msg.getCommandId() == 6)
        {
            resmsg = new SMPPUnbindRespMessage();
            smProxy.onTerminate();
        } else
        if(msg.getCommandId() == 5)
        {
            SMPPDeliverMessage tmpmes = (SMPPDeliverMessage)msg;
            resmsg = smProxy.onDeliver(tmpmes);
        } else
        {
            t.close();
        }
        if(resmsg != null)
        {
            try
            {
                t.send(resmsg);
            }
            catch(PException e)
            {
                e.printStackTrace();
            }
            t.close();
        }
    }
}
