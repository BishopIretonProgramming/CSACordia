package src.login;

/**
 * A class to represent the data associated with an account-holding CSACordia player.
 *
 * @author devinlinux
 */
public class User {

    /**
     * The username of this {@code User}.
     */
    private String username;

    /**
     * The number of games this {@code User} has played.
     */
    private int numGames;

    /**
     * The number of games this {@code User} has won.
     */
    private int numWins;

    /**
     * Constructor to make a new {@code User} with a username, number of games, and
     * number of wins.
     *
     * @param username the username of this {@code User}.
     * @param numGames the number of games this {@code User} has played.
     * @param numWins  the number of games this {@code User} has won.
     */
    public User(String username, int numGames, int numWins) {
        this.username = username;
        this.numGames = numGames;
        this.numWins = numWins;
    }

    /**
     * Getter to get the username of this {@code User}.
     * 
     * @return the username of this {@code User}.
     */
    public String username() {
        return this.username;
    }

    /**
     * Getter to get the number of games this {@code User} has played.
     *
     * @return the number of games this {@code User} has played.
     */
    public int numGames() {
        return this.numGames;
    }

    /**
     * Getter to get the number of games this {@code User} has won.
     *
     * @return the number of games this {@code User} has won.
     */
    public int numWins() {
        return this.numWins;
    }

    /**
     * Getter to get the number of games this {@code User} has lost.
     *
     * @return the number of games this {@code User} has lost.
     */
    public int numLosses() {
        return this.numGames - this.numWins;
    }

    /**
     * Getter to get the winning percentage of this {@code User}.
     *
     * @return the winning percentage of this {@code User}.
     */
    public double winningPercentage() {
        return (double) this.numWins / (double) this.numGames;
    }
}
