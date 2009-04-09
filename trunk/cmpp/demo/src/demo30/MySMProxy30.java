// FrontEnd Plus GUI for JAD
// DeCompiled : MySMProxy30.class

package demo30;

import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30DeliverMessage;
import com.huawei.insa2.util.Args;
import com.huawei.smproxy.SMProxy30;

// Referenced classes of package demo30:
//            Demo30

public class MySMProxy30 extends SMProxy30
{

    private Demo30 demo;

    public MySMProxy30(Demo30 demo, Args args)
    {
        super(args);
        this.demo = null;
        this.demo = demo;
    }

    public CMPPMessage onDeliver(CMPP30DeliverMessage msg)
    {
        demo.ProcessRecvDeliverMsg(msg);
        return super.onDeliver(msg);
    }

    public void OnTerminate()
    {
        demo.Terminate();
    }
}
