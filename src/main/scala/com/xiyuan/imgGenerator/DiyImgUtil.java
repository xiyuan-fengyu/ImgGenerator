package com.xiyuan.imgGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiyImgUtil {

	private static final float PI_PER = (float) (Math.PI / 180.0f);

	public static BufferedImage diy(DiyInfo info) {
		BufferedImage bim = new BufferedImage(info.width, info.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bim.createGraphics();
		
		g.setColor(info.background);
		g.fillRect(0, 0, info.width, info.height);

//		int size = diyInfo.imgs.size();
//		for(int i = 0; i < size; i ++)
//		{
//			DiyImgInfo imgInfo = diyInfo.imgs.get(i);
//			try {
//				Image image =  ImageIO.read(new File(EMO_PATH + imgInfo.i + ".png"));
//				if(image != null)
//				{
//					int centerX = imgInfo.x + imgInfo.w / 2;
//					int centerY = imgInfo.y + imgInfo.h / 2;
//					AffineTransform trans = null;
//					if(imgInfo.r != 0)
//					{
//						trans= new AffineTransform();
//						trans.rotate(imgInfo.r * PI_PER, centerX, centerY);
//			            g.setTransform(trans);
//					}
//
//					g.drawImage(image, imgInfo.x, imgInfo.y, imgInfo.w, imgInfo.h, null);
//
//		            if(imgInfo.r != 0 && trans != null)
//					{
//		            	trans.rotate(-imgInfo.r * PI_PER, centerX, centerY);
//			            g.setTransform(trans);
//			            trans = null;
//					}
//				}
//			}
//			catch (Exception e) {
//			}
//		}

		for (DiyItem diyItem: info.items) {
			if (diyItem == null) {
				continue;
			}

			if (diyItem instanceof DiyItemChar) {
				DiyItemChar item = (DiyItemChar) diyItem;
				if(item.rotation != 0)
				{
					g.rotate(item.rotation * PI_PER, item.centerX, item.centerY);
				}

				Font font = FontUtil.getFont("PLAIN", item.size);
				g.setColor(item.color);
				g.setFont(font);
				FontMetrics fm = g.getFontMetrics();
				Rectangle2D bounds = fm.getStringBounds("" + item.c, g);

				int ascent = fm.getAscent();
				int descent = fm.getDescent();
				int x = (int) (item.centerX - bounds.getWidth() / 2);
				int y = item.centerY +  (ascent + descent) / 4;
				g.drawString("" + item.c, x, y);

				if(item.rotation != 0)
				{
					g.rotate(-item.rotation * PI_PER, item.centerX, item.centerY);
				}
			}
			else if (diyItem instanceof DiyItemPoint) {
				DiyItemPoint item = (DiyItemPoint) diyItem;
				int r = item.size / 2;
				g.setColor(item.color);
				g.fillArc(item.centerX - r, item.centerY - r, item.size, item.size, 0, 360);
			}
			else if (diyItem instanceof DiyItemLine) {
				DiyItemLine item = (DiyItemLine) diyItem;
				g.setColor(item.color);
				g.drawLine(item.startX, item.startY, item.endX, item.endY);
			}
		}

		g.dispose();

		return bim;
	}

	public static Tuple<String, BufferedImage> randomCode(int len, int width, int height) {
		String code = RandomUtil.randomCode(len);
		int size = (int) (height * 0.8f * 0.78f);
		float marginLeft = width * 0.1f;
		int perCharWidth = (int) (width * 0.8f / len);
		int centerHeight = height / 2;

		DiyInfo info = new DiyInfo(width, height, Color.WHITE);
		for (int i = 0; i < len; i++) {
			info.items.add(new DiyItemChar(code.charAt(i), size, (int) (marginLeft + perCharWidth * (i + 0.5f)), centerHeight, RandomUtil.randomBetween(-30, 30), null, true));
		}

		final float pointPercent = 0.005f;
		int pointNum = (int) (width * height * pointPercent);
		for (int i = 0; i < pointNum; i++) {
			info.items.add(new DiyItemPoint(RandomUtil.randomBetween(1, 5), RandomUtil.randomBetween(5, width - 5), RandomUtil.randomBetween(5, height - 5)));
		}

		int lineNum = RandomUtil.randomBetween(1, 5);
		for (int i = 0; i < lineNum; i++) {
			info.items.add(new DiyItemLine(RandomUtil.randomBetween(0, width), RandomUtil.randomBetween(0, height), RandomUtil.randomBetween(0, width), RandomUtil.randomBetween(0, height), null));
		}

		return new Tuple<>(code, diy(info));
	}
	
	public static void main(String[] args) {

		BufferedImage img = randomCode(5, 100, 40)._2;
		File file = new File("C:\\Users\\xiyuan_fengyu\\Desktop\\diy.png");
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			ImageIO.write(img, "PNG", outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
