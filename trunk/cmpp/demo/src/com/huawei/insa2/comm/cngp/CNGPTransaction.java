// FrontEnd Plus GUI for JAD
// DeCompiled : CNGPTransaction.class

package com.huawei.insa2.comm.cngp;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cngp.message.CNGPMessage;
import com.huawei.insa2.util.Debug;

// Referenced classes of package com.huawei.insa2.comm.cngp:
//            CNGPConnection, CNGPConstant

public class CNGPTransaction extends PLayer
{

    private CNGPMessage receive;
    private int sequenceId;

    public CNGPTransaction(PLayer connection)
    {
        super(connection);
        sequenceId = id;
    }

    public synchronized void onReceive(PMessage msg)
    {
        receive = (CNGPMessage)msg;
        sequenceId = receive.getSequenceId();
        if(CNGPConstant.debug)
            Debug.dump(receive.toString());
        notifyAll();
    }

    public void send(PMessage message)
        throws PException
    {
        CNGPMessage msg = (CNGPMessage)message;
        msg.setSequenceId(sequenceId);
        parent.send(message);
        if(CNGPConstant.debug)
            Debug.dump(msg.toString());
    }

    public CNGPMessage getResponse()
    {
        return receive;
    }

    public synchronized void waitResponse()
    {
        if(receive == null)
            try
            {
                wait(((CNGPConnection)parent).getTransactionTimeout());
            }
            catch(InterruptedException interruptedexception) { }
    }
}
