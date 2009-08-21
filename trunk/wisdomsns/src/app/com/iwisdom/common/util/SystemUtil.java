package com.iwisdom.common.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import com.iwisdom.common.util.Constants;

public class SystemUtil {
	private static Properties properties = new Properties();
	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResource(Constants.SYSTEM_CONFIG_FILE+".properties")
					.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getPropertyValue(String key)
	{
		return properties.getProperty(key);
	}
	
	public static StringBuffer getSystemRootPath(final Class cls){
		if(cls==null){
			throw new NullPointerException();
		}
		URL url = cls.getProtectionDomain().getCodeSource().getLocation();
		String temp = url.getPath();
		
		File file = new File(temp);
		try {
			temp = file.getCanonicalPath();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		String parent = temp.replace("\\", "/");
		int location = parent.lastIndexOf("/WEB-INF");
		StringBuffer root = new StringBuffer(parent.substring(0,location));
		
		return root ;
	}
	
}
