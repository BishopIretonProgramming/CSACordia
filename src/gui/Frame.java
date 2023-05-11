package src.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Frame {
    
    public static void welcome() {
        // create a JFrame object
        JFrame wFrame = new JFrame("Welcome Frame");
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        // create "New Game" and "Load Game" buttons
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        // add buttons to the panel
        welcomePanel.add(newGameButton);
        welcomePanel.add(loadGameButton);

        // create a JLabel object with text "Welcome"
        JLabel welcomeLabel = new JLabel("WELCOME TO CONCORDIA");
        // add the label to the frame's content pane
        wFrame.getContentPane().add(welcomeLabel);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        // font
        Font welcomeFont = new Font("SansSerif", Font.BOLD, 48);
        welcomeLabel.setFont(welcomeFont);


        JLabel wBackground = new JLabel();
        try {
            // load the image file from the project folder
            Image img = ImageIO.read(Frame.class.getResource("Concordia board.jpg"));
            wBackground.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
         // add the label and panel to the frame's content pane
         wFrame.getContentPane().add(wBackground, BorderLayout.CENTER);
         wFrame.getContentPane().add(welcomeLabel, BorderLayout.NORTH);
         wFrame.getContentPane().add(welcomePanel, BorderLayout.SOUTH);

        // set the size and visibility of the frame
        wFrame.setSize(1000, 500);
        wFrame.setVisible(true);
    }

    public static void main(String[] args) {
        welcome();
    }
}
