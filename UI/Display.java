package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {

    private Line lines = new Line();

    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();


    public Display(){
        setTitle("Nine Men's Morris");
        setSize(screenSize);
        setBackground(new Color(202, 213, 75));
        add(lines);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}