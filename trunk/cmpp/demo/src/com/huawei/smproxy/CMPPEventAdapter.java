// FrontEnd Plus GUI for JAD
// DeCompiled : CMPPEventAdapter.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cmpp.CMPPConnection;
import com.huawei.insa2.comm.cmpp.CMPPTransaction;
import com.huawei.insa2.comm.cmpp.message.*;

// Referenced classes of package com.huawei.smproxy:
//            SMProxy

class CMPPEventAdapter extends PEventAdapter
{

    private SMProxy smProxy;
    private CMPPConnection conn;

    public CMPPEventAdapter(SMProxy smProxy)
    {
        this.smProxy = null;
        conn = null;
        this.smProxy = smProxy;
        conn = smProxy.getConn();
    }

    public void childCreated(PLayer child)
    {
        CMPPTransaction t = (CMPPTransaction)child;
        CMPPMessage msg = t.getResponse();
        CMPPMessage resmsg = null;
        if(msg.getCommandId() == 2)
        {
            resmsg = new CMPPTerminateRepMessage();
            smProxy.onTerminate();
        } else
        if(msg.getCommandId() == 8)
            resmsg = new CMPPActiveRepMessage(0);
        else
        if(msg.getCommandId() == 5)
        {
            CMPPDeliverMessage tmpmes = (CMPPDeliverMessage)msg;
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
        if(msg.getCommandId() == 2)
            conn.close();
    }
}
