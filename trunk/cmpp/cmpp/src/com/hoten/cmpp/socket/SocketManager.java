package com.hoten.cmpp.socket;
import java.io.*;
import java.net.*;
import java.util.*;
import com.hoten.cmpp.util.*;
import com.hoten.cmpp.CMPP_InitMessage;
import com.hoten.cmpp.message.*;
/**
 * <p>Title: Socket≥ÿπ‹¿Ì</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SocketManager {
    private String host;
    private String auth;
    private String user;
    private int version;
    private String timeStamp;

    private int port=7890;
    private int timeout=1000*10;
    private Socket s=null;
    private int i=0;
    private int n=0;
    public SocketManager(String host,String user,String auth,String timeStamp,int version,int port,int timeout){
        this.host=host;
        this.user=user;
        this.auth=auth;
        this.timeStamp=timeStamp;
        this.version=version;
        this.port=port;
        this.timeout=timeout;
    }
    public Socket getSocket()throws IOException{
        s= new Socket(host,port);
        if(s!=null){
            s.setSoTimeout(timeout);
            i++;
            n++;
        }else
            s=getSocket();
        return s;
    }
    public byte[] getAuth(){
        return auth.getBytes();
    }
    public byte[] getUSER(){
        return user.getBytes();
    }
    public int getVersion(){
        return version;
    }
    public String getTimeStamp(){
        return timeStamp;
    }

    public boolean checkSocket(){
        if(s==null)return false;
        try {
            java.io.DataInputStream in = new DataInputStream(s.getInputStream());
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    public void freeSocket(){
        i--;
        try {
            if(s!=null)
                s.close();
        }
        catch (Exception ex) {
        }
        s=null;
    }
}