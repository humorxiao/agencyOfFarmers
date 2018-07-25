package scau.zxck.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AuthCodeUtil {
	private static String sRand;


	public static String getsRand() {
		return sRand;
	}

	private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static BufferedImage createImage() {
		int width = 109, height = 40;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下??
		Graphics g = image.getGraphics();

		// 生成随机??
		Random random = new Random();

		// 设定背景??
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		 g.setFont(new Font("Times New Roman",Font.ITALIC,40));

		// 画边??
		// g.setColor(new Color());
		 g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测??
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 300; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证??(4位数??)
		// String rand = request.getParameter("rand");
		// rand = rand.substring(0,rand.indexOf("."));
		sRand = "";// 清空原有字符
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生??
			g.drawString(rand, 25 * i + 6, 34);
		}
		// 图象生效
		g.dispose();
		return image;
	}
}