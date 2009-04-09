// FrontEnd Plus GUI for JAD
// DeCompiled : PReader.class

package com.huawei.insa2.comm;

import java.io.IOException;

// Referenced classes of package com.huawei.insa2.comm:
//            PMessage

public abstract class PReader
{

    public PReader()
    {
    }

    public abstract PMessage read()
        throws IOException;
}
