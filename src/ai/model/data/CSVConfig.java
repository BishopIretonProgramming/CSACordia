package src.ai.model.data;

//  imports
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import src.util.Logger;

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
       
        protected List<String> columnNames = new ArrayList<>();

        /**
         * Method to add a column with a specified datatype to 
         * the CSV file configuration.
         *
         * @param name the name of the column
         * @param dtype the datatype of the column
         */
        public Builder addColumn(String name, Class<?> dtype) {
            return switch (dtype.getSimpleName()) {
                case "byte" -> addByteColumn(name);
                case "short" -> addShortColumn(name);
                case "int" -> addIntColumn(name);
                case "long" -> addLongColumn(name);
                case "boolean" -> addBoolColumn(name);
                case "char" -> addCharColumn(name);
                case "float" -> addFloatColumn(name);
                case "double" -> addFloatColumn(name);
                case "String" -> addStringColumn(name);
                default -> throw new IllegalArgumentException("Unknown or unsupported datatype: " + dtype.getSimpleName());
            };
        }

        /**
         * Method to add a column of type {@code byte} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addByteColumn(String name) {
            
        }

        /**
         * Method to add a column of type {@code short} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addShortColumn(String name) {

        }

        /**
         * Method to add a column of type {@code int} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addIntColumn(String name) {

        }

        /**
         * Method to add a column of type {@code long} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addLongColumn(String name) {

        }

        /**
         * Method to add a column of type {@code booloean} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addBoolColumn(String name) {

        }

        /**
         * Method to add a column of type {@code char} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addCharColumn(String name) {

        }

        /**
         * Method to add a column of type {@code float} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addFloatColumn(String name) {

        }

        /**
         * Method to add a column of type {@code double} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addDoubleColumn(String name) {

        }

        /**
         * Method to add a column of type {@code String} to the CSV file configuration.
         *
         * @param name the name of the column.
         */
        private Builder addStringColumn(String name) {

        }
    }
}
