package src.gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Frame {
    
    public static void welcome() {
        JFrame wFrame = new JFrame("Welcome Frame");
        wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create a JPanel object to hold the buttons
        JPanel wPanel = new JPanel();
        wPanel.setBackground(Color.WHITE);
        
        // create "New Game" and "Load Game" buttons
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        
        // add buttons to the panel
        wPanel.add(newGameButton);
        wPanel.add(loadGameButton);
        
        // create a JLabel object with an image as the background
        JLabel wBackground = new JLabel();
        try {
            // load the image file from the project folder
            Image imgM = ImageIO.read(Frame.class.getResource("Concordia board.jpg"));
            wBackground.setIcon(new ImageIcon(imgM));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // create a JLabel object with text "Welcome to Concordia"
        JLabel wLabel = new JLabel("Welcome to Concordia");
        
        // center the text in the label
        wLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // change the font and size of the label
        Font wFont = new Font("Egyptian", Font.BOLD, 48);
        wLabel.setFont(wFont);
        
        // add the label and panel to the frame's content pane
        wFrame.getContentPane().add(wBackground, BorderLayout.CENTER);
        wFrame.getContentPane().add(wLabel, BorderLayout.NORTH);
        wFrame.getContentPane().add(wPanel, BorderLayout.SOUTH);
        
        // set the size and visibility of the frame
        wFrame.setSize(1000, 600);
        wFrame.setVisible(true);
    }

    public static void board() { // Probably will have parameters for gamestate. 
        JFrame bord = new JFrame("Game Board");
        bord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Construct Board
        JLabel wBackground = new JLabel();
        try {
            // load the image file from the project folder
            Image imgMap = ImageIO.read(Frame.class.getResource("Concordia board.jpg"));
            wBackground.setIcon(new ImageIcon(imgMap));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel bPanel = new JPanel();
        bPanel.setBackground(Color.CYAN);
        // create "Player Hand" button
        JButton playerHandB = new JButton("Player Hand");
        // add buttons to the panel
        bPanel.add(playerHandB);

        // add background to the frame
        bord.getContentPane().add(wBackground, BorderLayout.CENTER);
        bord.getContentPane().add(bPanel, BorderLayout.SOUTH);
        bord.setSize(1225, 900);
        bord.setVisible(true);
    }

    public static void main(String[] args) {
        //welcome();
        board();
    }
}