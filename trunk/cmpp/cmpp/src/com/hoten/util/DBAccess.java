package com.hoten.util;
import com.hoten.db.*;
import java.util.*;
import java.sql.*;
/**
 * <p>Title:���ݿ����</p>
 * <p>Description:�����ݿ��������ȫ���װ </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: www.ddtong.com</p>
 * @author lqf
 * @version 1.0
 */

public class DBAccess {
    private DBConnectionManager dbcm = DBConnectionManager.getInstance();
    private String[] poolsName=null;
    private String logFile = null;
    private int dbTime = 1000;
    private String defPoolsName=null;//Ĭ�����ӳ�
    /**
     * Ĭ�Ϲ��캯��
     */
    public DBAccess() {
        poolsName = dbcm.getPoolName();
        defPoolsName = poolsName[0];
        logFile = dbcm.getLogFile();
    }
    /**
     * ����Ĭ�����ӳ�����
     * @param name Ĭ�����ӳ�����
     * @return��boolean �ж������Ƿ�ɹ�
     */
    public boolean setDefPoolName(String name){//����Ĭ�����ӳ�����
        if(checkPoolName(name)){
            defPoolsName=name;
            return true;
        }
        else
            return false;
    }
    /**
     * ȡ�����ݿ���ʵĴ���
     * @return��int ���ʴ���
     */
    public int getAccessNum(){
        return dbcm.getAccessNum();
    }
    /**
     * ȡ��ָ�����ӳص���Ч���Ӹ���
     * @param poolName ���ӳ�����
     * @return int ��Ч���Ӹ���
     */
    public int getFreeConNum(String poolName){
        return dbcm.getFreeConNum(poolName);
    }
    /**
     * ȡ��Ĭ�����ӳص���Ч���Ӹ���
     * @return int ��Ч���Ӹ���
     */
    public int getFreeConNum(){
        return dbcm.getFreeConNum(defPoolsName);
    }
    /**
     * ȡ��Ĭ�����ӳص���ʹ�����Ӹ���
     * @return int ��ʹ�����Ӹ���
     */
    public int getUsedConNum(){
        return dbcm.getUsedConNum(defPoolsName);
    }
    /**
     * ȡ��ָ�����ӳص���ʹ�����Ӹ���
     * @param poolName ���ӳ�����
     * @return int ��ʹ�����Ӹ���
     */
    public int getUsedConNum(String poolName){
        return dbcm.getUsedConNum(poolName);
    }
    /**
     * ȡ��Ĭ�����ӳ�����
     * @return String ���ӳ�����
     */
    public String getDefPoolName(){//ȡ��Ĭ�����ӳ�����
        return defPoolsName;
    }
    /**
     * ȡ��Ĭ�����ӳص�һ������
     * @return Connection ���õ�����
     */
    public Connection getConnection(){//ȡ��Ĭ�ϵ�����
        return getConnection(defPoolsName);
    }
    /**
     * ȡ��ָ�����ӳص�����
     * @param poolName ���ӳ�����
     * @return Connection ���õ�����
     */
    public Connection getConnection(String poolName){//ȡ��ָ�����ӳص�����
        if(!checkPoolName(poolName)){
            Log.printEvent("Thread :"+Thread.currentThread().getName()+" dbPoolName is error in getConnetion()! the errorName is "+poolName,logFile);
            poolName = defPoolsName;
        }
        Connection con = null;
        while(con==null){
            con=dbcm.getConnection(defPoolsName,dbTime);
        }
        return con;
    }
    /**
     * �ͷ����ӵ�Ĭ�ϵ����ӳ�
     * @param con �ͷŵ�����
     */
    public void freeConnection(Connection con){//�ͷ����ӵ�Ĭ�ϵ����ӳ�
        freeConnection(con,defPoolsName);
    }
    /**
     * �ͷ����ӵ�ָ�������ӳ�
     * @param con Ҫ�ͷŵ�����
     * @param��String poolName ���ӳ�����
     */
    public void freeConnection(Connection con,String poolName){
        if(!checkPoolName(poolName)){
            Log.printEvent("Thread :"+Thread.currentThread().getName()+" dbPoolName is error in freeConnection()! the errorName is "+poolName,logFile);
            poolName = defPoolsName;
        }
        dbcm.freeConnection(poolName,con);
    }
    /**
     * ע�������Ӳ�������
     */
    public void release(){
        dbcm.release();
    }
    /**
     * �������ʹ��Ĭ�����ӳ�
     * @param sql �����sql���
     * @throws Exception ��������ʱ�׳��쳣
     */
    public void insert(String sql)throws Exception{
        change_Access(sql,defPoolsName);
    }
    /*
     * �������ʹ��ָ�������ӳ�
     * @param sql�������sql���
     * @param poolName�����ӳص�����
     * @exception Exception����������ʱ�׳��쳣
     */
    public void insert(String sql,String poolName)throws Exception{//�������ʹ��ָ�����ӳ�
        change_Access(sql,poolName);
    }
    /**
     * ���²���ʹ��Ĭ�����ӳ�
     * @param sql�������sql���
     * @throws Exception����������ʱ�׳��쳣
     */
    public void update(String sql)throws Exception{//���²���ʹ��Ĭ�����ӳ�
        change_Access(sql,defPoolsName);
    }
    /**
     * ���²���ʹ��ָ�������ӳ�
     * @param sql�������sql���
     * @param poolName�����ӳص�����
     * @throws Exception����������ʱ�׳��쳣
     */
    public void update(String sql,String poolName)throws Exception{//���²���ʹ��ָ�����ӳ�
        change_Access(sql,poolName);
    }
    /**
     * ɾ������ʹ��Ĭ�����ӳ�
     * @param sql�������sql���
     * @throws Exception����������ʱ�׳��쳣
     */
    public void delete(String sql)throws Exception{//ɾ������ʹ��Ĭ�����ӳ�
        change_Access(sql,defPoolsName);
    }
    /**
     * ɾ������ʹ��ָ�������ӳ�
     * @param sql��ɾ����sql���
     * @param poolName�����ӳص�����
     * @throws Exception����������ʱ�׳��쳣
     */
    public void delete(String sql,String poolName)throws Exception{//ɾ������ʹ��ָ�����ӳ�
        change_Access(sql,poolName);
    }

    private void change_Access(String sql,String poolName)throws Exception{//�ǲ�ѯ����ʹ��ָ�����ӳ�
        Connection con = null;
        Statement stmt = null;
        try {
            con = getConnection(poolName);
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            try {
                stmt.close();
            }
            catch (Exception ex) {
            }
            freeConnection(con,poolName);
        }
    }
    /**
     * ��ѯһ��һ�У�ʹ��Ĭ�����ӳ�
     * @param sql�������sql���
     * @return String ���ز�ѯ��ֵ��û��Ϊnull,��֮Ϊһ�ַ�����
     * @throws Exception����������ʱ�׳��쳣
     */
    public String select(String sql) throws Exception{//��ѯһ��һ�У�ʹ��Ĭ�����ӳ�
        Vector v = select(sql,1,1,defPoolsName);
        if(v==null) return null;
        return (String)v.get(0);
    }
    /**
     * ��ѯһ��һ�У�ʹ��ָ�������ӳ�
     * @param sql�������sql���
     * @param poolName�����ӳ�����
     * @return String ���ز�ѯ��ֵ��û��Ϊnull,��֮Ϊһ�ַ�����
     * @throws Exception����������ʱ�׳��쳣
     */
    public String select(String sql,String poolName) throws Exception{//��ѯһ��һ�У�ʹ��ָ�����ӳ�
        Vector v =select(sql,1,1,poolName);
        if(v==null) return null;
        return (String)v.get(0);
    }
    /**
     * ��ѯһ�ж��У�ʹ��Ĭ�����ӳأ�lineNumΪ������
     * @param sql�������sql���
     * @param lineNum������
     * @return Vector ���ز�ѯ��ֵ��û��Ϊnull��
     * @throws Exception����������ʱ�׳��쳣
     */
    public Vector select(String sql,int lineNum) throws Exception{//��ѯһ�ж��У�ʹ��Ĭ�����ӳأ�lineNum��Ϊ������
        return select(sql,lineNum,1,defPoolsName);
    }
    /**
     * ��ѯһ�ж��У�ʹ��ָ�������ӳأ�lineNumΪ������
     * @param sql�������sql���
     * @param lineNum������
     * @param poolName���������ӳ�
     * @return Vector ���ز�ѯ��ֵ��û��Ϊnull��
     * @throws Exception����������ʱ�׳��쳣
     */
    public Vector select(String sql,int lineNum,String poolName) throws Exception{//��ѯһ�ж��У�ʹ��ָ�����ӳأ�lineNum��Ϊ������
        return select(sql,lineNum,1,poolName);
    }
    /**
     * ��ѯrowNum��lineNum�У�ʹ��Ĭ�����ӳ�
     * @param sql�������sql���
     * @param lineNum������
     * @param rowNum������
     * @return Vector ���ز�ѯ��ֵ��û��Ϊnull��
     * @throws Exception����������ʱ�׳��쳣
     */
    public Vector select(String sql,int lineNum,int rowNum) throws Exception{//��ѯrowNum��lineNum�У�ʹ��Ĭ�����ӳأ�lineNum��Ϊ����,rowNumΪ������
        return select(sql,lineNum,rowNum,defPoolsName);
    }
    /**
     * ��ѯrowNum��lineNum�У�ʹ��ָ�����ӳ�
     * @param sql�������sql���
     * @param lineNum������
     */
    public Vector select(String sql,int lineNum,int rowNum,String poolName) throws Exception{
        Vector rows = new Vector();//��
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection(poolName);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rowNum==1){//һ��ʱʹ��rows����ŷ��ص�����
                if(rs.next()){
                    for(int i=0;i<lineNum;i++){
                        String value = rs.getString(i+1);
                        if(value!=null) rows.addElement(Chinese.fromDatabase(value));
                        else rows.addElement(null);
                    }
                }else{
                    return null;
                }
            }else{
                while(rs.next()){
                    Vector line = new Vector();
                    for(int i=0;i<lineNum;i++){
                        String value = rs.getString(i+1);
                        if(value!=null) line.addElement(Chinese.fromDatabase(value));
                        else line.addElement(null);
                    }
                    rows.addElement(line);
                    if((--rowNum)==0) break;
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            try {
                rs.close();
                stmt.close();
            }
            catch (Exception ex) {
            }
            freeConnection(con,poolName);
        }
        if(rows.size()==0) return null;
        return rows;
    }
    private boolean checkPoolName(String name){//�����������ӳ������Ƿ���ȷ
        boolean flag =false;
        int size = poolsName.length;
        for(int i=0;i<size;i++){
            if(poolsName[i].equals(name)){
                flag=true;
                break;
            }
        }
        return flag;
    }

}