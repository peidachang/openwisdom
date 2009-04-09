// FrontEnd Plus GUI for JAD
// DeCompiled : SMPPTransaction.class

package com.huawei.insa2.comm.smpp;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.smpp.message.SMPPMessage;
import com.huawei.insa2.util.Debug;

// Referenced classes of package com.huawei.insa2.comm.smpp:
//            SMPPConnection, SMPPConstant

public class SMPPTransaction extends PLayer
{

    private SMPPMessage receive;
    private int sequenceId;

    public SMPPTransaction(PLayer connection)
    {
        super(connection);
        sequenceId = id;
    }

    public synchronized void onReceive(PMessage msg)
    {
        receive = (SMPPMessage)msg;
        sequenceId = receive.getSequenceId();
        if(SMPPConstant.debug)
            Debug.dump(receive.toString());
        notifyAll();
    }

    public void send(PMessage message)
        throws PException
    {
        SMPPMessage mes = (SMPPMessage)message;
        mes.setSequenceId(sequenceId);
        parent.send(message);
        if(SMPPConstant.debug)
            Debug.dump(mes.toString());
    }

    public SMPPMessage getResponse()
    {
        return receive;
    }

    public synchronized void waitResponse()
    {
        if(receive == null)
            try
            {
                wait(((SMPPConnection)parent).getTransactionTimeout());
            }
            catch(InterruptedException interruptedexception) { }
    }
}
