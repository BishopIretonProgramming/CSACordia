package src.ai.environment.game.cards.godtypes;

//  imports
import java.util.List;
import java.util.ArrayList;

import src.ai.environment.game.player.Player;
import src.ai.environment.game.Good;
import src.ai.environment.game.map.City;

/**
 * A basic class to represent the Roman god Jupiter.
 *
 * @author devinlinux
 */
public class Jupiter {

    /**
     * Method to calculate the number of points a player has earned from
     * personality cards with the Jupiter god type.
     *
     * @param player the player to calculate the score for
     * @return the number of personality cards to be awarded to the player
     */
    public int calculateScore(Player player) {
        return Math.min(player.getCitiesWithHouses().stream()
                .filter(city -> city.getGood()) != Good.BRICK)
                .count(), 15);
    }
}
