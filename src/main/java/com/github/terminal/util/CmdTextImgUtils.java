package com.github.terminal.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 文字图片输出
 *
 * @author 欧阳洁
 * @date 2020/10/29 19:14
 */
public class CmdTextImgUtils {
    public static void printPicByPath(String path) {
        try {
            // 获取图像资源，转成BufferedImage对象
            BufferedImage bimg = ImageIO.read(new File(path));
            CmdTextImgUtils.pringPic(bimg);
        } catch (IOException e) {
            System.out.println("--------------------输出图片时候产生异常！---------------------");
            e.printStackTrace();
        }
    }
    public static void printPicByUrl(String url) {
        try {
            // 获取图像资源，转成BufferedImage对象
            BufferedImage bimg = ImageIO.read(new URL(url));
            CmdTextImgUtils.pringPic(bimg);
        } catch (IOException e) {
            System.out.println("--------------------输出图片时候产生异常！---------------------");
            e.printStackTrace();
        }
    }

    private static void pringPic(BufferedImage bimg) {
        // 创建一个二维数组，用来存放图像每一个像素位置的颜色值
        int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
        // 以高度为范围，逐列扫描图像，存进数组对应位置
        for (int i = 0; i < bimg.getWidth(); i++) {
            for (int j = 0; j < bimg.getHeight(); j++) {
                // 得到的是sRGB值，4字节
                data[i][j] = bimg.getRGB(i, j);
            }
        }
        // 将数组旋转90°输出，实现逐行输出图像
        for (int i = 0; i < bimg.getHeight(); i++) {
            for (int j = 0; j < bimg.getWidth(); j++) {
                if (data[j][i] != -1) {
                    // 有颜色输出
                    System.out.print("**");
                } else {
                    // 无颜色输出
                    System.out.print("  ");
                }
            }
            // 每行结束换行
            System.out.println();
        }
    }
}
