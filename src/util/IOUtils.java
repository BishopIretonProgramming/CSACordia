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

    /**
     * The directory that contains the main images for the graphics. This directory must be present
     * in order to display most of the images in the game, including the game board image, the store
     * house images, and some of the game card images.
     */
    private static final String MAIN_GRAPHICS_IMAGES_DIRECTORY = String.format("src%cgui%cimages", separatorChar, separatorChar);

    /**
     * The directory that contains the images of the starting cards for the graphics. This directory
     * must be present in order to display the starting personality cards. If this directory is not
     * present, none of the starting personality cards will be able to be rendered.
     */
    private static final String STARTING_CARDS_IMAGES_DIRECTORY = String.format("src%cgui%cimages%cstartingcards", separatorChar, separatorChar, separatorChar);

    /**
     * The directory that contains the images of the game pieces. This directory must be present in order
     * to display the game pieces on the game board as well as in the inventory of the Player and possibly
     * in the store house of the player.
     */
    private static final String GAME_PIECES_IMAGES_DIRECTORY = String.format("src%cgui%cimages%cpieces", separatorChar, separatorChar, separatorChar);

    /**
     * The directory that contains the images of the personality cards that can be bought. This directory must
     * be present in order to be able to render the personality cards that can be bought from the game board.
     */
    private static final String BOUGHT_PERSONALITY_CARDS_IMAGES_DIRECTORY = String.format("src%cgui%cimages%cboughtcards", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the image of the game board. This file is used in several frames and panels to
     * display the game board as a functional component and as a background or feature image.
     */
    private static final String CONCORDIA_BOARD_IMAGE_FILE = String.format("src%cgui%cimages%cConcordia board.jpg", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the concordia board image file from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String CONCORDIA_BOARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/Concordia%20board.jpg";

    /**
     * The file that contains the image of the black store house. This file is used to display the image of
     * the black store house for the player that is using it.
     */
    private static final String BLACK_STORE_HOUSE_IMAGE_FILE = String.format("src%cgui%cimages%cblackStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the black store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String BLACK_STORE_HOUSE_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/blackStorehouse.png";

    /**
     * The file that contains the image of the blue store house. This file is used to display the image of
     * the blue store house for the player that is using it.
     */
    private static final String BLUE_STORE_HOUSE_IMAGE_FILE = String.format("src%cgui%cimages%cblueStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the blue store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String BLUE_STORE_HOUSE_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/blueStorehouse.png";

    /**
     * The file that contains an image of the back of the concordia card. This file is used to display the
     * concordia card when it is awarded to the player who ends the game.
     */
    private static final String CONCORDIA_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cconcordia.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the concordia card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String CONCORDIA_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/concordia.png";

    /**
     * The file that contains an image of teh concordia card. This file is used to display the concordia
     * card when it is awarded to the player who ends the game.
     */
    private static final String CONCORDIA_CARD_BACK_IMAGE_FILE = String.format("src%cgui%cimages%cconcordiaBack.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the concordia card back image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String CONCORDIA_CARD_BACK_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/concordiaBack.png";

    /**
     * The file that contains an image of the green store house. This file is used to display the image of
     * the green store house for the player that is using it.
     */
    private static final String GREEN_STORE_HOUSE_IMAGE_FILE = String.format("src%cgui%cimages%cgreenStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the green store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String GREEN_STORE_HOUSE_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/greenStorehouse.png";

    /**
     * The file that contains an image of the praefectus magnus card. This file is used to display the
     * praefectus magnus card when a player has it in their hand.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cpraefectusMagnus.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the praefectus magnus card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/praefectusMagnus.png";

    /**
     * The file that contains an image of the back of the praefectus magnus card. This file is used to
     * display the back of the praefectus magnus card when a player has it in their hand.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_BACK_IMAGE_FILE = String.format("src%cgui%cimages%cpraefectusMagnusBack.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of the praefectus magnus card from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_BACK_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/praefectusMagnusBack.png";

    /**
     * The file that contains an image of the red store house. This file is used to display the image of
     * the red store house for the player that is using it.
     */
    private static final String RED_STORE_HOUSE_IMAGE_FILE = String.format("src%cgui%cimages%credStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the red store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String RED_STORE_HOUSE_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/redStorehouse.png";

    /**
     * The file that contains an image of the first reference card. This file is used to display the
     * information found on the first reference card for players to read.
     */
    private static final String REFERENCE_CARD_A_IMAGE_FILE = String.format("src%cgui%cimages%creferenceCardA.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first reference card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String REFERENCE_CARD_A_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/referenceCardA.png";

    /**
     * The file that contains an image of the second reference card. This file is used to display the
     * information found on the second reference card for players to read.
     */
    private static final String REFERENCE_CARD_B_IMAGE_FILE = String.format("src%cgui%cimages%creferenceCardB.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second reference card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String REFERENCE_CARD_B_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/referenceCardB.png";

    /**
     * The file that contains an image of the yellow store house. This file is used to display the image of
     * the yellow store house for the player that is using it.
     */
    private static final String YELLOW_STORE_HOUSE_IMAGE_FILE = String.format("src%cgui%cimages%cyellowStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the yellow store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String YELLOW_STORE_HOUSE_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/yellowStorehouse.png";

    /*
     * File and directory creation methods. Note that all methods take the
     * same basic form: performing the checks to see if the directory or
     * file exists, and then creating them if they do not exist.
     */

    /**
     * Method to create a directory if it does not exist. This method will
     * check to see if the directory exists and if it does not, it will
     * create the directory. This method will be used in all the methods
     * that check to ensure the existence of the necessary game file directories
     * to reduce the amount of repeated code and to improve readability in the
     * entirety of the IOUtils class.
     *
     * @param directory The directory to check for and create if it does not exist
     * @param directoryName The name of the directory to check for and create if it does not exist
     */
    private static void createDirectoryIfNotFound(Path directory, String directoryName) {
        if (Files.exists(directory)) {
            Logger.info("IOUtils", "The " + directoryName + " directory already exists");
        } else {
            try {
                Files.createDirectories(directory);
                Logger.info("IOUtils", "Successfully created the " + directoryName + " directory");
            } catch (IOException e) {
                Logger.error("IOUtils", "Could not create the " + directoryName + " directory: " + e.getMessage());
            }
        }
    }

    /**
     * Checks for the directory that will be used to save the unfinished games
     * to and creates it if it does not exist.
     */
    public static void checkForUnfinishedGamesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(UNFINISHED_GAME_SAVE_FILE_DIRECTORY), "unfinished games");
    }

    /**
     * Checks for the directory that will be used to save the pre-built map files
     * and create the directory if it does not exist
     */
    public static void checkForPreBuiltMapFilesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(PRE_BUILT_MAP_FILES_DIRECTORY), "pre-built map files");
    }

    /**
     * Checks for the login information directory that is used to store usernames
     * and passwords and creates the directory if it does not exist
     */
    public static void checkForLoginInformationDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(LOGIN_INFORMATION_DIRECTORY), "login information");
    }

    /**
     * Checks for the player performance information directory that is used to store
     * information regarding the performance of players in games and creates it if
     * it does not exist
     */
    public static void checkForPlayerPerformanceInformationDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(PLAYER_PERFORMANCE_INFORMATION_DIRECTORY), "player performance information");
    }

    /**
     * Checks for the sprites directory that is used to store the png files with the 
     * sprites and creates it if it does not exist. 
     */
    public static void checkForSpritesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(SPRITES_DIRECTORY), "sprites");
    }

    /**
     * Checks for the main graphics images directory that is used to store the image files
     * associated with most of the images that are used in the game.
     */
    public static void checkForMainGraphicsImagesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(MAIN_GRAPHICS_IMAGES_DIRECTORY), "main graphics images");
    }

    /**
     * Checks for the starting cards images directory that is used to store the image files
     * of the starting personality cards that are used to render the personality cards that
     * the players start with.
     */
    public static void checkForStartingCardsImagesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(STARTING_CARDS_IMAGES_DIRECTORY), "starting cards images");
    }

    /**
     * Checks for the pieces image directory that is used to store the image that are used
     * to render the game pieces on the game board, in the inventory of the players, and in
     * the store house of the players.
     */
    public static void checkForGamePiecesImagesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(GAME_PIECES_IMAGES_DIRECTORY), "game pieces images");
    }

    /**
     * Checks for the bought personality cards images directory that is used to store the image
     * files of the personality cards that can be bought from the game board.
     */
    public static void checkForBoughtPersonalityCardsImagesDirectoryAndCreateIfNotFound() {
        createDirectoryIfNotFound(Paths.get(BOUGHT_PERSONALITY_CARDS_IMAGES_DIRECTORY), "bought personality cards images");
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
        checkForMainGraphicsImagesDirectoryAndCreateIfNotFound();
        checkForStartingCardsImagesDirectoryAndCreateIfNotFound();
        checkForGamePiecesImagesDirectoryAndCreateIfNotFound();
        checkForBoughtPersonalityCardsImagesDirectoryAndCreateIfNotFound();
    }

    /* just for testing */
    public static void main(String[] args) {
        checkForGameFiles();
    }
}
