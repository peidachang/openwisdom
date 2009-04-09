package com.hoten.util;
import java.util.*;
import java.sql.*;
/**
  *
  * <p>Title: ����������</p>
  * <p>Description: </p>
  * ������Ҫ�������ϵͳ����еĹ�������
  * <p>Copyright: Copyright (c) 2003</p>
  * <p>Company: </p>
  * @author hoten
  * @version 1.0
  */

public final class MOParam {
    static private MOParam _instance;       //����ʵ��
    public String mobileServiceID = "9555";//�ƶ��ط�����
    public String unicomServiceID = "9555";//��ͨ�ط�����

    public int getMONum = 100;              //ÿ��ȡ��Ϣ����

    public String dbPool = null;          //���ӳ�����

    public int dbTime = 2000;                   //���ӵȴ�ʱ��
    public int sendNum = 1;          //�·��߳���
    public int getNum  = 1;          //�����߳���
    private String separator=System.getProperty("file.separator");
    public String initPath = "."+separator+"config"+separator;

    private String _logFile_linux = "./log/event/System.log";
    private String _logFile_windows = ".\\log\\event\\System.log";

    private String _errorFile_linux = "./log/error/MainError.error";
    private String _errorFile_windows = ".\\log\\error\\MainError.error";

    public String logFile;                      //ϵͳ��־�ļ�
    public String errorFile;                   //ϵͳ�����ļ�

    /**
     * ȡ�ñ����Ψһʵ��
     *
     */
    static synchronized public MOParam getInstance() {
        if (_instance == null) {
                _instance = new MOParam();
        }
        return _instance;
    }
    /**
     * ���캯��
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