package com.huawei.insa2.util;

import java.text.SimpleDateFormat;

public class DateUtil {
	public static java.sql.Timestamp format(java.sql.Timestamp date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return java.sql.Timestamp.valueOf(sdf.format(date)) ;
	}
}
