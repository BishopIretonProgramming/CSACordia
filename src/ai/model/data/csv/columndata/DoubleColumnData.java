package src.ai.model.data.csv.columndata;

/**
 * A record to represent the data associated with a column of type double, all bounds are inclusive.
 *
 * @param name       the name of this column.
 * @param lowerBound the lowest value a double can have in this column.
 * @param upperBound the highest value a double can have in this column.
 *
 * @author devinlinux
 */
public record DoubleColumnData(String name, double lowerBound, double upperBound) implements ColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    @Override
    public boolean isValid(Object value) {
        return value instanceof Double val && val >= this.lowerBound && val <= this.upperBound;
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
