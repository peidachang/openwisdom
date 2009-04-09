/**
 * <p>Title: SaveMOList</p>
 * <p>Description: </p>
 * ����ws_mo��ws_mo_ui����Ϣ����
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Hoten</p>
 * @version 1.0
 */
package com.hoten.communicate.info;
import java.util.Vector;

public class MOInfoList {
        /** SaveMO��Ϣģ��ʵ�� */
        static private MOInfoList _instance;
        /** SaveMO��Ϣ���� */
        private Vector _vList;
        /** �߳�ͬ������ȷ��ģ�����һ��ʵ�� */
        static synchronized public MOInfoList getInstance() {
                if (_instance == null) {
                        _instance = new MOInfoList();
                }
                return _instance;
        }
        /** ��������Ĭ����Ϣ���г���Ϊ50 */
        private MOInfoList() {
                _vList=new Vector(50);
        }
        /** ����Ϣ���������Ϣ */
        public synchronized void add(MOInfo msg){
                _vList.addElement(msg);
        }
        /** ����Ϣ���в�����Ϣ(��ӵ�������ʼ��) */
        public synchronized void insert(MOInfo msg){
                _vList.add(0,msg);
        }
        /** ���ز�ɾ����Ϣ������ʼ����Ϣ������Ϣ����Ϊ�գ����ؿ� */
        public synchronized MOInfo remove(){
                if(_vList.size()==0)return null;
                return (MOInfo)_vList.remove(0);
        }
        /** ������Ϣ���г��� */
        public int getSize(){
                return _vList.size();
        }
}