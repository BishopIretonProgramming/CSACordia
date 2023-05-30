package src.ai.model.data.columndata;

/**
 * An interface to be a common super type of any class that defines column data for a specific type.
 *
 * @author devinlinux
 */
public sealed interface ColumnData permits ByteColumnData, ShortColumnData, IntColumnData, LongColumnData, BoolColumnData, CharColumnData, FloatColumnData, DoubleColumnData, StringColumnData {

    /**
     * A method to check if a value is a valid value to store in this column
     *
     * @param  value the value to check
     * @return whether the value is a valid value to store in this column.
     */
    boolean isValid(Object value);

    /**
     * A method to print out the column data, basically just requiring that each
     * implementing record has a toString.
     *
     * @return the {@code String} representation of the column data.
     */
    @Override
    String toString();
}