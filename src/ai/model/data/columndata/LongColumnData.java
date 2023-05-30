package src.ai.model.data.columndata;

/**
 * A record to represent the data associated with a column of type long, all bounds are inclusive.
 *
 * @param name       the name of this column.
 * @param lowerBound the lowest possible value a long can have in this column.
 * @param upperBound the highest possible value a long can have in this column.
 *
 * @author devinlinux
 */
public record LongColumnData(String name, int lowerBound, int upperBound) implements ColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    @Override
    public boolean isValid(Object value) {
        return value instanceof Long val && val >= this.lowerBound && val <= this.upperBound;
    }

    /**
     * A method to print out the column data, basically just requiring that each
     * implementing record has a toString.
     *
     * @return the {@code String} representation of the column data.
     */
    @Override
    public String toString() {
        return String.format("Name: %s%nLower Bound: %d%nUpper Bound: %d", this.name, this.lowerBound, this.upperBound);
    }
}
