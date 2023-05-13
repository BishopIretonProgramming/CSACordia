package src.game.map;

//  imports
import src.game.Good;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A record to represent a PathNode in a Network, each PathNode is associated with exactly
 * two CityNodes
 * Can be serialized for game saving
 * @param name the name of this PathNode
 * @param node1 the first CityNode that this PathNode is connected to
 * @param node2 the second CityNode that this PathNode is connected to
 * @param type the type of path
 * @author devinlinux
 */
public record PathNode(String name, CityNode node1, CityNode node2, PathType type) implements java.io.Serializable {

    /**
     * An enum to represent the two types of PathNodes - land or sea
     */
    public enum PathType {
        LAND,
        SEA;

        /**
         * Method to match a String to a PathType
         * @param type the String to match
         * @return the type that the String matches or null of the String does not match a type
         */
        public static PathType fromString(String type) {
            return switch (type.toUpperCase()) {
                case "LAND" -> LAND;
                case "SEA" -> SEA;
                default -> null;
            };
        }
    }

    /**
     * Version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * Getter method to return a List of the CityNodes this PathNode is connected to
     * @return a list of the CityNodes this PathNode is connected to
     */
    public List<CityNode> nodes() {
        return new ArrayList<>(Arrays.asList(node1, node2));
    }

    /**
     * Library (static) method to load Edges from a file
     * # means a comment
     * Should be written in the form
     * name::cityname1::cityid1::cityletter1::citygood1::cityname2::cityid2::cityletter2::citygood2::type
     * file form (no comments)
     * @param path the path to the file with the edges
     * @return a list of PathNodes loaded from path
     */
    public static List<PathNode> fread(String path) {
        List<PathNode> paths = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            paths = reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.startsWith("#"))
                    .map(line -> line.split("::"))
                    .filter(tokens -> tokens.length == 10)
                    .map(tokens -> new PathNode(tokens[0], new CityNode(tokens[1], Integer.parseInt(tokens[2]), tokens[3].charAt(0), Good.fromString(tokens[4])), new CityNode(tokens[5], Integer.parseInt(tokens[6]), tokens[7].charAt(0), Good.fromString(tokens[8])), PathType.fromString(tokens[9])))
                    .toList();
        } catch (IOException e) {
            System.err.printf("Error reading PathNodes from file: %s%n", e.getMessage());
        }
        return paths;
    }

    /**
     * toString method to return a String representation of a PathNode
     * @return a String representation of this PathNode
     */
    @Override
    public String toString() {
        return String.format("PathNode %s of type %s is connected to %s and %s", name, type, node1, node2);
    }
}
