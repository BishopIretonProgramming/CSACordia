package src.ai.environment.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;

/**
 * A basic class to represent a province in the game of Concordia.
 *
 * @author devinlinux
 */
public class Province {

    /**
     * The cities in the province.
     */
    private List<City> cities;

    /**
     * Constructor to make a new Province.
     *
     * @param cities the cities in the province
     */
    public Province(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Method to check if a {@code City} is in the province.
     *
     * @param city the {@code City} to check
     * @return whether the {@code City} is in the province
     */
    public boolean contains(City city) {
        return cities.contains(city);
    }

    /**
     * Method to get the cities in the province.
     *
     * @return the cities in the province
     */
    public List<City> getCities() {
        return cities;
    }
}
