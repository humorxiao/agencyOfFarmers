package scau.zxck.utils;

import java.util.Map;

public class RSAManagerUtil {

  // 单例模式
  private static RSAManagerUtil instance;
  private static String publicKey;
  private static String privateKey;



  private RSAManagerUtil() {
    this.startCircleKeyThread();
  }

  public static synchronized RSAManagerUtil getInstance() {
    if (instance == null) {
      instance = new RSAManagerUtil();
    }
    return instance;
  }

  public static String getPublicKey() {
    return publicKey;
  }


  public static String getPrivateKey() {
    return privateKey;
  }



  private void createKey() {

    try {
      Map<String, Object> keyMap;
      keyMap = RSAUtil.genKeyPair();
      publicKey = RSAUtil.getPublicKey(keyMap);
      privateKey = RSAUtil.getPrivateKey(keyMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void startCircleKeyThread() {
    CircleKeyThread t = new CircleKeyThread();
    new Thread(t).start();
  }

  public String encryptByPublicKey(String str, String publick) throws Exception {
    byte[] data = str.getBytes();
    byte[] encodedData = RSAUtil.encryptByPublicKey(data, publick);
    return byteToString(encodedData);
  }

  public String encryptByPrivateKey(String str) throws Exception {
    byte[] data = str.getBytes();
    byte[] encodedData = RSAUtil.encryptByPrivateKey(data, privateKey);
    return byteToString(encodedData);
  }

  public String decryptByPublicKey(String str, String publick) throws Exception {
    byte[] data = stringToByte(str);
    byte[] decodedData = RSAUtil.decryptByPublicKey(data, publick);
    return new String(decodedData);
  }

  public String decryptByPrivateKey(String str) throws Exception {
    byte[] data = stringToByte(str);
    byte[] decodedData = RSAUtil.decryptByPrivateKey(data, privateKey);
    return new String(decodedData);
  }

  // 把byte[]元素之间添加空格，并转化成字符串返回，
  public String byteToString(byte[] resouce) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < resouce.length; i++) {
      if (i == resouce.length - 1) {
        sb.append(Byte.toString(resouce[i]));
      } else {
        sb.append(Byte.toString(resouce[i]));
        sb.append(" ");
      }
    }
    return sb.toString();

  }

  // 把字符串按照空格进行拆分成数组，然后转化成byte[],返回
  public byte[] stringToByte(String resouce) {
    String[] strArr = resouce.split(" ");
    int len = strArr.length;
    byte[] clone = new byte[len];
    for (int i = 0; i < len; i++) {
      clone[i] = Byte.parseByte(strArr[i]);
    }
    return clone;
  }

  class CircleKeyThread implements Runnable {

    public CircleKeyThread() {
      super();
      createKey();
    }


    @Override
    public void run() {
      // TODO Auto-generated method stub
      while (true) {

        try {
          Thread.sleep(3600000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        createKey();
      }
    }
  }

}
