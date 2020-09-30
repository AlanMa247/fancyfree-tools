package cn.fancyfree.tools.form;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 材料力学性能查询界面
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class MainForm extends BaseForm {

    /** 时间戳 */
    private JButton timestamp = new JButton("时间戳转换");
    /** MD5 */
    private JButton md5 = new JButton("MD5加密");
    /** BASE64 */
    private JButton base64 = new JButton("BASE64文字加解密");;
    /** URL */
    private JButton url = new JButton("URL加解密");
    /** URL */
    private JButton base64Pic = new JButton("Base64图片转换");
    /** 操作集合 */
    List<JButton> opts = new ArrayList<>();
    /** 窗体集合 对应操作 */
    List<BaseForm> windows = new ArrayList<>();
    // 窗体

    TimestampForm timestampForm = null;
    Md5Form md5Form = null;
    UrlForm urlForm = null;
    Base64Form base64Form = null;
    Base64PicForm base64PicForm = null;

    public MainForm() {
        super();

        this.init();
        this.addListener();
    }

    private void init() {
        windows.add(timestampForm);
        windows.add(md5Form);
        windows.add(urlForm);
        windows.add(base64Form);
        windows.add(base64PicForm);

        opts.add(timestamp);
        opts.add(md5);
        opts.add(url);
        opts.add(base64);
        opts.add(base64Pic);

        opts.forEach(opt -> {
            opt.setBounds((getWidth() - 150 * 3) + ((opts.indexOf(opt)) % 3 - 1) * 200, 100 * (opts.indexOf(opt) / 3) + 100, 150, 50);
            add(opt);
        });

        add(new JLabel());
        repaint();
    }

    /**
     * 事件监听
     */
    private void addListener() {
        opts.forEach(opt -> {
            opt.addActionListener(e -> {
                if (windows.get(opts.indexOf(opt)) == null) {
                    // 失败
                    switch (opts.indexOf(opt))  {
                        case 0: windows.set(0, new TimestampForm("时间戳工具", "时间转换")); break;
                        case 1: windows.set(1, new Md5Form("MD5工具", "MD5加密")); break;
                        case 2: windows.set(2, new UrlForm("URL工具", "URL编码解码")); break;
                        case 3: windows.set(3, new Base64Form("BASE64工具", "BASE64文字加解密")); break;
                        case 4: windows.set(4, new Base64PicForm("BASE64图片工具", "BASE64图片转换")); break;
                        default: break;
                    }
                } else {
                    windows.get(opts.indexOf(opt)).setVisible(true);
                }
            });
        });
    }

}
