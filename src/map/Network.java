package src.map;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;

import static src.map.PathNode.PathType;

/**
 * A class to represent a Network of nodes
 * can be serialized for game saving
 * @author Michael Bobrowski (devinlinux)
 */
public class Network implements java.io.Serializable {
    
    /**
     * Version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * A list of all of the cities in the Network
     */
    private Map<String, CityNode> cities;
    private Map<String, PathNode> paths;

    /**
     * Constructor to make a new Network
     */
    public Network() {
        this.cities = new HashMap<>();
        this.paths = new HashMap<>();
    }

    /**
     * Method to add a PathNode to the Network
     * @param path the PathNode to add to the Network
     */
    public void addPath(PathNode path) {
        paths.put(path.name(), path);
    }

    /**
     * Method to add several PathNodes at a time
     * @param paths the PathNodes to add to the Network
     */
    public void addPaths(PathNode... paths) {
        for (PathNode path : paths) {
            this.paths.put(path.name(), path);
        }
    }

    /**
     * Method to add a CityNode to the Network
     * @param city the CityNode to add to the Network
     */
    public void addCity(CityNode city) {
        cities.put(city.name(), city);
    }

    /**
     * Method to add multiple CityNodes at a time
     * @param cities the CityNodes to add to the Network
     */
    public void addCities(CityNode... cities) {
        for (CityNode city : cities) {
            this.cities.put(city.name(), city);
        }
    }

    /**
     * Method to compute the Network
     * connects all of the paths and cities to form the network
     */
    public void compute() {
        paths.values().forEach(path -> {
            path.connections().stream()
                .map(CityNode::name)
                .forEach(city -> cities.get(city).addConnection(path));
        });
    }

    /**
     * Method to visualize the Network
     */
    public void visualize() {
        cities.values().forEach(city -> {
            System.out.printf("%s connections:%n", city.name());
            city.connections().forEach(path -> {
                List<CityNode> connections = path.connections();
                System.out.printf("\t%s - %s (%s)%n",
                    connections.get(0).name(), connections.get(1).name(), path.name());
            });
        });
    }
}
