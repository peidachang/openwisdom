// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPEventAdapter.class

package com.huawei.smproxy;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cngp.CNGPConnection;
import com.huawei.insa2.comm.cngp.CNGPTransaction;
import com.huawei.insa2.comm.cngp.message.*;

// Referenced classes of package com.huawei.smproxy:
//            CNGPSMProxy

class CNGPEventAdapter extends PEventAdapter
{

    private CNGPSMProxy smProxy;
    private CNGPConnection conn;

    public CNGPEventAdapter(CNGPSMProxy smProxy)
    {
        this.smProxy = null;
        conn = null;
        this.smProxy = smProxy;
        conn = smProxy.getConn();
    }

    public void childCreated(PLayer child)
    {
        CNGPTransaction t = (CNGPTransaction)child;
        CNGPMessage msg = t.getResponse();
        CNGPMessage resmsg = null;
        if(msg.getRequestId() == 6)
        {
            resmsg = new CNGPExitRespMessage();
            smProxy.onTerminate();
        } else
        if(msg.getRequestId() == 3)
        {
            CNGPDeliverMessage tmpmes = (CNGPDeliverMessage)msg;
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
