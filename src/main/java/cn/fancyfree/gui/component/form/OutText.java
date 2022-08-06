package cn.fancyfree.gui.component.form;

import javax.swing.*;
import java.awt.*;

/**
 * 下拉输入
 * @author AlanMa247@Gmail.COM
 * @date 19:07 2020/9/14
**/
public class OutText extends JPanel {
    private JLabel label;
    private JLabel value;

    public OutText(String label, Font font, int x, int y, int w, int h) {
        this.label = new JLabel(label, JLabel.RIGHT);
        this.label.setBounds(x, (int) (y + h * 0.2), 90, (int) (h * 0.6));
        this.label.setOpaque(false);
        add(this.label);
        this.value = new JLabel("", JLabel.LEFT);
        this.value.setBounds(x + 90 + 10, (int) (y + h * 0.2), w, (int) (h * 0.6));
        this.value.setFont(font);
        add(this.value);
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getShow() {
        return value;
    }

    public String getVal() {
        return value.getText();
    }
}
