// FrontEnd Plus GUI for JAD
// DeCompiled : CMPPTransaction.class

package com.huawei.insa2.comm.cmpp;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.util.Debug;

// Referenced classes of package com.huawei.insa2.comm.cmpp:
//            CMPPConnection, CMPPConstant

public class CMPPTransaction extends PLayer
{

    private CMPPMessage receive;
    private int sequenceId;

    public CMPPTransaction(PLayer connection)
    {
        super(connection);
        sequenceId = id;
    }

    public synchronized void onReceive(PMessage msg)
    {
        receive = (CMPPMessage)msg;
        sequenceId = receive.getSequenceId();
        if(CMPPConstant.debug)
            Debug.dump(receive.toString());
        notifyAll();
    }

    public void send(PMessage message)
        throws PException
    {
        CMPPMessage mes = (CMPPMessage)message;
        mes.setSequenceId(sequenceId);
        parent.send(message);
        if(CMPPConstant.debug)
            Debug.dump(mes.toString());
    }

    public CMPPMessage getResponse()
    {
        return receive;
    }

    public synchronized void waitResponse()
    {
        if(receive == null)
            try
            {
                wait(((CMPPConnection)parent).getTransactionTimeout());
            }
            catch(InterruptedException interruptedexception) { }
    }
}
