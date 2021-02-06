package com.xf.foundation.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

public class SealUtils {

	private static final int ROUND_IMG_SIZE = 300;// 圆章图片宽度
	private static final int ROUND_RADIUS = 140;// 圆圈的半径
	private static final int ROUND_STROKE = 8;// 圆圈线的宽度
	private static final int CENTERX = ROUND_IMG_SIZE / 2;// 画图中心X坐标位置
	private static final int CENTERY = ROUND_IMG_SIZE / 2;// 画图中心Y坐标位置
	private static String centerText = "业务专用章";
	private static Color backgroudColor = Color.RED;

	private static final int SQUARE_IMG_HEIGHT = 100;// 方章图片高度
	private static final int SQUARE_IMG_WIDTH = 200;// 方章图片宽度
	private static final int SQUARE_STROKE = 6;// 方形的宽度

	/**
	 * 背景色，默认红色
	 */
	public static void main(String[] args) throws Exception {
		String companyName = "公司艾科技科技有限公司公司艾科";
		BufferedImage image = makeRoundSeal(companyName);
		try {
			String filePath = "I:/test/seal/" + new Date().getTime() + ".png";
			ImageIO.write(image, "png", new File(filePath)); // 将其保存在D:\\下，得有这个目录
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*String personName = "王明";
		BufferedImage image = makeSquareSeal(personName);
		try {
			String filePath = "I:/test/seal/person_" + new Date().getTime() + ".png";
			ImageIO.write(image, "png", new File(filePath)); // 将其保存在D:\\下，得有这个目录
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}

	/**
	 * 制作圆章
	 * 
	 * @return
	 */
	public static BufferedImage makeRoundSeal(String companyName) {
		// 定义图像buffer
		// Image是一个抽象类，BufferedImage是其实现类，是一个带缓冲区图像类，主要作用是将一幅图片加载到内存中（BufferedImage生成的图片在内存里有一个图像缓冲区，利用这个缓冲区我们可以很方便地操作这个图片），提供获得绘图对象、图像缩放、选择图像平滑度等功能，通常用来做图片大小变换、图片变灰、设置透明不透明等
		BufferedImage buffImg = new BufferedImage(ROUND_IMG_SIZE, ROUND_IMG_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 增加下面代码使得背景透明
		buffImg = g.getDeviceConfiguration().createCompatibleImage(ROUND_IMG_SIZE,
				ROUND_IMG_SIZE, Transparency.TRANSLUCENT);
		g.dispose();
		g = buffImg.createGraphics();
		// 背景透明代码结束
		g.setColor(backgroudColor);
		// 设置锯齿圆滑
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 绘制圆圈
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + ROUND_RADIUS, CENTERY + ROUND_RADIUS);
		// 设置圆的宽度
		g.setStroke(new BasicStroke(ROUND_STROKE));
		g.draw(circle);
		// 绘制圆弧形文字
		drawArcStr(g , ROUND_RADIUS ,companyName);
		// 绘制中间的五角星
		String logoStr = "★";
		Font logoFont = new Font("Serif", Font.BOLD, 100);
		g.setFont(logoFont);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D logoRectangle2D = logoFont.getStringBounds(logoStr, context);
		g.drawString(logoStr, (float) (CENTERX - logoRectangle2D.getCenterX() + 1),
				(float) (CENTERY - logoRectangle2D.getCenterY()));
		// 公章中心文字
		Font centerTextFont = new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 30);
		g.setFont(centerTextFont);
		Rectangle2D textRectangle2D = centerTextFont.getStringBounds(centerText, context);
		g.drawString(centerText, (float) (CENTERX - textRectangle2D.getCenterX() + 1),
				(float) (CENTERY - textRectangle2D.getCenterY() + 70));
		return buffImg;
	}

	/**
	 * 绘制圆弧形文字
	 * 
	 * @param g
	 * @param circleRadius
	 * @param arcStr
	 */
	private static void drawArcStr(Graphics2D g, int circleRadius, String arcStr) {
		// 根据输入字符串得到字符数组
		char[] arcStrArr = arcStr.toCharArray();
		// 字体长度
		int textLength = arcStrArr.length;
		// 设置字体属性
		int fontsize = (50 - textLength * 2);
		Font textFont = new Font("Serif", Font.BOLD, fontsize);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D rectangle = textFont.getStringBounds(arcStr, context);
		// 文字之间间距，默认动态调整
		double fontSpace;
		if (textLength == 1) {
			fontSpace = 0;
		} else {
			fontSpace = rectangle.getWidth() / (textLength - 1) * 0.9;
		}
		// 距离外圈距离
		int marginSize = 10;
		// 写字
		double newRadius = circleRadius + rectangle.getY() - marginSize;
		double radianPerInterval = 2 * Math.asin(fontSpace / (2 * newRadius));

		double fix = 0.18;
		double firstAngle;
		if (textLength % 2 == 1) {
			firstAngle = (textLength - 1) * radianPerInterval / 2.0 + Math.PI / 2 + fix;
		} else {
			firstAngle = (textLength / 2.0 - 0.5) * radianPerInterval + Math.PI / 2 + fix;
		}

		for (int i = 0; i < textLength; i++) {
			double theta;
			double thetaX;
			double thetaY;

			theta = firstAngle - i * radianPerInterval;
			thetaX = newRadius * Math.sin(Math.PI / 2 - theta);
			thetaY = newRadius * Math.cos(theta - Math.PI / 2);
			AffineTransform transform;
			transform = AffineTransform.getRotateInstance(Math.PI / 2 - theta + Math.toRadians(8));
			Font f2 = textFont.deriveFont(transform);
			g.setFont(f2);
			g.drawString(Character.toString(arcStrArr[i]),
					(float) (circleRadius + thetaX + marginSize),
					(float) (circleRadius - thetaY + marginSize));
		}
	}

	/**
	 * 制作方章
	 * 
	 * @return
	 */
	public static BufferedImage makeSquareSeal(String personName) {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(SQUARE_IMG_WIDTH, SQUARE_IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 增加下面代码使得背景透明
		buffImg = g.getDeviceConfiguration().createCompatibleImage(SQUARE_IMG_WIDTH,
				SQUARE_IMG_HEIGHT, Transparency.TRANSLUCENT);
		g.dispose();
		g = buffImg.createGraphics();
		// 背景透明代码结束
		g.setColor(backgroudColor);
		// 设置锯齿圆滑
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(SQUARE_STROKE));
		g.drawRect(0, 0, SQUARE_IMG_WIDTH, SQUARE_IMG_HEIGHT);
		Font logoFont = new Font("Serif", Font.BOLD, 40);
		g.setFont(logoFont);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D nameRectangle2D = logoFont.getStringBounds(personName, context);
		g.drawString(personName, (float) (SQUARE_IMG_WIDTH/2 - nameRectangle2D.getCenterX() + 1),
				(float) (SQUARE_IMG_HEIGHT/2 - nameRectangle2D.getCenterY()));
		return buffImg;
	}
}
