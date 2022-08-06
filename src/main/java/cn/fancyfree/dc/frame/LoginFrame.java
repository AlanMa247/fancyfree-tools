package cn.fancyfree.dc.frame;

import cn.fancyfree.gui.component.form.InPwd;
import cn.fancyfree.gui.component.form.InText;
import cn.fancyfree.gui.component.form.NgBtn;
import cn.fancyfree.gui.component.form.OkBtn;
import cn.fancyfree.gui.component.frame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends BaseFrame {

    InText account = new InText("User", fontBase, 200, 100, 200, 50);
    InPwd password = new InPwd("Password", fontBase, 200, 200, 200, 50);

    OkBtn login = new OkBtn("Login", fontBase, 250, 300, 100, 50,
            new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(account.getVal());
            System.out.println(password.getVal());
            if (!"admin".equals(account.getVal()) || !"123456".equals(password.getVal())) {
                setMessage("Login Fail", true);
            } else {
                setMessage("Login Success", false);
            }
        }
    });

    NgBtn exit = new NgBtn("Exit", fontBase, 370, 300, 100, 50,
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

    public LoginFrame() {
        super("Login To FancyFree", "Login", false);
        addInPanel(account);
        addInPanel(password);
        add(login.getOpt());
        add(exit.getOpt());
        add(new JLabel());
        setVisible(true);
    }



    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
    }
}
