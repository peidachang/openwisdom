package com.hoten.util;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Tools {
    /**
     * 字符串替换函数
     * @param sAll String   原来的字符串
     * @param older String  要替换掉的字符串
     * @param newer String  新的字符串
     * @return String       替换后的结果
     */
    public synchronized static  String strReplace(String sAll,String sOld, String sNew){
        int iT=0;
        String sF = null,sH= null;
        //如果新串中包括旧串,不让替多只让替少
        if(sNew.indexOf(sOld)!= -1)
            return sAll;

        if(sAll == null || sOld==null ||sNew==null)
            return sAll;
        iT = sAll.indexOf(sOld);
        int i = 0;
        while(iT != -1){
            sF = sAll.substring(0,iT);
            sH= sAll.substring(iT+sOld.length());
            sAll = sF+sNew+sH;
            iT = sAll.indexOf(sOld);
        }
        return sAll;
    }

    /**
     * 大概检查手机号码是否是13位数字，并是否以13开头
     * @param sMobile String   传入的手机号码
     * @return boolean         true符合规范
     */
    public synchronized static boolean checkMobile(String sMobile){
        String sF6 = "",sB7= "",sF2="";
        if(sMobile == null)
            return false;
        if(sMobile.length()!= 11)
            return false;
        sF6 = sMobile.substring(0,7);
        sF2 = sMobile.substring(0,2);
        sB7 = sMobile.substring(7);
        try {
            int iT = Integer.valueOf(sF6).intValue() ;
            iT = Integer.valueOf(sB7).intValue();
            if(sF2.equals("13"))
                return true;
            else
                return false;
        }
        catch (Exception ex) {
            return false;
        }
    }

    /**
     * 过滤接收字符{MO}
     * @param sMo String       转换前字符
     * @return boolean         转换后字符
     * @说明
     */
    public synchronized static String convertMoString(String sMo){
        String sReturn = sMo;
        if(sReturn==null)
            return sReturn;
        try {
            sReturn = sReturn.toUpperCase();
            sReturn = sReturn.replace('，',',');
            sReturn = sReturn.replace('。','.');
            sReturn = sReturn.replace('；',';');
            sReturn = sReturn.replace('！','!');
            sReturn = sReturn.replace('？','?');
            sReturn = sReturn.replace('：',':');
            sReturn = sReturn.replace('"','＂');
            sReturn = sReturn.replace('“','＂');
            sReturn = sReturn.replace('”','＂');
            sReturn = sReturn.replace('-',' ');
            sReturn = sReturn.replace('_',' ');
            sReturn = sReturn.replace('，',',');
            sReturn = sReturn.replace('０','0');
            sReturn = sReturn.replace('１','1');
            sReturn = sReturn.replace('２','2');
            sReturn = sReturn.replace('３','3');
            sReturn = sReturn.replace('４','4');
            sReturn = sReturn.replace('５','5');
            sReturn = sReturn.replace('６','6');
            sReturn = sReturn.replace('７','7');
            sReturn = sReturn.replace('８','8');
            sReturn = sReturn.replace('９','9');
            sReturn = strReplace(sReturn,"‘","'");
        }
        catch (Exception ex) {
            return sMo;
        }
        return sReturn;
    }

    /**
     * 过滤接收字符{MT}
     * @param sMo String       转换前字符
     * @return boolean         转换后字符
     * @说明
     */
    public synchronized static String convertMtString(String sMt){
        String sReturn = sMt;
        if(sReturn==null)
            return sReturn;
        try {
            sReturn = strReplace(sReturn,"‘","'");
            sReturn = sReturn.replace('，',',');
            sReturn = sReturn.replace('。','.');
            sReturn = sReturn.replace('；',';');
            sReturn = sReturn.replace('！','!');
            sReturn = sReturn.replace('？','?');
            sReturn = sReturn.replace('：',':');
            sReturn = sReturn.replace('"','＂');
            sReturn = sReturn.replace('“','＂');
            sReturn = sReturn.replace('”','＂');
        }
        catch (Exception ex) {
            return sMt;
        }
        return sReturn;
    }
    public static boolean isNumeric(String msg){
        try {
            Integer.parseInt(msg);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static String isJSTQ(String sSercode){
        String sCorSercode = null;
        if(sSercode.equals("TQNJ")) sCorSercode = "025";
        if(sSercode.equals("TQTZ")) sCorSercode = "0523";
        if(sSercode.equals("TQSQ")) sCorSercode = "0527";
        if(sSercode.equals("TQWX")) sCorSercode = "0510";
        if(sSercode.equals("TQXZ")) sCorSercode = "0516";
        if(sSercode.equals("TQCZ")) sCorSercode = "0519";
        if(sSercode.equals("TQSZ")) sCorSercode = "0512";
        if(sSercode.equals("TQNT")) sCorSercode = "0513";
        if(sSercode.equals("TQLYG")) sCorSercode = "0518";
        if(sSercode.equals("TQHA")) sCorSercode = "0517";
        if(sSercode.equals("TQYC")) sCorSercode = "0515";
        if(sSercode.equals("TQYZ")) sCorSercode = "0514";
        if(sSercode.equals("TQZJ")) sCorSercode = "0511";
        if(sSercode.equals("TCZJH")) sCorSercode = "CTTC";
        if(sSercode.equals("LTTCH")) sCorSercode = "ZHTC";
        if(sSercode.equals("CTFCH")) sCorSercode = "CTFC";
        if(sSercode.equals("LTFCH")) sCorSercode = "LTFC";
        if(sSercode.equals("CTTC")) sCorSercode = "TCZJH";
        if(sSercode.equals("ZHTC")) sCorSercode = "LTTCH";
        if(sSercode.equals("CTFC")) sCorSercode = "CTFCH";
        if(sSercode.equals("LTFC")) sCorSercode = "LTFCH";
        return sCorSercode;
    }

}