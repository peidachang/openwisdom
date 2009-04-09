package com.hoten.cmpp.util;
import java.io.*;
import java.util.*;
/**
  *
  * <p>Title: LOG ��־��¼</p>
  * <p>Description:
  * ������Ҫ������¼ϵͳ�з������ش��¼����Լ����ڳ������������Ĵ�����Ϣ</p>
  * <p>Copyright: Copyright (c) 2003</p>
  * <p>Company: hoten </p>
  * @author lqf
  * @version 1.0
  */
////////////////////////////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////////////////////////
public class Log{
  /**
   * ������¼ϵͳ�ش��¼�
   * @param msg���ش��¼�
   * @param fileName����־�ļ���·��������
   */

    public static void printBytes(byte[] msg,String logFile){
         StringBuffer sb = new StringBuffer(100);
         for(int i=0;i<msg.length;i++){
             sb.append((int)msg[i]);
             sb.append(",");
         }
         printEvent(sb.toString(),logFile);
   }
  public synchronized static void printEvent(String msg,String fileName)
   {
      msg = new String( "ʱ�䣺"+CTime.getTime(CTime.YYMMDDhhmmss) + " �¼���Ϣ:  " + msg);
      if(fileName!=null) printToFile(msg,fileName);
      else print(msg);
      return;
   }

   public synchronized static void printError(Throwable e,String msg,String fileName)
   {
      StringBuffer errors=new StringBuffer(100);
      errors.append("ʱ�䣺");
      errors.append(CTime.getTime(CTime.YYMMDDhhmmssxxx));
      errors.append("����Ϣ��");
      errors.append(msg);
      errors.append(" Exception: ");
      if(fileName!=null) {
            printToFile(errors.toString().trim(),fileName);
            try {
                e.printStackTrace(new PrintWriter(new FileWriter(fileName,true),true));//
            }
            catch (Exception ex) {
            }
      }
      else print(errors.toString().trim());
      return;
   }
/**
 * ��¼Ӧ���������Ĵ�����Ҫ������Ա�۲졣
 * @param e����һ���ţ����������
 * @param mobile���û��ֻ�����
 * @param msg�������û����͵���Ϣ
 * @param fileName����־�ļ���·��������
 */
   public synchronized static void printError(Throwable e,String mobile,String msg,String fileName)
   {
      StringBuffer errors=new StringBuffer(100);
      errors.append("ʱ�䣺");
      errors.append(CTime.getTime(CTime.YYMMDDhhmmssxxx));
      errors.append("���ֻ�����:");
      errors.append(mobile);
      errors.append("����Ϣ��");
      errors.append(msg);
      errors.append(" Exception: ");
      if(fileName!=null) {
            printToFile(errors.toString().trim(),fileName);
            try {
                e.printStackTrace(new PrintWriter(new FileWriter(fileName,true),true));//
            }
            catch (Exception ex) {
            }
      }
      else print(errors.toString().trim());
      return;
   }
/**�Ѵ�����Ϣ��ӡ����Ļ��
 *
 * @param msg��������Ϣ
 */
   private static void print(String msg)
   {
       System.out.println(msg);
   }
/**
 * ����Ϣ��ӡ��ָ���ļ�
 * @param msg��������Ϣ
 * @param fileName��ָ�����ļ�
 */

   private static void printToFile(String msg,String fileName) //��ӡ���ļ���
   {
      BufferedWriter mBufWriter = null;
      try
      {
         if(!createFile(fileName)) return ;
         FileWriter fileWriter = new FileWriter(fileName, true);
         mBufWriter = new BufferedWriter(fileWriter);

         mBufWriter.write(msg);
         mBufWriter.newLine();

         mBufWriter.flush();
         mBufWriter.close();
      }
      catch (Throwable e)
      {
         try { mBufWriter.close(); } catch (Throwable t) {};
      }
      return;
   }
/**
 * ���������ļ����ļ���
 * @param fileName���ļ����ļ�������
 * @return
 * @throws IOException��
 * @throws Exception
 */

  private static boolean createFile(String fileName)throws IOException ,Exception{
        File file = new File(fileName);
         if (file.exists()) /* does file exist? If so, can it be written to */
         {
            if (file.canWrite() == false)
               return false;
         }
         else
         {
           String path = null;  /* Does not exist.  Create the directories */

           int firstSlash = fileName.indexOf(File.separatorChar);
           int finalSlash = fileName.lastIndexOf(File.separatorChar);

           if (finalSlash == 0) { /* error, not valid path */ }
           else if (finalSlash == 1) /* UNIX root dir */
           {
             path = File.separator;
           }
           else if (firstSlash == finalSlash)
           { /* for example c:\  Then make sure slash is part of path */
             path = fileName.substring(0,finalSlash+1);
           }
           else
           { path = fileName.substring(0,finalSlash); }

           File dir = new File(path);
           dir.mkdirs();
         }
         return true;
  }
}