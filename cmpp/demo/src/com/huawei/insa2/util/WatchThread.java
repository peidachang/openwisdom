// FrontEnd Plus GUI for JAD
// DeCompiled : WatchThread.class

package com.huawei.insa2.util;


public abstract class WatchThread extends Thread
{

    private boolean alive;
    private String state_;
    public static final ThreadGroup tg = new ThreadGroup("watch-thread");

    public WatchThread(String name)
    {
        super(tg, name);
        alive = true;
        state_ = "unknown";
        setDaemon(true);
    }

    public void kill()
    {
        alive = false;
    }

    public final void run()
    {
        while(alive) 
            try
            {
                task();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            catch(Throwable t)
            {
                t.printStackTrace();
            }
    }

    protected void setState_(String newState)
    {
        state_ = newState;
    }

    public String getState_()
    {
        return state_;
    }

    protected abstract void task();

}
