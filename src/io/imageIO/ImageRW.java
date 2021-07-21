package io.imageIO;

import jdk.nashorn.internal.ir.WithNode;
import org.springframework.beans.BeanUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author: Administrator.
 * @Description: TODO()
 * @since:Created in 2019/10/10 0010.
 * @Modified By:
 */
public class ImageRW {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\p1.png");//本地图片
//        BufferedImage originalImage=(BufferedImage) ImageIO.read(file);
        BufferedImage originalImage = ImageIO.read(new FileInputStream(file));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        System.out.println(width+","+height);
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        BeanUtils.copyProperties(originalImage,newImage);
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        ImageIO.write(newImage, "png", new File("C:\\Users\\Administrator\\Desktop\\p1_new.png"));
//        fileIOTest();

    }

    public static void fileIOTest() throws IOException {
        File fileIn = new File("C:\\Users\\Administrator\\Desktop\\p1.png");
        File fileOut = new File("C:\\Users\\Administrator\\Desktop\\p1_new.png");
        FileInputStream fileInputStream = new FileInputStream(fileIn);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut, true);
        //建立缓冲字节数组读存文件
        byte[] buf = new byte[1024];
        while ((fileInputStream.read(buf)) != -1) {
            fileOutputStream.write(buf);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }
}
