package cn.fancyfree.gui.component.form;

import java.awt.*;
import java.util.EventListener;

public class NgBtn extends BaseOpt {

    public NgBtn(String label, Font font, int x, int y, int w, int h, EventListener listener) {
        super(label, font, x, y, w, h, Color.RED, listener);
    }
}
