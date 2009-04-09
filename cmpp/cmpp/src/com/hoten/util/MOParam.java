package com.hoten.util;
import java.util.*;
import java.sql.*;
/**
  *
  * <p>Title: 公共变量类</p>
  * <p>Description: </p>
  * 此类主要用来存放系统框架中的公共变量
  * <p>Copyright: Copyright (c) 2003</p>
  * <p>Company: </p>
  * @author hoten
  * @version 1.0
  */

public final class MOParam {
    static private MOParam _instance;       //本类实例
    public String mobileServiceID = "9555";//移动特服代码
    public String unicomServiceID = "9555";//联通特服代码

    public int getMONum = 100;              //每次取消息个数

    public String dbPool = null;          //连接池名称

    public int dbTime = 2000;                   //连接等待时间
    public int sendNum = 1;          //下发线程数
    public int getNum  = 1;          //接收线程数
    private String separator=System.getProperty("file.separator");
    public String initPath = "."+separator+"config"+separator;

    private String _logFile_linux = "./log/event/System.log";
    private String _logFile_windows = ".\\log\\event\\System.log";

    private String _errorFile_linux = "./log/error/MainError.error";
    private String _errorFile_windows = ".\\log\\error\\MainError.error";

    public String logFile;                      //系统日志文件
    public String errorFile;                   //系统出错文件

    /**
     * 取得本类的唯一实例
     *
     */
    static synchronized public MOParam getInstance() {
        if (_instance == null) {
                _instance = new MOParam();
        }
        return _instance;
    }
    /**
     * 构造函数
     */
    private MOParam() {
        dbPool = System.getProperty("dbPool","100");
        //getParam();
        if(System.getProperty("file.separator").equals("/")){
            logFile=_logFile_linux;
            errorFile=_errorFile_linux;
        }else{
            logFile=_logFile_windows;
            errorFile=_errorFile_windows;
        }
    }
}