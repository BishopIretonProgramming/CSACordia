package src.game.map;

//  imports
import java.io.File;
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
     * An alias for File.separator
     */
    private static final String SEP = File.separator;

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
        if (type != IMPERIUM && type != ITALIA) {
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
//                cities = new ArrayList<>(
//                        Arrays.asList(
//                                new CityNode("Isca D.", 0, 'A'),
//                                new CityNode("Londinivm", 1, 'A'),
//                                new CityNode("Colonia A", 2, 'A'),
//                                new CityNode("Vindobona", 3, 'A'),
//                                new CityNode("Napoca", 4, 'A'),
//                                new CityNode("Lvtetia", 5, 'A'),
//                                new CityNode("Sirmivm", 6, 'A'),
//                                new CityNode("Tomis", 7, 'A'),
//                                new CityNode("Brigantivm", 8, 'B'),
//                                new CityNode("Bvrdigala", 9, 'B'),
//                                new CityNode("Massilia", 10, 'B'),
//                                new CityNode("Novaria", 11, 'D'),
//                                new CityNode("Aquileia", 12, 'D'),
//                                new CityNode("Bycantivm", 13, 'C'),
//                                new CityNode("Sinope", 14, 'C'),
//                                new CityNode("Olisipo", 15, 'B'),
//                                new CityNode("Valentia", 16, 'B'),
//                                new CityNode("Carthago", 17, 'B'),
//                                new CityNode("Syracvsa", 18, 'D'),
//                                new CityNode("Dyrrhachium", 19, 'D'),
//                                new CityNode("Athena", 20, 'D'),
//                                new CityNode("Attalia", 21, 'C'),
//                                new CityNode("Antiochia", 22, 'C'),
//                                new CityNode("Tyros", 23, 'C'),
//                                new CityNode("Rvsadir", 24, 'B'),
//                                new CityNode("Leptis Magna", 25, 'C'),
//                                new CityNode("Cyrene", 26, 'C'),
//                                new CityNode("Alexandria", 27, 'C'),
//                                new CityNode("Memphis", 28, 'C'),
//                                new CityNode("Petra", 29, 'C')
//                        )
//                );
//                CityNode.fwrite(String.format("src%sgame%smap%ssaves%simperium_cities.cn", SEP, SEP, SEP, SEP), cities);


                //  Note to self: 0 -> 29, no connections to lower, right > left! ^ ~ >! < ~ >!
//                this.network = new Network(30);
//                network.setCities(CityNode.fread("src/game/map/saves/imperium_cities.cn"));
//                network.connect(0, 8, SEA);
//                network.connect(0, 1, LAND);
//                network.connect(0, 1, SEA);
//                network.connect(0, 9, SEA);
//                network.connect(1, 5, LAND);
//                network.connect(1, 2, SEA);
//                network.connect(2, 5, LAND);
//                network.connect(2, 11, LAND);
//                network.connect(2, 3, LAND);
//                network.connect(3, 12, LAND);
//                network.connect(3, 6, LAND);
//                network.connect(4, 6, LAND);
//                network.connect(4, 7, LAND);
//                network.connect(5, 9, LAND);
//                network.connect(5, 10, LAND);
//                network.connect(6, 7, LAND);
//                network.connect(6, 12, LAND);
//                network.connect(6, 19, LAND);
//                network.connect(7, 13, SEA);
//                network.connect(7, 14, SEA);
//                network.connect(8, 15, SEA);
//                network.connect(8, 15, LAND);
//                network.connect(8, 16, LAND);
//                network.connect(8, 9, LAND);
//                network.connect(8, 9, SEA);
//                network.connect(9, 10, LAND);
//                network.connect(10, 11, LAND);
//                network.connect(10, 16, SEA);
//                network.connect(10, 16, LAND);
//                //  ROMA?: network.connect(10, Integer.MAX_VALUE, SEA);
//                network.connect(10, 17, SEA);
//                //  ROMA?: network.connect(11, Integer.MAX_VALUE, LAND);
//                network.connect(11, 12, LAND);
//                //  ROMA?: network.connect(12, Integer.MAX_VALUE, LAND);
//                network.connect(12, 19, SEA);
//                network.connect(13, 19, LAND);
//                network.connect(13, 14, SEA);
//                network.connect(13, 14, LAND);
//                network.connect(13, 20, SEA);
//                network.connect(13, 21, LAND);
//                network.connect(14, 22, LAND);
//                network.connect(15, 16, LAND);
//                network.connect(15, 24, LAND);
//                network.connect(15, 24, SEA);
//                network.connect(16, 24, LAND);
//                network.connect(16, 24, SEA);
//                network.connect(16, 17, SEA);
//                network.connect(17, 24, LAND);
//                network.connect(17, 24, SEA);
//                network.connect(17, 25, LAND);
//                network.connect(17, 25, SEA);
//                //  ROMA?: network.connect(17, Integer.MAX_VALUE, SEA);
//                network.connect(17, 18, SEA);
//                //  ROMA?: network.connect(18, Integer.MAX_VALUE, LAND);
//                //  ROMA?: network.connect(18, Integer.MAX_VALUE, SEA);
//                network.connect(18, 25, SEA);
//                network.connect(18, 26, SEA);
//                network.connect(18, 20, SEA);
//                network.connect(18, 19, SEA);
//                network.connect(19, 20, LAND);
//                network.connect(20, 26, SEA);
//                network.connect(20, 27, SEA);
//                network.connect(20, 21, SEA);
//                network.connect(21, 27, SEA);
//                network.connect(21, 22, LAND);
//                network.connect(21, 22, SEA);
//                network.connect(22, 23, SEA);
//                network.connect(22, 23, LAND);
//                network.connect(23, 27, LAND);
//                network.connect(23, 27, SEA);
//                network.connect(23, 29, LAND);
//                network.connect(25, 26, LAND);
//                network.connect(25, 26, SEA);
//                network.connect(26, 27, LAND);
//                network.connect(26, 27, SEA);
//                network.connect(26, 28, LAND);
//                network.connect(27, 28, LAND);
//                network.connect(28, 29, LAND);
//                Network.fwrite(String.format("src%sgame%smap%ssaves%snetwork.nw", SEP, SEP, SEP, SEP), network);
                this.network = Network.fread(String.format("src%sgame%smap%ssaves%simperium.nw", SEP, SEP, SEP, SEP), String.format("src%sgame%smap%ssaves%simperium_cities.cn", SEP, SEP, SEP, SEP));
                this.cities = network.cities();
                this.paths = this.network.paths();
            }
            case 1 -> {
                //  TODO: high resolution image of Italia map so that the names of the cities can be read
            }
        }
    }

    /**
     * Method to randomly assign goods to each city
     */
    public void assignGoods() {
        //  TODO: information on number and types of the Good tokens
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
