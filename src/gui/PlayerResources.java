package src.gui;

/*
    @author Caroline Miller
    PlayerResources: 
 */

import src.game.Player;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PlayerResources {
    private Player player; //The specific player whose resources these are

    private int sestertii; //number of sestertii
    private int numHouses; //number of houses

    private JPanel panel; //JPanel

    //private JLabel sestertiiLabel;
    //private JLabel housesLabel;

    private ButtonGroup resourcesFilter;
    private JRadioButton sestertiiButton; //shows the sestertii the player has
    private JRadioButton houseButton; //shows the houses the player has 

    public PlayerResources(Player player, int sestertii, int numHouses, JPanel panel) {
        this.player = player;
        this.sestertii = sestertii;
        this.numHouses = numHouses;
        this.panel = panel;

        createJRadioButtons();
    }

    public void createJRadioButtons(){
        resourcesFilter = new ButtonGroup();

        //TBD add images, maybe
        sestertiiButton = new JRadioButton("Sestertii :)"); 
        houseButton = new JRadioButton("Houses :)");

        resourcesFilter.add(sestertiiButton); //will add an image for the button 
        resourcesFilter.add(houseButton); //will add an image for the button

        sestertiiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Show sestertii amount");
            }
        });

        houseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Show house amount");
            }
        });
    }

    //return Player
    public Player getPlayer() {
       return player;
    }

    //return sestertii amount
    public int getSestertii() {
         return sestertii;
    }

    //return house amount
    public int getHouses() {
        return numHouses;
    }
}