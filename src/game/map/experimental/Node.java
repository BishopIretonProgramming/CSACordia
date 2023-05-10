package game.map.experimental;

//  imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
     * The id of the node, for a network id should start at 0 and increase
     * similar to the indices of an array
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

    /**
     * Library (static) method to read in Nodes from a file, the format should be as follows:
     * # indicates comment, everything left will be ignored
     * name::id # makes a new Node with name: name and id: id
     * @param path the path to the text file
     * @return the list of nodes found in the text file
     */
    public static List<Node> loadNodesFromFile(String path) {
        List<Node> nodes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith("#")) {
                    int commentIndex = line.indexOf("#");
                    if (commentIndex != -1) {
                        line = line.substring(0, commentIndex).trim();
                    }

                    String[] tokens = line.split("::");
                    String name = tokens[0];
                    int id = Integer.parseInt(tokens[1]);
                    nodes.add(new Node(name, id));
                }
            }
        } catch (IOException e) {
            System.err.printf("Error reading Nodes from file: %s", e.getMessage());
        }

        return nodes;
    }

    /**
     * toString to make a String representation of a Node
     * @return the String representation of this Node
     */
    public String toString() {
        return String.format("%s: %d", name, id);
    }
}
