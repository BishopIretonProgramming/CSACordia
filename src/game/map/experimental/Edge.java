package src.game.map.experimental;

//  imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to represent an edge (path) in the Network
 * @author devinlinux
 */
public class Edge {

    /**
     * The name of this Edge
     */
    private String name;

    /**
     * The first node that this edge is associated with
     */
    private Node node1;

    /**
     * The second node that this edge is associated with
     */
    private Node node2;

    /**
     * Constructor to make an Edge with a name
     * @param name the name of this Edge
     */
    public Edge(String name) {
        this(name, null, null);
    }

    /**
     * Constructor to make a complete Edge
     * @param name the name of this Edge
     * @param node1 the first node to associate with this Edge
     * @param node2 the second node to associate with this Edge
     */
    public Edge(String name, Node node1, Node node2) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
    }

    /**
     * Getter method for name
     * @return the name of this Edge
     */
    public String name() {
        return this.name;
    }

    /**
     * Getter method for the first node
     * @return the first node that this Edge is associated with
     */
    public Node node1() {
        return this.node1;
    }

    /**
     * Getter method for the second node
     * @return the second node that this Edge is associated with
     */
    public Node node2() {
        return this.node2;
    }

    /**
     * Getter method to return the Nodes that this Edge is associated with in
     * a List
     * @return a list of the Nodes this Edge is associated with
     */
    public List<Node> nodes() {
        return new ArrayList<>(Arrays.asList(node1, node2));
    }

    /**
     * Library (static) method to load Edges from a file
     * # means a comment
     * Should be written in the form
     * name [node1] [node2] # where node1 and node2 are in valid node file form (no comments)
     * @param path the path to the file with the edges
     * @return a list of Edges loaded from path
     */
    public static List<Edge> loadEdgesFromFile(String path) {
        return null;
    }

    /**
     * toString to return a String representation of an Edge
     * @return a String representation of this Edge
     */
    public String toString() {
        return String.format("%s is associated with |%s| and |%s|", this.name, this.node1, this.node2);
    }
}
