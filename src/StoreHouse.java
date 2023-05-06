package src;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a store house that can store 12 elements
 * can be serialized for game state saving
 * @author Michael Bobrowski (devinlinux)
 */
public class StoreHouse implements java.io.Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 1L;  //  the version ID for serialization
    private static final int MAX_CAPACITY = 12;       //  the maximum capacity of the store house
    private List<Object> elements;                    //  the elements of the store house

    //  no-args constructor to make a new store house
    public StoreHouse() {
        this.elements = new ArrayList<>(MAX_CAPACITY);
        init();
    }
    
    //  method to initialize the store house by adding the starting elements
    private void init() {
        //  TODO: Good enum/class, Colonist class
        // elements.add(Good.WINE);
        // elements.add(Good.BRICK);
        // elements.add(Good.TOOL);
        // elements.add(Good.CLOTH);
        // elements.add(Good.FOOD);
        // elements.add(Good.FOOD);
        // elements.add(new Colonist(ColonistType.SEA));
        // elements.add(new Colonist(ColonistType.SEA));
        // elements.add(new Colonist(ColonistType.LAND));
        // elements.add(new Colonist(ColonistType.LAND));
    }

    //  method to add an element to the StoreHouse
    public void add(Object element) {
        // TODO: Good enum/class, Colonist class
        //if (element instanceof Good || element instanceof Colonist) {
            if (elements.size() < MAX_CAPACITY) {
                elements.add(element);
            } else {
                throw new IllegalStateException("StoreHouse is full");
            }
        //} else {
            //throw new IllegalArgumentException("Element must be of type Good or Colonist");
        //}
    }

    //  method to check if an element is contained a certain number of times
    public boolean contains(Object element, int count) {
        return Collections.frequency(elements, element) == count;
    }

    //  getter to get the number of elements
    public int size() {
        return elements.size();
    }

    //  getter to get the capacity of the storehouse
    public int capacity() {
        return MAX_CAPACITY;
    }
}
