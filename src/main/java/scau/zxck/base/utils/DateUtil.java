package scau.zxck.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {

	/**
	 * 转换Date类型为字符串类型
	 * 
	 * @param value
	 * @return
	 */
	public static String getSimpleDate(Date value) {
		return getSimpleDate(value, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 转换Date类型为字符串类型
	 * 
	 * @param value
	 * @return
	 */
	public static String getSimpleDate(Date value, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(value);
	}

	/**
	 * 获取几月后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date nextMonth(Date d, int m) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) + m);
		return now.getTime();
	}

	/**
	 * 获取几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date nextDate(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 获取几小时后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date nextHour(Date d, int hour) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + hour);
		return now.getTime();
	}

	/**
	 * 获取几分钟后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date nextMinute(Date d, int minute) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minute);
		return now.getTime();
	}
	
	/**
	 * 获取两个日期的间隔天数
	 * 
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static int dayInterval(Date startDay, Date endDay) {
		return (int) ((endDay.getTime() - startDay.getTime()) / (24 * 60 * 60 * 1000));
	}

	public static String timeInterval(Date startDay, Date endDay) {
		long intervalSecond = (endDay.getTime() - startDay.getTime()) / 1000;
		long hour = intervalSecond / 3600;
		long minute = (intervalSecond - hour * 3600) / 60;
		long second = intervalSecond - hour * 3600 - minute * 60;
		String str = "";
		if (hour != 0) {
			str += hour + "小时";
		}
		if (minute != 0) {
			str += minute + "分";
		}
		str += second + "秒";
		return str;
	}

	/**
	 * 转换String类型为Date类型
	 * 
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static Date getSimpleDate(String value) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.parse(value);
	}

	/**
	 * 转换String类型为Date类型
	 * 
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static Date getSimpleDateBy(String value, String pattern)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(value);
	}

	/**
	 * 转换String类型为Date类型
	 * 
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static Date getSimpleDate2(String value) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(value);
	}

	/**
	 * 把时分秒置为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateOnly(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		return cal.getTime();
	}

	/**
	 * 首字母大写
	 * 
	 * @param name
	 * @return
	 */
	public static String getUpperName(String name) {
		byte[] items = name.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	/**
	 * 判断字符串是否合法(不含有非法字符或中文) 若数组中其中一字符串含有非法字符，返回false，反之，返回true
	 * 
	 * @param sarray
	 * @return
	 */
	public static boolean judgeIllegalChar(String[] sarray) {
		boolean result = true;
		if (sarray != null) {
			Pattern pattern = Pattern.compile("^\\w+$");
			for (int i = 0; i < sarray.length; i++) {
				if (!pattern.matcher(sarray[i]).matches()) {
					result = false;
					break;
				}
			}
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 从指定的时间截取年月日。将时分秒毫秒都设置为0
	 * 
	 * @param source
	 *            原始时间
	 * @return 将时分秒毫秒都设置为0的日期
	 */
	public static Date trimDate(Date source) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(source);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
