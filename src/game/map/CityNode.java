package src.game.map;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A record to represent a CityNode in a Network, can be serialized for game state saving
 * @param name the name of this CityNode (the name of the city)
 * @param id the id of this CityNode in a Network (should start at 0 and increase like the
 *           indices of an array)
 * @author devinlinux
 */
public record CityNode(String name, int id) implements java.io.Serializable {

    /**
     * Version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * Library (static) method to read in Nodes from a file, the format should be as follows:
     * # indicates comment, everything left will be ignored
     * name::id # makes a new CityNode with name: name and id: id
     * @param path the path to the text file
     * @return the list of CityNodes found in the text file
     */
    public static List<CityNode> loadNodesFromFile(String path) {
        List<CityNode> cities = new ArrayList<>();
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
                    cities.add(new CityNode(name, id));
                }
            }
        } catch (IOException e) {
            System.err.printf("Error reading Nodes from file: %s", e.getMessage());
        }

        return cities;
    }

    /**
     * toString to return a String representation of a CityNode
     * @return the String representation of this CityNode
     */
    @Override
    public String toString() {
        return String.format("CityNode %s: %d", name, id);
    }
}
