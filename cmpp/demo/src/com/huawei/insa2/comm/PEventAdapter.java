// FrontEnd Plus GUI for JAD
// DeCompiled : PEventAdapter.class

package com.huawei.insa2.comm;


// Referenced classes of package com.huawei.insa2.comm:
//            PLayer, PMessage, PEventListener, PEvent

public class PEventAdapter
    implements PEventListener
{

    public PEventAdapter()
    {
    }

    public void handle(PEvent e)
    {
        switch(e.getType())
        {
        case 2: // '\002'
            childCreated((PLayer)e.getData());
            break;

        case 1: // '\001'
            created();
            break;

        case 4: // '\004'
            deleted();
            break;

        case 64: // '@'
            messageDispatchFail((PMessage)e.getData());
            break;

        case 32: // ' '
            messageDispatchFail((PMessage)e.getData());
            break;

        case 8: // '\b'
            messageDispatchFail((PMessage)e.getData());
            break;

        case 16: // '\020'
            messageDispatchFail((PMessage)e.getData());
            break;
        }
    }

    public void childCreated(PLayer player)
    {
    }

    public void messageSendError(PMessage pmessage)
    {
    }

    public void messageSendSuccess(PMessage pmessage)
    {
    }

    public void messageDispatchFail(PMessage pmessage)
    {
    }

    public void messageDispatchSuccess(PMessage pmessage)
    {
    }

    public void created()
    {
    }

    public void deleted()
    {
    }
}
