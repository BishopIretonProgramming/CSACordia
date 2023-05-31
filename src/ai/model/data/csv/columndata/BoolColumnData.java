package src.ai.model.data.csv.columndata;

/**
 * A record to represent the data associated with a column of type boolean.
 *
 * @param name the name of this column.
 *
 * @author devinlinux
 */
public record BoolColumnData(String name) implements ColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    @Override
    public boolean isValid(Object value) {
        return value instanceof Boolean;
    }

    /**
     * A method to print out the column data, basically just requiring that each
     * implementing record has a toString.
     *
     * @return the {@code String} representation of the column data.
     */
    @Override
    public String toString() {
        return String.format("Name: %s", this.name);
    }
}
