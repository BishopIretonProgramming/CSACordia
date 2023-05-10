package game.map;

//  imports
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
     * A list of all the cities in the Network
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
     * connects all the paths and cities to form the network
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

    /**
     * Method to compute whether two PathNodes are connected
     * @param start the start PathNode
     * @param end the end PathNode
     * @return whether two PathNodes are connected
     */
    public boolean connected(PathNode start, PathNode end) {
        return start.connections().stream()
                .flatMap(city -> city.connections().stream())
                .anyMatch(path -> path.equals(end));
    }
    
    
   /* SEMI-PSEUDO CODE TO SUGGEST ALGORITHM FOR FINDING DISTANCE BETWEEN TWO PATHNODES:
   //This method assumes "paths" is an array and that "multiplyMatrix()" has been defined
   
   //returns the shortest distance between two pathnodes;
   //if no path is found under the specified maxDist, returns -1
   //@param x the index of one of the PathNodes in the paths "array"
   //@param y the index of teh second PathNode in the paths "array"
   public int getShortestDistance(int x, int y, int maxDist) {
      int[][] adjacency = new int[paths.length][paths.length]; //Adjacency Matrix representing the network of paths
      //set up adjacency matrix
      for(int r = 0; r < paths.length; r ++) {
         for(int c = 0; c < paths.length; c ++) {
            if(paths[r].isConnectedTo(paths[c])) {
               adjacency[r][c] = 1;
            }
         }
      }
      //search adjacency matrix at each distance until maxDist for a path
      int[][] temp = adjacency;
      for(int i = 1; i <= maxDist; i++) {
         if(temp[x][y] > 0) {
            //here we may also want to check if the path(s) already have a colonist on them
            return i; //the length of the path between the PathNodes
         }
         temp = multiplyMatrices(temp, adjacency); //adjacency ^ i (adjacency raised to the power of i)
      }
      return -1; //if no path was found
   }
   
   public int[][] multiplyMatrix(int[][] a, int[][] b) {
      int[][] temp = new int[a.length][a[0].length];
      for(int r = 0; r < temp.length; r ++) {
         for(int c = 0; c < temp[0].length; c++) {
            count = 0;
            for(int x = 0, x < a[0].length; x ++) {
               for(int y = 0; y < b.length; y++) {
                  count += a[r][x] * b[y][c];
               }
            }
            temp[r][c] = count;
         }
      }
      return temp;
   }
   */

    /**
     * Method to return the shortest distance between two pathnodes including end but not start
     * @param start the start node
     * @param end the end node
     * @return the number of nodes between start and end
     */
    public int shortestDistanceBetween(PathNode start, PathNode end) {
        return -1;
    }
}