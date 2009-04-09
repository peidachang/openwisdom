// FrontEnd Plus GUI for JAD
// DeCompiled : PWriter.class

package com.huawei.insa2.comm;

import java.io.IOException;

// Referenced classes of package com.huawei.insa2.comm:
//            PMessage

public abstract class PWriter
{

    public PWriter()
    {
    }

    public abstract void write(PMessage pmessage)
        throws IOException;
}
