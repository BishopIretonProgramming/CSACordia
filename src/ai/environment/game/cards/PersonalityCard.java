package src.ai.environment.game.cards;

import src.ai.environment.game.player.Player;
import src.ai.environment.ConcordiaGame;

/**
 * A basic interface to represent a personality card.
 *
 * @author devinlinux
 */
public interface PersonalityCard {

    /**
     * A method to do the action of the personality card.
     *
     * @param player the player who played the personality card.
     * @param game   the game that the personality card is affecting.
     */
    void play(Player player, ConcordiaGame game);
}
