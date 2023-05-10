package src.game.map.experimental;

//  imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;  //  not really sure what I am going to be using

import static src.game.map.PathNode.PathType;

/**
 * Experimental implementation of Network
 * Going to try to keep this implementation using just numbers
 * Expect A LOT of symbolic constants for cities if this works
 * @author devinlinux
 */
public class Network {

    /**
     * The number of nodes (cities) in this Network on land
     */
    private int numNodes;

    /**
     * The adjacency list of this Network on land
     */
    private List<List<Integer>> adjListLand;

    /**
     * The adjacency list of this Network on sea
     */
    private List<List<Integer>> adjListSea;

    /**
     * Constructor to make a new Network
     * @param numNodes the number of nodes (cities) in this Network
     */
    public Network(int numNodes) {
        this.numNodes = numNodes;
        this.adjListLand = new ArrayList<>();
        this.adjListSea = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            adjListLand.add(new ArrayList<>());
            adjListSea.add(new ArrayList<>());
        }
    }

    /**
     * Method to add an edge (path) to the Network
     * @param u the start node (city) of the new edge (path)
     * @param v the end node (city) of the new edge (path)
     * @param type the type of edge (path) to make
     */
    public void connect(int u, int v, PathType type) {
        switch (type) {
            case LAND -> {
                this.adjListLand.get(u).add(v);
                this.adjListLand.get(v).add(u);
            }
            case SEA -> {
                this.adjListSea.get(u).add(v);
                this.adjListSea.get(v).add(u);
            }
        }
    }

    /**
     * Method to add an edge (path) to the Network using the Node class
     * @param u the start Node of the new Edge (path)
     * @param v the end Node of the new Edge (path)
     * @param type the type of edge (path) to make
     */
    public void connect(Node u, Node v, PathType type) {
        connect(u.id(), v.id(), type);
    }

    /**
     * Method to find the number of edges between a start and end edge (represented by start and end nodes)
     * The basic plan here is to turn it into a normal pathfinding algorithm by using the min of start1
     * and start2 and the min of end1 and end2 in a breadth-first search algorithm
     * The method will return the number of paths between the start and end path including the end path
     * but not the start path and -1 if no path is found. In order to do this using nodes I am going to
     * take the precise number of nodes that it takes to get from start to end.
     * @param start1 the first node associated with the first edge
     * @param start2 the second node associated with the first edge
     * @param end1 the first node associated with the end edge
     * @param end2 the second node associated with the end edge
     * @return the number of edges between the start and end edge
     */
    public int computeDistanceBetween(int start1, int start2, PathType type1, int end1, int end2, PathType type2) {

        if (type1 != type2) {
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
            switch (type1) {
                case LAND -> {
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
                }
                case SEA -> {
                    for (int neighbor : adjListSea.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            dist[neighbor] = dist[curr] + 1;
                            queue.add(neighbor);NUM_NODES
                        }
                        if (neighbor == end) {
                            return dist[neighbor];
                        }
                    }
                }
            }
        }

        return  -1;
    }

    /**
     * Method to find the number of edges between a start and end edge (represented by start and end nodes)
     * The basic plan here is to turn it into a normal pathfinding algorithm by using the min of start1
     * and start2 and the min of end1 and end2 in a breadth-first search algorithm
     * The method will return the number of paths between the start and end path including the end path
     * but not the start path and -1 if no path is found. In order to do this using nodes I am going to
     * take the precise number of nodes that it takes to get from start to end.
     * This implementation using the Edge classes will just convert it to a number problem
     * @param start the start Edge
     * @param end the end Edge
     * @return the number of edges between the start and end edge
     */
    public int distanceBetweenEdges(Edge start, Edge end) {
        return distanceBetweenEdges(start.node1().id(), start.node2().id(), end.node1().id(), end.node2().id());
    }

    /**
     * Library (static) method to load a Network from a file
     * The first non comment line must be the number of nodes in the Network
     * From there you can connect nodes using u <-> v
     * @param path the path to the file containing the Network
     * @return the Network created by the file
     */
    public static Network loadNetworkFromFile(String path) {
        Network network = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith("#")) {
                    int commentIndex = line.indexOf("#");
                    if (commentIndex != -1) {
                        line = line.substring(0, commentIndex);
                    }
                    if (i == 0) {
                        network = new Network(Integer.parseInt(line.trim()));
                    }
                    if (i > 0 && line.contains("<->")) {
                        String[] tokens = line.split("<->");
                        int u = Integer.parseInt(tokens[0].trim());
                        int v= Integer.parseInt(tokens[1].trim());
                        network.connect(u, v);
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.err.printf("Error loading Network from file: %s", e.getMessage());
        }

        return network;
    }

    /**
     * toString to return a String representation of a Network
     * @return the String representation of this Network
     */
    @Override
    public String toString() {
        return String.format("%d", numNodes);
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

        List<Edge> edges = Edge.loadEdgesFromFile("src/game/map/experimental/files/test.edge");

        for (Edge edge : edges) {
            System.out.println(edge);
        }

        List<Node> nodes = Node.loadNodesFromFile("src/game/map/experimental/files/test.node");

        for (Node node : nodes) {
            System.out.println(node);
        }

        System.out.println("---");

        Node zero = new Node("ZERO", 0);
        Node one = new Node("ONE", 1);
        Node two = new Node("TWO", 2);
        Node three = new Node("THREE", 3);
        Node four = new Node("FOUR", 4);
        Node five = new Node("FIVE", 5);
        Network nn = new Network(6);
        nn.connect(zero, one);
        nn.connect(zero, three);
        nn.connect(zero, five);
        nn.connect(one, two);
        nn.connect(four, three);
        nn.connect(zero, two);
        nn.connect(five, two);

        Edge testOneOne = new Edge("test 1-1", zero, two);
        Edge testOneTwo = new Edge("test 1-2", three, four);

        Edge testTwoOne= new Edge("test 2-1", two, five);
        Edge testTwoTwo = new Edge("test 2-2", zero, one);

        Edge testThreeOne = new Edge("test 3-1", three, four);
        Edge testThreeTwo = new Edge("test 3-2", two, five);

        System.out.println(nn.distanceBetweenEdges(testOneOne, testOneTwo));  //  2
        System.out.println(nn.distanceBetweenEdges(testTwoOne, testTwoTwo));  //  2
        System.out.println(nn.distanceBetweenEdges(testThreeOne, testThreeTwo));  //  3

        System.out.println("---");

        Network readIn = Network.loadNetworkFromFile("src/game/map/experimental/files/test.network");
        System.out.println(readIn.distanceBetweenEdges(0, 2, 3, 4));  //  2
        System.out.println(readIn.distanceBetweenEdges(2, 5, 0, 1));  //  2
        System.out.println(readIn.distanceBetweenEdges(3, 4, 2, 5));  //  3

        System.out.println("\n\n\n\n\n\n\n\n\n" + network.adjList);
    }

}
