package src.ai.environment.game.player;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import src.ai.environment.game.Storeable;
import src.ai.environment.game.Good;
import src.ai.environment.game.Colonist;

/**
 * A basic class to represent the store house of a {@code Player}
 *
 * @author devinlinux
 */
public class StoreHouse {

    /**
     * The maximum capacity of a store house.
     */
    private static final int MAX_CAPACITY = 12;

    /**
     * The player who is associated with this store house.
     */
    protected Player player;

    /**
     * The elements present in this store house.
     */
    protected List<Storeable> elements;

    /**
     * Constructor to make a new store house for a player.
     *
     * @param player the {@code Player} who owns this store house.
     */
    public StoreHouse(Player player) {
        this.player = player;
        this.elements = new ArrayList<>(StoreHouse.MAX_CAPACITY);
        init();
    }

    /**
     * Method to initialize the store house by adding the starting elements.
     */
    private void init() {
        this.elements.add(Good.WINE);
        this.elements.add(Good.BRICK);
        this.elements.add(Good.TOOL);
        this.elements.add(Good.CLOTH);
        this.elements.add(Good.FOOD);
        this.elements.add(Good.FOOD);
        this.elements.add(new Colonist(this.player, true));
        this.elements.add(new Colonist(this.player, true));
        this.elements.add(new Colonist(this.player, false));
        this.elements.add(new Colonist(this.player, false));
    }
}
