package src.game;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import resources.default_data.SaveLoader;

import java.awt.Color;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import src.game.map.Map;
import src.game.cards.PersonalityCard;
import src.game.player.Player;

/**
 * A class to represent a Game and all of its components
 * @author devinlinux
 */
public class Game {

    private Map map;
    private List<Player> players;
    private List<Color> colors;
    private String name;
    private List<PersonalityCard> drawPile;
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
        this.bonusBox = new BonusBox(12);
        this.lastTurn = false;
        this.drawPile = initDrawPile();
        this.topFacingDiscardedCards = new ArrayList<>(players.size());
        this.totalTurnsPlayed = 0;
        this.path = String.format("resources%ssaves%s%s.gam", SEP, this.name);
    }

    /**
     * Method to make and shuffle the draw pile of this Game
     * @return the draw pile that will be used for this Game
     */
    private List<PersonalityCard> initDrawPile() {
        return null;  //  TODO: information on number and types of cards
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
 }
