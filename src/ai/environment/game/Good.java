package src.ai.environment.game;

/**
 * A basic enum to represent a Good in the game of Concordia.
 *
 * @author devinlinux
 */
public enum Good implements Storeable {

    /**
     * The brick good.
     */
    BRICK(3),
    /**
     * The food good.
     */
    FOOD(4),
    /**
     * The tool good.
     */
    TOOL(5),
    /**
     * The wine good.
     */
    WINE(6),
    /**
     * The cloth good.
     */
    CLOTH(7);

    /**
     * The value of the Good in sestertii
     */
    private int value;

    /**
     * Constructor to make a Good with a value.
     *
     * @param value the value in sestertii
     */
    Good(int value) {
        this.value = value;
    }
}
