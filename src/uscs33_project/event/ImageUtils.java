package uscs33_project.event;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImageUtils {

    public static ImageIcon getCircularIcon(ImageIcon icon, int diameter) {
        Image img = icon.getImage();
        BufferedImage src = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = src.createGraphics();

        // Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clip to a circle
        Shape circle = new Ellipse2D.Float(0, 0, diameter, diameter);
        g2.setClip(circle);

        // Draw the image scaled into the circle
        g2.drawImage(img, 0, 0, diameter, diameter, null);
        g2.dispose();

        return new ImageIcon(src);
    }
}
