package src.game.player;

//  imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

import src.game.Good;
import src.game.House;
import src.game.cards.PersonalityCard;
import src.game.cards.Architect;
import src.game.cards.Colonist;
import src.game.cards.Diplomat;
import src.game.cards.Mercator;
import src.game.cards.Tribune;
import src.game.cards.Prefect;
import src.game.cards.Senator;

/**
 * A class to represent a player
 * can be serialized for game state saving
 * @author devinlinux
 */

public class Player implements java.io.Serializable {

    /**
     * The version ID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The personality cards of this player
     */
    private ArrayList<PersonalityCard> cards;

    /**
     * The discard pile of this Player's personality cards
     */
    private ArrayList<PersonalityCard> discard;

    /**
     * The colonists of this Player
     */
    private ArrayList<Colonist> colonists;

    /**
     * The StoreHouse of this Player
     */
    private StoreHouse storeHouse;

    /**
     * The name of this Player
     */
    private final String NAME;

    /**
     * The number of victory points this Player has
     */
    private int victoryPoints;

    /**
     * The number of sestertii this Player has
     */
    private int sestertii;

    /**
     * The houses of the players
     */
    private ArrayList<House> houses;

    /**
     * The number of houses the player has
     */
    private int numHouses;

    /**
     * Whether the player has the praefectus magnus card
     */
    private boolean hasPraefectusMagnus;

    /**
     * The color of this player
     */
    private Color color;

    /**
     * The number of turns this player has left
     */
    private int numTurnsLeft;

    /**
     * Constructor to make a new Player with a name
     * @param NAME the name of this Player
     */
    public Player(final String NAME) {
        this.NAME = NAME;
        this.cards = new ArrayList<>(7);
        this.discard = new ArrayList<>();
        this.colonists = new ArrayList<>(6);
        this.storeHouse = new StoreHouse(this);
        this.victoryPoints = 0;
        this.sestertii = 15;
        this.houses = new ArrayList<>(15);
        this.numHouses = 15;
        this.hasPraefectusMagnus = false;
        this.numTurnsLeft = Integer.MAX_VALUE;
        init();
    }

    /**
     * Constructor to specify whether the player is first or last
     * @param NAME the name of this Player
     * @param first whether this Player plays first
     * @param last whether this Player plays last
     */
    public Player(final String NAME, boolean first, boolean last) {
        this.NAME = NAME;
        this.cards = new ArrayList<>(7);
        this.discard = new ArrayList<>();
        this.colonists = new ArrayList<>(6);
        this.storeHouse = new StoreHouse(this);
        this.victoryPoints = 0;
        this.sestertii = first ? 5 : 1;
        this.numHouses = 15;
        this.hasPraefectusMagnus = last;
        init();
    }

    /**
     * A method to fill the ArrayList of Houses of this Player
     */
    private void fillHouses() {
        for (int i = 0; i < 15; i++) {
            this.houses.add(new House(this));
        }
    }

    /**
     * Method to initialize the personality cards of this Player
     */
    public void init() {
        cards.add(new Tribune(this));
        cards.add(new Diplomat(this, 0, 0, 0, 0, 0, "Jvpiter"));
        cards.add(new Architect(this, true));
        cards.add(new Mercator(this, true));
        cards.add(new Prefect(this, true));
        cards.add(new Prefect(this, true));
        cards.add(new Senator(this));
    }

    /**
     * Method to check whether this Player can afford something based on its cost
     * @param cost the cost of the item, the key should be an Integer for sestertii
     *             and the value should be the value of either the sestertii or
     *             the good in the Map
     * @return     whether this Player can afford something based on its cost
     */
    public boolean canAfford(HashMap<Object, Integer> cost) {
        for (Map.Entry<Object, Integer> entry : cost.entrySet()) {
            Object key = entry.getKey();
            int value = entry.getValue();

            if (key instanceof Integer) {  //  if the key is a sestertius
                if (this.sestertii < value) {
                    return false;
                }
            }

            //  DO NOT PUT MORE THAN ONE REFERENCE TO A SINGLE TYPE OF GOOD IN THE HASHMAP
            if (key instanceof Good) {  //  if the key is a Good
                if (!this.storeHouse.contains((Good) key, value)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Getter to get the name of this Player
     * @return the name of this Player
     */
    public String name() {
        return this.NAME;
    }

    /**
     * Getter to return the StoreHouse of this Player
     * @return the StoreHouse of this Player
     */
    public StoreHouse storeHouse() {
        return this.storeHouse;
    }

    /**
     * Getter to return the number of Houses this Player has
     * @return the number of houses this Player has
     */
    public int numHouses() {
        return this.numHouses;
    }

    /**
     * Getter to return the personality cards this Player has
     * @return the personality cards of this Player
     */
    public ArrayList<PersonalityCard> cards() {
        return this.cards;
    }

    /**
     * Getter to return the discard pile of personality cards of this Player
     * @return the discarded personalty cards of this Player
     */
    public ArrayList<PersonalityCard> discard() {
        return this.discard;
    }

    /**
     * Getter to return the Colonists of this Player
     * @return the Colonists of this Player
     */
    public ArrayList<Colonist> colonists() {
        return this.colonists;
    }

    /**
     * Getter to return the number of victory points of this Player
     * @return the number of victory points this Player has
     */
    public int victoryPoints() {
        return this.victoryPoints;
    }

    /**
     * Getter to return the number of sestertii this Player has
     * @return the number of sestertii this Player has
     */
    public int sestertii() {
        return this.sestertii;
    }

    /**
     * Getter to return the houses of this Player
     * @return the houses of this Player
     */
    public ArrayList<House> houses() {
        return this.houses;
    }

    /**
     * Getter to return whether this Player has the praefectus magnus card
     * @return whether this Player has the praefectus magnus card
     */
    public boolean hasPraefectusMagnus() {
        return this.hasPraefectusMagnus;
    }

    /**
     * Getter to return the Color of this Player
     * @return the Color of this Player
     */
    public Color color() {
        return this.color;
    }

    /**
     * Method to get the number of turns left
     * @return the number of turns this Player has left to play
     */
    private int turnsLeft() {
        return this.numTurnsLeft;
    }

    /**
     * Method to decrement the number of turns left by 1
     */
    public void decrementTurnsLeft() {
        this.numTurnsLeft--;
    }

    /**
     * Method to set the number of turns this Player has left
     * @param turns the number of turns this Player has left to play
     */
    private void setTurnsLeft(int turns) {
        this.numTurnsLeft = turns;
    }

    /**
     * Setter to set the number of victory points this Player has
     * @param victoryPoints the number of victory points this Player should have
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Method to add a certain amount of victory points to this Player
     * @param victoryPoints the number of victory points to add to this Player
     */
    public void addVictoryPoints(int victoryPoints) {
        this.victoryPoints += victoryPoints;
    }

    /**
     * Setter to set whether this Player has the praefectus magnus card
     * @param has whether this Player should have the praefectus magnus card
     */
    public void setPraefectusMagnus(boolean has) {
        this.hasPraefectusMagnus = has;
    }

    /**
     * Setter to set the number of houses this Player should have
     * @param num the number of houses this Player should have
     */
    public void setNumHouses(int num) {
        this.numHouses = num;
    }

    /**
     * Setter to set the number of sestertii this Player should have
     * @param sestertii the number of sestertii this Player should have
     */
    public void setSestertii(int sestertii) {
        this.sestertii = sestertii;
    }

    /**
     * Method to add a certain amount of sestertii to this Player
     * @param sestertii the amount of sestertii to add to this Player
     */
    public void addSestertii(int sestertii) {
        this.sestertii += sestertii;
    }

    /**
     * Method to set the Color of this Player
     * @param color the Color of this Player
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Method to add a card to the cards of this {@code Player}.
     *
     * @param card the card to add.
     */
    public void addCard(PersonalityCard card) {
        this.cards.add(card);
    }
}
