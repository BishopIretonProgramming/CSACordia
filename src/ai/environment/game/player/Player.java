package src.ai.environment.game.player;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import src.ai.environment.game.cards.PersonalityCard;
import src.ai.environment.game.cards.*;
import src.ai.environment.game.cards.godtypes.*;
import src.ai.environment.game.Colonist;
import src.ai.environment.game.map.City;
import src.ai.environment.game.House;

/**
 * A basic class to represent the belongings of a player in the game
 * of Concordia.
 *
 * @author devinlinux
 */
public class Player {

    /**
     * The personalty cards this player has in their hand.
     */
    protected List<PersonalityCard> cards;

    /**
     * The personality cards this player has discarded.
     */
    protected List<PersonalityCard> discard;

    /**
     * The colonists of this player.
     */
    protected List<Colonist> colonists;

    /**
     * The houses of this player.
     */
    protected List<House> houses;

    /**
     * The storehouse of the player
     */
    protected StoreHouse storeHouse;

    /**
     * The amount of sestertii this player has.
     */
    protected int sestertii;

    /**
     * The amount of victory points this player has.
     */
    protected int victoryPoints;

    /**
     * Constructor to make a new Player.
     */
    public Player() {
        this.cards = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.colonists = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.storeHouse = new StoreHouse(this);
        this.sestertii = 0;
        this.victoryPoints = 0;
        this.init();
    }

    /**
     * Method to initialize the player.
     */
    private void init() {
        this.initCards();
        this.initColonists();
        this.initHouses();
    }

    /**
     * Method to initialize the cards of the player.
     */
    private void initCards() {
        this.cards.add(new Tribune());
        this.cards.add(new Diplomat());
        this.cards.add(new Architect());
        this.cards.add(new Mercator());
        this.cards.add(new Prefect());
        this.cards.add(new Prefect());
        this.cards.add(new Senator());
    }

    /**
     * Method to initialize the colonists of the player.
     */
    private void initColonists() {
        this.colonists.add(new Colonist(this, true));
        this.colonists.add(new Colonist(this, true));
    }

    /**
     * Method to initialize the houses of the player.
     */
    private void initHouses() {
        for (int i = 0; i < 15; i++) {
            this.houses.add(new House(this));
        }
    }

    /**
     * Method to get the cities where the player has houses.
     *
     * @return the cities where the player has houses
     */
    public List<City> getCitiesWithHouses() {
        return this.houses.stream()
                .map(House::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Method to get the {@code PersonalityCard}s of this player.
     *
     * @return the {@code PersonalityCard}s of this player.
     */
    public List<PersonalityCard> getCards() {
        return this.cards;
    }

    /**
     * Method to get the {@code PersonalityCard}s of this player.
     *
     * @return the {@code PersonalityCard}s of this player.
     */
    public List<PersonalityCard> getDiscard() {
        return this.discard;
    }

    /**
     * Method to get the {@code Colonist}s of this player.
     *
     * @return the {@code Colonist}s of this player.
     */
    public List<Colonist> getColonists() {
        return this.colonists;
    }

    /**
     * Method to add a colonist to the board.
     *
     * @param colonist the colonist to add to the board
     */
    public void addColonistToBoard(Colonist colonist) {
        this.colonists.add(colonist);
    }

    /**
     * Method to get the {@code House}s of this player.
     *
     * @return the {@code House}s of this player.
     */
    public List<House> getHouses() {
        return this.houses;
    }

    /**
     * Method to get the {@code StoreHouse} of this player.
     *
     * @return the {@code StoreHouse} of this player.
     */
    public StoreHouse getStoreHouse() {
        return this.storeHouse;
    }

    /**
     * Method to get the amount of sestertii this player has.
     *
     * @return the amount of sestertii this player has.
     */
    public int getSestertii() {
        return this.sestertii;
    }

    /**
     * Method to add sesttertii to this player.
     *
     * @param amount the amount of sestertii to add
     */
    public void addSestertii(int amount) {
        this.sestertii += amount;
    }

    /**
     * Method to get the amount of victory points this player has.
     *
     * @return the amount of victory points this player has.
     */
    public int getVictoryPoints() {
        return this.victoryPoints;
    }

    /**
     * Method to add victory points to this player.
     *
     * @param amount the amount of victory points to add
     */
    public void addVictoryPoints(int amount) {
        this.victoryPoints += amount;
    }
}
