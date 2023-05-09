package src.gui;

import javax.swing.*;
import java.awt.Font;

public class Frame {
    
    public static void welcome() {
        // create a JFrame object
        JFrame frame = new JFrame("Welcome Frame");
        
        // create a JLabel object with text "Welcome"
        JLabel label = new JLabel("WELCOME TO CONCORDIA");
        
        // add the label to the frame's content pane
        frame.getContentPane().add(label);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        // font
        Font welcomeFont = new Font("Wingdings 3", Font.BOLD, 48);
        label.setFont(welcomeFont);

        // set the size and visibility of the frame
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        welcome();
    }

}
