package cn.fancyfree.gui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Image工具类
 * @author AlanMa247@Gmail.COM
 * @date 0:41 2020/9/12
 **/
public class ImageUtils {
    /**
     * 获取图片
     * 1. 先获取用户目录
     * 2。 不存在获取程序类根目录
     * @param path 文件路径
     * @author AlanMa247@Gmail.COM
     * @date 2021/6/10 00:51
     * @return javax.swing.ImageIcon
     */
    public static ImageIcon getImageIcon(String path) {
        ImageIcon imageIcon = new ImageIcon(getImage(path));
        return imageIcon;
    }


    /**
     * 获取图片
     * 1. 先获取用户目录
     * 2。 不存在获取程序类根目录
     * @param path 文件路径
     * @author AlanMa247@Gmail.COM
     * @date 2021/6/10 00:58
     * @return java.awt.Image
     */
    public static Image getImage(String path) {

        File file = new File(System.getProperty("user.dir") + "/" + path);
        if (file.exists()) {
            try {
                Image image = ImageIO.read(new File(System.getProperty("user.dir") + "/" + path));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Image image = ImageIO.read(ImageUtils.class.getResourceAsStream("/" + path));
            return image;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

}
