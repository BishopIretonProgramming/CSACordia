package src.gui;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;

public class PlayerHandDisplay extends JPanel{

public static boolean isVisible;// allows other methods to turn panel on and off
public static JPanel handDisplay = new JPanel(); // this allows other methods to acess the panel
//public static JFrame window;   

    public static void display(){// makes the display or panel

        handDisplay.setBackground(Color.GRAY);
        handDisplay.setLayout(new BorderLayout());
        JLabel money = new JLabel("<html><font  size ='15' color = white> Sestertii:  </font></html>");// the html just changes the size and color should work on everyone's computer
        JLabel storeTitle = new JLabel("<html><font  size ='20' color = white> Storehouse  </font></html>");
        handDisplay.add(money, BorderLayout.WEST);
        handDisplay.add(storeTitle, BorderLayout.WEST);

        handDisplay.setVisible(isVisible);
    }

    public static void changeVisible(){
        isVisible = !isVisible;
        System.out.println( isVisible);
    }


}
