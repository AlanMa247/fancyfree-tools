package cn.fancyfree.tools;

import cn.fancyfree.tools.form.MainForm;

import javax.swing.*;

/**
 * 程序入口
 * @author AlanMa247@Gmail.COM
 * @date 16:54 2020/9/17
**/
public class Main {
    public static void main(String[] args) {
        // 程序入口打开窗体
        MainForm form = new MainForm();
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
