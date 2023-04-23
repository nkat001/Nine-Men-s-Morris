package UI;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private Line line = new Line();

    // Get the screen size
    int screenWidth = new JFrame().getToolkit().getScreenSize().width;
    int screenHeight = new JFrame().getToolkit().getScreenSize().height;

    public Display(){
        setTitle("Nine Men's Morris");
        setSize(screenWidth, screenHeight);
        add(line);
        setBackground(new Color(202, 213, 75));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
