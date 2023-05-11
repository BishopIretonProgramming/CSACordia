package src.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static src.game.map.PathNode.PathType.LAND;
import static src.game.map.PathNode.PathType.SEA;

/**
 * A class to represent an entire map in the game of Concordia
 * @author devinlinux
 */
public class Map {

    /**
     * The Network representing the various cities and paths of the map
     */
    private Network network;

    /**
     * The nodes for the map
     */
    private List<CityNode> cities;

    /**
     * The paths for the map
     */
    private List<PathNode> paths;

    /**
     * The type of map
     */
    private byte type;

    /**
     * The symbolic constant for the imperium map
     */
    public static final byte IMPERIUM = 0;

    /**
     * The symbolic constant for the italia map
     */
    public static final byte ITALIA = 1;

    /**
     * Constructor to make a new Map
     * @param type the type of map
     */
    public Map(byte type) {
        if (type != IMPERIUM || type != ITALIA) {
            throw new IllegalArgumentException("Invalid map type");
        }
        this.type = type;
        init();
    }

    /**
     * Method to make the network based on the type of map
     */
    public void init() {
        switch (type) {
            case 0 -> {
                cities = new ArrayList<>(
                        Arrays.asList(
                                new CityNode("Isca D.", 0),
                                new CityNode("Londinivm", 1),
                                new CityNode("Colonia A", 2),
                                new CityNode("Vindobona", 3),
                                new CityNode("Napoca", 4),
                                new CityNode("Lvtetia", 5),
                                new CityNode("Sirmivm", 6),
                                new CityNode("Tomis", 7),
                                new CityNode("Brigantivm", 8),
                                new CityNode("Bvrdigala", 9),
                                new CityNode("Massilia", 10),
                                new CityNode("Novaria", 11),
                                new CityNode("Aquileia", 12),
                                new CityNode("Bycantivm", 13),
                                new CityNode("Sinope", 14),
                                new CityNode("Olisipo", 15),
                                new CityNode("Valentia", 16),
                                new CityNode("Carthago", 17),
                                new CityNode("Syracvsa", 18),
                                new CityNode("Dyrrhachium", 19),
                                new CityNode("Athena", 20),
                                new CityNode("Attalia", 21),
                                new CityNode("Antiochia", 22),
                                new CityNode("Tyros", 23),
                                new CityNode("Rvsadir", 24),
                                new CityNode("Leptis Magna", 25),
                                new CityNode("Cyrene", 26),
                                new CityNode("Alexandria", 27),
                                new CityNode("Memphis", 28),
                                new CityNode("Petra", 29)
                        )
                );
                paths = null;  //  TODO: method in Network to return PathNodes from connections because I do not want to write out the code for every PathNode

            }
            case 1 -> {
                //  TODO: high resolution image of Italia map so that the names of the cities can be read
            }
        }
    }

    /**
     * Method to get the city by name
     * @param name the name of the CityNode to find
     * @return the CityNode in cities whose name matches name
     */
    private CityNode get(String name) {
        if (cities == null || name == null) {
            return null;
        }
        for (CityNode city : this.cities) {
            if (city.name().equals(name)) {
                return city;
            }
        }
        return null;
    }

    /**
     * Getter method for the map cities
     * @return the cities of this Map
     */
    public List<CityNode> cities() {
        return this.cities;
    }

    /**
     * Getter method for the map paths
     * @return the paths of this Map
     */
    public List<PathNode> paths() {
        return this.paths;
    }
}
