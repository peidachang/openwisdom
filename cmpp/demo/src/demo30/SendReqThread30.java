package demo30;

import java.util.Date;

public class SendReqThread30 extends Thread
{
  private boolean alive = true;
  public static final ThreadGroup tg = new ThreadGroup("Req-thread");
  private Demo30 myDemo = null;
  private long timeLong = -3215342285927481344L;
  private int sleepInterval = 0;
  private boolean IsSleep = false;

  public SendReqThread30(String name, Demo30 demo, int timelong, int sleepinterval)
  {
    super(tg, name);
    super.setDaemon(true);
    this.myDemo = demo;
    this.timeLong = (timelong * 60 * 1000);
    if (this.sleepInterval != 0)
    {
      this.sleepInterval = (sleepinterval * 1000);
      this.IsSleep = true;
    }
  }

  public final void run()
  {
    long beginTime = new Date().getTime();

    if (this.alive)
      try
      {
        do {
          this.myDemo.Task();
          if (this.IsSleep)
          {
            Thread.sleep(this.sleepInterval);
          }
        }
        while (new Date().getTime() - beginTime < this.timeLong);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      catch (Throwable t)
      {
        t.printStackTrace();
      }
  }

  public void Kill()
  {
    this.alive = false;
  }
}