package src.gui;
import javax.swing.*;
import java.awt.Color;

public class PlayerHandDisplay {

public static boolean isVisible;// allows other methods to turn panel on and off
public static JPanel handDisplay = new JPanel(); // this allows other methods to acess the panel
public static JFrame window;   
    public static void display(JFrame w){// makes the display or panel

        window = w;
        handDisplay.setBackground(Color.BLACK);

        handDisplay.setVisible(isVisible);
        
        
        System.out.println("display method works");
        JLabel test = new JLabel("test visible");
        window.getContentPane().add(test);
    }

    public static void changeVisible(){
        isVisible = !isVisible;
        System.out.println( isVisible);
    }


}
