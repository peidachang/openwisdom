package com.hoten.util;
import java.io.UnsupportedEncodingException;
 /**
  *
  * <p>Title: Chinese</p>
  * <p>Description: </p>
  * ������Ҫ���������ַ������ݿ���ȡ�������ʱ���໥ת��
  * <p>Copyright: Copyright (c) 2003</p>
  * <p>Company: </p>
  * @author hoten
  * @version 1.0
  */
public class Chinese {
/**
 * ��Ҫ�Ѵ����ݿ���ȡ�����ַ�ת��Ϊ�����п��ϵ������ַ�
 * @param change  ��Ҫת�������ݿ��ַ�
 * @return String��ת����ĳ����е������ַ�
 * @throws UnsupportedEncodingException������ת��ʱ�׳����쳣
 */

    public  synchronized static String fromDatabase(String change)
        throws UnsupportedEncodingException {
            return new String(change.getBytes("iso-8859-1"),"gb2312");
    }
/**
 * ��Ҫ�ѳ����п��ϵ������ַ�ת��Ϊ�������ݿ��е��ַ�
 * @param change����Ҫת���ĳ����е������ַ�
 * @return����������ת��������ݿ��ַ�
 * @throws UnsupportedEncodingException������ת��ʱ�׳����쳣
 */
    public  synchronized static String toDatabase(String change)
           throws UnsupportedEncodingException{
       return new String(change.getBytes("gb2312"),"iso-8859-1");
    }

}
