package net.wanho.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 */
public class DateUtils {
	public static String YYYY = "yyyy";

	public static String YYYY_MM = "yyyy-MM";

	public static String YYYY_MM_DD = "yyyy-MM-dd";

	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前日期, 默认格式为yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getDate() {
		return parseDateToStr(YYYY_MM_DD, new Date());
	}

	public static final String getDateTime() {
		return parseDateToStr(YYYY_MM_DD_HH_MM_SS, new Date());
	}

	public static Date parseDate(String str) {
		return parseStrToDate(YYYY_MM_DD, str);
	}

	public static Date parseDateTime(String str) {
		return parseStrToDate(YYYY_MM_DD_HH_MM_SS, str);
	}

	public static final String parseDateToStr(final String format, final Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static final Date parseStrToDate(final String format, final String ts) {
		if (StringUtils.isNotEmpty(ts)) {
			try {
				return new SimpleDateFormat(format).parse(ts);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

}
