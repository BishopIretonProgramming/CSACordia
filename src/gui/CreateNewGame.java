/**
 * @author Nora Hixson
 * only used by the welcome frame to create a new class given 
 * user input
 */
package src.gui;

import javax.swing.*;
import src.game.player.Player;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import src.game.Game;
import src.game.Game.initGame;
import src.game.map.Map;

 public class CreateNewGame{

private static JPanel panel = new JPanel();
private static JPanel inner = new JPanel();
private static JPanel bPanel = new JPanel();

    public static void create(JFrame frame, JPanel oldPanel, JLabel oldTitle, JLabel oldBackground ){

        // create a panel, remove old panel, and put new panel in frame
        
        oldPanel.setVisible(false);
        frame.remove(oldPanel);
        frame.remove(oldTitle);
        frame.remove(oldBackground);

        // make outer panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setVisible(true);
        panel.setBackground(Color.BLACK);

        frame.getContentPane().add(panel);

        //make inner panel
        inner.setLayout(new BorderLayout());
        inner.setVisible(true);
        inner.setBackground(Color.BLACK);

        panel.add(inner,BorderLayout.CENTER);
        
        // make title

        Font font = new Font("Egyptian", Font.BOLD, 48);
        JLabel title = new JLabel("Create a Game",SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(font);

        // make instructions for how many player
        JLabel numPlayers = new JLabel("Choose your player count", SwingConstants.CENTER);
        numPlayers.setForeground(Color.WHITE);
        numPlayers.setFont(font);

        // choice buttons
        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");

        // panel for these buttons
        bPanel.setVisible(true);
        bPanel.setBackground(Color.BLACK);

        // asks for name of game and such
        TextField gName = new TextField("Please name your game");
        gName.setFont(font);

        
        // add stuff to the panel

        panel.add(title, BorderLayout.NORTH);    
        inner.add(gName,BorderLayout.NORTH);
        inner.add(numPlayers,BorderLayout.CENTER); 
        inner.add(bPanel,BorderLayout.SOUTH);
        bPanel.add(one);
        bPanel.add(two);
        bPanel.add(three);
        bPanel.add(four);
        bPanel.add(five);

        // add action Listeners
    
        one.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inner.remove(numPlayers);
                    playerName(1, gName);
                    
                }
            }        );

        two.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inner.remove(numPlayers);
                    playerName(2, gName);
                }
            }        );
        three.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inner.remove(numPlayers);
                    playerName(3, gName);
                }
            }        );  
            
        four.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inner.remove(numPlayers);
                    playerName(4,gName);
                }
            }        );

        five.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    inner.remove(numPlayers);
                    playerName(5, gName);
                }
            }        );
        
    }

    // gets the player names
    private static void playerName(int num, TextField gName){

        // makes color list

        List<Color> color = new ArrayList<Color>();
            color.add(Color.BLUE);
            color.add(Color.RED);
            color.add(Color.MAGENTA);
            color.add(Color.YELLOW);
            color.add(Color.GREEN);

        // creates the text fields no matter what 

        TextField p1 = new TextField("Enter First Player");
        TextField p2 = new TextField("Enter Second Player");
        TextField p3 = new TextField("Enter Third Player");
        TextField p4 = new TextField("Enter Fourth Player");
        TextField p5 = new TextField("Enter Fifth Player");


        // creates button
        JButton createG = new JButton("Create Game"); // makes a button

        // removes what is no longer needed in the panels

        bPanel.setVisible(false);
        inner.remove(bPanel);

        // adds JPanel for player names
        JPanel playerPanel = new JPanel();

        inner.add(playerPanel,BorderLayout.CENTER);

        // adds everyhting to the JPanel and creates Player list, but doesn't fill it

        List<Player> players = new ArrayList<Player>();
        
        switch(num){
            case 1:
                playerPanel.add(p1);
                break;

            case 2:
                playerPanel.add(p1);
                playerPanel.add(p2);

                break;

            case 3:
                playerPanel.add(p1);
                playerPanel.add(p2);
                playerPanel.add(p3);
                

                break;

            case 4:
                playerPanel.add(p1);
                playerPanel.add(p2);
                playerPanel.add(p3);
                playerPanel.add(p4);

                break;

            case 5:
                playerPanel.add(p1);
                playerPanel.add(p2);
                playerPanel.add(p3);
                playerPanel.add(p4);
                playerPanel.add(p5);

                break;

        }
        
        
        playerPanel.add(createG);

        // action listener to make fill array list on names and create game
        createG.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){

                    switch(num){
                        case 1:
                            players.add(new Player(p1.getText(), true,false));
                        
                            break;

                        case 2:
                            players.add(new Player(p1.getText(), true,false));
                            players.add(new Player(p2.getText(), false,true));
        
                            break;
                        case 3:
                            players.add(new Player(p1.getText(), true,false));
                            players.add(new Player(p2.getText(), false,false));
                            players.add(new Player(p3.getText(), false,true));

                            break;
                        case 4:
                            players.add(new Player(p1.getText(), true,false));
                            players.add(new Player(p2.getText(), false,false));
                            players.add(new Player(p3.getText(), false,false));
                            players.add(new Player(p4.getText(), false,true));

                            break;
                        case 5:
                            players.add(new Player(p1.getText(), true,false));
                            players.add(new Player(p2.getText(), false,false));
                            players.add(new Player(p3.getText(), false,false));
                            players.add(new Player(p4.getText(), false,false));
                            players.add(new Player(p5.getText(), false,true));

                            break;
                    }
                    

                    
                 

                    // creates the game
                    Game game = Game.initGame(gName.getText(), new Map(),players, color,players.get(0) );
                }
            }        );
    }
 }