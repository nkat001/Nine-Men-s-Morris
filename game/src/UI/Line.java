package UI;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel{

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int[] xPoints = {80, 650, 680, 1250};
        int[] yPoints = {20, 20, 20, 20};

        int[] x2 = {65, 65, 65, 65};
        int[] y2 = {35, 305, 335, 605};

        int[] x3 = {80, 650, 680, 1250};
        int[] y3 = {620, 620, 620, 620};

        int[] x4 = {1265, 1265, 1265, 1265};
        int[] y4 = {35, 305, 335, 605};

        int[] x5 = {280, 650, 680, 1050};
        int[] y5 = {120, 120, 120, 120};

        int[] x6 = {265, 265, 265, 265};
        int[] y6 = {135, 305, 335, 505};

        int[] x7 = {280, 650, 680, 1050};
        int[] y7 = {520, 520, 520, 520};

        int[] x8 = {1065, 1065, 1065, 1065};
        int[] y8 = {135, 305, 335, 505};

        int[] x9 = {380, 650, 680, 950};
        int[] y9 = {220, 220, 220, 220};

        int[] x10 = {365, 365, 365, 365};
        int[] y10 = {225, 305, 335, 405};

        int[] x11 = {380, 650, 680, 950};
        int[] y11 = {420, 420, 420, 420};

        int[] x12 = {965, 965, 965, 965};
        int[] y12 = {225, 305, 335, 405};

        int[] x13 = {80, 250, 280, 350};
        int[] y13 = {320, 320, 320, 320};

        int[] x14 = {980, 1050, 1080, 1250};
        int[] y14 = {320, 320, 320, 320};

        int[] x15 = {665, 665, 665, 665};
        int[] y15 = {35, 105, 135, 205};

        int[] x16 = {665, 665, 665, 665};
        int[] y16 = {435, 505, 535, 605};

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawPolyline(xPoints, yPoints, xPoints.length);
        g2d.drawPolyline(x2, y2, x2.length);
        g2d.drawPolyline(x3, y3, x3.length);
        g2d.drawPolyline(x4, y4, x4.length);
        g2d.drawPolyline(x5, y5, x5.length);
        g2d.drawPolyline(x6, y6, x6.length);
        g2d.drawPolyline(x7, y7, x7.length);
        g2d.drawPolyline(x8, y8, x8.length);
        g2d.drawPolyline(x9, y9, x9.length);
        g2d.drawPolyline(x10, y10, x10.length);
        g2d.drawPolyline(x11, y11, x11.length);
        g2d.drawPolyline(x12, y12, x12.length);
        g2d.drawPolyline(x13, y13, x13.length);
        g2d.drawPolyline(x14, y14, x14.length);
        g2d.drawPolyline(x15, y15, x15.length);
        g2d.drawPolyline(x16, y16, x16.length);
    }
}
