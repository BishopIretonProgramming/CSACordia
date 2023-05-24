package src.ai.environment.game.cards.godtypes;

//  imports
import src.ai.environment.game.player.Player;
import src.ai.environment.ConcordiaGame;

/**
 * A basic class to represent a Roman god type.
 *
 * @author devinlinux
 */
public abstract class RomanGodType {

    /**
     * Method to calculate the score for a player given the game that
     * the player is playing
     * @param player the player to calculate the score for
     * @param game   the game that the player is playing
     * @return       the score for the player
     */
    public abstract int calculateScore(Player player, ConcordiaGame game);
}
