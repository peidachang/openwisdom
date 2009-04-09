/**
 * <p>Title: SaveMOList</p>
 * <p>Description: </p>
 * ����ws_mo��ws_mo_ui����Ϣ����
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Hoten</p>
 * @version 1.0
 */
package com.hoten.communicate.info;
import java.util.*;

public class MTInfoList {
        /** SaveMO��Ϣģ��ʵ�� */
        static private MTInfoList _instance;
        /** SaveMO��Ϣ���� */
        private Hashtable list = null;
        /** �߳�ͬ������ȷ��ģ�����һ��ʵ�� */
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
        /** ��������Ĭ����Ϣ���г���Ϊ50 */
        private MTInfoList() {
                list=new Hashtable();
        }
        /** ����Ϣ���������Ϣ */
        public synchronized boolean add(MTInfo msg,String listName){
            if(msg==null||listName==null) return false;
            if(list.containsKey(listName)){
                ((Vector)list.get(listName)).addElement(msg);
            }
            else
                return false;
            return true;
        }
        /** ����Ϣ���в�����Ϣ(��ӵ�������ʼ��) */
        public synchronized boolean insert(MTInfo msg,String listName){
            if(msg==null||listName==null) return false;
            if(list.containsKey(listName)){
                ((Vector)list.get(listName)).add(0,msg);
            }
            else
                return false;
            return true;
        }
        /** ���ز�ɾ����Ϣ������ʼ����Ϣ������Ϣ����Ϊ�գ����ؿ� */
        public synchronized MTInfo remove(String listName){
            if(listName==null) return null;
            if(list.containsKey(listName)){
                if(((Vector)list.get(listName)).size()==0) return null;
                return (MTInfo)((Vector)list.get(listName)).remove(0);
            }
            else
                return null;
        }
        /** ������Ϣ���г��� */
        public int getSize(String listName){
            if(listName==null) return -1;
            if(list.containsKey(listName)){
                return ((Vector)list.get(listName)).size();
            }
            else
                return -1;
        }
}