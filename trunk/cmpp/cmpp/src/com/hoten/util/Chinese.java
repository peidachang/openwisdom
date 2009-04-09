package com.hoten.util;
import java.io.UnsupportedEncodingException;
 /**
  *
  * <p>Title: Chinese</p>
  * <p>Description: </p>
  * 此类主要用于中文字符从数据库中取出与存入时的相互转换
  * <p>Copyright: Copyright (c) 2003</p>
  * <p>Company: </p>
  * @author hoten
  * @version 1.0
  */
public class Chinese {
/**
 * 主要把从数据库中取出的字符转换为程序中可认的中文字符
 * @param change  需要转换的数据库字符
 * @return String　转换后的程序中的中文字符
 * @throws UnsupportedEncodingException　不能转换时抛出的异常
 */

    public  synchronized static String fromDatabase(String change)
        throws UnsupportedEncodingException {
            return new String(change.getBytes("iso-8859-1"),"gb2312");
    }
/**
 * 主要把程序中可认的中文字符转换为存入数据库中的字符
 * @param change　需要转换的程序中的中文字符
 * @return　　　　　转换后的数据库字符
 * @throws UnsupportedEncodingException　不能转换时抛出的异常
 */
    public  synchronized static String toDatabase(String change)
           throws UnsupportedEncodingException{
       return new String(change.getBytes("gb2312"),"iso-8859-1");
    }

}
