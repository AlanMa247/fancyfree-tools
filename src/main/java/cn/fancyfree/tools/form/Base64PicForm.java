package cn.fancyfree.tools.form;

import sun.misc.BASE64Encoder;
import sun.nio.ch.IOUtil;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;

/**
 * Base64转解码
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class Base64PicForm extends BaseForm {
    JTextField path = new JTextField();
    JButton chose = new JButton("选择文件");
    JTextArea base64 = new JTextArea();
    JButton img = new JButton();
    JButton cover2Base64 = new JButton("转Base64");
    JButton showImg = new JButton("显示图片");

    public Base64PicForm(String winName, String appName) {
        super(winName, appName);
        path.setBounds((getWidth() - 300 - 150) / 2, 100, 300, 40);
        add(path);
        chose.setBounds((getWidth() + 300 - 120 + 30) / 2, 100, 120, 40);
        add(chose);
        base64.setBounds((getWidth() - 300 - 150) / 2, 150, 450, 200);
        add(base64);
        img.setBounds((getWidth() - 400) / 2, (getHeight() - 400) / 2, 400, 400);
        img.setVisible(false);
        add(img);
        cover2Base64.setBounds((getWidth() - 120 - 120 - 30) / 2, 360, 120, 40);
        add(cover2Base64);
        showImg.setBounds((getWidth() + 30) / 2, 360, 120, 40);
        add(showImg);
        add(new JLabel());
        chose.addActionListener(e -> addListener());
        cover2Base64.addActionListener(e -> {
            if ("".equals(path.getText())) {
                base64.setText("请先选择文件或者输入文件路径");
                return;
            }
            try {
                File file = new File(path.getText());
                if (!file.exists()) {
                    base64.setText("文件不存在");
                    return;
                }
                BufferedImage bi = ImageIO.read(file);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bi, "JPG", outputStream);
                base64.setText(Base64.getEncoder().encodeToString(outputStream.toByteArray()));
            } catch (Exception exception) {
                base64.setText("转换失败");
            }
        });
        showImg.addActionListener(e -> {
            // 日历图标
            ImageIcon c = new ImageIcon(Base64.getDecoder().decode(base64.getText()));
            c.setImage(c.getImage().getScaledInstance(380, 380, Image.SCALE_DEFAULT));
            img.setIcon(c);
            hideAll();
            img.setVisible(true);
        });
        img.addActionListener(e -> {
            showAll();
            img.setVisible(false);
        });

    }

    void addListener() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);
        int ret = chooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            path.setText(file.getAbsolutePath());
            try {
                BufferedImage bi = ImageIO.read(file);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bi, "JPG", outputStream);
                base64.setText(Base64.getEncoder().encodeToString(outputStream.toByteArray()));
            } catch (Exception e) {
                base64.setText("转换失败");
            }
        }
    }

    void hideAll() {
        path.setVisible(false);
        chose.setVisible(false);
        base64.setVisible(false);
        img.setVisible(false);
        cover2Base64.setVisible(false);
        showImg.setVisible(false);
    }

    void showAll() {
        path.setVisible(true);
        chose.setVisible(true);
        base64.setVisible(true);
        img.setVisible(true);
        cover2Base64.setVisible(true);
        showImg.setVisible(true);
    }

}
