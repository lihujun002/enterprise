package org.tiger.framework.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * 
 * @author junlu.chen
 *
 */
public final class DateUtil {
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private DateUtil() {
	}

	public static Date now() {
		return new Date();
	}

	public static String getcurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_YYYYMMDDHHMMSS);
		return df.format(now());
	}

	public static String parseDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(DATE_TIME_FORMAT);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 格式化date
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static String formatDate(Date date, String fmt) {
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		return df.format(date);
	}

	/**
	 * 获取时间
	 * 
	 * @param strNowDate
	 * @return
	 */
	public final static long getDate(String strNowDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_YYYYMMDDHHMMSS);
		try {
			return sdf.parse(strNowDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Date getDate(String date, String pattern) {
		if (StringUtils.isBlank(date)) {
			return DateTime.now().toDate();
		}
		if (StringUtils.isBlank(pattern)) {
			pattern = DATE_TIME_FORMAT;
		}

		return DateTime.parse(date, DateTimeFormat.forPattern(pattern)).toDate();
	}

	/**
	 * @param stamp
	 * @return
	 */
	public static boolean checkStamp(String stamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_YYYYMMDDHHMMSS);
		sdf.setLenient(false);
		try {
			Date d = sdf.parse(stamp);
			String des = sdf.format(d);
			if (stamp.equals(des)) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static long currentTimeSeconds() {
		long seconds = System.currentTimeMillis() / 1000L;
		return seconds;
	}

}
