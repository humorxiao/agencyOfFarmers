package scau.zxck.base.utils;


import java.awt.*;
import java.util.Random;

/**
 * 生成数字校验码
 * 
 * @author kevins
 * 
 */
public class RandomUtil {
    public static final String numberChar = "23456789";
    
    public static final String strChar = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    
    public static final String lowerCaseChar = "abcdefghijkmnpqrstwxyz";

    public static final String allChar = numberChar + strChar + lowerCaseChar;

    public static final String noUpperCaseChar = numberChar + lowerCaseChar;
    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     * 
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generatePassword(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int size = allChar.length();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(size)));
        }
        return sb.toString();
    }

    /**
     * <li>方法描述：获取数字随机数</li>
     * 
     * @param length
     * @return
     */
    public static String generateNumber(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }

    /**
     * <li>方法描述：获取数字、小写字母随机数
     * 
     * @param size 指定随机数的长度
     * @return
     */
    public static String getRandom(int size) {
    	String str = numberChar + lowerCaseChar;
        StringBuilder sb = new StringBuilder(size);
     
        for(int i = 0; i < size; i++){
        	int intValue=(int)(Math.random() * 30);
        	sb.append(str.charAt(intValue));
        }
        return sb.toString();
    }

    /**
     * <li>方法描述：根据随即码以及宽高绘制图片函数
     * 
     * @param g
     * @param width
     * @param height
     * @param randomCode
     */
    public static void drawRandomPicture(Graphics g, int width, int height, String randomCode) {
        g.setColor(randColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        g.setColor(randColor(160, 200));

        Random random = new Random(System.currentTimeMillis());

        //  随机产生155条干扰线，使图像中的验证码不易被识别
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        //  将验证码显示在图像中
        for (int i = 0; i < 4; i++) {
            g.setColor(randColor(20, 130));
            g.drawString(randomCode.substring(i, i + 1), 13 * i + 25, 25);
        }
    }

    /**
     * <li>方法描述：获取随机颜色
     * 
     * @param fc
     * @param bc
     * @return
     */
    public static Color randColor(int fc, int bc) {
        Random random = new Random(System.currentTimeMillis());
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
}
