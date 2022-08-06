package cn.fancyfree.gui.form;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * 日历
 * @author AlanMa247@Gmail.COM
 * @date 1:17 2020/9/12
**/
public class CalendarForm extends BaseForm {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String second;
    private List<JButton> years = new ArrayList<>();
    private List<JButton> months = new ArrayList<>();
    private List<JButton> weeks = new ArrayList<>();
    private List<JButton> days = new ArrayList<>();
    private JSlider hours = new JSlider(0, 23);
    private JSlider minutes = new JSlider(0, 59);
    private JSlider seconds = new JSlider(0, 59);

    public JTextField dateTime = new JTextField("", JTextField.CENTER);
    public JButton ok = new JButton("确定");
    private JButton now = new JButton("现在时间");
    private JButton nowStamp = new JButton("现在时间戳");

    public static void main(String[] args) {
        CalendarForm calendarForm = new CalendarForm("日历", "日期选择");
        calendarForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public CalendarForm() {
        super();
        this.init();
        this.addListener();
    }

    public CalendarForm(String winName, String appName) {
        super(winName, appName);
        this.init();
        this.addListener();
    }

    private void init() {
        initIn();
        {
            // 年
            int yearSize = Integer.parseInt(config.get("calendarYears"));
            int nowYear = Calendar.getInstance().get(Calendar.YEAR);
            JButton now = new JButton(nowYear + "");
            years.add(now);
            for (int i = 0; i < yearSize; i++) {
                years.add(0, new JButton(nowYear - i - 1 + ""));
                years.add(years.size(), new JButton(nowYear + i + 1 + ""));
            }
            years.forEach(year -> {
                year.setBounds(70 * (years.indexOf(year) % 9) + 50, 42 * (years.indexOf(year) / 9) + 80, 65, 40);
                year.addActionListener(e -> {
                    this.year = year.getText();
                    dateTime.setText(getDateTime());
                    initDay();
                    selectYear();
                });
                add(year);
            });
        }
        {
            // 月
            for (int i = 1; i <= 12; i++) {
                months.add(new JButton("" + i));
            }
            months.forEach(month -> {
                month.setBounds(53 * (months.indexOf(month) % 12) + 50, 42 * (months.indexOf(month) / 12) + 122, 48, 40);
                month.addActionListener(e -> {
                    this.month = month.getText();
                    dateTime.setText(getDateTime());
                    initDay();
                    selectMonth();
                });
                add(month);
            });
        }
        {
            // 星期
            weeks = Arrays.asList(new JButton[]{
                    new JButton("日"),
                    new JButton("一"),
                    new JButton("二"),
                    new JButton("三"),
                    new JButton("四"),
                    new JButton("五"),
                    new JButton("六")});
            weeks.forEach(week -> {
                week.setEnabled(false);
                week.setBounds(50 * (weeks.indexOf(week)) + 50, 165, 50, 30);
                add(week);
            });
        }


        // 日期
        {
            for (int i = 1; i <= 42; i++) {
                days.add(new JButton(""));
            }
            days.forEach(day -> {
                day.setBounds(50 * (days.indexOf(day) % 7) + 51, 42 * (days.indexOf(day) / 7 + 1) + 155 , 48, 40);
                day.addActionListener(e -> {
                    if (day.getText().length() > 0) {
                        this.day = day.getText();
                    }
                    dateTime.setText(getDateTime());
                    selectDay();
                });
                add(day);
            });
        }
        // 时分秒
        {
            hours.setBounds(420, 200, 250, 40);
            hours.setValue(Integer.parseInt(hour));
            add(hours);
            hours.addChangeListener(e -> {
                hour = hours.getValue() + "";
                dateTime.setText(getDateTime());
            });
            minutes.setBounds(420, 250, 250, 40);
            minutes.setValue(Integer.parseInt(minute));
            add(minutes);
            minutes.addChangeListener(e -> {
                minute = minutes.getValue() + "";
                dateTime.setText(getDateTime());
            });
            seconds.setBounds(420, 300, 250, 40);
            seconds.setValue(Integer.parseInt(second));
            add(seconds);
            seconds.addChangeListener(e -> {
                second = seconds.getValue() + "";
                dateTime.setText(getDateTime());
            });

        }

        // 时间显示
        dateTime.setHorizontalAlignment(JTextField.CENTER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTime.setText(simpleDateFormat.format(new Date()));
        dateTime.setBounds(420, 405, 150, 40);
        add(dateTime);
        ok.setBounds(580, 405, 100, 40);
        add(ok);
        now.setBounds(50, 20, 150, 40);
        add(now);
        nowStamp.setBounds(530, 20, 150, 40);
        add(nowStamp);
        new Timer(1000, e -> {
            now.setText(simpleDateFormat.format(new Date()));
            nowStamp.setText(System.currentTimeMillis() / 1000 + "");
        }).start();



        // 防止最后一个元素位置错乱
        add(new JLabel());

        // 初始化选择
        initDay();
        selectYear();

    }


    private String getDateTime(){
        return year + "-" + leftPaddingZero(month) + "-" + leftPaddingZero(day) + " "
                + leftPaddingZero(hour) + ":" + leftPaddingZero(minute) + ":" + leftPaddingZero(second);
    }

    private void initDay(){
        days.forEach(day -> day.setText(""));
        Calendar first = Calendar.getInstance();
        first.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
        int daysCount = 0;
        int dayStart = first.get(Calendar.DAY_OF_WEEK);
        switch(Integer.parseInt(month)) {
            case 4: case 6: case 9: case 11:
                daysCount = 30;
                break;
            case 2:
                daysCount = 29;
                break;
            default:
                daysCount = 31;
                break;
        }
        for (int i = 1; i <= daysCount; i++) {
            days.get(i - 1 + dayStart - 1).setText(i + "");
        }

    }
    /**
     * 事件监听
     */
    private void addListener() {
//        ok.addActionListener(e -> {
//
//        });
        now.addActionListener(e -> {
            dateTime.setText(now.getText());
        });
    }


    /**
     * 初始化输入
     * @author AlanMa247@Gmail.COM
     * @date 17:10 2020/9/11
     **/
    private void initIn() {
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR) + "";
        month = now.get(Calendar.MONTH) + 1 + "";
        day = now.get(Calendar.DAY_OF_MONTH) + "";
        hour = now.get(Calendar.HOUR_OF_DAY) + "";
        minute = now.get(Calendar.MINUTE) + "";
        second = now.get(Calendar.SECOND) + "";
    }


    private String leftPaddingZero(String content) {
        return leftPadding(content, '0', 2);
    }

    /**
     * 左补齐
     * @author AlanMa247@Gmail.COM
     * @date 1:14 2020/9/23
     * @param content 内容
     * @param padding 补齐字符
     * @param length 长度
     * @return java.lang.String
     **/
    private String leftPadding(String content, char padding, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
        stringBuilder.reverse();
        while (stringBuilder.length() < length) {
            stringBuilder.append(padding);
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * 右补齐
     * @author AlanMa247@Gmail.COM
     * @date 1:14 2020/9/23
     * @param content 内容
     * @param padding 补齐字符
     * @param length 长度
     * @return java.lang.String
     **/
    private String rightPadding(String content, char padding, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
        while (stringBuilder.length() < length) {
            stringBuilder.append(padding);
        }
        return stringBuilder.toString();
    }

    private void selectClicked(List<JButton> all, JButton needSelect) {
        if (needSelect.getText().length() > 0) {
            // 先清除选项
            all.forEach(item -> {
                item.setBackground(null);
            });
            // 选择
            needSelect.setBackground(Color.GREEN);
        }
    }

    private void selectYear() {
        selectClicked(years, years.stream().filter(year -> this.year.equals(year.getText())).findFirst().get());
        selectMonth();
    }
    private void selectMonth() {
        selectClicked(months, months.stream().filter(month -> this.month.equals(month.getText())).findFirst().get());
        selectDay();
    }
    private void selectDay() {
        selectClicked(days, days.stream().filter(day -> this.day.equals(day.getText())).findFirst().get());
    }

}
