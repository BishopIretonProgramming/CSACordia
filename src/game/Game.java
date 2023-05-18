package src.game;

//  imports
import java.util.List;
import java.util.ArrayList;

import src.game.map.Map;

/**
 * A class to represent a Game and all of its components
 * @author devinlinux
 */
public class Game {

    /**
     * The Map of the game
     */
    private Map map;

    /**
     * The Players of the game
     */
    private List<Player> players;

    /**
     * The name of the game
     */
    private String name;
}
