package src.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static src.game.map.PathNode.PathType;

/**
 * A class to represent a Network of Nodes
 * can be serialized for game saving
 * @author devinlinux
 */
public class Network implements java.io.Serializable {

    /**
     * The version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * The number of nodes (cities) in this Network
     */
    private final int NUM_NODES;

    /**
     * The adjacency list of this Network for land
     */
    private List<List<Integer>> adjListLand;

    /**
     * The adjacency list of this Network for sea
     */
    private List<List<Integer>> adjListSea;

    /**
     * Constructor to make a new Network
     * @param NUM_NODES the number of nodes (cities) in this Network
     */
    public Network(final int NUM_NODES) {
        this.NUM_NODES = NUM_NODES;
        this.adjListLand = new ArrayList<>();
        this.adjListSea = new ArrayList<>();
        for (int i = 0; i < this.NUM_NODES; i++) {
            adjListLand.add(new ArrayList<>());
            adjListSea.add(new ArrayList<>());
        }
    }

    /**
     * Method to add an edge (path) to the Network
     * @param u the starting node (city) of the new edge (path)
     * @param v the ending node (city) of the new edge (path)
     * @param type the type of edge (path) (land or sea)
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
     * Method to add an edge (path) to the Network using CityNodes
     * @param u the starting CityNode of the new edge (path)
     * @param v the ending CityNode of the new edge (path)
     * @param type the type of edge (path) (land or sea)
     */
    public void connect(CityNode u, CityNode v, PathType type) {
        connect(u.id(), v.id(), type);
    }

    /**
     * Method to find the number of edges between a start and an edge (represented by start and end nodes)
     * Uses a breadth-first search pathfinding algorithm
     * @param start1 the first node associated with the first edge
     * @param start2 the second node associated with the first edge
     * @param type1 the type associated with the first edge
     * @param end1 the first node associated with the second edge
     * @param end2 the second node associated with the second edge
     * @param type2 the type associated with the second edge
     * @end2 the second node associated with the second edge
     * @return the number of edges between a start and end edge including the end edge, but
     * excluding the start edge. Or -1 if no path is found
     */
    public int computeDistanceBetween(int start1, int start2, PathType type1, int end1, int end2, PathType type2) {

        if (type1 != type2) {
            return -1;
        }

        int start = Math.min(start1, start2);
        int end = Math.min(end1, end2);

        boolean[] visited = new boolean[this.NUM_NODES];
        int[] dist = new int[this.NUM_NODES];
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
                            queue.add(neighbor);
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
     * Method to find the number of PathNodes between a start and an end PathNode
     * Uses the above implementation of the method using the ids of the cities associated
     * with the provided PathNodes
     * @param start the start PathNode
     * @param end the end PathNode
     * @return the number of PathNodes between the start PathNode and the end PathNode including the end,
     * but not the start. Or -1 if no path is found
     */
    public int computeDistanceBetween(PathNode start, PathNode end) {
        return computeDistanceBetween(start.node1().id(), start.node2().id(), start.type(), end.node1().id(), end.node2().id(), end.type());
    }

    /**
     * Library (static) method to load a Network from a file
     * The first non comment line must be the number of nodes in the Network
     * From there you can connect nodes using u <-> v <-> t
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
                        PathType type = PathType.fromString(tokens[2].trim());
                        network.connect(u, v, type);
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
        return String.format("Network has: %d nodes", this.NUM_NODES);
    }
}
