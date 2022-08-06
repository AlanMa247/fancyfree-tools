package cn.fancyfree.gui.component.form;

import javax.swing.*;
import java.awt.*;

/**
 * 下拉输入
 * @author AlanMa247@Gmail.COM
 * @date 19:07 2020/9/14
**/
public class InPwd extends BaseIn<JPasswordField> {

    public InPwd(String label, Font font, int x, int y, int w, int h) {
        super(label, font, x, y, w, h, JPasswordField.class);
    }
}
