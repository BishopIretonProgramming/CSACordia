package src.ai.environment.game.map;

/**
 * A basic class to represent a path between two cities on the game map.
 *
 * @author devinlinux
 */
public class Path {

    /**
     * The first {@code City} this path connects.
     */
    private City city1;

    /**
     * The second {@code City} this path connects.
     */
    private City city2;

    /**
     * Whether this path is land or not.
     */
    private boolean isLand;

    /**
     * Whether this path is currently occupied by a {@code Colonist}.
     */
    private boolean occupied;

    /**
     * Constructor to make a new Path with two cities and whether it is land or
     * not.
     *
     * @param city1 the first {@code City} this path connects
     * @param city2 the second {@code City} this path connects
     * @param isLand whether this path is land or not
     */
    public Path(City city1, City city2, boolean isLand) {
        this.city1 = city1;
        this.city2 = city2;
        this.isLand = isLand;
    }

    /**
     * Method to set whether this path is occupied or not.
     *
     * @param occupied whether this path is occupied or not
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Method to get the first {@code City} this path connects.
     *
     * @return the first {@code City} this path connects
     */
    public City getCity1() {
        return city1;
    }

    /**
     * Method to get the second {@code City} this path connects.
     *
     * @return the second {@code City} this path connects
     */
    public City getCity2() {
        return city2;
    }

    /**
     * Method to get whether this path is land or not.
     *
     * @return whether this path is land or not
     */
    public boolean isLand() {
        return isLand;
    }
}
