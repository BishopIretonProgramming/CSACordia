package src.ai.environment.game.cards.godtypes;

//  imports
import java.util.List;
import java.util.ArrayList;

import src.ai.environment.ConcordiaGame;
import src.ai.environment.game.player.Player;
import src.ai.environment.game.Good;

/**
 * A basic class to represent the Roman god Jupiter.
 *
 * @author devinlinux
 */
public class Jupiter extends RomanGodType {

    /**
     * Method to calculate the number of points a player has earned from
     * personality cards with the Jupiter god type.
     *
     * @param player the player to calculate the score for
     * @param game the game that the player is in
     * @return the number of personality cards to be awarded to the player
     */
    @Override
    public int calculateScore(Player player, ConcordiaGame game) {
        return Math.min((int) player.getCitiesWithHouses().stream()
                .filter(city -> city.getGood() != Good.BRICK)
                .count(), 15);
    }
}
