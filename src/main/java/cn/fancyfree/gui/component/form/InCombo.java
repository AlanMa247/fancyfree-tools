package cn.fancyfree.gui.component.form;

import javax.swing.*;
import java.awt.*;

/**
 * 下拉输入
 * @author AlanMa247@Gmail.COM
 * @date 19:07 2020/9/14
**/
public class InCombo extends BaseIn<JComboBox> {

    public InCombo(String label, String[] combo, Font font, int x, int y, int w, int h) {
        super(label, combo, font, x, y, w, h, JComboBox.class);
    }

    public int getIndex() {
        return this.input.getSelectedIndex();
    }
}
