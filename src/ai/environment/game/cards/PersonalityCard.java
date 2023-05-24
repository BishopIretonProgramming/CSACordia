package src.ai.environment.game.cards;

import src.ai.environment.game.cards.godtypes.RomanGod;

/**
 * A basic class to represent a personality card.
 *
 * @author devinlinux
 */
public abstract class PersonalityCard {

    /**
     * The roman god associated with this personality card.
     */
    private RomanGod romanGod;

    /**
     * Constructor to make a personality card
     *
     * @param romanGod the roman god associated with this personality card
     */
    public PersonalityCard(RomanGod romanGod) {
        this.romanGod = romanGod;
    }

    /**
     * Getter to get the roman god associated with this personality card
     *
     * @return the roman god associated with this personality card
     */
    public RomanGod getRomanGod() {
        return romanGod;
    }
}
