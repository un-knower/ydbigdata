package com.yd.common.utils;

import java.util.Calendar;
import java.util.Random;

public class SerialNoUtils {

	
	public static long getTimeSerialNo(){
		long serialNo;
		Random r = new Random();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int ms = c.get(Calendar.MILLISECOND);
		serialNo = year * 10000 + month * 100 + day;
		serialNo = serialNo * 1000000 + hour * 10000 + minute * 100 + second;
		serialNo = ( serialNo * 1000 + ms ) * 1000 + r.nextInt(1000);
		
		return serialNo;
	}

}
