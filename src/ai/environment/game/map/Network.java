package src.ai.environment.game.map;

//  imports
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * A basic class to represent a network of cities and paths.
 *
 * @author devinlinux
 */
public class Network {

    /**
     * The number of nodes in the network.
     */
    private final int numNodes;

    /**
     * The adjacency matrix of the network for land.
     */
    private final List<List<Integer>> adjListLand;

    /**
     * The adjacency matrix of the network for sea.
     */
    private final List<List<Integer>> adjListSea;

    /**
     * The list of paths in the network.
     */
    private List<Path> paths;

    /**
     * The list of cities in the network.
     */
    private List<City> cities;

    /**
     * Constructor to make a new Network.
     *
     * @param numNodes the number of nodes in the network
     */
    public Network(int numNodes) {
        this.numNodes = numNodes;
        this.adjListLand = new ArrayList<>();
        this.adjListSea = new ArrayList<>();
        for (int i = 0; i < this.numNodes; i++) {
            adjListLand.add(new ArrayList<>());
            adjListSea.add(new ArrayList<>());
        }
    }

    /**
     * Method to add a path to the network
     *
     * @param u    the id of the starting city of the path
     * @param v    the id of the ending city of the path
     * @param land whether the path is land or sea
     */
    public void connect(int u, int v, boolean land) {
        if (land) {
            this.adjListLand.get(u).add(v);
            this.adjListLand.get(v).add(u);
        } else {
            this.adjListSea.get(u).add(v);
            this.adjListSea.get(v).add(u);
        }

        if (this.cities != null) {
            if (this.paths == null) {
                this.paths = new ArrayList<>();
            }
            this.paths.add(new Path(paths.size(), cities.get(u), cities.get(v), land));
        }
    }

    /**
     * Method to add a path to the network using the {@code City} class.
     *
     * @param u    the starting city of the path
     * @param v    the ending city of the path
     * @param land whether the path is land or sea
     */
    public void connect(City u, City v, boolean land) {
        this.connect(u.getId(), v.getId(), land);
    }

    /**
     * Method to find the number of paths between a start and an edge node.
     * Uses a breadth first search pathfinding algorithm to calculate the
     * fastest route between the two nodes.
     *
     * @param start1 the first node associated with the first path
     * @param start2 the second node associated with the first path
     * @param land1 whether the first path is a land path
     * @param end1 the second node associated with the second path
     * @param end2 the second node associated with the second path
     * @param land2 whether the second path is a land path
     */
    public int computeDistanceBetween(int start1, int start2, boolean land1, int end1, int end2, boolean land2) {
        if (land1 != land2) {
            return -1;
        }

        int start = Math.min(start1, start2);
        int end = Math.min(end1, end2);

        boolean[] visited = new boolean[this.numNodes];
        int[] dist = new int[this.numNodes];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (land1) {
                for (int neighbor : adjListLand.get(curr)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        dist[neighbor] = dist[curr] + 1;
                        queue.add(neighbor);
                    }
                    if (neighbor == end) {
                        return dist[neighbor];
                    }
                }
            } else {
                for (int neighbor : adjListSea.get(curr)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        dist[neighbor] = dist[curr] + 1;
                        queue.add(neighbor);
                    }
                    if (neighbor == end) {
                        return dist[neighbor];
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Method to compute the distance between a start path and an end path
     * using the {@code Path} class.
     * Uses the implementation of the other {@code computeDistanceBetween} method.
     *
     * @param start the start Path
     * @param end the end Path
     * @return the distance between the two paths
     */
    public int computeDistanceBetween(Path start, Path end) {
        return this.computeDistanceBetween(start.getCity1().id, start.getCity2().id, start.isLand(),
                end.getCity1().id, end.getCity2().id, end.isLand());
    }

    /**
     * Setter to set the cities in the network.
     *
     * @param cities the list of cities in the network.
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Setter to set the paths in the network.
     *
     * @param paths the list of paths in the network.
     */
    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    /**
     * Getter to return the cities in the network.
     *
     * @return the list of cities in the network
     */
    public List<City> getCities() {
        return this.cities;
    }

    /**
     * Getter to return the paths in the network.
     *
     * @return the list of paths in the network
     */
    public List<Path> getPaths() {
        return this.paths;
    }
}
