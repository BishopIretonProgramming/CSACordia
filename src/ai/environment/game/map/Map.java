package src.ai.environment.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import src.ai.environment.game.Good;

/**
 * A basic class to represent the game map.
 *
 * @author devinlinux
 */
public class Map {

    /**
     * The {@code Network} representing the map.
     */
    private Network network;

    /**
     * The nodes of the map
     */
    private List<City> cities;

    /**
     * The edges of the map
     */
    private List<Path> paths;

    /**
     * Whether it is the imperium map
     */
    private boolean imperium;

    /**
     * The list of provinces in the map
     */
    private List<Province> provinces;

    /**
     * The list of city tokens to be used in the construction
     * of the map.
     */
    private List<Good> cityTokens;

    /**
     * The random number generator for this class
     */
    Random random;

    /**
     * Constructor to make a new Map
     *
     * @param imperium whether it is the imperium map
     */
    public Map(boolean imperium) {
        this.imperium = imperium;
        this.random = new Random();
        init();
    }

    /**
     * Method to make the network based on the type of map.
     */
    private void init() {
        if (imperium) {

            this.cityTokens = new ArrayList<>(
                    Arrays.asList(
                            Good.BRICK,
                            Good.FOOD,
                            Good.WINE,
                            Good.BRICK,
                            Good.FOOD,
                            Good.TOOL,
                            Good.CLOTH,  //  END A
                            Good.TOOL,
                            Good.FOOD,
                            Good.BRICK,
                            Good.TOOL,
                            Good.WINE,
                            Good.FOOD,
                            Good.CLOTH,
                            Good.BRICK,  //  END B
                            Good.BRICK,
                            Good.WINE,
                            Good.FOOD,
                            Good.FOOD,
                            Good.BRICK,
                            Good.BRICK,
                            Good.CLOTH,
                            Good.WINE,
                            Good.TOOL,
                            Good.TOOL,  //  END C
                            Good.CLOTH,
                            Good.WINE,
                            Good.BRICK,
                            Good.TOOL,
                            Good.FOOD  //  END D
                    )
            );

            cities = new ArrayList<>(
                    Arrays.asList(
                            new City(0, getRandomCityToken()),
                            new City(1, getRandomCityToken()),
                            new City(2, getRandomCityToken()),
                            new City(3, getRandomCityToken()),
                            new City(4, getRandomCityToken()),
                            new City(5, getRandomCityToken()),
                            new City(6, getRandomCityToken()),
                            new City(7, getRandomCityToken()),
                            new City(8, getRandomCityToken()),
                            new City(9, getRandomCityToken()),
                            new City(10, getRandomCityToken()),
                            new City(11, getRandomCityToken()),
                            new City(12, getRandomCityToken()),
                            new City(13, getRandomCityToken()),
                            new City(14, getRandomCityToken()),
                            new City(15, getRandomCityToken()),
                            new City(16, getRandomCityToken()),
                            new City(17, getRandomCityToken()),
                            new City(18, getRandomCityToken()),
                            new City(19, getRandomCityToken()),
                            new City(20, getRandomCityToken()),
                            new City(21, getRandomCityToken()),
                            new City(22, getRandomCityToken()),
                            new City(23, getRandomCityToken()),
                            new City(24, getRandomCityToken()),
                            new City(25, getRandomCityToken()),
                            new City(26, getRandomCityToken()),
                            new City(27, getRandomCityToken()),
                            new City(28, getRandomCityToken()),
                            new City(29, getRandomCityToken()),
                            new City(30, null)
                    )
            );

            this.network = new Network(cities.size());
            network.setCities(cities);
            network.connect(0, 8, false);
            network.connect(0, 1, true);
            network.connect(0, 1, false);
            network.connect(0, 9, false);
            network.connect(1, 5, true);
            network.connect(1, 2, false);
            network.connect(2, 5, true);
            network.connect(2, 11, true);
            network.connect(2, 3, true);
            network.connect(3, 12, true);
            network.connect(3, 6, true);
            network.connect(4, 6, true);
            network.connect(4, 7, true);
            network.connect(5, 9, true);
            network.connect(5, 10, true);
            network.connect(6, 7, true);
            network.connect(6, 12, true);
            network.connect(6, 19, true);
            network.connect(7, 13, false);
            network.connect(7, 14, false);
            network.connect(8, 15, false);
            network.connect(8, 15, true);
            network.connect(8, 16, true);
            network.connect(8, 9, true);
            network.connect(8, 9, false);
            network.connect(9, 10, true);
            network.connect(10, 11, true);
            network.connect(10, 16, false);
            network.connect(10, 16, true);
            network.connect(10, 30, false);
            network.connect(10, 17, false);
            network.connect(11, 30, true);
            network.connect(11, 12, true);
            network.connect(12, 30, true);
            network.connect(12, 19, false);
            network.connect(13, 19, true);
            network.connect(13, 14, false);
            network.connect(13, 14, true);
            network.connect(13, 20, false);
            network.connect(13, 21, true);
            network.connect(14, 22, true);
            network.connect(15, 16, true);
            network.connect(15, 24, true);
            network.connect(15, 24, false);
            network.connect(16, 24, true);
            network.connect(16, 24, false);
            network.connect(16, 17, false);
            network.connect(17, 24, true);
            network.connect(17, 24, false);
            network.connect(17, 25, true);
            network.connect(17, 25, false);
            network.connect(17, 30, false);
            network.connect(17, 18, false);
            network.connect(18, 30, true);
            network.connect(18, 30, false);
            network.connect(18, 25, false);
            network.connect(18, 26, false);
            network.connect(18, 20, false);
            network.connect(18, 19, false);
            network.connect(19, 20, true);
            network.connect(20, 26, false);
            network.connect(20, 27, false);
            network.connect(20, 21, false);
            network.connect(21, 27, false);
            network.connect(21, 22, true);
            network.connect(21, 22, false);
            network.connect(22, 23, false);
            network.connect(22, 23, true);
            network.connect(23, 27, true);
            network.connect(23, 27, false);
            network.connect(23, 29, true);
            network.connect(25, 26, true);
            network.connect(25, 26, false);
            network.connect(26, 27, true);
            network.connect(26, 27, false);
            network.connect(26, 28, true);
            network.connect(27, 28, true);
            network.connect(28, 29, true);

            this.paths = network.getPaths();
        }
    }

    /**
     * Method to get a random city token.
     *
     * @return a random city token
     */
    private Good getRandomCityToken() {
        return this.cityTokens.remove(this.random.nextInt(this.cityTokens.size()));
    }

    /**
     * Getter to return the cities of this map.
     *
     * @return the cities of this map
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Getter to return the paths of this map.
     *
     * @return the paths of this map
     */
    public List<Path> getPaths() {
        return paths;
    }

    public static void main(String[] args) {
        Map map = new Map(true);
        System.out.println(map.paths.size());
    }
}
