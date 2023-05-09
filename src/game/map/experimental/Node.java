package src.game.map.experimental;

//  imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** A class to represent a city in the experimental network
 * @author devinlinux
 */
public class Node {

    /**
     * The name of the node
     */
    private String name;

    /**
     * The id of the node
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
     * name id # makes a new Node with name: name and id: id
     * @param path the path to the text file
     * @return the list of nodes found in the text file
     */
    public static List<Node> loadNodesFromFile(String path) {
        List<Node> nodes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            nodes = br.lines()
                    .filter(line -> !line.startsWith("#"))
                    .map(line -> {
                        int index = line.indexOf("#");
                        String trimmedLine = index != -1 ? line.substring(0, index) : line;
                        int id = Integer.parseInt(trimmedLine.replaceAll("\\D", ""));
                        String name = trimmedLine.replaceAll("[^a-zA-Z\\s\"]", "").trim();
                        name = name.startsWith("\"") && name.endsWith("\"") ? name.substring(1, name.length() - 1) : name;
                        return new Node(name, id);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error while reading nodes from file: " + e.getMessage());
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
