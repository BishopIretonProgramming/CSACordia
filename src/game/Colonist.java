package src.game;

//  imports
import src.game.map.PathNode;

/**
 * A class to represent a colonist
 * 
 * @author Jonah Cook
 * @author Caroline Miller
 */
public abstract class Colonist {

	/**
	 * The player that the colonist is associated with
	 */
	private Player player;

	/**
	 * The current path that the colonist is on
	 */
	private PathNode path;

	/**
	 * whether the colonist is a land colonist
	 */
	private boolean isLand;

	/**
	 * Constructor to make a new Colonist
	 * 
	 * @param player the player that the colonist is associated with
	 * @param path   the current path of the player
	 * @param isLand whether the colonist is a land colonist
	 */
	public Colonist(Player player, PathNode path, boolean isLand) {
		this.player = player;
		this.path = path;
		this.isLand = isLand;
	}

	/**
	 * Getter to get the player that this Colonist is associated with
	 */
	public Player player() {
		return this.player;
	}

	/**
	 * Getter to return the PathNode that this colonis is on
	 */
	public PathNode path() {
		return this.path;
	}

	/**
	 * Getter to return whether this colonist is a land colonist
	 */
	public boolean isLand() {
		return this.isLand;
	}

	/**
	 * Getter to return whether this colonist is a sea colonist
	 */
	public boolean isSea() {
		return !this.isLand;
	}

	/**
	 * Method to determine if this colonist can move to a PathNode
	 * @param node the PathNode to move to
	 */
	public abstract boolean canMoveTo(PathNode node);
}