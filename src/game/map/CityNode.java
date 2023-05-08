package src.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;

/**
 * A record to represent a City in a network of Nodes
 * can be serialized to facilitate game saving
 * @param name the name of the city
 * @param connections the PathNodes that this city is connected to
 * @author devinlinux
 */
public record CityNode(String name, List<PathNode> connections) implements java.io.Serializable {

    /**
     * The version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * Secondary constructor to construct a CityNode without initially specifying connections
     * @param name the name of the city
     */
    public CityNode(String name) {
        this(name, new ArrayList<>());
    }

    /**
     * Method to add a connection to this CityNode
     * @param node the PathNode to connect to this CityNode
     */
    public void addConnection(PathNode node) {
        this.connections.add(node);
    }

    /**
     * Method to add several connections at a time
     * @param nodes the PathNodes to connect to this CityNode
     */
    public void addConnections(PathNode... nodes) {
        for (PathNode node : nodes) {
            addConnection(node);
        }
    }

    /**
     * equals method to return if this CityNode is equal to another object
     * @param other the Object to compare this to
     * @return whether this and other are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof CityNode node) {
            return this.name.equals(node.name) && this.connections.equals(node.connections);
        }
        return false;
    }
}
