package scau.zxck.base.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class CharacterFilterUtil {
	
	/**
	 * 字符串会加上引号，过滤掉一些非法字符，转义一些字符防止注入
	 * 
	 * @param value
	 * @return
	 */
	public static String filter(String value) {
		String result = value;
		if (isEscapeNeededForString(value, value.length())) {
			result = escape(result, "utf8");
		} else {
			result = "\"" + result + "\"";
		}
		// FIXME 过滤掉4字节utf8字符，因为数据库没升级为支持utf8mb4
		if (isNeedFilterOutUtf8mb4(result, result.length())) {
			result = filterOffUtf8Mb4(result);
		}
		return result;
	}
	
	public static boolean isNeedFilter(String value) {
		if (isEscapeNeededForString(value, value.length())) {
			return true;
		}
		return false;
	}

	private static String filterOffUtf8Mb4(String text) {
		try {
			byte[] bytes = text.getBytes("utf-8");
			int length = bytes.length;
			ByteBuffer buffer = ByteBuffer.allocate(length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[i++]);
					continue;
				}
				b += 256;
				// 2字节utf8字符110xxxxx 10xxxxxx
				// 可能是1100开头，也就是0xC0，也可能是1101开头，也就是0xD0
				if (((b ^ 0xC0) >> 4 == 0) || ((b ^ 0xD0) >> 4 == 0)) {
					buffer.put(bytes, i, 2);
					i += 2;
				} else if ((b ^ 0xE0) >> 4 == 0) {
					// 3字节utf8字符1110xxxx 10xxxxxx 10xxxxxx
					buffer.put(bytes, i, 3);
					i += 3;
				} else if ((b ^ 0xF0) >> 4 == 0) {
					// 4字节utf8字符11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
					length -= 4; // 减少了一个4字节的位置
					i += 4;
				} else {
					// FIXME 未知类型，可能传入了二进制数据，直接返回
					return text;
				}
			}
			buffer.flip();
			return new String(buffer.array(), 0, length, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return text;
		}
	}

	private static boolean isNeedFilterOutUtf8mb4(String x, int stringLength) {
		boolean needsFilterCharOver4bitUTF8 = false;
		try {
			byte[] bytes = x.getBytes("utf-8");
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				// 1字节utf8字符0xxxxxxx
				if (b > 0) {
					i++;
					continue;
				}
				b += 256;
				// 2字节utf8字符110xxxxx 10xxxxxx
				// 可能是1100开头，也就是0xC0，也可能是1101开头，也就是0xD0
				if (((b ^ 0xC0) >> 4 == 0) || ((b ^ 0xD0) >> 4 == 0)) {
					i += 2;
				} else if ((b ^ 0xE0) >> 4 == 0) {
					// 3字节utf8字符1110xxxx 10xxxxxx 10xxxxxx
					i += 3;
				} else if ((b ^ 0xF0) >> 4 == 0) {
					// 4字节utf8字符11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
					needsFilterCharOver4bitUTF8 = true;
					break;
				} else {
					// FIXME 这是什么字符，可能是二进制数据
					needsFilterCharOver4bitUTF8 = false;
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// ignore
		}
		return needsFilterCharOver4bitUTF8;
	}

	private static String escape(String x, String encoding) {
		StringBuffer buf = new StringBuffer((int) (x.length() * 1.1));

		buf.append('\'');

		//
		// Note: buf.append(char) is _faster_ than
		// appending in blocks, because the block
		// append requires a System.arraycopy()....
		// go figure...
		//
		for (int i = 0; i < x.length(); ++i) {
			char c = x.charAt(i);

			switch (c) {
				case 0: /* Must be escaped for 'mysql' */
					buf.append('\\');
					buf.append('0');
	
					break;
	
				case '\n': /* Must be escaped for logs */
					buf.append('\\');
					buf.append('n');
	
					break;
	
				case '\r':
					buf.append('\\');
					buf.append('r');
	
					break;
	
				case '\\':
					buf.append('\\');
					buf.append('\\');
	
					break;
	
				case '\'':
					buf.append('\\');
					buf.append('\'');
	
					break;
				// case '"': Better safe than sorry
				// if (this.usingAnsiMode) {
				// buf.append('\\');
				// }
				//
				// buf.append('"');
				//
				// break;
				case '\032': /* This gives problems on Win32 */
					buf.append('\\');
					buf.append('Z');
	
					break;
	
				case '\u00a5':
				case '\u20a9':
					// escape characters interpreted as backslash by mysql
					if (encoding != null) {
						CharsetEncoder charsetEncoder = Charset.forName(encoding).newEncoder();
						CharBuffer cbuf = CharBuffer.allocate(1);
						ByteBuffer bbuf = ByteBuffer.allocate(1);
						cbuf.put(c);
						cbuf.position(0);
						charsetEncoder.encode(cbuf, bbuf, true);
						if (bbuf.get(0) == '\\') {
							buf.append('\\');
						}
					}
					// fall through
				default:
					buf.append(c);
			}
		}
		buf.append('\'');
		return buf.toString();
	}

	private static boolean isEscapeNeededForString(String x, int stringLength) {
		boolean needsHexEscape = false;

		for (int i = 0; i < stringLength; ++i) {
			char c = x.charAt(i);

			switch (c) {
				case 0: /* Must be escaped for 'mysql' */
	
					needsHexEscape = true;
					break;
	
				case '\n': /* Must be escaped for logs */
					needsHexEscape = true;
	
					break;
	
				case '\r':
					needsHexEscape = true;
					break;
	
				case '\\':
					needsHexEscape = true;
	
					break;
	
				case '\'':
					needsHexEscape = true;
	
					break;
	
				case '"': /* Better safe than sorry */
					needsHexEscape = true;
	
					break;
	
				case '\032': /* This gives problems on Win32 */
					needsHexEscape = true;
					break;
			}
			if (needsHexEscape) {
				break; // no need to scan more
			}
		}
		return needsHexEscape;
	}
}
