package cn.fancyfree.gui.utils;

import cn.fancyfree.gui.component.frame.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *@描述 窗体使用工具类
 * 1. 支持托盘
 *@创建人 AlanMa247@Gmail.COM
 *@创建时间 2022/8/20
 *@修改人和其它信息
 */
public class FrameUtils {


    /**
     *@描述 启用关闭托盘
     *@参数 [frame, iconPath, tips]
     *@返回值 void
     *@创建人 AlanMa247@Gmail.COM
     *@创建时间 2022/8/20
     *@修改人和其它信息
     */
    public static void enableClose2Tray(BaseFrame frame, String iconPath, String tips) {
        SystemTray tray;
        TrayIcon trayIcon;
        // 获得本操作系统托盘的实例
        tray = SystemTray.getSystemTray();
        // 显示在托盘中的图标
        ImageIcon icon = ImageUtils.getImageIcon("image/Alan.jpg");
        // 构造一个右键弹出式菜单
        PopupMenu pop = new PopupMenu();
        MenuItem exit = new MenuItem("Exit");
        pop.add(exit);
        trayIcon = new TrayIcon(icon.getImage(), "FancyFree小工具\n版权所有：©AlanMa247@Gmail.COM", pop);
        // 这句很重要，没有会导致图片显示不出来
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    tray.remove(trayIcon);
                    frame.setVisible(true);
                    // 还原成原来的窗口，而不是显示在任务栏
                    frame.setExtendedState(Frame.NORMAL);
                }
            }
        });
        exit.addActionListener(e -> {
            tray.remove(trayIcon);
            System.exit(0);
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                // 将托盘图标添加到系统的托盘实例中
                try {
                    tray.add(trayIcon);
                    System.out.println("关闭");
                    frame.setVisible(false);
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
            }


            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }
}
