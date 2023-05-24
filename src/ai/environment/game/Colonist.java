package src.ai.environment.game;

//  imports
import src.ai.environment.game.player.Player;

/**
 * A basic class to represent a colonist in the game of Concordia.
 *
 * @author devinlinux
 */
public class Colonist implements Storeable {

    /**
     * The player who owns this colonist
     */
    protected Player player;

    /**
     * Whether this colonist is a land colonist.
     */
    protected boolean isLand;

    /**
     * Constructor to make a new colonist.
     *
     * @param player the player who owns this colonist.
     * @param isLand whether this colonist is a land colonist.
     */
    public Colonist(Player player, boolean isLand) {
        this.player = player;
        this.isLand = isLand;
    }
}
