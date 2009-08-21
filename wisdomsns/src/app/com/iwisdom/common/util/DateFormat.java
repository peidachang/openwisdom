package com.iwisdom.common.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateFormat {
	public static String format(String date,String format){
		if (date != null && !"".equals(date)){
			SimpleDateFormat formatDate = new SimpleDateFormat(format);
			if (date.trim().length() <= 10)
				return formatDate.format(Date.valueOf(date));
			
			Timestamp t = Timestamp.valueOf(date);
			return formatDate.format(new Date(t.getTime()));
		}
		
		return date;
	}
	
	public static String format(Timestamp date,String format){
		if (date != null && !"".equals(date)){
			SimpleDateFormat formatDate = new SimpleDateFormat(format);			
			return formatDate.format(date.getTime());
		}
		
		return date.toString();
	}
	
	public static Date formatToDate(Timestamp date,String format){
		if (date != null && !"".equals(date)){
			SimpleDateFormat formatDate = new SimpleDateFormat(format);
			return Date.valueOf(formatDate.format(date.getTime()));
		}
		
		return new Date(System.currentTimeMillis());
	}
	
	public static String format(Date date,String format){
		if (date != null){
			SimpleDateFormat formatDate = new SimpleDateFormat(format);			
			return formatDate.format(date.getTime());
		}
		
		return date.toString();
	}
	public static String format(java.util.Date date,String format){
		if(date!=null)
		{
			SimpleDateFormat formatDate = new SimpleDateFormat(format);	
			return formatDate.format(date);
		}
		return "" ;
	}
}
