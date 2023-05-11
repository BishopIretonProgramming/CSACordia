package src.game.map;

//  imports
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
                      
            }
            case 1 -> {
                //  TODO: high resolution image of Italia map so that the names of the cities can be read
            }
        }
    }
}
