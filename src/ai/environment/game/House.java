package src.ai.environment.game;

//  imports
import src.ai.environment.game.player.Player;
import src.ai.environment.game.map.City;

/**
 * A basic class to represent the house of a {@code Player} in the game of Concordia.
 *
 * @author devinlinux
 */
public class House {

    /**
     * The player who owns this house.
     */
    protected Player player;

    /**
     * The city where this house has been placed.
     */
    protected City city;

    /**
     * Constructor to make a new house.
     *
     * @param player the player who owns this house
     */
    public House(Player player) {
        this.player = player;
    }

    /**
     * Method to set the {@code City} where this house has been placed.
     *
     * @param city the city where this house has been placed.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Method to get the {@code City} where this house has been placed.
     *
     * @return the city where this house has been placed.
     */
    public City getCity() {
        return this.city;
    }
}
