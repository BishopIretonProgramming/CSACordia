package src.game;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static src.game.Colonist.ColonistType;

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
    private Player player;                            //  the player that this StoreHouse is associated with

    //  no-args constructor to make a new store house
    public StoreHouse(Player player) {
        this.player = player;
        this.elements = new ArrayList<>(MAX_CAPACITY);
        init();
    }
    
    //  method to initialize the store house by adding the starting elements
    private void init() {
        elements.add(Good.WINE);
        elements.add(Good.BRICK);
        elements.add(Good.TOOL);
        elements.add(Good.CLOTH);
        elements.add(Good.FOOD);
        elements.add(Good.FOOD);
        elements.add(new Colonist(this.player, ColonistType.SEA));
        elements.add(new Colonist(this.player, ColonistType.SEA));
        elements.add(new Colonist(this.player, ColonistType.LAND));
        elements.add(new Colonist(this.player, ColonistType.LAND));
        //the colonists dont take enums in the constructor anymore, instead we should do this:
        //elements.add(new Colonist(*player*, whichever pathnode represents rome, whether it is a land colonist or not)); x4
    }

    //  method to add an element to the StoreHouse
    public void add(Object element) {
        // TODO: Colonist class
        if (element instanceof Good || element instanceof Colonist) {
            if (elements.size() < MAX_CAPACITY) {
                elements.add(element);
            } else {
                throw new IllegalStateException("StoreHouse is full");
            }
        } else {
            throw new IllegalArgumentException("Element must be of type Good or Colonist");
        }
    }

    //  method to check if an element is contained a certain number of times
    public boolean contains(Object element, int count) {
        return Collections.frequency(elements, element) == count;
    }

    //  method to get element at certain index
    public Object get(int index) {
        return elements.get(index);
    }

    //  getter to get the number of elements
    public int size() {
        return elements.size();
    }

    //  getter to get the capacity of the storehouse
    public int capacity() {
        return MAX_CAPACITY;
    }
    
    // method to remove an element
    // returns true if an element was removed, returns false if no matching element was found
    // accepts an object as a parameter just in case, but if the object is not a good it will return false
    public boolean removeGood(Object o) {
        if (o instanceof Good) {
            for (Object e : elements) {
                if (e == o) { // both .equals and == work with enums, however == is null safe so this stays
                    elements.remove(e);
                    return true;
                }
            }
        }
        return false;
    }
    
    // this is purely for convenienience since I strongly dislike enums -jonah
    // even though a string is an object, passing a string as an argument would work
    // since the method with the most specific matching parameter is called
    public boolean removeGood(String s) {
        if (s.equals("wine")) return removeGood(Good.WINE);
        if (s.equals("brick")) return removeGood(Good.BRICK);
        if (s.equals("tool")) return removeGood(Good.TOOL);
        if (s.equals("cloth")) return removeGood(Good.CLOTH);
        if (s.equals("food")) return removeGood(Good.FOOD);
        System.out.println("you misspelled a good, go fix it");
        return false;
    }
/* 
    // written by Nora Hixson
    public toString(){
        for(Object thing: elements){
            if(thing instanceof Good){
                
            }else if(thing instanceof Colonist){

            }

        }
    }
*/
}
