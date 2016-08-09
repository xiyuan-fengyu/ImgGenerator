package com.xiyuan.imgGenerator;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class FontUtil
{
	public static String FONT_PATH;

	private static Map<String, Font> fontCache = new HashMap<String, Font>();
	
    public static Font loadFont(String fontFileName, int fontStyle, float fontSize)  //第一个参数是外部字体名，第二个是字体大小
    {
        try
        {
            File file = new File(fontFileName);
            FileInputStream input = new FileInputStream(file);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, input);
            dynamicFont = dynamicFont.deriveFont(fontSize);
            dynamicFont = dynamicFont.deriveFont(fontStyle);
            input.close();
            return dynamicFont;
        }
        catch(Exception e)//异常处理
        {
//            e.printStackTrace();
            return new Font("微软雅黑", Font.PLAIN, (int)fontSize);
        }
    }
    
//    Font.PLAIN, Font.BOLD, Font.ITALIC
    public static Font createFont(String style, int size){
        int styleInt = Font.PLAIN;
        if(style.equals("BOLD"))
        {
        	styleInt = Font.BOLD;
        }
        else if(style.equals("ITALIC"))
        {
        	styleInt = Font.ITALIC;
        }
        Font font = FontUtil.loadFont(FONT_PATH + "default.ttf", styleInt, size);//调用
        return font;
    }

    public static Font getFont(String style, int size){
    	if(style == null)
    	{
    		style = "PLAIN";
    	}
    	else if(!style.equals("BOLD") && !style.equals("ITALIC"))
    	{
    		style = "PLAIN";
    	}
    	
    	if(size < 12)
    	{
    		size = 12;
    	}
    	else if(size > 50)
    	{
    		size = 50;
    	}
    	
    	String key = style + "" + size;
    	Font font = fontCache.get(key);
    	if(font == null)
    	{
    		font = createFont(style, size);
    		fontCache.put(key, font);
    	}
    	return font;
    }
    
}