package game;

public class City {
    private String name;
    private double x, y;

    private City[] landConnections;
    private City[] seaConnections;

    /**
     * Creates an instance of <code>City</code>.
     * 
     * @param name city name
     * @param x    x position
     * @param y    y position
     */
    public City(String name, double x, double y) {
        if (name == null)
            throw new IllegalArgumentException("Cannot have a city with no name");
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the name of this city.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x position of this city.
     * 
     * @return x position
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y position of this city.
     * 
     * @return y position
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a <code>City</code> array containing this city's neighbors.
     * 
     * @param landed landed connections or sea connections
     * @return adjacent cities
     */
    public City[] getConnections(boolean landed) {
        return landed ? landConnections : seaConnections;
    }

    /**
     * Sets the current array of connected cities.
     * 
     * @param landed landed connections or sea connections
     * @param connections cites to be connected
     */
    public void setConnectedCities(boolean landed, City... connections) {
        if (landed)
            landConnections = connections;
        else
            seaConnections = connections;
    }
}