package src.ai.model.data;

//  imports
import java.util.List;
import java.util.ArrayList;

/**
 * A basic class to represent the data of a CSV file.
 *
 * @author devinlinux
 */
public class CSVRow {

    /**
     * The values of the data.
     */
    private List<Double> values;

    public CSVRow() {
        this.values = new ArrayList<>();
    }

    /**
     * Method to add a value to the data.
     *
     * @param value the value to add
     */
    public void addValue(double value) {
        this.values.add(value);
    }

    /**
     * Method to get a value from the data.
     *
     * @param index the index of the value to get.
     * @return the value at the given index.
     */
    public double getValue(int index) {
        return this.values.get(index);
    }

    /**
     * Method to get the size of the data.
     *
     * @return the size of the data.
     */
    public int size() {
        return this.values.size();
    }
}
