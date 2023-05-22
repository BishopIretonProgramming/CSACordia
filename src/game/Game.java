package src.game;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.awt.Color;

import src.game.map.Map;
import src.game.cards.PersonalityCard;

/**
 * A class to represent a Game and all of its components
 * @author devinlinux
 */
public class Game {

    /**
     * The Map of this Game
     */
    private Map map;

    /**
     * The Players of this Game
     */
    private List<Player> players;

    /**
     * The Colors of each of the Players
     */
    private List<Color> colors;

    /**
     * The name of this Game
     */
    private String name;

    /**
     * The draw pile of this Game
     */
    private List<PersonalityCard> drawPile;

    /**
     * The Bonus Box of this Game
     */
    private BonusBox bonusBox;

    /**
     * The current Player who is playing their turn
     */
    private Player currentPlayer;

    /**
     * The Player who ended the game
     */
    private Player endingPlayer;

    /**
     * The List of top facing cards in the discard pile of each Player
     */
    private List<PersonalityCard> topFacingDiscardedCards;

    /**
     * Whether it is the last turn
     */
    private boolean lastTurn;

    /**
     * The number of turns that have been played in this Game
     */
    private int totalTurnsPlayed;

    /**
     * Constructor to make a new Game
     * @param name the name of this Game
     * @param map the map that this Game will use
     * @param players the players who will be playing the game
     * @param colors the colors of the players who will be playing this Game
     * @param firstPlayer the Player who will play first
     */
    public Game(String name, Map map, List<Player> players, List<Color> colors, Player firstPlayer) {
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
}
