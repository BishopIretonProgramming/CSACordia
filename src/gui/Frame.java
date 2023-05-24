package src.gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;  
import java.awt.event.ActionEvent;

import src.game.Game;
import src.game.map.Map;
import src.game.Player;

public class Frame {

    public Frame(){

    }
    
    public void welcome() {
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
            Image imgM = ImageIO.read(new File(String.format("src%sgui%simages%sConcordia board.jpg", File.separator, File.separator, File.separator)));
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

    public void board(Game g) { // Probably will have parameters for gamestate. 
        JFrame bord = new JFrame("Game Board");
        PlayerHandDisplay d = new PlayerHandDisplay();

        bord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Construct Board
        JLabel wBackground = new JLabel();
        try {

            Image img = ImageIO.read(new File("src\\gui\\images\\Concordia board.jpg"));

            Image imgMap = img.getScaledInstance(1080, 600,  java.awt.Image.SCALE_SMOOTH);
            wBackground.setIcon(new ImageIcon(imgMap));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // centers the map so you can see the whole map
        wBackground.setHorizontalAlignment(JLabel.CENTER);

        JPanel bPanel = new JPanel();
        bPanel.setBackground(Color.CYAN);

        // create "Player Hand" button
        JButton playerHandB = new JButton("Player Hand");
        
        

        // adds an action listener only prints for now
        d.display(g);

        playerHandB.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    System.out.println("Player hand was pressed");
                    d.changeVisible();
                    
                }
            }        );

        // add buttons to the panel
        bPanel.add(playerHandB);

        // add background to the frame
        bord.getContentPane().add(wBackground, BorderLayout.CENTER);
        bord.getContentPane().add(bPanel, BorderLayout.SOUTH);
        bord.getContentPane().add(d.handDisplay);
        bord.setSize(1225, 900);
        bord.setVisible(true);
    }

    public static void main(String[] args) {
        // what I need for game String name, Map map, List<Player> players, List<Color> colors, Player firstPlayer
        String name ="We're no strangers to love";
        Map youKnowTheRulesAndSoDoI = new Map();

        Player aFullCommitmentsWhatImThinkingOf = new Player("You wouldn't get this from any other guy");
        Player iJustWannaTellYouHowImFeeling = new Player("Gotta make you understand");
        Player neverGonnaGiveYouUp = new Player("Never gonna let you down");
        Player neverGonnaRunAroundAndDesertYou = new Player("Never gonna make you cry"); 
        Player neverGonnaSayGoodbye = new Player("Never gonna tell a lie and hurt you");
        List<Player> rickroll = new ArrayList<Player>();

        rickroll.add(aFullCommitmentsWhatImThinkingOf);
        rickroll.add(iJustWannaTellYouHowImFeeling);
        rickroll.add(neverGonnaGiveYouUp);
        rickroll.add(neverGonnaRunAroundAndDesertYou);
        rickroll.add(neverGonnaSayGoodbye);

        List<Color> lol = new ArrayList<Color>();
        lol.add(Color.BLUE);


        Game Rick = new Game(name, youKnowTheRulesAndSoDoI, rickroll, lol, aFullCommitmentsWhatImThinkingOf);
        Frame test = new Frame();
        test.welcome();
        test.board(Rick);
    }
}