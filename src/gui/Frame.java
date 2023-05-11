package src.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Frame {
    
    // 1st frame the user sees upon running the game
    public static void welcome() {
        // create a JFrame object
        JFrame wFrame = new JFrame("Welcome Frame");
        wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.BLUE);
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
        wFrame.setSize(900, 500);
        wFrame.setVisible(true);
    }

/*Nora Hixson this is the frame users will be able to interact with eventually */
    public static void concordia(){

        JFrame concordia = new JFrame("Concordia");
        concordia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add map
        JLabel imgMap = new JLabel();

        try {
            // creates Image then makes image an icon then sets the icon for the JLabel
            Image map = ImageIO.read(Frame.class.getResource("Concordia board.jpg"));
            ImageIcon img = new ImageIcon(map);
            imgMap.setIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        concordia.getContentPane().add(imgMap, BorderLayout.CENTER);
        
        /* 
        Dimension mapSize = new Dimension(200, 200);
        imgMap.setPreferredSize(mapSize);
        */
    // set up frame
        concordia.setSize(900, 500);
        concordia.setVisible(true);

    }

    public static void main(String[] args) {
        welcome();
        concordia();
    }
}
