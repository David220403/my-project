package com.swing.scroll;

import com.ScrollBarCustom1.ModernScrollBarUI;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setOpaque(false);
        setUnitIncrement(20);
    }
}
