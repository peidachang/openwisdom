// FrontEnd Plus GUI for JAD
// DeCompiled : SGIPTransaction.class

package com.huawei.insa2.comm.sgip;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.sgip.message.SGIPMessage;
import com.huawei.insa2.util.Debug;

// Referenced classes of package com.huawei.insa2.comm.sgip:
//            SGIPConnection, SGIPConstant

public class SGIPTransaction extends PLayer
{

    private SGIPMessage receive;
    private int src_nodeid;
    private int timestamp;
    private int sequenceId;

    public SGIPTransaction(PLayer connection)
    {
        super(connection);
        sequenceId = id;
    }

    public void setSPNumber(int spNumber)
    {
        src_nodeid = spNumber;
    }

    public void setTimestamp(int timestamp)
    {
        this.timestamp = timestamp;
    }

    public synchronized void onReceive(PMessage msg)
    {
        receive = (SGIPMessage)msg;
        src_nodeid = receive.getSrcNodeId();
        timestamp = receive.getTimeStamp();
        sequenceId = receive.getSequenceId();
        if(SGIPConstant.debug)
            Debug.dump(receive.toString());
        notifyAll();
    }

    public void send(PMessage message)
        throws PException
    {
        SGIPMessage mes = (SGIPMessage)message;
        mes.setSrcNodeId(src_nodeid);
        mes.setTimeStamp(timestamp);
        mes.setSequenceId(sequenceId);
        parent.send(message);
        if(SGIPConstant.debug)
            Debug.dump(mes.toString());
    }

    public SGIPMessage getResponse()
    {
        return receive;
    }

    public boolean isChildOf(PLayer connection)
    {
        if(parent == null)
            return false;
        else
            return connection == parent;
    }

    public PLayer getParent()
    {
        return parent;
    }

    public synchronized void waitResponse()
    {
        if(receive == null)
            try
            {
                wait(((SGIPConnection)parent).getTransactionTimeout());
            }
            catch(InterruptedException interruptedexception) { }
    }
}
