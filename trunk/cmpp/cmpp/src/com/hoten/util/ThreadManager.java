package com.hoten.util;
import java.util.*;
/**
 * <p>Title: �̹߳�����</p>
 * <p>Description:��Ҫ������������е��̣߳�
 * �ڶ�̬������ж���н��д���������ڳ�����������ɾ���߳�
 * ������������ַ�����get��set��Ҫ�Ƕ���������Խ��в�����
 * </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: ���պ�ͼ�������ϵͳ���޹�˾</p>
 * @author hoten
 * @version 1.0
 */

public class ThreadManager {
    static private ThreadManager _instance;
    private Hashtable _threadList;

    private ThreadGroup _tgRoot;
    private ThreadGroup _tgControl;
    private ThreadGroup _tgDeal;
    private ThreadGroup _tgRead;
    private ThreadGroup _tgSendMT;
/**
 * ʹ�ô˷�����ñ���Ψһʵ����
 * @return
 */
    static synchronized public ThreadManager getInstance() {
        if (_instance == null) {
                _instance = new ThreadManager();
        }
        return _instance;
    }

    private ThreadManager() {
        _tgRoot     =new ThreadGroup("Root");
        _tgControl  =new ThreadGroup(_tgRoot,"Control");
        _tgDeal     =new ThreadGroup(_tgRoot,"Deal");
        _tgRead    =new ThreadGroup(_tgRoot,"Read");
        _tgSendMT   =new ThreadGroup(_tgRoot,"Send");
        _threadList =new Hashtable();
    }
    public synchronized ThreadGroup getRootGroup(){
        return _tgRoot;
    }
    public synchronized void stopRootGroup(){
        _tgRoot.interrupt();
    }

    public synchronized ThreadGroup getControlGroup(){
        return _tgControl;
    }
    public synchronized void stopControlGroup() {
        _tgControl.interrupt();
    }

    public ThreadGroup getDealGroup(){
        return _tgDeal;
    }
    public synchronized void stopDealGroup() {
        _tgDeal.interrupt();
    }

    public synchronized ThreadGroup getReadGroup(){
        return _tgRead;
    }
    public synchronized void stopReadGroup() {
        _tgRead.interrupt();
    }

    public synchronized ThreadGroup getSendMTGroup(){
        return _tgSendMT;
    }
    public synchronized void stopSendMTGroup() {
        _tgSendMT.interrupt();
    }
/**
 * ���̶߳��������һ�߳�
 * @param threadName���̵߳�����
 * @param thread      �߳�ʵ��
 */
    public synchronized void addThread(String threadName,Thread thread){
        _threadList.put(threadName,thread);
    }
/**
 * �õ�һ���߳�ʵ��
 * @param threadName��Ҫȡ�õ��߳���
 * @return��Thread �߳�ʵ��
 */
    public synchronized Thread getThread(String threadName){
        return (Thread)_threadList.get(threadName);
    }
/**
 * �õ�һ���߳�ʵ����ͬʱ�Ѹ�ʵ�����̶߳�����ɾ��
 * @param threadName��Ҫɾ�����߳���
 * @return��Thread �����߳�ʵ��
 */
    public synchronized Thread removeThread(String threadName){
        return (Thread)_threadList.remove(threadName);
    }
/**
 * ȡ���̶߳���
 * @return��Hashtable ����ʵ��
 */
    public synchronized Hashtable getThreadList(){
        return _threadList;
    }
/**
 * ����̶߳���
 */
    public synchronized void removeAllThread(){
        _threadList.clear();
    }
}