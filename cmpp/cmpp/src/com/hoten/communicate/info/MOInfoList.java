/**
 * <p>Title: SaveMOList</p>
 * <p>Description: </p>
 * 存于ws_mo、ws_mo_ui的消息队列
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Hoten</p>
 * @version 1.0
 */
package com.hoten.communicate.info;
import java.util.Vector;

public class MOInfoList {
        /** SaveMO消息模块实例 */
        static private MOInfoList _instance;
        /** SaveMO消息队列 */
        private Vector _vList;
        /** 线程同步控制确保模块仅有一个实例 */
        static synchronized public MOInfoList getInstance() {
                if (_instance == null) {
                        _instance = new MOInfoList();
                }
                return _instance;
        }
        /** 构造器，默认消息队列长度为50 */
        private MOInfoList() {
                _vList=new Vector(50);
        }
        /** 向消息队列添加消息 */
        public synchronized void add(MOInfo msg){
                _vList.addElement(msg);
        }
        /** 向消息队列插入消息(添加到队列起始处) */
        public synchronized void insert(MOInfo msg){
                _vList.add(0,msg);
        }
        /** 返回并删除消息队列起始处消息，若消息队列为空，返回空 */
        public synchronized MOInfo remove(){
                if(_vList.size()==0)return null;
                return (MOInfo)_vList.remove(0);
        }
        /** 返回消息队列长度 */
        public int getSize(){
                return _vList.size();
        }
}