package cn.fancyfree.gui.form;

import javax.swing.*;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * MD5工具
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class Md5Form extends BaseCovertForm {

    public Md5Form(String winName, String appName) {
        super(winName, appName,
                "需要MD5内容", "MD5结果",
                Arrays.asList(new JButton[]{new JButton("Md5加密")}));

        this.addListener();
    }

    /**
     * 事件监听
     */
    @Override
    void addListener() {
        super.opts.get(0).addActionListener(e -> {
            try {
                out.setText(new String(encodeHex(MessageDigest.getInstance("MD5").digest(input.getText().getBytes()))));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 转换16进制
     * @author AlanMa247@Gmail.COM
     * @date 17:59 2020/9/18
     * @param bytes 字符互传
     * @return char[]
    **/
    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }
        return chars;
    }
}
