package com.jcx.utils;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class CaptchaUtil {

    public static String[] operator = {"+", "-", "*"};
    public static Integer result;

    public static String[] random() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(3);
        int n = random.nextInt(3)+2;
        for (int i = 0; i < n; i++) {
            if(i == n-1){
                sb.append(random.nextInt(10));
            }else{
                int idx = random.nextInt(operator.length);
                sb.append(random.nextInt(10)).append(operator[idx]);
            }
        }
        String s = sb.toString();
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            result = (Integer) engine.eval(s);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        String[] str = {s,result.toString()};
        return str;
    }

    public static void outputImage(String str,OutputStream os) {
        BufferedImage image = new BufferedImage(200, 50, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 50);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("楷体", Font.PLAIN, 36));
        graphics.drawString(str, 0, 40);
        graphics.drawLine(0, 0, 200, 50);
        graphics.drawLine(200, 0, 0, 50);
        graphics.drawOval(0, 0, 200, 50);
        try {
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
