package src.game.map.experimental;

//  imports
import java.util.*;  //  not really sure what I am going to be using

/**
 * Experimental implementation of Network
 * Going to try to keep this implementation using just numbers
 * Expect A LOT of symbolic constants for cities if this works
 * @author devinlinux
 */
public class Network {

    /**
     * The number of nodes (cities) in this Network
     */
    private int numNodes;

    /**
     * the adjacency list of this Network
     */
    private List<List<Integer>> adjList;

    /**
     * Constructor to make a new Network
     * @param numNodes the number of nodes (cities) in this Network
     */
    public Network(int numNodes) {
        this.numNodes = numNodes;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Method to add an edge (path) to the Network
     * @param u the start node (city) of the new edge (path)
     * @param v the end node (city) of the new edge (path)
     */
    public void connect(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    /**
     * Method to find the number of edges between a start and end edge (represented by start and end nodes)
     * The basic plan here is to turn it into a normal pathfinding algorithm by using the min of start1
     * and start2 and the min of end1 and end2 in a breadth-first search algorithm
     * The method will return the number of paths between the start and end path including the end path
     * but not the start path and -1 if no path is found. In order to do this using nodes I am going to
     * take the precise number of nodes that it takes to get from start to end.
     * @param start1 the first node associated with the first edge
     * @param start2 the second node associated with the second edge
     * @param end1 the first node associated with the end edge
     * @param end2 the second node associated with the end edge
     * @return the number of edges between the start and end edge
     */
    public int distanceBetweenEdges(int start1, int start2, int end1, int end2) {

        int start = Math.min(start1, start2);
        int end = Math.min(end1, end2);

        boolean[] visited = new boolean[this.numNodes];
        int[] dist = new int[numNodes];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adjList.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    dist[neighbor] = dist[curr] + 1;
                    queue.add(neighbor);
                }
                if (neighbor == end) {
                    return dist[neighbor] + 1;
                }
            }
        }

        return -1;
    }

    /**
     * Main method for testing
     * @param args the run arguments of the program
     */
    public static void main(String[] args) {

        //  see image
        Network network = new Network(6);
        network.connect(0, 1);
        network.connect(0, 3);
        network.connect(0, 5);
        network.connect(1, 2);
        network.connect(4, 3);
        network.connect(0, 2);
        network.connect(5, 2);

        System.out.println(network.distanceBetweenEdges(0, 2, 3, 4));  //  2
        System.out.println(network.distanceBetweenEdges(2, 5, 0, 1));  //  2
        System.out.println(network.distanceBetweenEdges(3, 4, 2, 5));  //  3
        System.out.println(network.distanceBetweenEdges(2, 0, 3, 4));  //  2
        System.out.println(network.distanceBetweenEdges(4, 3, 5, 2));  //  3
        System.out.println(network.distanceBetweenEdges(2, 5, 1, 0));  //  2
    }

}
