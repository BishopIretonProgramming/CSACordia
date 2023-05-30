package src.ai.environment.game.cards.godtypes;

//  imports
import java.util.List;

import src.ai.environment.ConcordiaGame;
import src.ai.environment.game.player.Player;
import src.ai.environment.game.cards.PersonalityCard;
import src.ai.environment.game.Good;
import src.ai.environment.game.map.City;

/**
 * A basic class to represent the Roman god Minerva.
 *
 * @author devinlinux
 */
public class Minerva extends RomanGodType {

    /**
     * The {@code Good} associated with the personality card.
     */
    private Good good;

    /**
     * The good value associated with the personality card. 
     */
    private int goodValue;

    /**
     * Constructor to make a new personality card that is associated
     * with the roman god Minerva.
     *
     * @param good      the good associated with this personality card.
     * @param goodValue the good value associated with this personality card.
     */
    public Minerva(Good good, int goodValue) {
        this.good = good;
        this.goodValue = goodValue;
    }

    /**
     * Method to calculate the score for a player given the game that
     * the player is playing. For each city of the related city type
     * the player receives a certain number of victory points as depicted
     * on the specialist's card. Example: the player owns a farmer who
     * rewards him with 3 victory points for each house inside a food city.
     * With 4 such houses the player receives 12 victory points.
     *
     * @param player the player to calculate the score for
     * @param game   the game that the player is playing
     *
     * @return the score for the player
     */
    @Override
    public int calculateScore(Player player, ConcordiaGame game) {
        return player.getCitiesWithHouses().stream()
                .flatMapToInt(city -> player.getCards().stream()
                        .filter(card -> card instanceof Minerva && ((Minerva) card).good == city.getGood())
                        .mapToInt(card -> ((Minerva) card).goodValue))
                .sum();
    }
}
