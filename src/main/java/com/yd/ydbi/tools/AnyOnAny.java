package com.yd.ydbi.tools;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * 同比环比计算工具
 * @author Administrator
 *
 */
public  class AnyOnAny {
	/**
	 * 计算日环比
	 * @param tdz
	 * @param ytz
	 * @return
	 */
	public static String getDOD(String tdz, String ytz) {
		BigDecimal td =new BigDecimal(Double.valueOf(tdz));
		BigDecimal yd =new BigDecimal(Double.valueOf(ytz));

		if (yd.compareTo(new BigDecimal(0))==0) {
			return "0";
		}
		BigDecimal bg1 = td.subtract(yd);
		BigDecimal bg2 = bg1.divide(yd,10,ROUND_HALF_DOWN);
		BigDecimal bg3 = bg2.multiply(new BigDecimal(100));
		double result2 = bg3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		String rt = String.valueOf(result2);
		return rt;
	}

	/**
	 * 计算同周比
	 * @param tdz
	 * @param lwz
	 * @return
	 */
	public static String getWOW(String tdz, String lwz) {
		return  getDOD(tdz,lwz);
	}

}
