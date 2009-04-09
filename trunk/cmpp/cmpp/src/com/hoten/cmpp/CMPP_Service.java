package com.hoten.cmpp;
import com.hoten.cmpp.message.*;
import java.util.*;
import java.io.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public interface CMPP_Service {
    int initCMPP(String poolName,String host,String icp_ID,String user,String auth,String timeStamp,int version,int port,String loginFlag,Vector list);
    int submit(SubmitMsg msg)throws IOException;
    boolean activeTest()throws IOException;
    void deliver()throws IOException;
    void quit();
}