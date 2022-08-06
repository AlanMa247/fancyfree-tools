package cn.fancyfree.gui.component.form;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class OkBtn extends BaseOpt {

    public OkBtn(String label, Font font, int x, int y, int w, int h, EventListener listener) {
        super(label, font, x, y, w, h, Color.BLUE, listener);
    }
}
