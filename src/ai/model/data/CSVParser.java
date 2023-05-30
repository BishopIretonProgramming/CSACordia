package src.ai.model.data;

//  imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A basic class to parse a CSV file.
 *
 * @author devinlinux
 */
public class CSVParser {

    /**
     * The {@code BufferedReader} to read the data.
     */
    private BufferedReader reader;

    /**
     * The delimiter for the CSV file.
     */
    private String delimiter;

    /**
     * Constructor to make a new CSVParser
     *
     * @param path      the path to the CSV file to parse.
     * @param delimiter the delimiter of the CSV file to parse.
     *
     * @throws IOException for opening the {@code BufferedReader}.
     */
    public CSVParser(String path, String delimiter) throws IOException {
        reader = new BufferedReader(new FileReader(path));
        this.delimiter = delimiter;
    }

    /**
     * Method to get the next row of data in the CSV file.
     *
     * @return the next row in the CSV file.
     *
     * @throws IOException for reading the line.
     */
    public CSVRow getNextRow() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return null;
        }

        String[] values = line.split(delimiter);
        CSVRow row = new CSVRow();
        for (String value : values) {
            row.addValue(Double.parseDouble(value));
        }

        return row;
    }

    /**
     * Method to close the CSVParser.
     *
     * @throws IOException for closing the {@code BufferedReader}.
     */
    public void close() throws IOException {
        reader.close();
    }
}
