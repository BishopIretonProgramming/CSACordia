package src.ai.model.data.columndata;

public record StringColumnData() implements ColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    @Override
    public boolean isValid(Object value) {
        return false;
    }

    /**
     * A method to print out the column data, basically just requiring that each
     * implementing record has a toString.
     *
     * @return the {@code String} representation of the column data.
     */
    @Override
    public String toString() {
        return null;
    }
}
