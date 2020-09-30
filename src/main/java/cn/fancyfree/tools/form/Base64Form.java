package cn.fancyfree.tools.form;

import javax.swing.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;

/**
 * Base64转解码
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class Base64Form extends BaseCovertForm {

    public Base64Form(String winName, String appName) {
        super(winName, appName,
                "Base64转/解码内容", "Base64转/解码结果",
                Arrays.asList(new JButton[]{new JButton("Base64转码"), new JButton("Base64解码")}));
        this.addListener();
    }

    /**
     * 事件监听
     */
    @Override
    void addListener() {
        super.opts.get(0).addActionListener(e -> {
            try {
                out.setText(Base64.getEncoder().encodeToString(input.getText().getBytes()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        super.opts.get(1).addActionListener(e -> {
            try {
                out.setText(new String(Base64.getDecoder().decode(input.getText().getBytes()), "UTF-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
