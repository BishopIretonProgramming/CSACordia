package src.ai.environment.game.cards;

//  imports
import src.ai.environment.ConcordiaGame;
import src.ai.environment.game.cards.godtypes.Vesta;
import src.ai.environment.game.player.Player;

/**
 * A basic class to represent the senator personality card.
 *
 * @author devinlinux
 */
public class Senator extends Vesta implements PersonalityCard {

    /**
     * A method to do the action of the personality card.
     *
     * @param player the player who played the personality card.
     * @param game   the game that the personality card is affecting.
     */
    @Override
    public void play(Player player, ConcordiaGame game) {

    }
}
