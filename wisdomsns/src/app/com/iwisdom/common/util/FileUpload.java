package com.iwisdom.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileUpload {
	public Object uploadFile(String fileName,String filePath){
		return null ;
	}
	public static Object uploadFile(File in,File out){
		try {
			FileInputStream fis = new FileInputStream(in);
			FileOutputStream fos = new FileOutputStream(out);
			byte[] b = new byte[1024];
			int len = -1 ;
			while ((len=fis.read(b, 0, 1024)) != -1) {
				fos.write(b, 0, len);
			}

			fis.close();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "" ;
	}
}
