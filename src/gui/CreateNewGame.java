/**
 * @author Nora Hixson
 * only used by the welcome frame to create a new class given 
 * user input
 */
package src.gui;

import javax.swing.*;

import src.game.Game;
import src.game.player.Player;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;

 public class CreateNewGame{

private static JPanel panel = new JPanel();

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
        JPanel inner = new JPanel();
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

        JPanel bPanel = new JPanel();
        bPanel.setVisible(true);
        bPanel.setBackground(Color.BLACK);
        // Action 

        // asks for name of game and such
        JTextField gName = new JTextField("Please name your game");
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
                    playerName(1);
                }
            }        );

        two.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    playerName(2);
                }
            }        );
        three.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    playerName(3);
                }
            }        );  
            
            three.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        playerName(3);
                    }
                }        );
        
    }

    // gets the player names
    private static void playerName(int numPlayers){
        TextField p1 = new TextField("Enter First Player");
        TextField p2 = new TextField("Enter Second Player");
        TextField p3 = new TextField("Enter Third Player(optional)");
        TextField p4 = new TextField("Enter Fourth Player(optional)");
        TextField p5 = new TextField("Enter Fifth Player(optional)");

        
        p1.setBounds(bounds);
        p2.setBounds(bounds);
        p3.setBounds(bounds);
        p4.setBounds(bounds);
        p5.setBounds(bounds);

        // creates button
        JButton createG = new JButton("Create Game"); // makes a button

        // adds everyhting to the JPanel
        
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(createG);

        // action listener to make to game
        createG.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){

                    List<Player> players = new ArrayList<Player>();
                    players.add(new Player(p1.getText(), true,false));
                    players.add(new Player(p2.getText(), false,false));
                    players.add(new Player(p3.getText(), false,false));
                    players.add(new Player(p4.getText(), false,false));
                    players.add(new Player(p5.getText(), false,true));

                    List<Color> color = new ArrayList<Color>();
                    color.add(Color.BLUE);
                    color.add(Color.RED);
                 

                    // creates the game
                    //Game game = new Game.initGame(gName.getText(), new Map(),players, ,players.get(0) );
                }
            }        );
    }
 }