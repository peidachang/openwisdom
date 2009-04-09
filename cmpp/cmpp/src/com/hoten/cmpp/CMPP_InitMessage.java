package com.hoten.cmpp;

/**
 * <p>Title:通信初始化信息 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPP_InitMessage {
    static private CMPP_InitMessage _instance;       //本类实例


    public int C=3;//三分钟
    public int T=60;//等待间隔６０秒
    public int N=3;//重发三次
    public int timeout=1000;

    private String _logFile_linux = "./log/cmpp/System.log";
    private String _logFile_windows = ".\\log\\cmpp\\System.log";
    private String _errorFile_linux = "./log/cmpp/MainError.error";
    private String _errorFile_windows = ".\\log\\cmpp\\MainError.error";
    private String _socketFile_linux = "./log/cmpp/socket.log";
    private String _socketFile_windows = ".\\log\\cmpp\\socket.log";
    private String _mobileError_linux = "./log/cmpp/mobile.log";
    private String _mobileError_windows = ".\\log\\cmpp\\mobile.log";


    public String logFile;                      //系统日志文件
    public String mobileFile;                      //系统日志文件
    public String errorFile;                   //系统出错文件
    public String socketLogFile;                 //Socket池日志
    public String socketInitPath;               //初始化文件路径


    static synchronized public CMPP_InitMessage getInstance() {
        if (_instance == null) {
            _instance = new CMPP_InitMessage();
        }
        return _instance;
    }
    /**
     * 构造函数
     */
    private CMPP_InitMessage() {
        getParam();
        if(System.getProperty("file.separator").equals("/")){
            logFile=_logFile_linux;
            errorFile=_errorFile_linux;
            socketLogFile=_socketFile_linux;
            mobileFile=_mobileError_linux;
        }else{
            logFile=_logFile_windows;
            errorFile=_errorFile_windows;
            socketLogFile=_socketFile_windows;
            mobileFile=_mobileError_windows;
        }
    }

    private void getParam(){


    }
}