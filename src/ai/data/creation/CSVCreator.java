package src.ai.data.creation;

//  imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static java.io.File.separatorChar;

import src.util.Logger;

/**
 * A class to make a csv file to store the dataset for the game.
 *
 * @author devinlinux
 */
public class CSVCreator {
    
    public static void main(String[] args) {
        String player1Filename = String.format("src%cai%cdata%cnFAKE_player_1.csv", separatorChar, separatorChar, separatorChar);
        String player2Filename = String.format("src%cai%cdata%cnFAKE_player_2.csv", separatorChar, separatorChar, separatorChar);
        String player3Filename = String.format("src%cai%cdata%cnFAKE_player_3.csv", separatorChar, separatorChar, separatorChar);
        String player4Filename = String.format("src%cai%cdata%cnFAKE_player_4.csv", separatorChar, separatorChar, separatorChar);
    
        List<String> playerPaths = new ArrayList<>(Arrays.asList(player1Filename, player2Filename, player3Filename, player4Filename));

        for (String path : playerPaths) {
            makeGameCSV(path);
            appendRow(path, 0, 0, 1, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 1, 2, 0, 0, 12);
            appendData(path, genRandData(10000000));
        }
    }

    /**
     * Method to make the game data csv file.
     *
     * @param filename the path to the csv file to make.
     */
    public static void makeGameCSV(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("num_weaver,");
            writer.write("num_vintner,");
            writer.write("num_tribune,");
            writer.write("num_smith,");
            writer.write("num_senator,");
            writer.write("num_prefect,");
            writer.write("num_mercator,");
            writer.write("num_mason,");
            writer.write("num_farmer,");
            writer.write("num_diplomat,");
            writer.write("num_consul,");
            writer.write("num_colonist,");
            writer.write("num_architect,");
            writer.write("land_colonist_1_path,");
            writer.write("land_colonist_2_path,");
            writer.write("land_colonist_3_path,");
            writer.write("sea_colonist_1_path,");
            writer.write("sea_colonist_2_path,");
            writer.write("sea_colonist_3_path,");
            writer.write("num_coins,");
            writer.write("num_bricks,");
            writer.write("num_tools,");
            writer.write("num_food,");
            writer.write("num_wine,");
            writer.write("num_cloth,");
            writer.write("card_to_play");
            writer.newLine();

            Logger.info("CSVCreator", "Successfully created csv file " + filename);
        } catch (IOException e) {
            Logger.error("CSVCreator", String.format("Error creating csv file %s: %s", filename, e.getMessage()));
        }
    }

    /**
     * Method to append a row of data to the game data csv file.
     *
     * @param filename the csv file to append the row to.
     * @param rowData the array of data to add to the file, must have a 
     *                length of 26 elements. 
     */
    public static void appendRow(String filename, int... rowData) {
        assert rowData.length == 26;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (int i = 0; i < rowData.length; i++) {
                if (i == 25) {
                    writer.write("" + rowData[i]);
                    break;
                }
                writer.write(rowData[i] + ", ");
            }
            writer.newLine();
            Logger.info("CSVCreator", "Successfully wrote row to csv file " + filename);
        } catch (IOException e) {
            Logger.error("CSVCreator", String.format("Error writing row to csv file %s: %s", filename, e.getMessage()));
        }
    }   

    /**
     * Method to append data to the csv file. 
     *
     * @param filename the path to the csv file.
     * @param data the data that is to be appended to the csv file.
     */
    public static void appendData(String filename, int[][] data) {
        assert data[0].length == 26;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (j == 25) {
                        writer.write("" + data[i][j]);
                        continue;
                    }
                    writer.write(data[i][j] + ", ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            Logger.error("CSVCreator", String.format("Error writing data to csv file %s: %s", filename, e.getMessage()));
        }
    }

    /**
     * Method to generate random data for the game data csv in line with the general
     * rules of what should be contained inside the file.
     *
     * @param rows the number of rows of data to generate. 
     * @return the random data that was generated.
     */
    public static int[][] genRandData(int rows) {
        int[][] generatedRowData = new int[rows][26];
        Random random = new Random();

        for (int i = 0; i < generatedRowData.length; i++) {
            boolean allZero = true;
            for (int j = 0; j < generatedRowData[i].length; j++) {
                switch (j) {
                    case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> {
                        generatedRowData[i][j] = random.nextInt(4);
                        if (generatedRowData[i][j] > 0) {
                            allZero = false;
                        }
                    }
                    case 13, 14, 15 -> generatedRowData[i][j] = random.nextInt(78);
                    case 16, 17, 18 -> generatedRowData[i][j] = random.nextInt(31);
                    case 19 -> generatedRowData[i][j] = random.nextInt(26);
                    case 20, 21, 22, 23, 24 -> generatedRowData[i][j] = random.nextInt(8);
                    case 25 -> {
                        int randomIndex = -1;
                        if (allZero) {
                            generatedRowData[i][2] = 1;
                        }
                        do {
                            randomIndex = random.nextInt(13);
                        } while (generatedRowData[i][randomIndex] == 0);
                        generatedRowData[i][j] = randomIndex;
                    }
                    default -> generatedRowData[i][j] = -1;
                }
            }
        }

        return generatedRowData;
    }
}
