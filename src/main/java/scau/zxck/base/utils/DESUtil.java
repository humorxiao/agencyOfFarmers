package scau.zxck.base.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DESUtil {

	private static BASE64Encoder base64en = new BASE64Encoder();
	private static BASE64Decoder base64de = new BASE64Decoder();

	public static String encrypto(String data, String key) throws Exception {

		if (StringUtil.isEmpty(data)) {
			return null;
		}
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 从原始密匙创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes("utf-8"));
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
		// 执行数据并加密
		return base64en.encode(cipher.doFinal(data.getBytes("utf-8")));
	}

	public static String decrypto(String data, String key) throws Exception {
		
		if (StringUtil.isEmpty(data)) {
			return null;
		}
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 从原始密匙创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes("utf-8"));
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 执行数据并解密
		return new String(cipher.doFinal(base64de.decodeBuffer(data)), "utf-8");
	}

	public static void main(String[] args) {
		// 待加密内容
		String str = "qwifi1001";
		// 密码，长度要是8的倍数
		String password = "qwifi1001";
		// 直接将如上内容解密
		try {
			String result = DESUtil.encrypto(str, password);
			System.out.println("加密后内容为：" + result);
			String decryResult = DESUtil.decrypto(result, password);
			System.out.println("解密后内容为：" + decryResult);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
