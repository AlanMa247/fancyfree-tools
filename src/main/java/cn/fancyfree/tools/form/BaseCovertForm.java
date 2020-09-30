package cn.fancyfree.tools.form;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 转换模板
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class BaseCovertForm extends BaseForm {

    JLabel labelIn = new JLabel("要加解密的字符串：");
    JTextArea input = new JTextArea();
    JLabel labelOut = new JLabel("加解密的结果：");
    JTextArea out = new JTextArea();

    List<JButton> opts = new ArrayList<>();

    public BaseCovertForm(String winName, String appName, String inputTitle, String outputTitle, List<JButton> opts) {
        super(winName, appName);
        labelIn.setText(inputTitle);
        labelOut.setText(outputTitle);
        this.opts.addAll(opts);
        this.init();
        this.addListener();
    }

    void init() {

        // 转换内容
        // 输入
        labelIn.setBounds((getWidth() - 600) / 2, baseY + 60 , 200, 50);
        labelIn.setForeground(word);
        labelIn.setFont(base);
        add(labelIn);
        input.setBounds((getWidth() - 500) / 2, baseY + 110, 500, 120);
        add(input);
        // 输出
        labelOut.setBounds((getWidth() - 600) / 2, baseY + 230 , 200, 50);
        labelOut.setForeground(word);
        labelOut.setFont(base);
        add(labelOut);
        out.setBounds((getWidth() - 500) / 2, baseY + 300, 500, 120);
        add(out);

        // 操作
        opts.forEach(opt -> {
            opt.setBounds( getWidth() - opts.indexOf(opt) * 120 - 150, baseY + 62 , 120, 40);
            add(opt);
        });

        // 防止最后一个元素位置错乱
        add(new JLabel());

    }

    /**
     * 事件监听
     */
    void addListener() {
    }

}
