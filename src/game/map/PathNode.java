package src.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;

/**
 * A record to represent a PathNode
 * can be serialized for game saving
 * @param name the name of the path
 * @param type the type of path
 * @param connections the CityNodes that the path is connected to
 * @author Michael Bobrowski (devinlinux)
 */
public record PathNode(String name, PathType type, List<CityNode> connections) implements java.io.Serializable {

    /**
     * Secondary constructor to make a PathNode without initially specifying the connections
     */
    public PathNode(String name, PathType type) {
        this(name, type, new ArrayList<>(2));
    }

    /**
     * The version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * An enum to represent the different types of PathNodes
     * a PathNode can be a land path or a sea path
     */
    public enum PathType {
        /**
         * Represents a land path
         */
        LAND,
        /**
         * Represents a sea path
         */
        SEA;
    }

    /**
     * A method to add a connection to this PathNode
     * @param node the CityNode to connect to this PathNode
     */
    public void addConnection(CityNode node) {
        this.connections.add(node);
    }

    /**
     * Method to add several connections at a time
     * @param nodes the CityNodes to connect to this PathNode
     */
    public void addConnections(CityNode... nodes) {
        for (CityNode node : nodes) {
            addConnection(node);
        }
    }

    /**
     * Method to determine whether this PathNode is connected to a CityNode
     * @param city the CityNode to check
     */
    public boolean isConnectedTo(CityNode city) {
        return this.connections != null &&
                (this.connections.get(0).equals(city) ||
                this.connections.get(1).equals(city));
    }

    /**
     * equals method to check if two PathNodes are the same
     * @param other the other PathNode to check against
     * @return whether this is equal to other
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof PathNode node) {
            return this.name.equals(node.name) && this.type == node.type && this.connections.equals(node.connections);
        }
        return false;
    }
}
