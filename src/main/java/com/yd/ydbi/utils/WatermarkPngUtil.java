package com.yd.ydbi.utils;
 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
 
/*******************************************************************************
 * Description: 图片水印工具类 
 * Copyright: Copyright (c) 2011 
 * Company: Founder 
 * Project: CMS
 * 
 * @Author taoxg
 * @version 1.0
 * @See 
 * HISTORY 2011-9-27 taoxg create
 ******************************************************************************/
public class WatermarkPngUtil {
     
    // 水印横向位置
//    private static int positionWidth = 10;
    // 水印纵向位置
//    private static int positionHeight = 300;
    // 水印文字字体
    private static Font font = new Font("微软雅黑", Font.PLAIN, 12);
    // 水印文字颜色
    private static Color color = new Color(222,222,222);
     
   
    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     * 
     * @param logoText
     * @param srcImgPath
     * @param targerPath
     * @param degree
     */
    public static void markImageByText(OutputStream os,String logoText, Integer degree,int width, int height) {
         
        try {
            // 1、源图片
//            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
         // ----------  增加下面的代码使得背景透明  -----------------  
            buffImg = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);  
            g.dispose();  
            g = buffImg.createGraphics();  
            // ----------  背景透明代码结束  -----------------  
              
              
            // 画图  
            g.setColor(new Color(255,0,0));  
            g.setStroke(new BasicStroke(1)); 
            
            
            
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),(double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, 0, 45);
            // 9、释放资源
            g.dispose();
            
            ImageIO.write(buffImg, "PNG", os);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String [] args){
        String logoText = "康元佳 29999497";
         
        String targerPath = "d:/shuiyin.png";
         
        System.out.println("给图片添加水印文字开始...");
        // 给图片添加水印文字,水印文字旋转-30  
//        markImageByText(logoText, targerPath, -30,130,80);  
        System.out.println("给图片添加水印文字结束...");
         
    }
 
}