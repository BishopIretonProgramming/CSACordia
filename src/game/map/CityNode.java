package src.game.map;

//  imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import src.game.Good;

/**
 * A class to represent a CityNode in a Network and a city in a Game
 * can be serialized for game saving
 * @author devinlinux
 */

public class CityNode implements java.io.Serializable {

    /**
     * The version ID for serialization
     */
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    /**
     * The name of this CityNode (city)
     */
    private String name;

    /**
     * The id of this CityNode in a Network (should be assigned similar to the indices of an array, that
     * is to say that they start at 0 and increment from there without skipping numbers)
     */
    private int id;

    /**
     * The letter associated with this CityNode (city)
     */
    private char letter;

    /**
     * The Good that this CityNode (city) produces
     */
    private Good good;

    /**
     * Constructor to make a new CityNode
     * @param name the name of this CityNode (city)
     * @param id the id of this CityNode in a Network
     * @param letter the letter associated with this CityNode (city)
     * @param good the Good that this CityNode (city) should produce
     */
    public CityNode(String name, int id, char letter, Good good) {
        this.name = name;
        this.id = id;
        this.letter = letter;
        this.good = good;
    }

    /**
     * Constructor to make a new CityNode
     * @param name the name of this CityNode (city)
     * @param id the id of this CityNode in a Network
     * @param letter the letter associated with this CityNode (city)
     */
    public CityNode(String name, int id, char letter) {
        this.name = name;
        this.id = id;
        this.letter = letter;
    }

    /**
     * Library (static) method to read in a List of CityNodes from a file, the format should be
     * as follows:
     * # indicates a comment, everything right of this will be ignored
     * name::id::good # the good should be a string that represents the Good that it should produce
     * the good should be one of the Goods defined in the Good enum
     * @param path the path to the file where the CityNodes are stored
     * @return a List of the CityNodes found in path
     */
    public static List<CityNode> fread(String path) {
        List<CityNode> cities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            cities = reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.startsWith("#"))
                    .map(line -> line.split("::"))
                    .filter(tokens -> tokens.length == 4)
                    .map(tokens -> new CityNode(tokens[0], Integer.parseInt(tokens[1]), tokens[2].charAt(0), Good.fromString(tokens[3])))
                    .toList();
        } catch (IOException e) {
            System.err.printf("Error reading CityNodes from file: %s%n", e.getMessage());
        }
        return cities;
    }

    /**
     * Setter to set the Good that this CityNode (city) produces
     * @param good the Good that this CityNode (city) should produce
     */
    public void setGood(Good good) {
        this.good = good;
    }

    /**
     * Getter to return the name of this CityNode (city)
     * @return the name of this CityNode
     */
    public String name() {
        return this.name;
    }

    /**
     * Getter to return the id of this CityNode in a Network
     * @return the id of this CityNode in a Network
     */
    public int id() {
        return this.id;
    }

    /**
     * Getter to return the Good that this CityNode (city) produces
     * @return the Good that is produced by this CityNode (city)
     */
    public Good good() {
        return this.good;
    }
}