package src.ai.model.data;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import src.ai.model.data.columndata.ColumnData;
import src.ai.model.data.columndata.ByteColumnData;
import src.ai.model.data.columndata.ShortColumnData;
import src.ai.model.data.columndata.IntColumnData;
import src.ai.model.data.columndata.LongColumnData;
import src.ai.model.data.columndata.BoolColumnData;
import src.ai.model.data.columndata.CharColumnData;
import src.ai.model.data.columndata.FloatColumnData;
import src.ai.model.data.columndata.DoubleColumnData;
import src.ai.model.data.columndata.StringColumnData;

/**
 * A class to represent the configuration of a CSV file
 * that is being read in as a dataset to train a machine 
 * learning algorithm
 *
 * @author devinlinux
 */
public class CSVConfig {
    
    /**
     * A builder class to build a CSV configuration
     */
    public static class Builder {

        /**
         * The list of columns and their data.
         */
        protected List<ColumnData> columnData = new ArrayList<>();

        /**
         * Method to add a column with a specified datatype to 
         * the CSV file configuration.
         *
         * @param name the name of the column
         * @param dtype the datatype of the column
         */
        public Builder addColumn(String name, Class<?> dtype) {
            return switch (dtype.getSimpleName()) {
                case "byte" -> addByteColumn(name, Byte.MIN_VALUE, Byte.MAX_VALUE);
                case "short" -> addShortColumn(name, Short.MIN_VALUE, Short.MAX_VALUE);
                case "int" -> addIntColumn(name, Integer.MIN_VALUE, Integer.MAX_VALUE);
                case "long" -> addLongColumn(name, Long.MIN_VALUE, Long.MAX_VALUE);
                case "boolean" -> addBoolColumn(name);
                case "char" -> addCharColumn(name, Integer.MIN_VALUE, Integer.MAX_VALUE);
                case "float" -> addFloatColumn(name, Float.MIN_VALUE, Float.MAX_VALUE);
                case "double" -> addDoubleColumn(name, Double.MIN_VALUE, Double.MAX_VALUE);
                case "String" -> addStringColumn(name, Integer.MIN_VALUE, Integer.MAX_VALUE);
                default -> throw new IllegalArgumentException("Unknown or unsupported datatype: " + dtype.getSimpleName());
            };
        }

        /**
         * Method to add a column of type {@code byte} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of the column.
         * @param upperBound the upper bound of the column.
         * @return the updated {@code Builder}.
         */
        public Builder addByteColumn(String name, byte lowerBound, byte upperBound) {
           return addColumnData(new ByteColumnData(name, lowerBound, upperBound)); 
        }

        /**
         * Method to add a column of type {@code short} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of this column.
         * @param upperBound the upperBound of this column.
         * @return the updated {@code Builder}.
         */
        public Builder addShortColumn(String name, short lowerBound, short upperBound) {
            return addColumnData(new ShortColumnData(name, lowerBound, upperBound));
        }

        /**
         * Method to add a column of type {@code int} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of this column.
         * @param upperBound the upper bound of this column.
         * @return the updated {@code Builder}.
         */
        public Builder addIntColumn(String name, int lowerBound, int upperBound) {
            return addColumnData(new IntColumnData(name, lowerBound, upperBound));
        }

        /**
         * Method to add a column of type {@code long} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of this column.
         * @param upperBound the upper bound of this column.
         * @return the updated {@code Builder}.
         */
        public Builder addLongColumn(String name, long lowerBound, long upperBound) {
            return addColumnData(new LongColumnData(name, lowerBound, upperBound));
        }

        /**
         * Method to add a column of type {@code boolean} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @return the updated {@code Builder}.
         */
        public Builder addBoolColumn(String name) {
            return addColumnData(new BoolColumnData(name));
        }

        /**
         * Method to add a column of type {@code char} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of this column.
         * @param upperBound the upper bound of this column.
         * @return the updated {@code Builder}.
         */
        public Builder addCharColumn(String name, int lowerBound, int upperBound) {
            return addColumnData(new CharColumnData(name, lowerBound, upperBound));
        }

        /**
         * Method to add a column of type {@code float} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of this column.
         * @param upperBound the upper bound of this column.
         * @return the updated {@code Builder}.
         */
        public Builder addFloatColumn(String name, float lowerBound, float upperBound) {
            return addColumnData(new FloatColumnData(name, lowerBound, upperBound));
        }

        /**
         * Method to add a column of type {@code double} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param lowerBound the lower bound of this column.
         * @param upperBound the upper bound of this column.
         * @return the updated {@code Builder}.
         */
        public Builder addDoubleColumn(String name, double lowerBound, double upperBound) {
            return addColumnData(new DoubleColumnData(name, lowerBound, upperBound));
        }

        /**
         * Method to add a column of type {@code String} to the CSV file configuration.
         *
         * @param name the name of the column.
         * @param shortestLength the shortest length of a {@code String} in this column.
         * @param longestLength the longest length of a {@code String} in this column.
         * @return the updated {@code Builder}.
         */
        public Builder addStringColumn(String name, int shortestLength, int longestLength) {
            return addColumnData(new StringColumnData(name, shortestLength, longestLength));
        }

        /**
         * Method to add column data to the column data list and return the updated {@code Builder}.
         *
         * @param data the column data to add.
         * @return the updated {@code Builder}.
         */
        private Builder addColumnData(ColumnData data) {
            this.columnData.add(data);
            return this;
        }
    }
}
