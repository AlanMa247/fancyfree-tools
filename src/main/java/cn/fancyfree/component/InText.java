package cn.fancyfree.component;

import javax.swing.*;
import java.awt.*;

/**
 * 下拉输入
 * @author AlanMa247@Gmail.COM
 * @date 19:07 2020/9/14
**/
public class InText extends JPanel {
    private JLabel label;
    private JTextField textField;

    public InText(String label,  Font font, int x, int y, int w, int h) {
        this.label = new JLabel(label, JLabel.CENTER);
        this.label.setBounds(x, (int) (y + h * 0.2), 90, (int) (h * 0.6));
        this.label.setOpaque(false);
        add(this.label);
        this.textField = new JTextField();
        this.textField.setBounds(x + 90 + 10, (int) (y + h * 0.2), w, (int) (h * 0.6));
        this.textField.setFont(font);
        add(this.textField);
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getText() {
        return textField;
    }

    public String getVal() {
        return textField.getText();
    }
}
