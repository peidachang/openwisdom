package com.hoten.cmpp;

/**
 * <p>Title:ͨ�ų�ʼ����Ϣ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPP_InitMessage {
    static private CMPP_InitMessage _instance;       //����ʵ��


    public int C=3;//������
    public int T=60;//�ȴ����������
    public int N=3;//�ط�����
    public int timeout=1000;

    private String _logFile_linux = "./log/cmpp/System.log";
    private String _logFile_windows = ".\\log\\cmpp\\System.log";
    private String _errorFile_linux = "./log/cmpp/MainError.error";
    private String _errorFile_windows = ".\\log\\cmpp\\MainError.error";
    private String _socketFile_linux = "./log/cmpp/socket.log";
    private String _socketFile_windows = ".\\log\\cmpp\\socket.log";
    private String _mobileError_linux = "./log/cmpp/mobile.log";
    private String _mobileError_windows = ".\\log\\cmpp\\mobile.log";


    public String logFile;                      //ϵͳ��־�ļ�
    public String mobileFile;                      //ϵͳ��־�ļ�
    public String errorFile;                   //ϵͳ�����ļ�
    public String socketLogFile;                 //Socket����־
    public String socketInitPath;               //��ʼ���ļ�·��


    static synchronized public CMPP_InitMessage getInstance() {
        if (_instance == null) {
            _instance = new CMPP_InitMessage();
        }
        return _instance;
    }
    /**
     * ���캯��
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