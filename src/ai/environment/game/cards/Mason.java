package src.ai.environment.game.cards;

//  imports
import src.ai.environment.ConcordiaGame;
import src.ai.environment.game.cards.godtypes.Minerva;
import src.ai.environment.game.player.Player;
import src.ai.environment.game.Good;

/**
 * A basic class to represent the mason personality card.
 *
 * @author devinlinux
 */
public class Mason extends Minerva implements PersonalityCard {

    /**
     * Constructor to make a new Mason personality card.
     */
    public Mason() {
        super(Good.BRICK, 3);
    }

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
