package src.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

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
     * The list of CityNodes (cities) associated with this Network
     */
    private List<CityNode> cities;

    /**
     * The list of PathNodes (paths) associated with this Network
     */
    private List<PathNode> paths;

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

        if (this.cities != null) {
            if (this.paths == null) {
                this.paths = new ArrayList<>();
            }
            this.paths.add(new PathNode(
                    cities.get(u).name() + " to " + cities.get(v).name(),
                    cities.get(u),
                    cities.get(v),
                    type));
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
     * Library (static) method to load a Network from a file with CityNodes (cities)
     * The first non comment line must be the number of nodes in the Network
     * From there you can connect nodes using u <-> v <-> t
     * @param networkPath the path to the file containing the Network
     * @param citiesPath the path to the file containing the CityNodes (cities) of the Network
     * @return the Network created by the file
     */
    public static Network fread(String networkPath, String citiesPath) {
        Network network = null;
        List<CityNode> cities = CityNode.fread(citiesPath);
        try (BufferedReader reader = new BufferedReader(new FileReader(networkPath))) {
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
                        network.setCities(cities);
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
            System.err.printf("Error loading Network from file: %s%n", e.getMessage());
        }
        return network;
    }

    /**
     * Library (static) method to write a Network to a file
     * @param path the path to the file to write to
     * @param network the Network to write to the file
     */
    public static void fwrite(String path, Network network) {
        try {
            File file = new File(path);

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.err.println("Could not make file");
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(Integer.toString(network.NUM_NODES));
                writer.newLine();

                for (int u = 0; u < network.NUM_NODES; u++) {
                    for (int v : network.adjListLand.get(u)) {
                        String line = String.format("%d <-> %d <-> %s", u, v, PathType.LAND.name());
                        writer.write(line);
                        writer.newLine();
                    }

                    for (int v : network.adjListSea.get(u)) {
                        String line = String.format("%d <-> %d <-> %s", u, v, PathType.SEA.name());
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.printf("Error writing Network to file: %s%n", e.getMessage());
        }
    }

    /**
     * Method to create a graphical visualization of this network
     * mostly for testing purposes
     */
    public void visualize() {
        JFrame frame = new JFrame("Network Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setFont(new Font("Arial", Font.PLAIN, 12));

                // Calculate the size of each node based on the panel size and number of nodes
                int nodeSize = Math.min(getWidth() / 10, getHeight() / (NUM_NODES / 10));

                for (int i = 0; i < NUM_NODES; i++) {
                    int x = (i % 10) * nodeSize + nodeSize / 2;
                    int y = (i / 10) * nodeSize + nodeSize / 2;

                    g.setColor(Color.WHITE);
                    g.drawOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);

                    // Draw the node ID
                    String nodeId = Integer.toString(i);
                    int nodeIdWidth = g.getFontMetrics().stringWidth(nodeId);
                    g.drawString(nodeId, x - nodeIdWidth / 2, y);
                }

                for (int u = 0; u < NUM_NODES; u++) {
                    for (int v : adjListLand.get(u)) {
                        int x1 = (u % 10) * nodeSize + nodeSize / 2;
                        int y1 = (u / 10) * nodeSize + nodeSize / 2;
                        int x2 = (v % 10) * nodeSize + nodeSize / 2;
                        int y2 = (v / 10) * nodeSize + nodeSize / 2;

                        g.setColor(Color.GREEN);
                        g.drawLine(x1, y1, x2, y2);
                    }

                    for (int v : adjListSea.get(u)) {
                        int x1 = (u % 10) * nodeSize + nodeSize / 2;
                        int y1 = (u / 10) * nodeSize + nodeSize / 2;
                        int x2 = (v % 10) * nodeSize + nodeSize / 2;
                        int y2 = (v / 10) * nodeSize + nodeSize / 2;

                        g.setColor(Color.BLUE);
                        g.drawLine(x1, y1, x2, y2);
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(800, 600)); // Increase the preferred size

        panel.setBackground(Color.DARK_GRAY);

        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Setter to set the CityNodes (cities) associated with this Network
     * @param cities the CityNodes (cities) to be associated with this Network
     */
    public void setCities(List<CityNode> cities) {
        this.cities = cities;
    }

    /**
     * Setter to set the PathNodes (paths) associated with this Network
     * @param paths the PathNodes (paths) to be associated with this Network
     */
    public void setPaths(List<PathNode> paths) {
        this.paths = paths;
    }

    /**
     * Getter to return a list of the CityNodes (cities) associated with this Network
     * @return the CityNodes (cities) associated with this Network
     */
    public List<CityNode> cities() {
        return this.cities;
    }

    /**
     * Getter to return a list of the PathNodes (paths) associated with this Network
     * @return the PathNodes (paths) associated with this Network
     */
    public List<PathNode> paths() {
        return this.paths;
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
