package src;

//  imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent a player
 * can be serialized for game state saving
 * @author Michael Bobrowski (devinlinux)
 */

public class Player implements java.io.Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    //  waiting for PersonalityCard class or something similar
    //  TODO: Personality Cards, Colonists
    //private ArrayList<PersonalityCard> cards;  //  the player's personality cards
    //private ArrayList<PersonalityCard> discard;  //  the personality cards that have been played
    //private ArrayList<Colonist> colonists;  //  the player's colonists
    private StoreHouse storeHouse;  //  the player's storeHouse
    private final String NAME;  //  the name of the player
    private int victoryPoints;  //  the number of victory points the player has
    private int sestertii;  //  the amount of sestertii the player has
    private int numHouses;  //  the number of houses the player has
    private boolean hasPraefectusMagnus;  //  whether the player has the praefectus magnus card

    //  constructor with name - basic constructor
    public Player(final String NAME) {
        this.NAME = NAME;
        //this.cards = new ArrayList<>(7);
        //this.discard = new ArrayList<>();
        //this.colonists = new ArrayList<>(6);
        this.storeHouse = new StoreHouse();
        this.victoryPoints = 0;
        this.sestertii = 15;
        this.numHouses = 0;
        this.hasPraefectusMagnus = false;
        init();
    }

    //  constructor with option to set the player as first or last
    public Player(final String NAME, boolean first, boolean last) {
        this.NAME = NAME;
        //this.cards = new ArrayList<>(7);
        //this.discard = new ArrayList<>();
        //this.colonists = new ArrayList<>(6);
        this.storeHouse = new StoreHouse();
        this.victoryPoints = 0;
        this.sestertii = first ? 5 : 1;
        this.numHouses = 15;
        this.hasPraefectusMagnus = last;
        init();
    }

    public void init() {
        //  TODO: Classes for personality cards
        // cards.add(new Tribune());
        // cards.add(new Diplomat());
        // cards.add(new Architect());
        // cards.add(new Mercator());
        // cards.add(new Prefect());
        // cards.add(new Prefect());
        // cards.add(new Senator());
    }

    //  method to check if a player can afford something based on the cost
    public boolean canAfford(HashMap<Object, Integer> cost) {
        for (Map.Entry<Object, Integer> entry : cost.entrySet()) {
            Object key = entry.getKey();
            int value = entry.getValue();

            if (key instanceof Integer) {  //  if the key is a sestertius
                if (this.sestertii < value) {
                    return false;
                }
            }
            
            //  TODO: Good enum/class
            //if (key instanceof Good) {  //  if the key is a Good
                if (!this.storeHouse.contains(key, value)) {
                    return false;
                }
            //}
        }
        return true;
    }
    
    //  getter to get the name of the player
    public String name() {
        return this.NAME;
    }

    //  getter to get the StoreHouse of a plyer
    public StoreHouse storeHouse() {
        return this.storeHouse;
    }

    //  getter for numHouses
    public int numHouses() {
        return this.numHouses;
    }

    //  getter for victory points
    public int victoryPoints() {
        return this.victoryPoints;
    }

    //  getter for sestertii
    public int sestertii() {
        return this.sestertii;
    }

    //  getter for praefectus magnus
    public boolean hasPraefectusMagnus() {
        return this.hasPraefectusMagnus;
    }

    //  setter for victory points
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    //  setter for praefectus magnus
    public void setPraefectusMagnus(boolean has) {
        this.hasPraefectusMagnus = has;
    }

    //  setter for numHouses
    public void setNumHouses(int num) {
        this.numHouses = num;
    }

    //  setter for sestertii
    public void setSestertii(int sestertii) {
        this.sestertii = sestertii;
    }
}
