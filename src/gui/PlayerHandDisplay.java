package src.gui;

//imports 
import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import src.game.Player;
import src.game.StoreHouse;
import src.game.Game;

/* @author Nora Hixson
 * a class to display the Jpanel with the players storehouse, Sesterii, and mabye there deck of cards
 */

public class PlayerHandDisplay extends JPanel{

public boolean isVisible;// allows other methods to turn panel on and off
public JPanel handDisplay = new JPanel(); // this allows other methods to acess the panel

private Player currentPlayer;
private StoreHouse store;
private int sestertii;  
   
// this  constructor should allow for different values to be displayed for different players

// constructor
    

    public void display(Game g){// makes the display or panel
        
       currentPlayer = g.currentPlayer();

        // sets format and layout of Panel

        PlayerDeck deck = new PlayerDeck(currentPlayer,currentPlayer.cards(), handDisplay);
        handDisplay.setBackground(Color.GRAY);
        handDisplay.setLayout(new GridBagLayout());
        GridBagConstraints mgb = new GridBagConstraints();
        GridBagConstraints sgb = new GridBagConstraints();
        GridBagConstraints dgb = new GridBagConstraints();
        mgb.anchor = GridBagConstraints.WEST;
        sgb.anchor = GridBagConstraints.BASELINE;
        dgb.anchor = GridBagConstraints.BASELINE;

        // creates JLables
        JLabel money = new JLabel("<html><font  size ='20' color = white> Sestertii:  </font></html>");// the html just changes the size and color should work on everyone's computer
        JLabel s = new JLabel( "" + currentPlayer.sestertii());
        JLabel storeTitle = new JLabel("<html><font  size ='20' color = white> Storehouse Contents:  </font></html>");
        JLabel store = new JLabel( "" + currentPlayer.storeHouse());

        //Adds JLabels to Panel
        handDisplay.add(money,mgb);
        handDisplay.add(s,mgb);
        handDisplay.add(storeTitle,sgb);
        handDisplay.add(store,sgb);

        //Makes sure the JPanel starts as invisble
        handDisplay.setVisible(isVisible);
        
    }

    public void changeVisible(){
        isVisible = !isVisible;
        handDisplay.setVisible(isVisible);
        System.out.println( isVisible);
    }


}
