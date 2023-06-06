package src.game.player;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static src.game.Colonist.ColonistType;

import src.game.Colonist;
import src.game.Good;
import src.game.Storeable;

/**
 * A class to represent a store house of an {@code Player}, each
 * store house has a maximum capacity of 12 elements and starts
 * with 2 land colonists, 2 sea colonists, 1 wine good, 1 brick
 * good, 1 tool good, 1 cloth good, and 2 food goods.
 *
 * @author devinlinux
 */
public class StoreHouse {

    /**
     * The maximum capacity of a store house.
     */
    private static final int MAXIMUM_CAPACITY = 12;

    /**
     * The {@code Player} that is associated with this store house.
     */
    private Player player;

    /**
     * The elements that are present in this store house.
     */
    private List<Storeable> elements;

    /**
     * The number of elemenst in this store house.
     */
    private int size;

    /**
     * Constructor to make a new store house that is associated with
     * an {@code Player}.
     */
    public StoreHouse(Player player) {
        this.player = player;

        List<Storeable> list = Arrays.asList(new Storeable[MAXIMUM_CAPACITY]);// added by Nora gets rid of java.lang.UnsupportedOperationException
        this.elements = new ArrayList<>(list);
        this.size = 0;
        init();
    }

    /**
     * A method to initialize a store house by adding the starting elements.
     */
    private void init() {
        this.elements.add(Good.WINE);
        this.elements.add(Good.BRICK);
        this.elements.add(Good.TOOL);
        this.elements.add(Good.CLOTH);
        this.elements.add(Good.FOOD);
        this.elements.add(Good.FOOD);
        this.elements.add(new Colonist(this.player, ColonistType.SEA));
        this.elements.add(new Colonist(this.player, ColonistType.SEA));
        this.elements.add(new Colonist(this.player, ColonistType.LAND));
        this.elements.add(new Colonist(this.player, ColonistType.LAND));
    }

    /**
     * A method to add an element to this store house
     *
     * @param element the element to add.
     * @return        the elements in the store house if the store house is full,
     *                otherwise null.
     */
    public List<Storeable> add(Storeable element) {
        if (this.size < MAXIMUM_CAPACITY) {
            this.elements.add(element);
            this.size++;
            return null;
        } 
        return this.elements;
    }

    /**
     * A method to check if this store house contains an 
     * element a certain number of times.
     *
     * @param element the element to check for.
     * @param count   the number of occurances to check for.
     * @return        whether element is in this storehouse count number of times.
     */
    public boolean contains(Storeable element, int count) {
        return Collections.frequency(elements, element) == count;
    }   

    /**
     * A method to check if this store house contains an element.
     *
     * @param element the element to check for.
     * @return        whether element is contained.
     */
    public boolean contains(Storeable element) {
        return this.contains(element, 1);
    }

    /**
     * A method to remove a {@code Good} from this store house.
     *
     * @param good the {@code Good} to remove from this store house.
     * @return     the {@code Good} that was removed, or null if nothing
     *             was removed;
     */
    public Good removeGood(Good good) {
        for (Storeable s : this.elements) {
            if (s == good) {
                this.elements.remove(s);
                this.size--;
                return (Good) s;
            }
        }
        return null;
    }

    /**
     * A method to remove a {@code Colonist} from this store house.
     *
     * @param colonist the {@code Colonist} to remove from this store house.
     * @return         the {@code Colonist} that was removed, or null if nothing
     *                 was removed.
     */
    public Colonist removeColonist(Colonist colonist) {
        for (Storeable s : this.elements) {
            if (s == colonist) {
                this.elements.remove(s);
                this.size--;
                return (Colonist) s;
            }
        }
        return null;
    }

    /**
     * A method to remove a {@code Storeable} from this store house.
     *
     * @param storeable the element to remove from this store house.
     * @return          the {@code Storeable} that was removed, or null
     *                  if nothing was removed.
     */
    public Storeable remove(Storeable storeable) {
        for (Storeable s : this.elements) {
            if (s == storeable) {
                this.elements.remove(s);
                this.size--;
                return s;
            }
        }
        return null;
    }

    /**
     * Method to return the size of this store house.
     *
     * @return the number of elements in this store house.
     */
    public int size() {
        return this.size;
    }

    /**
     * Method to get an element at a specified index.
     *
     * @param index the index of the element to get.
     * @return      the element at index.
     */
    public Storeable get(int index) {
        return this.elements.get(index);
    }

    /**
     * Getter to get list of resources
     * @return list of resources
     * @author Emily --> added for scoring (refer to bottom of Game.java)
     */
    public List<Storeable> getResources() {
        return elements; 
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
