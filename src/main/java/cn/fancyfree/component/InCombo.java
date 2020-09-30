package cn.fancyfree.component;

import javax.swing.*;
import java.awt.*;

/**
 * 下拉输入
 * @author AlanMa247@Gmail.COM
 * @date 19:07 2020/9/14
**/
public class InCombo extends JPanel {
    private JLabel label;
    private JComboBox<String> combo;

    public InCombo(String label, String[] combo, Font font, int x, int y, int w, int h) {
        this.label = new JLabel(label, JLabel.CENTER);
        this.label.setBounds(x, (int) (y + h * 0.2), 90, (int) (h * 0.6));
        this.label.setOpaque(false);
        add(this.label);
        this.combo = new JComboBox(combo);
        this.combo.setBounds(x + 90 + 10, (int) (y + h * 0.2), w, (int) (h * 0.6));
        this.combo.setFont(font);
        add(this.combo);
    }

    public JLabel getLabel() {
        return label;
    }

    public JComboBox<String> getCombo() {
        return combo;
    }

    public String getVal() {
        return (String) this.combo.getSelectedItem();
    }

    public int getIndex() {
        return this.combo.getSelectedIndex();
    }
}
