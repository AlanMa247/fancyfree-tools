package cn.fancyfree.gui.component.form;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EventListener;

@Data
public class BaseOpt extends JPanel {
    JButton opt;

    public BaseOpt(String label, Font font, int x, int y, int w, int h, Color color, EventListener listener) {
        opt = new JButton(label);
        opt.setFont(font);
        opt.setBounds(x, y, w, h);
        opt.setHorizontalTextPosition(SwingConstants.CENTER);
        opt.setVerticalTextPosition(SwingConstants.CENTER);
//        opt.setOpaque(true);
//        opt.setContentAreaFilled(false);
        String colorPath = getColorPath(color);
        if (Files.exists(Paths.get(colorPath))) {
            ImageIcon bg = new ImageIcon(colorPath);
            bg.setImage(bg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            opt.setIcon(bg);
        }
        if (listener instanceof ActionListener) {
            opt.addActionListener((ActionListener) listener);
        }
    }

    private static String getColorPath(Color color) {
        if (Color.BLUE.equals(color)) {
            return System.getProperty("user.dir") + "/image/color/blue.png";
        } else if (Color.RED.equals(color)) {
            return System.getProperty("user.dir") + "/image/color/red.png";
        } else {
            return null;
        }
    }
}
