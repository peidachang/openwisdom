package com.iwisdom.vote.util;

import java.util.Date;

public class DateTimeUtil {
	public static String format() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		Date date = new Date();
		return sdf.format(date);
	}
}
