package scau.zxck.base.utils;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private static final int MAXASCII = 0x7f;
	private static final char[] PATH_TRIM_CHARS = new char[] { '\\', '/' };
	
	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if(length < 1)
			return false;
		
		int i = 0;
		if(length > 1 && chars[0] == '-')
			i = 1;
		
		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value 待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 * 
	 * @param values 字符串列表
	 * @return true/false
	 */
	public static boolean isNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	public static String getNotNullString(String str) {
		return str == null ? "" : str;
	}

	public static String getNotNullAndTrimString(String str) {
		return str == null ? "" : str.trim();
	}

	public static String getNullWhenEmpty(String str) {
		return str != null && str.length() == 0 ? null : str;
	}

	public static String getNullWhenWhiteSpace(String str) {
		return str != null && str.trim().length() == 0 ? null : str;
	}

	public static String getNullWhenWhiteSpaceOrTrim(String str) {
		return str == null || str.trim().length() == 0 ? null : str.trim();
	}

	public static String getNullWhenEmptyOrUnspecified(String str, String unspecifiedStr) {
		if (str != null && (str.length() == 0 || str.equals(unspecifiedStr))) {
			return null;
		}
		return str;
	}

	public static String getNullOrTrim(String str) {
		return str == null ? null : str.trim();
	}

	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String initialUpperCase(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char ch = str.charAt(0);
		if (!Character.isUpperCase(ch)) {
			str = Character.toUpperCase(ch) + str.substring(1);
		}
		return str;
	}

	public static String initialLowerCase(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char ch = str.charAt(0);
		if (!Character.isLowerCase(ch)) {
			str = Character.toLowerCase(ch) + str.substring(1);
		}
		return str;
	}

	public static String trimLeadingTabsAndSpaces(String line) {
		int size = line.length();
		int start = size;
		for (int i = 0; i < size; i++) {
			char c = line.charAt(i);
			if (!isIndentChar(c)) {
				start = i;
				break;
			}
		}
		if (start == 0)
			return line;
		else if (start == size)
			return ""; //$NON-NLS-1$
		else
			return line.substring(start);
	}

	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (isNullOrEmpty(str))
			return str;
		StringBuilder buf = new StringBuilder(str);
		while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	public static String trimTrailingCharacter(String str, char trailingCharacter) {
		if (isNullOrEmpty(str))
			return str;
		StringBuilder buf = new StringBuilder(str);
		while (buf.length() > 0 && buf.charAt(buf.length() - 1) == trailingCharacter) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	public static String trimLeadingCharacter(String str, char[] leadingCharacters) {
		if (isNullOrEmpty(str))
			return str;
		StringBuilder buf = new StringBuilder(str);
		while (buf.length() > 0 && isMatchingChar(buf.charAt(0), leadingCharacters)) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	public static String trimTrailingCharacter(String str, char[] trailingCharacters) {
		if (isNullOrEmpty(str))
			return str;
		StringBuilder buf = new StringBuilder(str);
		while (buf.length() > 0 && isMatchingChar(buf.charAt(buf.length() - 1), trailingCharacters)) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	public static boolean isMatchingChar(char ch, char[] matchings) {
		for (char matching : matchings) {
			if (ch == matching) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 用于格式化路径，将路径字符串两边多余的‘\’和‘/’去除并将字符串中的‘\’和‘/’替换成当前 操作系统的文件分隔符。
	 * 
	 * @param path
	 * @return
	 */
	public static String trimPath(String path) {
		if (isNullOrEmpty(path))
			return path;

		path = path.trim();
		path = StringUtil.trimLeadingCharacter(path, PATH_TRIM_CHARS);
		path = StringUtil.trimTrailingCharacter(path, PATH_TRIM_CHARS);
		if ("\\".equals(File.separator)) {
			path = path.replace("/", File.separator);
		} else {
			path = path.replace("\\", File.separator);
		}

		return path;
	}

	/**
	 * 用于格式化路径，将路径字符串两边多余的‘\’和‘/’去除并将字符串中的‘\’替换成‘/’
	 * 
	 * @param path
	 * @return
	 */
	public static String trimUnitPath(String path) {
		if (isNullOrEmpty(path))
			return path;

		path = path.trim();
		path = StringUtil.trimLeadingCharacter(path, PATH_TRIM_CHARS);
		path = StringUtil.trimTrailingCharacter(path, PATH_TRIM_CHARS);
		path = path.replace("\\", "/");

		return path;
	}

	/**
	 * Indent char is a space char but not a line delimiters.
	 * <code>== Character.isWhitespace(ch) && ch != '\n' && ch != '\r'</code>
	 */
	public static boolean isIndentChar(char ch) {
		return Character.isWhitespace(ch) && !isLineDelimiterChar(ch);
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s.trim());
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

	/**
	 * Line delimiter chars are '\n' and '\r'.
	 */
	public static boolean isLineDelimiterChar(char ch) {
		return ch == '\n' || ch == '\r';
	}

	public static boolean isEqual(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		} else {
			return str1.equals(str2);
		}
	}

	public static boolean isLong(String text) {
		try {
			Long.parseLong(text.trim());
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

	public static String formatLong(long ycoordinateValue) {
		String longStr = Long.toString(ycoordinateValue);
		int size = longStr.length();
		if (size > 3) {
			int count = size / 3;
			if (size % 3 == 0) {
				count--;
			}
			StringBuffer formatStr = new StringBuffer();
			int beginIndex = 0;
			for (int i = count; i > 0; i--) {
				int endIndex = size - i * 3;
				formatStr.append(longStr.substring(beginIndex, endIndex)).append(",");
				beginIndex = endIndex;
			}
			formatStr.append(longStr.substring(beginIndex));
			return formatStr.toString();
		}
		return longStr;
	}

	public static String bytesToMega(long v) {
		double value = (double) v;
		double kiloBytes = value / 1024;
		if (kiloBytes == 0) {
			return value + " bytes";
		}
		double megaBytes = kiloBytes / 1024;
		if (megaBytes == 0) {
			BigDecimal bigKiloBytes = new BigDecimal(value)
					.divide(new BigDecimal(1024D), 2, BigDecimal.ROUND_HALF_EVEN);
			return bigKiloBytes.toString() + " KB";
		}
		BigDecimal bigMegaBytes = new BigDecimal(value).divide(new BigDecimal(1024D * 1024D), 2,
				BigDecimal.ROUND_HALF_EVEN);
		return bigMegaBytes.toString() + " MB";
	}

	public static int toASCCharLength(String value) {
		if (value == null)
			return 0;
		char[] chars = value.toCharArray();
		int len = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] > MAXASCII) {
				len += 2;
			} else {
				len++;
			}
		}
		return len;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("(-?[^\\D]+)|(-?[^\\D]+\\.?[^\\D]+)");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isEL(String str) {
		Pattern pattern = Pattern.compile("^#\\u007B[\\s|\\S]*}$");
		Matcher isEL = pattern.matcher(str);
		if (!isEL.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * @param str
	 *            the checking string
	 * @param suffixs
	 *            the anticipant suffixs
	 * @return
	 */
	public static boolean isEndsWith(String str, List<String> suffixs) {
		if (suffixs == null) {
			return false;
		}

		for (String suffix : suffixs) {
			if (str.endsWith(suffix)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * replace all the char to "*"
	 */
	public static String toHiddenChar(String value) {
		if (value == null) {
			return null;
		}

		char[] hidden = new char[value.length()];
		for (int i = 0, size = value.length(); i < size; i++) {
			hidden[i] = '*';
		}

		return String.valueOf(hidden);
	}

	/**
	 * 获取数字随机数
	 * 
	 * @param size 随机数个数
	 * @return 指定个数的数字随机数的字符串
	 */
	public static String getRandom(int size) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			sb.append(random.nextInt(9));
		}
		return sb.toString();
	}

	public static String join(final String gap, final String... array) {
		final StringBuilder builder = new StringBuilder();
		if (null != array && 0 < array.length) {
			for (int i = 0; i < array.length; i++) {
				builder.append(array[i]);
				if (array.length - 1 != i) {
					builder.append(gap);
				}
			}
		}
		return builder.toString();
	}
	
	/**
	 * join string.
	 * 
	 * @param array
	 *            String array.
	 * @return String.
	 */
	public static String join(String[] array) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String s : array)
			sb.append(s);
		return sb.toString();
	}

	/**
	 * join string like javascript.
	 * 
	 * @param array
	 *            String array.
	 * @param split
	 *            split
	 * @return String.
	 */
	public static String join(String[] array, char split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	/**
	 * join string like javascript.
	 * 
	 * @param array
	 *            String array.
	 * @param split
	 *            split
	 * @return String.
	 */
	public static String join(String[] array, String split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	public static String join(Collection<String> coll, String split) {
		if (coll.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String s : coll) {
			if (isFirst)
				isFirst = false;
			else
				sb.append(split);
			sb.append(s);
		}
		return sb.toString();
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
			DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"), TIME_FORMAT = new SimpleDateFormat("HH:mm");

	public static String valueOf(final Object obj) {
		if (null == obj) {
			return "";
		} else if (obj instanceof String) {
			return (String) obj;
		} else if (obj.getClass().isArray()) {
			final Class<?> type = obj.getClass().getComponentType();
			if (boolean.class.equals(type)) {
				return Arrays.toString((boolean[]) obj);
			} else if (int.class.equals(type)) {
				return Arrays.toString((int[]) obj);
			} else if (long.class.equals(type)) {
				return Arrays.toString((long[]) obj);
			} else if (char.class.equals(type)) {
				return Arrays.toString((char[]) obj);
			} else if (byte.class.equals(type)) {
				return Arrays.toString((byte[]) obj);
			} else if (float.class.equals(type)) {
				return Arrays.toString((float[]) obj);
			} else if (double.class.equals(type)) {
				return Arrays.toString((double[]) obj);
			} else if (short.class.equals(type)) {
				return Arrays.toString((short[]) obj);
			} else {
				return Arrays.toString((Object[]) obj);
			}
		} else if (obj instanceof Date) {
			final Date date = (Date) obj;
			if (obj instanceof java.sql.Date) {
				return DATE_FORMAT.format(date);
			} else if (obj instanceof Time) {
				return TIME_FORMAT.format(date);
			} else if (obj instanceof Timestamp) {
				return TIMESTAMP_FORMAT.format(date);
			} else {
				return DATETIME_FORMAT.format(date);
			}
		}
		return String.valueOf(obj);
	}

	public static Date getDateTime(final String str) {
		try {
			return DATETIME_FORMAT.parse(str);
		} catch (final ParseException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static java.sql.Date getDate(final String str) {
		try {
			return new java.sql.Date(DATE_FORMAT.parse(str).getTime());
		} catch (final ParseException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static Time getTime(final String str) {
		try {
			return new Time(TIME_FORMAT.parse(str).getTime());
		} catch (final ParseException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static Timestamp getTimestamp(final String str) {
		try {
			return new Timestamp(TIMESTAMP_FORMAT.parse(str).getTime());
		} catch (final ParseException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	
	/**
	 * 获取字符串的长度，中文占一个字符,英文数字占半个字符
	 * 
	 * @param value 指定的字符串
	 * @return 字符串的长度
	 */
	public static double length(String value) {
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 1;
			} else {
				valueLength += 0.5;
			}
		}
		return Math.ceil(valueLength);
	}
}
