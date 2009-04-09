// FrontEnd Plus GUI for JAD
// DeCompiled : SSListener.class

package com.huawei.insa2.comm.sgip;

import java.io.IOException;
import java.net.*;

// Referenced classes of package com.huawei.insa2.comm.sgip:
//            SSEventListener

public class SSListener extends Thread
{

    private ServerSocket serversocket;
    private SSEventListener listener;
    private String ip;
    private int port;
    private int status;
    private static final int ON = 0;
    private static final int OFF = 1;

    public SSListener(String ip, int port, SSEventListener lis)
    {
        status = 1;
        this.ip = ip;
        this.port = port;
        listener = lis;
        start();
    }

    public void run()
    {
        do
            if(status == 0)
                try
                {
                    Socket incoming = serversocket.accept();
                    if(status == 0)
                        listener.onConnect(incoming);
                }
                catch(Exception ex)
                {
                    if(status != 0);
                }
            else
                synchronized(this)
                {
                    try
                    {
                        wait(10000L);
                    }
                    catch(Exception exception) { }
                }
        while(true);
    }

    public synchronized void beginListen()
        throws IOException
    {
        if(status == 0)
            return;
        try
        {
            serversocket = new ServerSocket();
            serversocket.bind(new InetSocketAddress(ip, port));
            status = 0;
            notifyAll();
        }
        catch(IOException ioex)
        {
            throw ioex;
        }
    }

    public synchronized void stopListen()
    {
        if(status == 0)
            try
            {
                if(serversocket != null)
                {
                    status = 1;
                    serversocket.close();
                    serversocket = null;
                }
            }
            catch(IOException ioexception) { }
    }

    
}
