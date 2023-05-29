package src.ai.data.creation;

//  imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.io.File.separatorChar;

import src.util.Logger;

/**
 * A class to make a csv file to store the dataset for the game.
 *
 * @author devinlinux
 */
public class CSVCreator {
    
    public static void main(String[] args) {
        String filename = String.format("src%cai%cdata%ccreation%cgame_data.csv", separatorChar, separatorChar, separatorChar, separatorChar);

        makeGameCSV(filename);
        appendRow(filename, 0, 0, 1, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 1, 2, 0, 0, 12);
    }

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
}
