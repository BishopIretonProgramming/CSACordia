package src.ai.environment.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;

import src.ai.environment.game.Good;
import src.ai.environment.game.Colonist;
import src.ai.environment.game.House;

/**
 * A basic class to represent a city on the game map.
 *
 * @author devinlinux
 */
public class City {

    /**
     * The id of this city in a {@code Network}
     */
    protected int id;

    /**
     * The {@code Good} produced by this city
     */
    private Good good;

    /**
     * The colonists in this City
     */
    private List<Colonist> colonists;

    /**
     * The houses in this city
     */
    private List<House> houses;

    /**
     * Constructor to make a new City with an id and a good.
     *
     * @param id the id of this City in a {@code Network}
     * @param good the {@code Good} produced by this City
     */
    public City(int id, Good good) {
        this.id = id;
        this.good = good;
        this.colonists = new ArrayList<>();
        this.houses = new ArrayList<>();
    }

    /**
     * Method to add a colonist to this City.
     *
     * @param colonist the colonist to add to this City.
     */
    public void addColonist(Colonist colonist) {
        this.colonists.add(colonist);
    }

    /**
     * Method to remove a colonist from this City.
     *
     * @param colonist the colonist to remove from this City.
     * @return whether the colonist was successfully removed from this City.
     */
    public boolean removeColonist(Colonist colonist) {
        return this.colonists.remove(colonist);
    }

    /**
     * Getter to return the id of this City.
     *
     * @return the id of this City.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter to return the {@code Good} produced by this City.
     *
     * @return the {@code Good} produced by this City.
     */
    public Good getGood() {
        return this.good;
    }

    /**
     * Getter to return the colonists in this City.
     *
     * @return the colonists in this City.
     */
    public List<Colonist> getColonists() {
        return this.colonists;
    }

    /**
     * Getter to return the houses in this City.
     *
     * @return the houses in this City.
     */
    public List<House> getHouses() {
        return this.houses;
    }

    /**
     * Equals method to check if this City is equal to another object.
     *
     * @param obj the object to check equality with
     *            (must be a {@code City} to be equal)
     * @return whether this City is equal to the other object
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof City city && this.id == city.id;
    }
}
