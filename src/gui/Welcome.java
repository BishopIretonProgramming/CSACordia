package src.gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.event.ActionListener;  
import java.awt.event.ActionEvent;

/**
 *  @author Joe Lentini
 *  @author Nora Hixson
 *  this should display th start screen
 */
public class Welcome extends JFrame {

    public static void welcomeFrame() {
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
            Image imgM = ImageIO.read(new File(String.format("resources%simages%sConcordia board.jpg", File.separator, File.separator)));
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

        
        // add action listeners to all the buttons
        loadGameButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        System.out.println("load game was pressed");
                        
                        
                    }
                }        );

        newGameButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            System.out.println("new game was pressed");
                            CreateNewGame.create(wFrame, wPanel,wLabel,wBackground);
                            
                        }
                    }        );
    }



/* 
    testing method
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

        welcomeFrame();


        //Game Rick = Game.(name, youKnowTheRulesAndSoDoI, rickroll, lol, aFullCommitmentsWhatImThinkingOf);
        
        //welcomeFrame();
        //test.board(Rick);
    }
*/
}