package src.gui;

/*
    @author Caroline Miller
    PlayerResources: GUI Representation of the Player's Resources (houses + sestertii)
 */

import src.game.player.Player;

import javax.swing.JPanel;
import javax.swing.JLabel;


public class PlayerResources {
    private Player player; //The specific player whose resources these are

    private int sestertii; //number of sestertii
    private int numHouses; //number of houses

    private JPanel panel; //JPanel

    private JLabel sestertiiLabel;
    private JLabel housesLabel;
 
    
    /**
     * Contructor
     * @param player the player
     * @param sestertii amount of money the player has
     * @param numHouses number of houses the player has
     * @param panel panel that holds the graphics
     */
    public PlayerResources(Player player, int sestertii, int numHouses, JPanel panel) {
        this.player = player;
        this.sestertii = sestertii;
        this.numHouses = numHouses;
        this.panel = panel;

        createJLabels();
        addingJLabels();
    }

    /** 
     * creates JLabels
    */
    public void createJLabels() {
        sestertii = getSestertii();
        numHouses = getHouses();
        
        sestertiiLabel = new JLabel("Sestertii Amount = " + sestertii, JLabel.LEFT); //add where the label will go
        housesLabel = new JLabel("Number of Houses = " + numHouses, JLabel.RIGHT); //add where the label will go

        sestertiiLabel.setSize(100,100); //width + height will change
        housesLabel.setSize(100,100); //width + height will change
    }

    /**
     * adds JLabels to the JPanel
     */
    public void addingJLabels() {
        panel.add(sestertiiLabel);
        panel.add(housesLabel);
    }

    /* 
    public void updateJLabels() {
        sestertiiLabel.setText("Sestertii Amount = " + getSestertii());
        housesLabel.setText("Number of Houses = " + getHouses());
    }
    */
        
    /**
     * @return player
     */
    public Player getPlayer() {
       return player;
    }

    /**
     * @return sestertii
     */
    public int getSestertii() {
         return sestertii;
    }

    /**
     * @return numHouses
     */
    public int getHouses() {
        return numHouses;
    }
}