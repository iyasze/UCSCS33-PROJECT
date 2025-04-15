/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uscs33_project.event;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MouseDetector implements AWTEventListener {
    private final Component parent;
    private final Component innerBound;
    private boolean hasExited = true;
    
    public MouseDetector(Component p, Component p2)
    {
        parent = p;
        innerBound = p2;
    }
    
    @Override
    public void eventDispatched(AWTEvent e)
    {
        if (e instanceof MouseEvent)
        {
            if (SwingUtilities.isDescendingFrom(
                (Component) e.getSource(), parent))
            {
                MouseEvent m = (MouseEvent) e;
                if (m.getID() == MouseEvent.MOUSE_ENTERED)
                {
                    if (hasExited)
                    {
//                        System.out.println("Entered");
                        hasExited = false;
                        innerBound.setVisible(true);
                    }
                } else if (m.getID() == MouseEvent.MOUSE_EXITED)
                {
                    Point p = SwingUtilities.convertPoint(
                        (Component) e.getSource(),
                        m.getPoint(),
                        innerBound);
                    if (!innerBound.getBounds().contains(p))
                    {
//                        System.out.println("Exited");
                        hasExited = true;
                        innerBound.setVisible(false);
                    }
                }
            }
        }
    }
}
