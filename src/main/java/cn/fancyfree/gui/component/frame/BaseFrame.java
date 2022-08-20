package cn.fancyfree.gui.component.frame;

import cn.fancyfree.gui.component.form.BaseIn;
import cn.fancyfree.gui.component.form.InCombo;
import cn.fancyfree.gui.component.form.OutText;
import cn.fancyfree.gui.utils.PropertiesUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BaseFrame extends JFrame {

    /** 配置 */
    protected Map<String, String> config;

    // 颜色
    /** 文字 */
    protected Color word = Color.WHITE;
    /** 背景 */
    protected Color bg = new Color(50, 50, 50);
    // 字体
    /** 错误 */
    protected Font fontErr = new Font("微软雅黑", Font.BOLD, 25);
    /** 信息 */
    protected Font fontInfo = new Font("微软雅黑", Font.BOLD, 15);
    /** 基础 */
    protected Font fontBase = new Font("微软雅黑", Font.BOLD, 18);
    /** 提示 */
    protected Font fontTip = new Font("微软雅黑", Font.BOLD, 23);


    // 坐标系
    /** 横X */
    int baseX;
    /** 纵Y */
    int baseY;
    /** LOGO图片 */
    JLabel logoImg;
    // 背景
    /** 背景容器 */
    JPanel contentPanel;
    /** 背景图片 */
    JLabel bgImg;
    // 显示内容
    /** 标题 */
    JLabel title;
    /** 错误提示 */
    JLabel message;
    /** 版权 */
    JLabel copyright;

    /** 输入输出提示 */
    JLabel tip;

    public BaseFrame() {
        config = PropertiesUtils.readProperties(System.getProperty("user.dir") + "/config/config.properties");
        baseX = Integer.parseInt(config.get("baseX"));
        baseY = Integer.parseInt(config.get("baseY"));
        this.setSize(Integer.parseInt(config.get("windowWidth")), Integer.parseInt(config.get("windowHeight")));
        this.setLocationRelativeTo(null);
        // 背景
        initBackground();
        // 错误消息
        initMessage();
        // 头部和底部
        initTitle(config.get("windowTitle"));
        initCopyright();
        setVisible(true);

    }

    public BaseFrame(String winName, String appName) {
        new BaseFrame(winName, appName, true);
    }

    public BaseFrame(String winName, String appName, boolean visible) {
        config = PropertiesUtils.readProperties(System.getProperty("user.dir") + "/config/config.properties");
        baseX = Integer.parseInt(config.get("baseX"));
        baseY = Integer.parseInt(config.get("baseY"));
        this.setSize(Integer.parseInt(config.get("windowWidth")), Integer.parseInt(config.get("windowHeight")));
        this.setLocationRelativeTo(null);
        setTitle(winName);
        // 背景
        initBackground();
        // 错误消息
        initMessage();
        // 头部和底部
        initTitle(appName);
        initCopyright();
        if (visible) setVisible(true);
    }

    /**
     * 初始化错误信息
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    void initMessage() {
        message = new JLabel("", JLabel.CENTER);
        add(message);
        message.setBounds((getWidth() - 400) / 2, baseY + 70, 400, 40);
        message.setFont(fontInfo);
        message.setForeground(Color.red);
    }

    /**
     * 初始化错误信息
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    protected void setMessage(String message, boolean error) {
        this.message.setText(message);
        this.message.setBounds((getWidth() - 400) / 2, baseY + 70, 400, 40);
        this.message.setFont(fontInfo);
        if (error) {
            this.message.setFont(fontErr);
            this.message.setForeground(Color.red);
        } else {
            this.message.setFont(fontInfo);
            this.message.setForeground(Color.green);
        }
    }

    /**
     * 初始化头部底部
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    void initCopyright() {
        copyright = new JLabel(config.get("copyright"), JLabel.CENTER);
        copyright.setBounds(0, getHeight() - 62, getWidth(), 25);
        copyright.setForeground(Color.gray);
        add(copyright);
        add(new JLabel());
    }


    /**
     * 初始化头部底部
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    void initTitle(String applicationTitle) {
        title = new JLabel(applicationTitle, JLabel.CENTER);
        add(title);
        setStyle(title);
        title.setBounds((getWidth() - 400) / 2, baseY + 20, 400, 50);
        Font f = new Font("微软雅黑", Font.BOLD, 30);
        title.setFont(f);

    }

    /**
     * 初始化背景
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    void initBackground() {
        ImageIcon logo = new ImageIcon(System.getProperty("user.dir") + "/image/logo.jpg");
        logoImg = new JLabel(logo);
        logoImg.setBounds(0, 0, logo.getIconWidth(), logo.getIconHeight());
        setStyle(logoImg);
        add(logoImg);
        contentPanel = new JPanel();
        // 界面背景图片
        ImageIcon image = new ImageIcon(System.getProperty("user.dir") + "/image/bg.jpg");
        bgImg = new JLabel(image);
        bgImg.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        // 在LayeredPane最底层上添加两个带图片的标签，并且label2在label上方
        this.getLayeredPane().add(bgImg, new Integer(Integer.MIN_VALUE));
        // 将内容面板设置为透明，就能够看见添加在LayeredPane上的背景。
        ((JPanel) this.getContentPane()).setOpaque(false);
        /* 添加组件到contentPanel容器中 布局方式为自由布局。 */
        contentPanel.setLayout(null);
        /* 组件透明化 */
        contentPanel.setOpaque(true);
        getContentPane().add(contentPanel);
    }


    /**
     * 设置公共样式
     * @author AlanMa247@Gmail.COM
     * @date 1:20 2020/9/15
     * @param component 组件
     **/
    void setStyle(Component component) {
        component.setFont(fontBase);
        component.setForeground(word);
        if (component instanceof JRadioButton) {
            ((JRadioButton) component).setOpaque(false);
        }
    }

    /**
     * 添加自定义组件
     * @author AlanMa247@Gmail.COM
     * @date 13:55 2020/9/15
     * @param component 组件
     **/
    void addInPanel(InCombo component) {
        if (component instanceof InCombo) {
            setStyle(component.getLabel());
            setBg(component.getLabel());
            add(component.getLabel());
            add(component.getInput());
        }
    }

    /**
     * 添加自定义组件
     * @author AlanMa247@Gmail.COM
     * @date 13:55 2020/9/15
     * @param component 组件
     **/
    protected void addInPanel(BaseIn component) {
        setStyle(component.getLabel());
        setBg(component.getLabel());
        add(component.getLabel());
        add(component.getInput());
    }

    /**
     * 添加自定义组件
     * @author AlanMa247@Gmail.COM
     * @date 13:55 2020/9/15
     * @param component 组件
     **/
    void addOutPanel(OutText component) {
        setStyle(component.getLabel());
        setBg(component.getLabel());
        component.getShow().setOpaque(true);
        component.getShow().setBackground(Color.GRAY);
        component.getShow().setForeground(Color.white);
        add(component.getLabel());
        add(component.getShow());
    }


    /**
     * 设置背景色
     * @author AlanMa247@Gmail.COM
     * @date 17:31 2020/9/15
     * @param component
     **/
    void setBg(Component component) {
        if (component instanceof JComboBox) {
            ((JComboBox) component).setOpaque(true);
            component.setBackground(bg);
        }
        if (component instanceof JLabel) {
            ((JLabel) component).setOpaque(true);
            component.setBackground(bg);
        }
    }
}
