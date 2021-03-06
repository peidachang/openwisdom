// FrontEnd Plus GUI for JAD
// DeCompiled : SMGPTransaction.class

package com.huawei.insa2.comm.smgp;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.smgp.message.SMGPMessage;
import com.huawei.insa2.util.Debug;

// Referenced classes of package com.huawei.insa2.comm.smgp:
//            SMGPConnection, SMGPConstant

public class SMGPTransaction extends PLayer
{

    private SMGPMessage receive;
    private int sequenceId;

    public SMGPTransaction(PLayer connection)
    {
        super(connection);
        sequenceId = id;
    }

    public synchronized void onReceive(PMessage msg)
    {
        receive = (SMGPMessage)msg;
        sequenceId = receive.getSequenceId();
        if(SMGPConstant.debug)
            Debug.dump(receive.toString());
        notifyAll();
    }

    public void send(PMessage message)
        throws PException
    {
        SMGPMessage mes = (SMGPMessage)message;
        mes.setSequenceId(sequenceId);
        parent.send(message);
        if(SMGPConstant.debug)
            Debug.dump(mes.toString());
    }

    public SMGPMessage getResponse()
    {
        return receive;
    }

    public synchronized void waitResponse()
    {
        if(receive == null)
            try
            {
                wait(((SMGPConnection)parent).getTransactionTimeout());
            }
            catch(InterruptedException interruptedexception) { }
    }
}
