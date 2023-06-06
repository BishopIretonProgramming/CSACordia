package src.game;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import resources.default_data.saveloading.SaveLoader;

import java.awt.Color;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import src.game.map.Map;
import src.ai.environment.game.Good;
import src.game.cards.PersonalityCard;
import src.game.cards.CardStack;
import src.game.player.Player;
import src.game.player.StoreHouse;

/**
 * A class to represent a Game and all of its components
 * @author devinlinux
 */
public class Game {

    private Map map;
    private List<Player> players;
    private List<Color> colors;
    private String name;
    private CardStack drawPile;
    private BonusBox bonusBox;
    private Player currentPlayer;
    private static final String SEP = File.separator;
    private Player endingPlayer;
    private List<PersonalityCard> topFacingDiscardedCards;
    private boolean lastTurn;
    private int totalTurnsPlayed;
    private String path;

    public static Game initGame(String name, Map map, List<Player> players, List<Color> colors, Player firstPlayer) {
        if (SaveLoader.existingNames().contains(name))
            return new Game(name, map, players, colors, firstPlayer);
        return null;
    }

    public static Game DO_NOT_USE___SAVE_LOADER_GAME_INITIALIZER(String name, Map map, List<Player> players, List<Color> colors, Player firstPlayer) {
        return new Game(name, map, players, colors, firstPlayer);
    }

    /**
     * Constructor to make a new Game
     * @param name the name of this Game
     * @param map the map that this Game will use
     * @param players the players who will be playing the game
     * @param colors the colors of the players who will be playing this Game
     * @param firstPlayer the Player who will play first
     */
    private Game(String name, Map map, List<Player> players, List<Color> colors, Player firstPlayer) {
        this.name = name;
        this.map = map;
        this.players = players;
        this.colors = colors;
        this.currentPlayer = firstPlayer;
        this.bonusBox = new BonusBox(20, 20);
        this.lastTurn = false;
        this.drawPile = new CardStack();
        this.topFacingDiscardedCards = new ArrayList<>(players.size());
        this.totalTurnsPlayed = 0;
        this.path = String.format("resources%ssaves%s%s.gam", SEP, this.name);
    }

    /**
     * Method to make the topFacingDiscardedCards List
     * @return the contents of the List after each turn
     */
    private List<PersonalityCard> updateTopFacingDiscardedCards() {
        return this.players.stream()
                .map(player -> !player.discard().isEmpty() ? player.discard().get(0) : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Method to check if a Player has ended the game, thus making it the last turn of this Game
     * @return whether a Player has ended this Game
     */
    private boolean endedGame() {
        return this.drawPile.isEmpty()
                || players.stream().anyMatch(player -> player.numHouses() == 0);
    }

    /**
     * Method to play the game
     */
    public void play() {
        while (!lastTurn) {
            this.currentPlayer = this.players.get(totalTurnsPlayed % players.size());
            /* TODO: Graphics interoperability */
            updateTopFacingDiscardedCards();
            this.lastTurn = endedGame();
            this.totalTurnsPlayed++;
        }
        this.currentPlayer.addVictoryPoints(7);
        this.endingPlayer = this.currentPlayer;
        do {
            currentPlayer = this.players.get(totalTurnsPlayed % players.size());
            /* TODO: Graphics interoperability */
            updateTopFacingDiscardedCards();
        } while (this.currentPlayer != this.endingPlayer);
    }

    /**
     * Method to write this Game to a file
     * @param path the path to the file to write to
     */
    public void save(String path) { // TODO we need the game saving to actually save stuff so we can recreate the data from a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String line = String.format("");
        } catch (IOException e) {
            System.err.printf("Error saving game: %s%n", e.getMessage());
        }
    }

    /**
     * Setter to set the save location of this Game
     * @param path the path to where the game should be saved
     */
    public void setSavePath(String path) {
        this.path = path;
    }

    /**
     * Getter to get the current Player
     * @return the current Player
     */
    public Player currentPlayer() {
        return this.currentPlayer;
    }

    public List<PersonalityCard> getTopFacingDiscardedCards() {
        return topFacingDiscardedCards;
    }

    /**
     * Getter to get the map
     * @return the map
     */
    public Map getMap(){
        return this.map;
    }

    /**
     * Getter to get the list of players
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Getter to get the list of colors
     * @return the list of colors
     */
    public List<Color> getColors() {
        return this.colors;
    }

    /**
     * Getter to get the name of Game
     * @return the name of Game
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter to get the draw pile of personality cards
     * @return the draw pile personality cards
     */
    public List<PersonalityCard> getDrawPile(){
        return this.drawPile;
    }

    /**
     * Getter to get the bonus box
     * @return the bonus box
     */
    public BonusBox getBonusBox() {
        return this.bonusBox;
    }

    /**
     * Getter to get the ending player
     * @return the ending player
     */
    public Player getEndingPlayer() {
        return this.endingPlayer;
    }

    /**
     * Getter to get the last turn played
     * @return the last turn played
     */
    public boolean getLastTurn() {
        return this.lastTurn;
    }

    /**
     * Getter to get the total number of turns played
     * @return the total number of turns played
     */
    public int getTotalTurnsPlayed() {
        return this.totalTurnsPlayed;
    }

    /**
     * Getter to get the path
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /*SCORING - EMILY */ 

    /**
     * Vesta calculations: gets total amout of sestertii from money + storehouse
     * @return number of victory points from Vesta
     */
    public int getVestaVP(Player p) {
        StoreHouse resources = p.storeHouse();
        int totalSestertii = p.sestertii();

        for(Storeable o: resources.getResources()){
            if (o.equals(Good.BRICK))
                totalSestertii += 3;
            else if (o.equals(Good.FOOD))
                totalSestertii += 4;
            else if (o.equals(Good.TOOL))
                totalSestertii += 5;
            else if (o.equals(Good.WINE))
                totalSestertii += 6;
            else if (o.equals(Good.CLOTH))
                totalSestertii += 7;
            else 
                totalSestertii += 0;
        }

        return totalSestertii/10;
    }

    /**
     * Jvpiter calculations: number of non brick house muliplied by number of Jvpiter cards
     * @return number of victory points from Jvpiter
     */
    public int getJvpiterVP(Player p){
        ArrayList<House> houses = p.houses();
        int count = 0;
        // get jvp cards

        for (House h: houses){
            if(!h.good().equals(Good.BRICK))
                count++;
        }
        return count; // * num of jvp cards
    }

    /**
     * Satvrnvs calculations: number of provinces that have a players city in it muliplied by number of Satvrnvs cards
     * @return number of victory points from Satvrnvs
     */
    public int getSatvrnvsVP(Player p){
        return 0;
    }

    /**
     * Mercvrivs calculations: number of goods produced muliplied by number of Mercvrivs cards
     * @return number of victory points from Mercvrivs
     */
    public int getMercvrivsVP(Player p){
        ArrayList<House> houses = p.houses();
        int foodCount = 0;
        int toolCount = 0;
        int wineCount = 0;
        int clothCount = 0;
        int brickCount = 0;
        int count = 0;
        // get merc cards

        for (House h: houses){
            if (count == 5){
                return (count * 2); //  *merc cards
            }
            if (h.good().equals(Good.BRICK)){
                brickCount++;
                if (brickCount == 1)
                    count++;
            }
            else if (h.good().equals(Good.FOOD)){
                foodCount++;
                if(foodCount == 1)
                    count++;
            }
            else if(h.good().equals(Good.TOOL)){
                toolCount++;
                if(toolCount == 1)
                    count++;
            }
            else if(h.good().equals(Good.WINE)){
                wineCount++;
                if(wineCount == 1)
                    count++;
            }
            else {
                clothCount++;
                if(clothCount == 1)
                    count++;
            }
                    
        }
        return count * 2; // *num of merc cards
    }

    /**
     * Mars calculations: number of colonists on the board produced muliplied by number of Mars cards
     * @return number of victory points from Mars
     */
    public int getMarsVP (Player p) {
        // get num of colonists on board
        // return num of colonists on board * num of mars cards
        return 0;
    }

    /**
     * Minera calculations: based of speciality cards muliplied by number on certain Minera cards
     * @return number of victory points from Minera
     */
    public int getMineraVP(Player p) {
        return 0;
    }
 }
