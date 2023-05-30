package src.ai.model.data.columndata;

/**
 * A record to represent the information regarding a column of the {@code String} type, lengths are inclusive.
 *
 * @param name           the name of this column.
 * @param shortestLength the shortest length a {@code String} can be in this column.
 * @param longestLength  the longest length a {@code String} can be in this column.
 *
 * @author devinlinux
 */
public record StringColumnData(String name, int shortestLength, int longestLength) implements ColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    @Override
    public boolean isValid(Object value) {
        return value instanceof String val && val.length() >= this.shortestLength && val.length() <= this.longestLength;
    }

    /**
     * A method to print out the column data, basically just requiring that each
     * implementing record has a toString.
     *
     * @return the {@code String} representation of the column data.
     */
    @Override
    public String toString() {
        return String.format("Name : %s%nShortest Allowed Length: %d%nLongest Allowed Length: %d", this.name, this.shortestLength, this.longestLength);
    }
}
