package scau.zxck.base.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MD5Util {

	public static byte[] md5ToByte(File file) throws NoSuchAlgorithmException, IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fis);
			return md5ToByte(in);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					// ignore
				}
			}
		}
	}

	public static byte[] md5ToByte(InputStream stream) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[8192];
		int length = -1;
		while ((length = stream.read(buffer)) != -1) {
			md.update(buffer, 0, length);
		}
		return md.digest();
	}

	public static byte[] md5ToByte(byte[] in) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		// byte[] out = new byte[16];
		md.reset();
		md.update(in);
		// out = md.digest();
		// return out;
		return md.digest();
	}

	public static byte[] md5ToByte(byte[] in, int len) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		// byte[] out = new byte[16];
		md.reset();
		md.update(in, 0, len);
		// out = md.digest();
		// return out;
		return md.digest();
	}

	public static String md5ToHex(byte[] in) throws NoSuchAlgorithmException {
		byte[] b = md5ToByte(in);
		return HexUtil.byteArrToHex(b);
	}

	public static String md5ToHex(byte[] in, int len) throws NoSuchAlgorithmException {
		byte[] b = md5ToByte(in, len);
		return HexUtil.byteArrToHex(b);
	}

	public static String md5ToHex(final String str) throws NoSuchAlgorithmException {
		return md5ToHex(str.getBytes());
	}
	
	public static String md5(String content, String randomKey) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		sb.append(randomKey).append(content);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(sb.toString().getBytes());
		return HexUtil.byteArrToHex(md.digest());
	}
}
