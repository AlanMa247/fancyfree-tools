package cn.fancyfree.tools.form;

import javax.swing.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 材料力学性能查询界面
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class UrlForm extends BaseCovertForm {

    public UrlForm(String winName, String appName) {
        super(winName, appName,
                "URL转/解码参数", "转/解码结果",
                Arrays.asList(new JButton[]{new JButton("URL转码"), new JButton("URL解码")}));
        addListener();
    }

    /**
     * 事件监听
     */
    @Override
    void addListener() {
        super.opts.get(0).addActionListener(e -> {
            try {
                out.setText(URLEncoder.encode(input.getText(), "UTF-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        super.opts.get(1).addActionListener(e -> {
            try {
                out.setText(URLDecoder.decode(input.getText(), "UTF-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
