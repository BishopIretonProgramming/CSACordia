package src.game.map.experimental;

//  imports
import java.util.List;

/** A class to represent a city in the experimental network
 * @author devinlinux
 */
public class Node {

    /**
     * The name of the node
     */
    private String name;

    /**
     * The id of the node
     */
    private int id;

    /**
     * Constructor to make a new node
     * @param name the name of the node
     * @param id the id of the node
     */
    public Node(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Getter method to get the name of this Node
     * @return the name of the city
     */
    public String name() {
        return this.name;
    }

    /**
     * Getter method to return the id of this Node
     * @return the id of the node
     */
    public int id() {
        return this.id;
    }
}
