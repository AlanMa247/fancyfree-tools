package cn.fancyfree.gui.component.form;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class BaseIn<I extends JComponent> extends JPanel {
    protected JLabel label;
    protected I input;

    public BaseIn(String label, Font font, int x, int y, int w, int h, Class<I> inputClass) {
        this.label = new JLabel(label, JLabel.CENTER);
        this.label.setBounds(x, (int) (y + h * 0.2), 90, (int) (h * 0.6));
        this.label.setOpaque(false);
        add(this.label);
        try {
            input = inputClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        input.setBounds(x + 90 + 10, (int) (y + h * 0.2), w, (int) (h * 0.6));
        input.setFont(font);
        add(this.input);
    }


    public BaseIn(String label, String[] combo, Font font, int x, int y, int w, int h, Class<I> inputClass) {
        this.label = new JLabel(label, JLabel.CENTER);
        this.label.setBounds(x, (int) (y + h * 0.2), 90, (int) (h * 0.6));
        this.label.setOpaque(false);
        add(this.label);
        try {
            input = inputClass.newInstance();
            ((JComboBox)input).setModel(new DefaultComboBoxModel(combo));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        this.input.setBounds(x + 90 + 10, (int) (y + h * 0.2), w, (int) (h * 0.6));
        this.input.setFont(font);
        add(this.input);
    }

    public JLabel getLabel() {
        return label;
    }

    public <I> JComponent getText() {
        return input;
    }

    public String getVal() {
        if (input instanceof JTextField) {
            return ((JTextField)input).getText();
        } else if (input instanceof JPasswordField) {
            return ((JPasswordField)input).getPassword().toString();
        } else  if (input instanceof JComboBox) {
            return ((JComboBox)input).getSelectedItem().toString();
        } else {
            return null;
        }
    }

    public int getIndex() {
        if (input instanceof JComboBox) {
            return ((JComboBox)input).getSelectedIndex();
        } else {
            return -1;
        }
    }
}
