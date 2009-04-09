package com.hoten.util;
import com.hoten.communicate.info.*;
import com.hoten.db.*;
import java.sql.*;
import java.util.*;
/**
 * DBCommon 用于封装公共数据库操作的类
 * 主要方法： RecordMO()          记录MO信息(ws_mo)
 *          RecordMT()          记录MT信息(由table决定)
 *          getActiveGame()     获取用户当前处于活动状态的游戏信息
 *          getSpecifiedGame    获取用户指定游戏信息
 *          ChangeUserStatus()  改变用户游戏状态信息
 */
public class DBCommon {
    /**
     * 通过传递MOInfo类实例，记录MO信息(ws_mo)
     * @param moInfo MOInfo
     * @throws Exception
     */
    public synchronized static void  SaveMO(MOInfo moInfo,DBAccess dba) throws Exception {
        StringBuffer sbSql=new StringBuffer(300);         //使用StringBuffer 能提高系统的性能
        sbSql.append("insert into ");
        sbSql.append(moInfo.getTable());
        if(moInfo.getTable().equals("AHYD"))
            sbSql.append("(time,mobile,bz,msg) values('");
        else
            sbSql.append("(time,mobile,bz,msg,area) values('");
        sbSql.append(moInfo.getTime());
        sbSql.append("','");
        sbSql.append(moInfo.getMobile());
        sbSql.append("','");
        sbSql.append(moInfo.getBZ());
        sbSql.append("','");
        if(moInfo.getMsg()!=null)
            sbSql.append(Chinese.toDatabase(moInfo.getMsg()));
        if(moInfo.getTable().equals("AHYD"))
            sbSql.append("')");
        else{
            sbSql.append("','");
            sbSql.append(moInfo.getArea());
            sbSql.append("')");
        }
        dba.update(sbSql.toString());
        sbSql=null;
    }
    /**
     * 通过传递MTInfo类实例，记录MT信息(由table域决定存放地)
     * @param mtinfo MTInfo
     * @throws Exception
     */
    public static void RecodeMT(int state , MTInfo mtInfo,DBAccess dba,String table)throws Exception{
        StringBuffer sbSql=new StringBuffer(300);         //使用StringBuffer 能提高系统的性能
        if(mtInfo.getArea()==null)
            mtInfo.setArea("JSYD");
        sbSql.append("insert into ");
        sbSql.append(table);
        if(table.equals("ui_sendinfo"))
            sbSql.append("(xh,xxly,jtly,xxlx,qqfssj,sjhm,zt,NRXH,fee,bz1,bz2,bz3,bz4,area) values('");
        else
            sbSql.append("(xh,xxly,jtly,xxlx,qqfssj,sjhm,zt,NRXH,fee,bz1,bz2,bz3,bz4) values('");
        sbSql.append(mtInfo.getXH());
        sbSql.append("','");
        if(mtInfo.getXXLY()!=null)
            sbSql.append(mtInfo.getXXLY());
        sbSql.append("','");
        if(mtInfo.getJTLY()!=null)
            sbSql.append(mtInfo.getJTLY());
        sbSql.append("','");
        if(mtInfo.getXXLX()!=null)
            sbSql.append(mtInfo.getXXLX());
        sbSql.append("','");
        if(mtInfo.getQQFSSJ()!=null)
            sbSql.append(mtInfo.getQQFSSJ());
        sbSql.append("','");
        if(mtInfo.getSJHM()!=null)
            sbSql.append(mtInfo.getSJHM());
        sbSql.append("','");
        sbSql.append(state);
        sbSql.append("','");
        if(mtInfo.getNRXH()!=null)
            sbSql.append(mtInfo.getNRXH());
        sbSql.append("','");
        if(mtInfo.getFee()!=null)
            sbSql.append(mtInfo.getFee());
        sbSql.append("','");
        if(mtInfo.getBZ1()!=null)
            sbSql.append(mtInfo.getBZ1());
        sbSql.append("','");
        if(mtInfo.getYWDM()!=null)
            sbSql.append(mtInfo.getYWDM());
        sbSql.append("','");
        if(mtInfo.getFlagFee()!=null)
            sbSql.append(mtInfo.getFlagFee());
        sbSql.append("','");
        if(mtInfo.getYWDM()!=null)
            sbSql.append(mtInfo.getYWDM());
        if(table.equals("ui_sendinfo")){
            sbSql.append("','");
            sbSql.append(mtInfo.getArea());
        }
        sbSql.append("')");
        dba.update(sbSql.toString());
        sbSql=null;
    }
}