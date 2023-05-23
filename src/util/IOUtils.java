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
     * The directory that contains the images related to the graphics of the game. This directory
     * must be present in order to prevent errors while loading the images and to properly display
     * the images on the various frames and panels of the game.
     */
    private static final String GRAPHICS_IMAGES_DIRECTORY = String.format("src%cgui%cimages", separatorChar, separatorChar);

    /**
     * The file that represents the pre-build network of the imperium map.
     * This file must be present in order to play the imperium map so that an adequate
     * network can be formed to play the game.
     */
    private static final String PRE_BUILT_IMPERIUM_NETWORK_FILE = String.format("resources%csaves%cpre_built_maps%cimperium.nw", separatorChar, separatorChar, separatorChar);

    /**
     * The file that represents the cities in the imperium network.
     * This file must be present in order to play the imperium map so that an adequate
     * map can be made with the correct cities.
     */
    private static final String PRE_BUILT_IMERPIUM_CITIES_FILE = String.format("resources%csaves%cpre_built_maps%cimperium_cities.cnw", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the sprites for the colonists. This file is essential 
     * to the graphics of the game as it is necessary for the displaying of the colonists. 
     */
    private static final String COLONISTS_SPRITES_FILE = String.format("assets%csprites%ccolonists.png", separatorChar, separatorChar);

    /**
     * The file that contains the sprites for the goods. This file is essential to the
     * graphics of the game as it is necessary for the displaying of the goods. 
     */
    private static final String GOODS_SPRITES_FILE = String.format("assets%csprites%cgoods.png", separatorChar, separatorChar);

    /**
     * The file that contains the sprites for the houses. This file is essential to the 
     * graphics of the game as it is necessary for the displaying of the houses on the boards. 
     */
    private static final String HOUSES_SPRITES_FILE = String.format("assets%csprites%chouses.png", separatorChar, separatorChar);

    /**
     * The file that contains the image of the game board. This file is essential to the graphics
     * of the game as it is extensively used in various frames and panels and if not present
     * will be unable to be rendered on in all the various places it needs to be rendered.
     */
    private static final String CONCORDIA_BOARD_IMAGE_FILE = String.format("src%cgui%cimages%cConcordia board.jpg", separatorChar, separatorChar, separatorChar);

    /**
     * The first file that contains an image of the architect personality card. This file is
     * essential to the graphics of the game as it will be used to render the card during
     * gameplay.
     */
    private static final String ARCHITECT_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%carchitect.png", separatorChar, separatorChar, separatorChar);

    /**
     * The second file that contains an image of the architect personality card. This file is
     * essential to the graphics of the game as it will be used to render the card during
     * gameplay.
     */
    private static final String ARCHITECT_PERSONALITY_CARD_IMAGE_FILE_2 = String.format("src%cgui%cimages%carchitect2.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the image of the colonist personality card. This file is
     * essential to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String COLONIST_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%ccolonist.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of the consul personality card. This file is essential to
     * the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String CONSUL_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cconsul.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of the diplomat personality card. This file is essential to
     * the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String DIPLOMAT_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cdiplomat.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the first image of the mercator personality card. This file is
     * essential to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String MERCATOR_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cmercator.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the second image of the mercator personality card. This file is essential
     * to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String MERCATOR_PERSONALITY_CARD_IMAGE_FILE_2 = String.format("src%cgui%cimages%cmercator2.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the image of the prefect personality card. This file is essential to the
     * graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String PREFECT_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cprefect.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of the reference card for the price of goods in the game. This
     * file is essential to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String GOOD_PRICE_REFERENCE_CARD_IMAGE_FILE = String.format("src%cgui%cimages%creference.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of the senator personality card. This file is essential to the
     * graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String SENATOR_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%csenator.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of an empty green store house. This file is essential to the
     * graphics of the game as it will be used to render the store house during gameplay and to
     * display what is in the store house of a player.
     */
    private static final String GREEN_STOREHOUSE_IMAGE_FILE = String.format("src%cgui%cimages%cstorehouse-green.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of the tribune personality card. This file is essential
     * to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String TRIBUNE_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%ctribune.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains an image of the vinter personality card. This file is essential
     * to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String VINTER_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cvinter.png", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the image of the weaver personality card. This file is essential
     * to the graphics of the game as it will be used to render the card during gameplay.
     */
    private static final String WEAVER_PERSONALITY_CARD_IMAGE_FILE = String.format("src%cgui%cimages%cweaver.png", separatorChar, separatorChar, separatorChar);

    /**
     * The link to where the pre-built imperium network can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String PRE_BUILT_IMPERIUM_NETWORK_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/resources/saves/pre_built_maps/imperium.nw";

    /**
     * The link to where the pre-built imperium network cities file can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String PRE_BUILT_IMPERIUM_CITIES_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/resources/saves/pre_built_maps/imperium_cities.cnw";

    /**
     * The link to where the sprites file for the colonists sprites can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String COLONISTS_SPRITES_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/assets/sprites/colonists.png";

    /**
     * The link to where the sprites file for the goods sprites can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String GOODS_SPRITES_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/assets/sprites/goods.png";

    /**
     * The link to where the sprites file for the houses sprites can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String HOUSES_SPRITES_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/assets/sprites/houses.png";

    /**
     * The link to where the image file for the concordia board can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String CONCORDIA_BOARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/Concordia%20board.jpg";

    /**
     * The link to where the first image of the architect personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String ARCHITECT_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/architect.png";

    /**
     * The link to where the second image of the architect personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String ARCHITECT_PERSONALITY_CARD_IMAGE_FILE_2_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/architect2.png";

    /**
     * The link to where the image for the colonist personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String COLONIST_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/consul.png";

    /**
     * The link to where the image for the consul personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String CONSUL_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/consul.png";

    /**
     * The link to where the image for the diplomat personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String DIPLOMAT_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/diplomat.png";

    /**
     * The link to where the first image of the mercator personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String MERCATOR_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/mercator.png";

    /**
     * The link to where the second image of the mercator personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String MERCATOR_PERSONALITY_CARD_IMAGE_FILE_2_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/mercator2.png";

    /**
     * The link to where the image of the prefect personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String PREFECT_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/prefect.png";

    /**
     * The link to where the image of the reference card for the good prices can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String GOOD_PRICE_REFERENCE_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/reference.png";

    /**
     * The link to where the image of the senator personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String SENATOR_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/senator.png";

    /**
     * The link to where the image of the green store house can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String GREEN_STOREHOUSE_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/storehouse-green.png";

    /**
     * The link to where the image of the tribune personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String TRIBUNE_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/tribune.png";

    /**
     * The link to where the image of the vinter personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String VINTER_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/vintner.png";

    /**
     * The link to where the image of the weaver personality card can be downloaded from.
     * This link is to be used if the file is not already present.
     */
    private static final String WEAVER_PERSONALITY_CARD_IMAGE_FILE_DOWNLOAD_LINK = "https://raw.githubusercontent.com/devinlinux/CSACordia-Forkenstein/main/src/gui/images/weaver.png";

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
     * Checks for the graphics images directory that is used to store the images related to
     * the graphics of the game and creates the directory if it does not exist.
     */
    public static void checkForGraphicsImagesDirectoryAndCreateIfNotFound() {
        Path path = Paths.get(GRAPHICS_IMAGES_DIRECTORY);
        try {
            Files.createDirectories(path);
            Logger.info("IOUtils", "Successfully created graphics images directory");
        } catch (IOException e) {
            Logger.error("IOUtils", "Could not create the graphics images directory: " + e.getMessage());
        }
    }

    /**
     * Checks for the pre-built imperium network file which is necessary for the playing
     * of the game when the imperium map is selected and downloads it if it is not found
     */
    public static void checkForPreBuiltImperiumNetworkFileAndDownloadIfNotFound() {
        Path file = Path.of(PRE_BUILT_IMPERIUM_NETWORK_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Pre-Built Imperium network file already exists");
            return;
        }
        try {
            URI uri = new URI(PRE_BUILT_IMPERIUM_NETWORK_FILE_DOWNLOAD_LINK);
            URL url = uri.toURL();
            Files.copy(url.openStream(), file, StandardCopyOption.REPLACE_EXISTING);
            Logger.info("IOUtils", "Successfully downloaded the pre-built imperium network file");
        } catch (URISyntaxException | IOException e) {
            Logger.error("IOUtils", "Error occurred while downloading the pre-built imperium network file: " + e.getMessage());
        }
    }

    /**
     * Checks for the pre-built imperium cities file which is necessary for the playing
     * of the game when the imperium map is selected as it is used to build the map and 
     * downloads it if it is not found
     */
    public static void checkForPreBuiltImperumCitiesFileAndDownloadIfNotFound() {
        Path file = Path.of(PRE_BUILT_IMERPIUM_CITIES_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Pre-Built Imperium cities file already exists");
            return;
        }
        try {
            URI uri = new URI(PRE_BUILT_IMPERIUM_CITIES_FILE_DOWNLOAD_LINK);
            URL url = uri.toURL();
            Files.copy(url.openStream(), file, StandardCopyOption.REPLACE_EXISTING);
            Logger.info("IOUtils", "Successfully downloaded the pre-built imperium cities file");
        } catch (URISyntaxException | IOException e) {
            Logger.error("IOUtils", "Error occurred while downloading the pre-built imperium cities file: " + e.getMessage());
        }
    }

    /**
     * Checks for the colonists sprite file which is necessary for the graphics of the game
     * and displaying the colonists on the game board and downloads it if it is not found.
     */
    public static void checkForColonistsSpriteFileAndDownloadIfNotFound() {
        Path file = Path.of(COLONISTS_SPRITES_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Colonists sprite file already exists");
            return;
        }
        try {
            URI uri = new URI(COLONISTS_SPRITES_FILE_DOWNLOAD_LINK);
            URL url = uri.toURL();
            Files.copy(url.openStream(), file, StandardCopyOption.REPLACE_EXISTING);
            Logger.info("IOUtils", "Successfully downloaded the colonists sprite file");
        } catch (URISyntaxException | IOException e) {
            Logger.error("IOUtils", "Error occurred while downloading the colonists sprite file: " + e.getMessage());
        }
    }

    /**
     * Checks for the goods sprite file which is necessary for the graphics of the game
     * and displaying the goods on the game board and in the store house of a player and
     * downloads it if it is not found.
     */
    public static void checkForGoodsSpriteFileAndDownloadedIfNotFound() {
        Path file = Path.of(GOODS_SPRITES_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Goods sprite file already exists");
            return;
        }
        try {
            URI uri = new URI(GOODS_SPRITES_FILE_DOWNLOAD_LINK);
            URL url = uri.toURL();
            Files.copy(url.openStream(), file, StandardCopyOption.REPLACE_EXISTING);
            Logger.info("IOUtils", "Successfully downloaded the goods sprite file");
        } catch (URISyntaxException | IOException e) {
            Logger.error("IOUtils", "Error occurred while downloading the goods sprite file: " + e.getMessage());
        }
    }

    /**
     * Checks for the houses sprites file which is necessary for the graphics of the game and
     * displaying the goods on the cities of the game board and in the inventory of a player
     * and downloads it if it is not found.
     */
    public static void checkForHousesSpriteFileAndDownloadIfNotFound() {
        Path file = Path.of(HOUSES_SPRITES_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Houses sprites file already exists");
            return;
        }
        try {
            URI uri = new URI(HOUSES_SPRITES_FILE_DOWNLOAD_LINK);
            URL url = uri.toURL();
            Files.copy(url.openStream(), file, StandardCopyOption.REPLACE_EXISTING);
            Logger.info("IOUtils", "Successfully downloaded the houses sprites file");
        } catch (URISyntaxException | IOException e) {
            Logger.error("IOUtils", "Error occurred while downloading the houses sprites file: " + e.getMessage());
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
        checkForGraphicsImagesDirectoryAndCreateIfNotFound();
        checkForPreBuiltImperiumNetworkFileAndDownloadIfNotFound();
        checkForPreBuiltImperumCitiesFileAndDownloadIfNotFound();
        checkForColonistsSpriteFileAndDownloadIfNotFound();
        checkForGoodsSpriteFileAndDownloadedIfNotFound();
        checkForHousesSpriteFileAndDownloadIfNotFound();
    }
}
