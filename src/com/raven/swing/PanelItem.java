
package com.raven.swing;

import uscs33_project.swing.*;
import java.awt.Color;
import javax.swing.JPanel;
import uscs33_project.swing.WrapLayout;

public class PanelItem extends JPanel {
    public PanelItem() {
        setBackground(Color.WHITE);
        setLayout(new WrapLayout(WrapLayout.LEFT, 10, 10));
        
    }
}
