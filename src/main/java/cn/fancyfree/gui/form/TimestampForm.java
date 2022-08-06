package cn.fancyfree.gui.form;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 材料力学性能查询界面
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class TimestampForm extends BaseForm {

    private JTextField dateTime;
    private JTextField timestamp;
    private JButton leftCovert;
    private JButton rightCovert;
    private JButton calendarIcon;
    private CalendarForm calendarForm;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private JButton now = new JButton("现在时间");
    private JButton nowStamp = new JButton("现在时间戳");

    public TimestampForm() {
        super();

        this.init();
        this.addListener();
    }

    public TimestampForm(String winName, String appName) {
        super(winName, appName);
        this.init();
        this.addListener();
    }

    private void init() {
        // 背景
        initBackground();
        // 错误消息
        initMessage();
        // 头部和底部
        initIn();
        initCopyright();

        // 时间戳转换
        // 显示当前时间
        now.setBounds(50, 20, 200, 40);
        now.setBorder(null);
        now.setContentAreaFilled(false);
        now.setEnabled(false);
        now.setFont(info);
        add(now);
        nowStamp.setBounds(530, 20, 200, 40);
        nowStamp.setBorder(null);
        nowStamp.setContentAreaFilled(false);
        nowStamp.setEnabled(false);
        nowStamp.setFont(info);
        add(nowStamp);
        new Timer(1000, e -> {
            now.setText(format.format(new Date()));
            nowStamp.setText(System.currentTimeMillis() / 1000 + "");
        }).start();
        // 日历图标
        ImageIcon c = new ImageIcon(System.getProperty("user.dir") + "/image/calendar.png");
        c.setImage(c.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        calendarIcon = new JButton();
        calendarIcon.setIcon(c);
        calendarIcon.setBounds(152, 0, 50, 50);
        calendarIcon.setContentAreaFilled(false);
        calendarIcon.setBorder(null);
        // 时间
        dateTime = new JTextField(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        dateTime.setHorizontalAlignment(JTextField.CENTER);
        dateTime.setBounds((getWidth() - 200) / 2, 120, 200, 50);
        dateTime.setBorder(null);
        dateTime.setBackground(Color.white);
        dateTime.add(calendarIcon);
        add(dateTime);
        // 时间戳
        timestamp = new JTextField(System.currentTimeMillis() / 1000 + "");
        timestamp.setHorizontalAlignment(JTextField.CENTER);
        timestamp.setBounds((getWidth() - 200) / 2, 270, 200, 50);
        add(timestamp);

        ImageIcon l = new ImageIcon(System.getProperty("user.dir") + "/image/left.png");
        l.setImage(l.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon r = new ImageIcon(System.getProperty("user.dir") + "/image/right.png");
        r.setImage(r.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        leftCovert = new JButton();
        rightCovert = new JButton();
        leftCovert.setIcon(l);
        rightCovert.setIcon(r);
        leftCovert.setBounds((getWidth() - 200) / 2 - 100, 170, 100, 100);
        rightCovert.setBounds((getWidth() + 200) / 2, 170, 100, 100);
        leftCovert.setContentAreaFilled(false);
        rightCovert.setContentAreaFilled(false);
        leftCovert.setBorder(null);
        rightCovert.setBorder(null);
        add(leftCovert);
        add(rightCovert);

        // 防止最后一个元素位置错乱
        add(new JLabel());

    }

    /**
     * 事件监听
     */
    private void addListener() {

        leftCovert.addActionListener(e -> {
            calendar.setTimeInMillis(Long.parseLong(timestamp.getText()) * 1000);
            dateTime.setText(format.format(calendar.getTime()));
        });
        rightCovert.addActionListener(e -> {
            try {
                timestamp.setText(format.parse(dateTime.getText()).getTime() / 1000 + "");
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });

        calendarIcon.addActionListener(e -> {
            if (calendarForm == null) {
                calendarForm = new CalendarForm("选择日期", "日历");
                calendarForm.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                calendarForm.ok.addActionListener(e1 -> {
                    dateTime.setText(calendarForm.dateTime.getText());
                    calendarForm.dispose();
                });
            } else {
                calendarForm.setVisible(true);
            }
        });
    }


    /**
     * 初始化输入
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    private void initIn() {
    }

}
