package src.ai.model.data.columndata;

/**
 * A record to store the information regarding a column, all bounds are inclusive.
 * @param name        the name of the column
 * @param lowerBound  the lowest possible value that an integer in this column can have
 * @param upperBound  the highest possible value that an integer in this column can have
 */
public record IntColumnData(String name, int lowerBound, int upperBound) implements ColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    @Override
    public boolean isValid(Object value) {
        return value instanceof Integer val && val >= this.lowerBound && val <= this.upperBound;
    }
}
