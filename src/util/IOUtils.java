package src.util;

//  imports
import static java.io.File.separatorChar;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A utility class to assist with Input/Output operations regarding files
 * in the game of Concordia
 *
 * @author devinlinux
 * @version 1.0
 */
public class IOUtils {

    /**
     * Suppress the default no-args constructor provided by the compiler,
     * ensuring that no one can make an instance of this class.
     */
    private IOUtils() {}

    /*
     * The fields and constants that will be used throughout this class,
     * most of the fields represent a file or directory that should be
     * present with the game for saving files or reading files associated
     * with the game.
     *
     * Other fields may include links to where the files can be downloaded
     * from in case necessary files for launching the game are not present
     * and must be acquired from the internet.
     */

    /**
     * The directory that should be used for the save files of unfinished
     * games. This directory must be present in order for the saving of
     * the games to ensure that a {@code FileNotFoundException} is not
     * thrown by any of the save methods of the {@code Game} class.
     */
    private static final String UNFINISHED_GAME_SAVE_FILE_DIRECTORY = String.format("resources%csaves%cunfinished_games", separatorChar, separatorChar);

    /**
     * The directory that should be used for the save files of maps, networks,
     * and cities. This directory must be present for the game to be played because
     * it is where the pre-built networks for the imperium and italia maps will
     * be located. If any of the files are not found or this directory does not exist,
     * the files will need to be downloaded the internet in order for proper game functioning.
     */
    private static final String PRE_BUILT_MAP_FILES_DIRECTORY = String.format("resources%csaves%cpre_built_maps", separatorChar, separatorChar);

    /**
     * The directory that should be used for saving information regarding log in
     * information about the players. This directory must be present for logging into
     * the game because it will be where files for usernames and passwords will be stored.
     */
    private static final String LOGIN_INFORMATION_DIRECTORY = String.format("resources%clog_in%cuap", separatorChar, separatorChar);

    /**
     * The directory that should be used for saving information regarding performance
     * information about the players. This directory must be present for storing information
     * about player performance during games.
     */
    private static final String PLAYER_PERFORMANCE_INFORMATION_DIRECTORY = String.format("resources%cplayers%cperformance", separatorChar, separatorChar);

    /**
     * The directory that contains the sprites for the graphics of the game. This directory
     * must be present in order to properly load the sprites into the graphics without causing
     * any errors or problems with displaying the sprites. 
     */
    private static final String SPRITES_DIRECTORY = String.format("assets%csprites", separatorChar);

    /*
     * File and directory creation methods. Note that all methods take the
     * same basic form: performing the checks to see if the directory or
     * file exists, and then creating them if they do not exist.
     */

    /**
     * Checks for the directory that will be used to save the unfinished games
     * to and creates it if it does not exist.
     */
    public static void checkForUnfinishedGamesDirectoryAndCreateIfNotFound() {
        Path path = Paths.get(UNFINISHED_GAME_SAVE_FILE_DIRECTORY);
        try {
            Files.createDirectories(path);
            Logger.info("IOUtils", "Successfully created unfinished game directory");
        } catch (IOException e) {
            Logger.error("IOUtils", "Could not create unfinished game save file directory: " + e.getMessage());
        }
    }

    /**
     * Checks for the directory that will be used to save the pre-built map files
     * and create the directory if it does not exist
     */
    public static void checkForPreBuiltMapFilesDirectoryAndCreateIfNotFound() {
        Path path = Paths.get(PRE_BUILT_MAP_FILES_DIRECTORY);
        try {
            Files.createDirectories(path);
            Logger.info("IOUtils", "Successfully created pre-build map files directory");
        } catch (IOException e) {
            Logger.error("IOUtils", "Could not create pre-built map files directory: " + e.getMessage());
        }
    }

    /**
     * Checks for the login information directory that is used to store usernames
     * and passwords and creates the directory if it does not exist
     */
    public static void checkForLoginInformationDirectoryAndCreateIfNotFound() {
        Path path = Paths.get(LOGIN_INFORMATION_DIRECTORY);
        try {
            Files.createDirectories(path);
            Logger.info("IOUtils", "Successfully created login information directory");
        } catch (IOException e) {
            Logger.error("IOUtils", "Could not create login information directory: " + e.getMessage());
        }
    }

    /**
     * Checks for the player performance information directory that is used to store
     * information regarding the performance of players in games and creates it if
     * it does not exist
     */
    public static void checkForPlayerPerformanceInformationDirectoryAndCreateIfNotFound() {
        Path path = Paths.get(PLAYER_PERFORMANCE_INFORMATION_DIRECTORY);
        try {
            Files.createDirectories(path);
            Logger.info("IOUtils", "Successfully created player performance information directory");
        } catch (IOException e) {
            Logger.error("IOUtils", "Could not create player performance information directory: " + e.getMessage());
        }
    }

    /**
     * Checks for the sprites directory that is used to store the png files with the 
     * sprites and creates it if it does not exist. 
     */
    public static void checkForSpritesDirectoryAndCreateIfNotFound() {
        Path path = Paths.get(SPRITES_DIRECTORY);
        try {
            Files.createDirectories(path);
            Logger.info("IOUtils", "Successfully created sprites directory");
        } catch (IOException e) {
            Logger.error("IOUtils", "Could not create sprites directory: " + e.getMessage());
        }
    }

    /**
     * Method to check for all the necessary files and directories to play the game
     * and either creates them or downloads them depending on the occasion. This is the 
     * main method that should be called on game startup to ensure that the game does
     * not experience any errors in creation and initialization. While the client is able 
     * to call the other methods individually, it is not recommended as this method will
     * be constantly updated to ensure that it creates or downloads all necessary files
     * and directories for the game. 
     */
    public static void checkForGameFiles() {
        checkForUnfinishedGamesDirectoryAndCreateIfNotFound();
        checkForPreBuiltMapFilesDirectoryAndCreateIfNotFound();
        checkForLoginInformationDirectoryAndCreateIfNotFound();
        checkForPlayerPerformanceInformationDirectoryAndCreateIfNotFound();
        checkForSpritesDirectoryAndCreateIfNotFound();
    }
}
