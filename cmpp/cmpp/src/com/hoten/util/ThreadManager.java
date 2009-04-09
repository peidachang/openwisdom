package com.hoten.util;
import java.util.*;
/**
 * <p>Title: 线程管理类</p>
 * <p>Description:主要用来管理程序中的线程，
 * 在动态加载与卸载中将有此类来完成在程序中增加与删除线程
 * 此类基本有两种方法，get和set主要是对里面的属性进行操作。
 * </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: 江苏宏图嘉腾软件系统有限公司</p>
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
 * 使用此方法获得本类唯一实例。
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
 * 向线程队列中添加一线程
 * @param threadName　线程的名称
 * @param thread      线程实例
 */
    public synchronized void addThread(String threadName,Thread thread){
        _threadList.put(threadName,thread);
    }
/**
 * 得到一个线程实例
 * @param threadName　要取得的线程名
 * @return　Thread 线程实例
 */
    public synchronized Thread getThread(String threadName){
        return (Thread)_threadList.get(threadName);
    }
/**
 * 得到一个线程实例，同时把该实例从线程队列中删除
 * @param threadName　要删除的线程名
 * @return　Thread 　　线程实例
 */
    public synchronized Thread removeThread(String threadName){
        return (Thread)_threadList.remove(threadName);
    }
/**
 * 取得线程队列
 * @return　Hashtable 队列实例
 */
    public synchronized Hashtable getThreadList(){
        return _threadList;
    }
/**
 * 清空线程队列
 */
    public synchronized void removeAllThread(){
        _threadList.clear();
    }
}