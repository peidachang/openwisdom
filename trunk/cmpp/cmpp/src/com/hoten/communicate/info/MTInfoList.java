/**
 * <p>Title: SaveMOList</p>
 * <p>Description: </p>
 * 存于ws_mo、ws_mo_ui的消息队列
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Hoten</p>
 * @version 1.0
 */
package com.hoten.communicate.info;
import java.util.*;

public class MTInfoList {
        /** SaveMO消息模块实例 */
        static private MTInfoList _instance;
        /** SaveMO消息队列 */
        private Hashtable list = null;
        /** 线程同步控制确保模块仅有一个实例 */
        static synchronized public MTInfoList getInstance() {
                if (_instance == null) {
                        _instance = new MTInfoList();
                }
                return _instance;
        }
        public boolean regedit(String listName){
            if(listName==null) return false;
            if(list.containsKey(listName)){
                return true;
            }else{
                list.put(listName,new Vector(50));
                return true;
            }
        }
        /** 构造器，默认消息队列长度为50 */
        private MTInfoList() {
                list=new Hashtable();
        }
        /** 向消息队列添加消息 */
        public synchronized boolean add(MTInfo msg,String listName){
            if(msg==null||listName==null) return false;
            if(list.containsKey(listName)){
                ((Vector)list.get(listName)).addElement(msg);
            }
            else
                return false;
            return true;
        }
        /** 向消息队列插入消息(添加到队列起始处) */
        public synchronized boolean insert(MTInfo msg,String listName){
            if(msg==null||listName==null) return false;
            if(list.containsKey(listName)){
                ((Vector)list.get(listName)).add(0,msg);
            }
            else
                return false;
            return true;
        }
        /** 返回并删除消息队列起始处消息，若消息队列为空，返回空 */
        public synchronized MTInfo remove(String listName){
            if(listName==null) return null;
            if(list.containsKey(listName)){
                if(((Vector)list.get(listName)).size()==0) return null;
                return (MTInfo)((Vector)list.get(listName)).remove(0);
            }
            else
                return null;
        }
        /** 返回消息队列长度 */
        public int getSize(String listName){
            if(listName==null) return -1;
            if(list.containsKey(listName)){
                return ((Vector)list.get(listName)).size();
            }
            else
                return -1;
        }
}