package src.util;

//  imports
import static java.io.File.separatorChar;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;

import java.io.IOException;

import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.HttpURLConnection;

import java.util.concurrent.*;

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

    /**
     * The maximum number of concurrent downloads represented by an {@code ExecutorService}
     */
     private static final ExecutorService executorService = Executors.newFixedThreadPool(20);

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
    private static final String UNFINISHED_GAME_SAVE_DIR = String.format("resources%csaves%cunfinished_games", separatorChar, separatorChar);

    /**
     * The directory that should be used for the save files of maps, networks,
     * and cities. This directory must be present for the game to be played because
     * it is where the pre-built networks for the imperium and italia maps will
     * be located. If any of the files are not found or this directory does not exist,
     * the files will need to be downloaded the internet in order for proper game functioning.
     */
    private static final String PRE_BUILT_MAP_DIR = String.format("resources%csaves%cpre_built_maps", separatorChar, separatorChar);

    /**
     * The directory that should be used for saving information regarding log in
     * information about the players. This directory must be present for logging into
     * the game because it will be where files for usernames and passwords will be stored.
     */
    private static final String LOGIN_INFORMATION_DIR = String.format("resources%clog_in%cuap", separatorChar, separatorChar);

    /**
     * The directory that should be used for saving information regarding performance
     * information about the players. This directory must be present for storing information
     * about player performance during games.
     */
    private static final String PLAYER_PERFORMANCE_INFORMATION_DIR = String.format("resources%cplayers%cperformance", separatorChar, separatorChar);

    /**
     * The directory that contains the sprites for the graphics of the game. This directory
     * must be present in order to properly load the sprites into the graphics without causing
     * any errors or problems with displaying the sprites. 
     */
    private static final String SPRITES_DIR = String.format("assets%csprites", separatorChar);

    /**
     * The directory that contains the main images for the graphics. This directory must be present
     * in order to display most of the images in the game, including the game board image, the store
     * house images, and some of the game card images.
     */
    private static final String MAIN_GRAPHICS_IMAGES_DIR = String.format("src%cgui%cimages", separatorChar, separatorChar);

    /**
     * The directory that contains the images of the starting cards for the graphics. This directory
     * must be present in order to display the starting personality cards. If this directory is not
     * present, none of the starting personality cards will be able to be rendered.
     */
    private static final String STARTING_CARDS_IMAGES_DIR = String.format("src%cgui%cimages%cstartingcards", separatorChar, separatorChar, separatorChar);

    /**
     * The directory that contains the images of the game pieces. This directory must be present in order
     * to display the game pieces on the game board as well as in the inventory of the Player and possibly
     * in the store house of the player.
     */
    private static final String GAME_PIECES_IMAGES_DIR = String.format("src%cgui%cimages%cpieces", separatorChar, separatorChar, separatorChar);

    /**
     * The directory that contains the images of the personality cards that can be bought. This directory must
     * be present in order to be able to render the personality cards that can be bought from the game board.
     */
    private static final String BOUGHT_PCARDS_IMAGES_DIR = String.format("src%cgui%cimages%cboughtcards", separatorChar, separatorChar, separatorChar);

    /**
     * The file that contains the pre-built imperium network. This file is required for playing the game with the
     * imperium map and must be present in order for the game to be played with the imperium map so that the cities
     * and connections can be accurately mapped.
     */
    private static final String IMPERIUM_PRE_BUILT_NETWORK_FILE = String.format("resources%csaves%cpre_built_maps%cimperium.nw", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the imperium pre-built network file from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String IMPERIUM_PRE_BUILT_NETWORK_FILE_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/resources/saves/pre_built_maps/imperium.nw";

    /**
     * The file that contains the pre-built list of cities for the imperium network. This file is required for
     * playing the game with the imperium map and must be present in order for the game to be played with the
     * imperium map so that the cities and connections can be accurately mapped.
     */
    private static final String IMPERIUM_PRE_BUILT_CITIES_FILE = String.format("resources%csaves%cpre_built_maps%cimperium_cities.cnw", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the imperium pre-built cities file from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String IMPERIUM_PRE_BUILT_CITIES_FILE_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/resources/saves/pre_built_maps/imperium_cities.cnw";

    /**
     * The file that contains the image of the game board. This file is used in several frames and panels to
     * display the game board as a functional component and as a background or feature image.
     */
    private static final String CONCORDIA_BOARD_IMG = String.format("src%cgui%cimages%cConcordia board.jpg", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the concordia board image file from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String CONCORDIA_BOARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/Concordia%20board.jpg";

    /**
     * The file that contains the image of the black store house. This file is used to display the image of
     * the black store house for the player that is using it.
     */
    private static final String BLACK_STORE_HOUSE_IMG = String.format("src%cgui%cimages%cblackStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the black store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String BLACK_STORE_HOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/blackStorehouse.png";

    /**
     * The file that contains the image of the blue store house. This file is used to display the image of
     * the blue store house for the player that is using it.
     */
    private static final String BLUE_STORE_HOUSE_IMG = String.format("src%cgui%cimages%cblueStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the blue store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String BLUE_STORE_HOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/blueStorehouse.png";

    /**
     * The file that contains an image of the back of the concordia card. This file is used to display the
     * concordia card when it is awarded to the player who ends the game.
     */
    private static final String CONCORDIA_CARD_IMG = String.format("src%cgui%cimages%cconcordia.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the concordia card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String CONCORDIA_CARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/concordia.png";

    /**
     * The file that contains an image of the concordia card. This file is used to display the concordia
     * card when it is awarded to the player who ends the game.
     */
    private static final String CONCORDIA_CARD_BACK_IMG = String.format("src%cgui%cimages%cconcordiaBack.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the concordia card back image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String CONCORDIA_CARD_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/concordiaBack.png";

    /**
     * The file that contains an image of the green store house. This file is used to display the image of
     * the green store house for the player that is using it.
     */
    private static final String GREEN_STORE_HOUSE_IMG = String.format("src%cgui%cimages%cgreenStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the green store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String GREEN_STORE_HOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/greenStorehouse.png";

    /**
     * The file that contains an image of the praefectus magnus card. This file is used to display the
     * praefectus magnus card when a player has it in their hand.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_IMG = String.format("src%cgui%cimages%cpraefectusMagnus.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the praefectus magnus card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/praefectusMagnus.png";

    /**
     * The file that contains an image of the back of the praefectus magnus card. This file is used to
     * display the back of the praefectus magnus card when a player has it in their hand.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_BACK_IMG = String.format("src%cgui%cimages%cpraefectusMagnusBack.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of the praefectus magnus card from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PRAEFECTUS_MAGNUS_CARD_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/praefectusMagnusBack.png";

    /**
     * The file that contains an image of the red store house. This file is used to display the image of
     * the red store house for the player that is using it.
     */
    private static final String RED_STORE_HOUSE_IMG = String.format("src%cgui%cimages%credStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the red store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String RED_STORE_HOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/redStorehouse.png";

    /**
     * The file that contains an image of the first reference card. This file is used to display the
     * information found on the first reference card for players to read.
     */
    private static final String REFERENCE_CARD_A_IMG = String.format("src%cgui%cimages%creferenceCardA.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first reference card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String REFERENCE_CARD_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/referenceCardA.png";

    /**
     * The file that contains an image of the second reference card. This file is used to display the
     * information found on the second reference card for players to read.
     */
    private static final String REFERENCE_CARD_B_IMG = String.format("src%cgui%cimages%creferenceCardB.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second reference card image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String REFERENCE_CARD_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/referenceCardB.png";

    /**
     * The file that contains an image of the yellow store house. This file is used to display the image of
     * the yellow store house for the player that is using it.
     */
    private static final String YELLOW_STORE_HOUSE_IMG = String.format("src%cgui%cimages%cyellowStorehouse.png", separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the yellow store house image from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String YELLOW_STORE_HOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/yellowStorehouse.png";

    /**
     * The file that contains an image of the architect personality card. This file is used to display the
     * image of the architect personality card when a player has it in their hand or wants to buy it.
     */
    private static final String ARCHITECT_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%carchitect.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the architect personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String ARCHITECT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/architect.png";

    /**
     * The file that contains an image of the black back of personality cards. This file is used to display
     * the back of a personality card when it is black.
     */
    private static final String PCARD_BLACK_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cblackBack.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the black back of personality cards from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PCARD_BLACK_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/blackBack.png";

    /**
     * The file that contains an image of the blue back of personality cards. This file is used to display
     * the back of a personality card when it is blue.
     */
    private static final String PCARD_BLUE_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cblueBack.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the blue back of personality cards from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PCARD_BLUE_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/blueBack.png";

    /**
     * The file that contains an image of the diplomat personality card. This file is used to display the
     * image of the diplomat personality card when a player has it in their hand or wants to buy it.
     */
    private static final String DIPLOMAT_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%cdiplomat.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the diplomat personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String DIPLOMAT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/diplomat.png";

    /**
     * The file that contains an image of the green back of personality cards. This file is used to display
     * the back of a personality card when it is green.
     */
    private static final String PCARD_GREEN_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cgreenBack.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the green back of personality cards from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PCARD_GREEN_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/greenBack.png";

    /**
     * The file that contains an image of the mercator personality card. This file is used to display the
     * image of the mercator personality card when a player has it in their hand or wants to buy it.
     */
    private static final String MERCATOR_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%cmercator.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the mercator personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String MERCATOR_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/mercator.png";

    /**
     * The file that contains the image of the prefect personality card. This file is used to display the
     * image of the prefect personality card when a player has it in their hand or wants to buy it.
     */
    private static final String PREFECT_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%cprefect.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the prefect personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String PREFECT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/prefect.png";

    /**
     * The file that contains an image of the red back of personality cards. This file is used to display
     * the back of a personality card when it is red.
     */
    private static final String PCARD_RED_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%credBack.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the red back of personality cards from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PCARD_RED_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/redBack.png";

    /**
     * The file that contains an image of the senator personality card. This file is used to display the
     * image of the senator personality card when a player has it in their hand or wants to buy it.
     */
    private static final String SENATOR_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%csenator.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the senator personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String SENATOR_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/senator.png";

    /**
     * The file that contains the image of the tribune personality card. This file is used to display the
     * image of the tribune personality card when a player has it in their hand or wants to buy it.
     */
    private static final String TRIBUNE_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%ctribune.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the tribune personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String TRIBUNE_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/tribune.png";

    /**
     * The file that contains the image of the yellow back of personality cards. This file is used to display
     * the back of a personality card when it is yellow.
     */
    private static final String PCARD_YELLOW_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cyellowBack.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the yellow back of personality cards from the GitHub
     * repository. This link is to be used if the file does not already exist locally.
     */
    private static final String PCARD_YELLOW_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/yellowBack.png";

    /**
     * The file that contains the image of a brick city. This file is used to display the image of a brick
     * city on the game board.
     */
    private static final String BRICK_CITY_IMG = String.format("src%cgui%cimages%cpieces%cbrickCity.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a brick city from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String BRICK_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/brickCity.png";

    /**
     * The file that contains the image of a brick tile. This file is used to display the image of a brick tile
     * on the game board.
     */
    private static final String BRICK_TILE_IMG = String.format("src%cgui%cimages%cpieces%cbrickTile.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a brick tile from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String BRICK_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/brickTile.png";

    /**
     * The file that contains the image of a cloth city. This file is used to display the image of a cloth city
     * on the game board.
     */
    private static final String CLOTH_CITY_IMG = String.format("src%cgui%cimages%cpieces%cclothCity.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a cloth city from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String CLOTH_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/clothCity.png";

    /**
     * The file that contains the image of a cloth tile. This file is used to display the image of a cloth tile
     * on the game board.
     */
    private static final String CLOTH_TILE_IMG = String.format("src%cgui%cimages%cpieces%cclothTile.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a cloth tile from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String CLOTH_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/clothTile.png";

    /**
     * The file that contains the first image of a coin tile. This file is used to display the first image of a
     * coin tile on the game board.
     */
    private static final String COIN_TILE_IMG_1 = String.format("src%cgui%cimages%cpieces%ccoinTile1.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first image of a coin tile from the GitHub repository. This link is to
     * be used if the file does not already exist locally.
     */
    private static final String COIN_TILE_IMG_1_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/coinTile1.png";

    /**
     * The file that contains the second image of a coin tile. This file is used to display the second image of a
     * coin tile on the game board.
     */
    private static final String COIN_TILE_IMG_2 = String.format("src%cgui%cimages%cpieces%ccoinTile2.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second image of a coin tile from the GitHub repository. This link is to
     * be used if the file does not already exist locally.
     */
    private static final String COIN_TILE_IMG_2_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/coinTile2.png";

    /**
     * The file that contains the first image of the five coin tile. This file is used to display the first image
     * of the five coin tile on the game board.
     */
    private static final String FIVE_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%cfiveCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first image of the five coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String FIVE_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/fiveCoinA.png";

    /**
     * The file that contains the second image of the five coin tile. This file is used to display the second
     * image of the five coin tile on the game board.
     */
    private static final String FIVE_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%cfiveCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second image of the five coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String FIVE_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/fiveCoinB.png";

    /**
     * The file that contains the image of a food city. This file is used to display the image of a food city
     * on the game board.
     */
    private static final String FOOD_CITY_IMG = String.format("src%cgui%cimages%cpieces%cfoodCity.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a food city from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String FOOD_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/foodCity.png";

    /**
     * The file that contains the image of the food tile. This file is used to display the image of the food
     * tile on the game board.
     */
    private static final String FOOD_TILE_IMG = String.format("src%cgui%cimages%cpieces%cfoodTile.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the food tile from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String FOOD_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/foodTile.png";

    /**
     * The file that contains the first image of the one coin tile. This file is used to display the first image
     * of the one coin tile on the game board.
     */
    private static final String ONE_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%coneCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first image of the one coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String ONE_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/oneCoinA.png";

    /**
     * The file that contains the second image of the one coin tile. This file is used to display the second
     * image of the one coin tile on the game board.
     */
    private static final String ONE_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%coneCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second image of the one coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String ONE_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/oneCoinB.png";

    /**
     * The file that contains the first image of the ten coin tile. This file is used to display the first image
     * of the ten coin tile on the game board.
     */
    private static final String TEN_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%ctenCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first image of the ten coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String TEN_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/tenCoinA.png";

    /**
     * The file that contains the second image of the ten coin tile. This file is used to display the second
     * image of the ten coin tile on the game board.
     */
    private static final String TEN_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%ctenCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second image of the ten coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String TEN_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/tenCoinB.png";

    /**
     * The file that contains the image of a tool city. This file is used to display the image of a tool city
     * on the game board.
     */
    private static final String TOOL_CITY_IMG = String.format("src%cgui%cimages%cpieces%ctoolCity.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a tool city from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String TOOL_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/toolCity.png";

    /**
     * The file that contains the image of the tool tile. This file is used to display the image of the tool
     * tile on the game board.
     */
    private static final String TOOL_TILE_IMG = String.format("src%cgui%cimages%cpieces%ctoolTile.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the tool tile from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String TOOL_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/toolTile.png";

    /**
     * The file that contains the first image of the two coin tile. This file is used to display the first image
     * of the two coin tile on the game board.
     */
    private static final String TWO_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%ctwoCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the first image of the two coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String TWO_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/twoCoinA.png";

    /**
     * The file that contains the second image of the two coin tile. This file is used to display the second
     * image of the two coin tile on the game board.
     */
    private static final String TWO_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%ctwoCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the second image of the two coin tile from the GitHub repository. This link
     * is to be used if the file does not already exist locally.
     */
    private static final String TWO_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/twoCoinB.png";

    /**
     * The file that contains the iamge of a wine city. This file is used to display the image of a wine city
     * on the game board.
     */
    private static final String WINE_CITY_IMG = String.format("src%cgui%cimages%cpieces%cwineCity.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of a wine city from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String WINE_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/wineCity.png";

    /**
     * The file that contains the image of the wine tile. This file is used to display the image of the wine
     * tile on the game board.
     */
    private static final String WINE_TILE_IMG = String.format("src%cgui%cimages%cpieces%cwineTile.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the wine tile from the GitHub repository. This link is to be
     * used if the file does not already exist locally.
     */
    private static final String WINE_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/wineTile.png";

    /**
     * The file that contains the image of the architect card. This file is used to display the image of the
     * architect card on the game board.
     */
    private static final String ARCHITECT_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%carchitect.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the architect card from the GitHub repository. This link is
     * to be used if the file does not already exist locally.
     */
    private static final String ARCHITECT_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/architect.png";

    /**
     * The file that contains the image of the back of personality cards in the first set. This file is used
     * to display the image of the back of personality cards in the first set on the game board.
     */
    private static final String BACK_OF_PCARD_I_IMG = String.format("src%cgui%cimages%cboughtcards%cbackI.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of personality cards in the first set from the
     * GitHub repository. This link is to be used if the file does not already exist locally.
     */
    private static final String BACK_OF_PCARD_I_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backI.png";

    /**
     * The file that contains the image of the back of personality cards in the second set. This file is used
     * to display the image of the back of personality cards in the second set on the game board.
     */
    private static final String BACK_OF_PCARD_II_IMG = String.format("src%cgui%cimages%cboughtcards%cbackII.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of personality cards in the second set from the
     * GitHub repository. This link is to be used if the file does not already exist locally.
     */
    private static final String BACK_OF_PCARD_II_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backII.png";

    /**
     * The file that contains the image of the back of personality cards in the third set. This file is used
     * to display the image of the back of personality cards in the third set on the game board.
     */
    private static final String BACK_OF_PCARD_III_IMG = String.format("src%cgui%cimages%cboughtcards%cbackIII.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of personality cards in the third set from the
     * GitHub repository. This link is to be used if the file does not already exist locally.
     */
    private static final String BACK_OF_PCARD_III_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backIII.png";

    /**
     * The file that contains the image of the back of personality cards in the fourth set. This file is used
     * to display the image of the back of personality cards in the fourth set on the game board.
     */
    private static final String BACK_OF_PCARD_IV_IMG = String.format("src%cgui%cimages%cboughtcards%cbackIV.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of personality cards in the fourth set from the
     * GitHub repository. This link is to be used if the file does not already exist locally.
     */
    private static final String BACK_OF_PCARD_IV_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backIV.png";

    /**
     * The file that contains the image of the back of personality cards in the fifth set. This file is used
     * to display the image of the back of personality cards in the fifth set on the game board.
     */
    private static final String BACK_OF_PCARD_V_IMG = String.format("src%cgui%cimages%cboughtcards%cbackV.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the back of personality cards in the fifth set from the
     * GitHub repository. This link is to be used if the file does not already exist locally.
     */
    private static final String BACK_OF_PCARD_V_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backV.png";

    /**
     * The file containing the image of the colonist personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String COLONIST_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%ccolonist.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the colonist personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String COLONIST_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/colonist.png";

    /**
     * The file that contains the image of the diplomat personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String DIPLOMAT_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cdiplomat.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the diplomat personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String DIPLOMAT_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/diplomat.png";

    /**
     * The file that contains the image of the farmer personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String FARMER_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cfarmer.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the farmer personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String FARMER_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/farmer.png";

    /**
     * The file that contains the image of the mason personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String MASON_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cmason.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the mason personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String MASON_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/mason.png";

    /**
     * The file that contains the image of the mercator personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String MERCATOR_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cmercator.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the mercator personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String MERCATOR_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/mercator.png";

    /**
     * The file that contains the image of the prefect personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String PREFECT_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cprefect.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the prefect personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String PREFECT_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/prefect.png";

    /**
     * The file that contains the image of the smith personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String SMITH_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%csmith.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the smith personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String SMITH_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/smith.png";

    /**
     * The file that contains the image of the vinter personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String VINTER_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cvinter.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the vinter personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String VINTER_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/vinter.png";

    /**
     * The file that contains the image of the weaver personality card to be bought. This file is used to
     * display the correct image when the player is buying personality cards.
     */
    private static final String WEAVER_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cweaver.png", separatorChar, separatorChar, separatorChar, separatorChar);

    /**
     * The download link to download the image of the weaver personality card from the GitHub repository.
     * This link is to be used if the file does not already exist locally.
     */
    private static final String WEAVER_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/weaver.png";

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
    private static void ensureDirExistence(Path directory, String directoryName) {
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
    public static void verifyUnfinishedGamesDirectory() {
        ensureDirExistence(Paths.get(UNFINISHED_GAME_SAVE_DIR), "unfinished games");
    }

    /**
     * Checks for the directory that will be used to save the pre-built map files
     * and create the directory if it does not exist
     */
    public static void verifyPreBuiltMapFilesDirectory() {
        ensureDirExistence(Paths.get(PRE_BUILT_MAP_DIR), "pre-built map files");
    }

    /**
     * Checks for the login information directory that is used to store usernames
     * and passwords and creates the directory if it does not exist
     */
    public static void verifyLoginInformationDirectory() {
        ensureDirExistence(Paths.get(LOGIN_INFORMATION_DIR), "login information");
    }

    /**
     * Checks for the player performance information directory that is used to store
     * information regarding the performance of players in games and creates it if
     * it does not exist
     */
    public static void verifyPlayerPerformanceInformationDirectory() {
        ensureDirExistence(Paths.get(PLAYER_PERFORMANCE_INFORMATION_DIR), "player performance information");
    }

    /**
     * Checks for the sprites directory that is used to store the png files with the 
     * sprites and creates it if it does not exist. 
     */
    public static void verifySpritesDirectory() {
        ensureDirExistence(Paths.get(SPRITES_DIR), "sprites");
    }

    /**
     * Checks for the main graphics images directory that is used to store the image files
     * associated with most of the images that are used in the game.
     */
    public static void verifyMainGraphicsDir() {
        ensureDirExistence(Paths.get(MAIN_GRAPHICS_IMAGES_DIR), "main graphics images");
    }

    /**
     * Checks for the starting cards images directory that is used to store the image files
     * of the starting personality cards that are used to render the personality cards that
     * the players start with.
     */
    public static void verifyStartingCardsDir() {
        ensureDirExistence(Paths.get(STARTING_CARDS_IMAGES_DIR), "starting cards images");
    }

    /**
     * Checks for the pieces image directory that is used to store the image that are used
     * to render the game pieces on the game board, in the inventory of the players, and in
     * the store house of the players.
     */
    public static void verifyGamePiecesDir() {
        ensureDirExistence(Paths.get(GAME_PIECES_IMAGES_DIR), "game pieces images");
    }

    /**
     * Checks for the bought personality cards images directory that is used to store the image
     * files of the personality cards that can be bought from the game board.
     */
    public static void verifyBoughtPersonalityCardsDir() {
        ensureDirExistence(Paths.get(BOUGHT_PCARDS_IMAGES_DIR), "bought personality cards images");
    }

    /*
     * Methods to check for necessary game files and download them from the internet if they
     * are not found locally.
     */

    /**
     * Method to download a file from the internet if it is not found locally. This method will
     * be used by each of the individual methods that check for the existence of the necessary
     * game files to reduce the amount of repeated code and to improve readability in the entirety
     * of the IOUtils class. This method uses asynchronous I/O along with concurrent downloads to
     * download the fast.
     *
     * @param downloadLink       the link to the file that should be downloaded
     * @param destinationPath    the path to where the file should be stored
     * @param fileName           the name of the file
     */
    private static void downloadFile(String downloadLink, String destinationPath, String fileName) {
        if (!isInternetAvailable()) {
            Logger.error("IOUtils", "Internet is not available. Cannot download the " + fileName);
            return;
        }
        try {
            URL url = new URI(downloadLink).toURL();
            Path destination = Path.of(destinationPath);

            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(destination,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

            CompletionHandler<Integer, Void> completionHandler = new CompletionHandler<>() {
                @Override
                public void completed(Integer result, Void attachment) {
                    if (result == -1) {
                        try {
                            fileChannel.close();
                            Logger.info("IOUtils", "Successfully downloaded the " + fileName);
                        } catch (IOException e) {
                            Logger.error("IOUtils", "Error occurred while closing the file channel for " + fileName + ": " + e.getMessage());
                        }
                    } else {
                        buffer.flip();
                        fileChannel.write(buffer, 0, null, this);
                        buffer.clear();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    Logger.error("IOUtils", "Error occurred while downloading the " + fileName + ": " + exc.getMessage());
                }
            };

            Callable<Boolean> downloadTask = () -> {
                try {
                    fileChannel.write(buffer, 0, null, completionHandler);
                    return true;
                } catch (Exception e) {
                    Logger.error("IOUtils", "Error occurred while initiating the download for " + fileName + ": " + e.getMessage());
                    return false;
                }
            };

            Future<Boolean> future = executorService.submit(downloadTask);
            future.get();

            Logger.info("IOUtils", "Successfully downloaded the " + fileName);
        } catch (URISyntaxException | IOException | InterruptedException | ExecutionException e) {
            Logger.error("IOUtils", "Error occurred while downloading the " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Method to shut down the {@code ExecutorService} that is used to download the necessary
     * game files concurrently. This will ensure that the program will not continue to run after
     * the main thread has finished executing.
     */
    private static void shutdownExecutorService() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS))
                    Logger.error("IOUtils", "Could not shut down the executor service for downloads");
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Method to check if internet is available to start a download. This method will be used
     * by the {@code downloadFile} method to check if internet is available before starting a
     * download.
     *
     * @return  {@code true} if internet is available, {@code false} otherwise
     */
    private static boolean isInternetAvailable() {
        int maxAttempts = 3;
        int retryInterval = 200;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                URL url = new URL("https://gnu.org");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return true;
                }
            } catch (IOException e) {
                Logger.warn("IOUtils", "Attempt " + attempt + " to check for internet availability failed");
            }

            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                Logger.error("IOUtils", "Error waiting to retry checking for internet availability: " + e.getMessage());
            }
        }

        return false;
    }

    /**
     * Checks for the pre-built imperium network file which is necessary for the game to run
     * properly. If the file is not found locally, it will be downloaded from the internet
     * using the {@code downloadFile} method.
     */
    public static void verifyPreBuiltImperiumNetworkFile() {
        Path file = Path.of(IMPERIUM_PRE_BUILT_NETWORK_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Pre-built imperium network file already exists");
            return;
        }
        downloadFile(IMPERIUM_PRE_BUILT_NETWORK_FILE_EXTERNAL, IMPERIUM_PRE_BUILT_NETWORK_FILE, "pre-built imperium network file");
    }

    /**
     * Checks for the pre-built imperium cities file which is necessary for the game to run
     * properly. If the file is not found locally, it will be downloaded from the internet
     * using the {@code downloadFile} method.
     */
    public static void verifyPreBuiltImperiumCitiesFile() {
        Path file = Path.of(IMPERIUM_PRE_BUILT_CITIES_FILE);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Pre-built imperium cities file already exists");
            return;
        }
        downloadFile(IMPERIUM_PRE_BUILT_CITIES_FILE_EXTERNAL, IMPERIUM_PRE_BUILT_CITIES_FILE, "pre-built imperium cities file");
    }

    /**
     * Checks for the concordia board image file which is necessary for the graphics of the game
     * in terms of displaying the game board on all the various frames and panels that it is used
     * on. If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyConcordiaBoardImg() {
        Path file = Path.of(CONCORDIA_BOARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Concordia board image file already exists");
            return;
        }
        downloadFile(CONCORDIA_BOARD_IMG_EXTERNAL, CONCORDIA_BOARD_IMG, "concordia board image file");
    }

    /**
     * Checks for the black store house image file which is necessary for the graphics of the game
     * in terms of displaying what is in the store house of a player and what resources the player
     * has at any given moment in the game. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyBlackStoreHouseImg() {
        Path file = Path.of(BLACK_STORE_HOUSE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Black store house image file already exists");
            return;
        }
        downloadFile(BLACK_STORE_HOUSE_IMG_EXTERNAL, BLACK_STORE_HOUSE_IMG, "black store house image file");
    }

    /**
     * Checks for the blue store house image file which is necessary for the graphics of the game
     * in terms of displaying what is in the store house of a player and what resources the player
     * has at any given moment in the game. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyBlueStoreHouseImg() {
        Path file = Path.of(BLUE_STORE_HOUSE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Blue store house image file already exists");
            return;
        }
        downloadFile(BLUE_STORE_HOUSE_IMG_EXTERNAL, BLUE_STORE_HOUSE_IMG, "blue store house image file");
    }

    /**
     * Checks for the concordia card image file which is necessary for the graphics of the game
     * in terms of displaying the concordia card when it is awarded to the player who ends the game.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyConcordiaCardImg() {
        Path file = Path.of(CONCORDIA_CARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Concordia card image file already exists");
            return;
        }
        downloadFile(CONCORDIA_CARD_IMG_EXTERNAL, CONCORDIA_CARD_IMG, "concordia card image file");
    }

    /**
     * Checks for the back of the concordia card image file which is necessary for the graphics of the game
     * in terms of displaying the back of the concordia card when it is awarded to the player who ends the game.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyConcordiaCardImgBack() {
        Path file = Path.of(CONCORDIA_CARD_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Back of concordia card image file already exists");
            return;
        }
        downloadFile(CONCORDIA_CARD_BACK_IMG_EXTERNAL, CONCORDIA_CARD_BACK_IMG, "back of concordia card image file");
    }

    /**
     * Checks for the green store house image file which is necessary for the graphics of the game
     * in terms of displaying what is in the store house of a player and what resources the player
     * has at any given moment in the game. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyGreenStoreHouseImg() {
        Path file = Path.of(GREEN_STORE_HOUSE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Green store house image file already exists");
            return;
        }
        downloadFile(GREEN_STORE_HOUSE_IMG_EXTERNAL, GREEN_STORE_HOUSE_IMG, "green store house image file");
    }

    /**
     * Checks for the praefectus magnus card image file which is necessary for the graphics of the game
     * in terms of displaying which player has the praefectus magnus card during the game. If the file
     * is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPraefectusMagnusCardImg() {
        Path file = Path.of(PRAEFECTUS_MAGNUS_CARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Praefectus magnus card image file already exists");
            return;
        }
        downloadFile(PRAEFECTUS_MAGNUS_CARD_IMG_EXTERNAL, PRAEFECTUS_MAGNUS_CARD_IMG, "praefectus magnus card image file");
    }

    /**
     * Checks for the back of the praefectus magnus card image file which is necessary for the graphics of the game
     * in terms of displaying the back of the praefectus magnus card when it is awarded to the player who ends the game.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPraefectusMagnusCardImgBack() {
        Path file = Path.of(PRAEFECTUS_MAGNUS_CARD_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Back of praefectus magnus card image file already exists");
            return;
        }
        downloadFile(PRAEFECTUS_MAGNUS_CARD_BACK_IMG_EXTERNAL, PRAEFECTUS_MAGNUS_CARD_BACK_IMG, "back of praefectus magnus card image file");
    }

    /**
     * Checks for the red store house image file which is necessary for the graphics of the game
     * in terms of displaying what is in the store house of a player and what resources the player
     * has at any given moment in the game. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyRedStoreHouseImg() {
        Path file = Path.of(RED_STORE_HOUSE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Red store house image file already exists");
            return;
        }
        downloadFile(RED_STORE_HOUSE_IMG_EXTERNAL, RED_STORE_HOUSE_IMG, "red store house image file");
    }

    /**
     * Checks for the first reference card image file which is necessary for the graphics of the game
     * in terms of displaying the information that the first reference card displays to the players.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyReferenceCardAImg() {
        Path file = Path.of(REFERENCE_CARD_A_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "First reference card image file already exists");
            return;
        }
        downloadFile(REFERENCE_CARD_A_IMG_EXTERNAL, REFERENCE_CARD_A_IMG, "first reference card image file");
    }

    /**
     * Checks for the second reference card image file which is necessary for the graphics of the game
     * in terms of displaying the information that the second reference card displays to the players.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyReferenceCardBImg() {
        Path file = Path.of(REFERENCE_CARD_B_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Second reference card image file already exists");
            return;
        }
        downloadFile(REFERENCE_CARD_B_IMG_EXTERNAL, REFERENCE_CARD_B_IMG, "second reference card image file");
    }

    /**
     * Checks for the yellow store house image file which is necessary for the graphics of the game
     * in terms of displaying what is in the store house of a player and what resources the player
     * has at any given moment in the game. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyYellowStoreHouseImg() {
        Path file = Path.of(YELLOW_STORE_HOUSE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Yellow store house image file already exists");
            return;
        }
        downloadFile(YELLOW_STORE_HOUSE_IMG_EXTERNAL, YELLOW_STORE_HOUSE_IMG, "yellow store house image file");
    }

    /**
     * Checks for the architect personality card image file which is necessary for the graphics of
     * the game in terms of displaying the card in the player hands and displaying the card on the
     * game board when it can be bought. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFIle} method.
     */
    public static void verifyArchitectPersonalityCardImg() {
        Path file = Path.of(ARCHITECT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Architect personality card image file already exists");
            return;
        }
        downloadFile(ARCHITECT_PCARD_IMG_EXTERNAL, ARCHITECT_PCARD_IMG, "architect personality card image file");
    }

    /**
     * Checks for the image containing the black back of personality cards which is necessary for the graphics of
     * the game in terms of rendering the correct back of personality cards. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyBlackBackOfPersonalityCardsImg() {
        Path file = Path.of(PCARD_BLACK_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Black back of personality cards image file already exists");
            return;
        }
        downloadFile(PCARD_BLACK_BACK_IMG_EXTERNAL, PCARD_BLACK_BACK_IMG, "black back of personality cards image file");
    }

    /**
     * Checks for the image containing the blue back of personality cards which is necessary for the graphics of
     * the game in terms of rendering the correct back of personality cards. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyBlueBackOfPersonalityCardsImg() {
        Path file = Path.of(PCARD_BLUE_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Blue back of personality cards image file already exists");
            return;
        }
        downloadFile(PCARD_BLUE_BACK_IMG_EXTERNAL, PCARD_BLUE_BACK_IMG, "blue back of personality cards image file");
    }

    /**
     * Checks for the diplomat personality card image file which is necessary for the graphics of
     * the game in terms of displaying the card in the player hands and displaying the card on the
     * game board when it can be bought. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyDiplomatPersonalityCardImg() {
        Path file = Path.of(DIPLOMAT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Diplomat personality card image file already exists");
            return;
        }
        downloadFile(DIPLOMAT_PCARD_IMG_EXTERNAL, DIPLOMAT_PCARD_IMG, "diplomat personality card image file");
    }

    /**
     * Checks for the image containing the green back of personality cards which is necessary for the graphics of
     * the game in terms of rendering the correct back of personality cards. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyGreenBackOfPersonalityCardsImg() {
        Path file = Path.of(PCARD_GREEN_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Green back of personality cards image file already exists");
            return;
        }
        downloadFile(PCARD_GREEN_BACK_IMG_EXTERNAL, PCARD_GREEN_BACK_IMG, "green back of personality cards image file");
    }

    /**
     * Checks for the mercator personality card image file which is necessary for the graphics of
     * the game in terms of displaying the card in the player hands and displaying the card on the
     * game board when it can be bought. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyMercatorPersonalityCardImg() {
        Path file = Path.of(MERCATOR_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Mercator personality card image file already exists");
            return;
        }
        downloadFile(MERCATOR_PCARD_IMG_EXTERNAL, MERCATOR_PCARD_IMG, "mercator personality card image file");
    }

    /**
     * Checks for the prefect personality card image file which is necessary for the graphics of
     * the game in terms of displaying the card in the player hands and displaying the card on the
     * game board when it can be bought. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyPrefectPersonalityCardImg() {
        Path file = Path.of(PREFECT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Prefect personality card image file already exists");
            return;
        }
        downloadFile(PREFECT_PCARD_IMG_EXTERNAL, PREFECT_PCARD_IMG, "prefect personality card image file");
    }

    /**
     * Checks for the image containing the red back of personality cards which is necessary for the graphics of
     * the game in terms of rendering the correct back of personality cards. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyRedBackOfPersonalityCardsImg() {
        Path file = Path.of(PCARD_RED_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Red back of personality cards image file already exists");
            return;
        }
        downloadFile(PCARD_RED_BACK_IMG_EXTERNAL, PCARD_RED_BACK_IMG, "red back of personality cards image file");
    }

    /**
     * Checks for the senator personality card image file which is necessary for the graphics of
     * the game in terms of displaying the card in the player hands and displaying the card on the
     * game board when it can be bought. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifySenatorPersonalityCardImg() {
        Path file = Path.of(SENATOR_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Senator personality card image file already exists");
            return;
        }
        downloadFile(SENATOR_PCARD_IMG_EXTERNAL, SENATOR_PCARD_IMG, "senator personality card image file");
    }

    /**
     * Checks for the tribune personality card image file which is necessary for the graphics of
     * the game in terms of displaying the card in the player hands and displaying the card on the
     * game board when it can be bought. If the file is not found locally, it will be downloaded
     * from the internet using the {@code downloadFile} method.
     */
    public static void verifyTribunePersonalityCardImg() {
        Path file = Path.of(TRIBUNE_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Tribune personality card image file already exists");
            return;
        }
        downloadFile(TRIBUNE_PCARD_IMG_EXTERNAL, TRIBUNE_PCARD_IMG, "tribune personality card image file");
    }

    /**
     * Checks for the image containing the yellow back of personality cards which is necessary for the graphics of
     * the game in terms of rendering the correct back of personality cards. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyYellowBackOfPersonalityCardsImg() {
        Path file = Path.of(PCARD_YELLOW_BACK_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Yellow back of personality cards image file already exists");
            return;
        }
        downloadFile(PCARD_YELLOW_BACK_IMG_EXTERNAL, PCARD_YELLOW_BACK_IMG, "yellow back of personality cards image file");
    }

    /**
     * Checks for the image containing the brick city which is necessary for the graphics of
     * the game in terms of rendering the correct city. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyBrickCityImg() {
        Path file = Path.of(BRICK_CITY_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Brick city image file already exists");
            return;
        }
        downloadFile(BRICK_CITY_IMG_EXTERNAL, BRICK_CITY_IMG, "brick city image file");
    }

    /**
     * Checks for the image containing the brick tile which is necessary for the graphics of
     * the game in terms of rendering the resource correct tile. If the file is not found locally,
     * it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyBrickTileImg() {
        Path file = Path.of(BRICK_TILE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Brick tile image file already exists");
            return;
        }
        downloadFile(BRICK_TILE_IMG_EXTERNAL, BRICK_TILE_IMG, "brick tile image file");
    }

    /**
     * Checks for the image containing the cloth city which is necessary for the graphics of
     * the game in terms of rendering the correct city. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyClothCityImg() {
        Path file = Path.of(CLOTH_CITY_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Cloth city image file already exists");
            return;
        }
        downloadFile(CLOTH_CITY_IMG_EXTERNAL, CLOTH_CITY_IMG, "cloth city image file");
    }

    /**
     * Checks for the image containing the cloth tile which is necessary for the graphics of
     * the game in terms of rendering the correct tile. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyClothTileImg() {
        Path file = Path.of(CLOTH_TILE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Cloth tile image file already exists");
            return;
        }
        downloadFile(CLOTH_TILE_IMG_EXTERNAL, CLOTH_TILE_IMG, "cloth tile image file");
    }

    /**
     * Checks for the file containing the first image of the coin which is necessary for the graphics of
     * the game in terms of rendering the correct coin. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyCoinImg1() {
        Path file = Path.of(COIN_TILE_IMG_1);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Coin image file already exists");
            return;
        }
        downloadFile(COIN_TILE_IMG_1_EXTERNAL, COIN_TILE_IMG_1, "coin image file");
    }

    /**
     * Checks for the file containing the second image of the coin which is necessary for the graphics of
     * the game in terms of rendering the correct coin. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyCoinImg2() {
        Path file = Path.of(COIN_TILE_IMG_2);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Coin image file 2 already exists");
            return;
        }
        downloadFile(COIN_TILE_IMG_2_EXTERNAL, COIN_TILE_IMG_2, "coin image file 2");
    }

    /**
     * Checks for the file containing the first image of the five coin which is necessary for the graphics of
     * the game in terms of rendering the correct value coin. If the file is not found locally, it
     * will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyFiveCoinAImg() {
        Path file = Path.of(FIVE_COIN_A_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Five coin image file already exists");
            return;
        }
        downloadFile(FIVE_COIN_A_IMG_EXTERNAL, FIVE_COIN_A_IMG, "five coin image file");
    }

    /**
     * Checks for the file containing the second image of the five coin which is necessary for the
     * graphics of the game in terms of rendering the correct value coin. If the file is not found
     * locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyFiveCoinBImg() {
        Path file = Path.of(FIVE_COIN_B_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Five coin image file 2 already exists");
            return;
        }
        downloadFile(FIVE_COIN_B_IMG_EXTERNAL, FIVE_COIN_B_IMG, "five coin image file 2");
    }

    /**
     * Checks for the file containing the image of the food city which is necessary for the graphics of
     * the game in terms of rendering the correct city. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyFoodCityImg() {
        Path file = Path.of(FOOD_CITY_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Food city image file already exists");
            return;
        }
        downloadFile(FOOD_CITY_IMG_EXTERNAL, FOOD_CITY_IMG, "food city image file");
    }

    /**
     * Checks for the file containing the image of the food tile which is necessary for the graphics of
     * the game in terms of rendering the correct tile. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyFoodTileImg() {
        Path file = Path.of(FOOD_TILE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Food tile image file already exists");
            return;
        }
        downloadFile(FOOD_TILE_IMG_EXTERNAL, FOOD_TILE_IMG, "food tile image file");
    }

    /**
     * Checks for the file containing the first image of the one coin which is necessary for the graphics of
     * the game in terms of rendering the correct value coin. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyOneCoinAImg() {
        Path file = Path.of(ONE_COIN_A_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "One coin image file already exists");
            return;
        }
        downloadFile(ONE_COIN_A_IMG_EXTERNAL, ONE_COIN_A_IMG, "one coin image file");
    }

    /**
     * Checks for the file containing the second image of the one coin which is necessary for the graphics of
     * the game in terms of rendering the correct value coin. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyOneCoinBImg() {
        Path file = Path.of(ONE_COIN_B_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "One coin image file 2 already exists");
            return;
        }
        downloadFile(ONE_COIN_B_IMG_EXTERNAL, ONE_COIN_B_IMG, "one coin image file 2");
    }

    /**
     * Checks for the file containing the first image of the ten coin which is necessary for the graphics
     * of the game in terms of rendering the correct value coin. If the file is not found locally, it will
     * be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyTenCoinAImg() {
        Path file = Path.of(TEN_COIN_A_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Ten coin image file already exists");
            return;
        }
        downloadFile(TEN_COIN_A_IMG_EXTERNAL, TEN_COIN_A_IMG, "ten coin image file");
    }

    /**
     * Checks for the file containing the second image of the ten coin which is necessary for the graphics
     * of the game in terms of rendering the correct value coin. If the file is not found locally, it will
     * be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyTenCoinBImg() {
        Path file = Path.of(TEN_COIN_B_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Ten coin image file 2 already exists");
            return;
        }
        downloadFile(TEN_COIN_B_IMG_EXTERNAL, TEN_COIN_B_IMG, "ten coin image file 2");
    }

    /**
     * Checks for the file containing the image of the tool city which is necessary for the graphics of
     * the game in terms of rendering the correct city. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyToolCityImg() {
        Path file = Path.of(TOOL_CITY_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Tool city image file already exists");
            return;
        }
        downloadFile(TOOL_CITY_IMG_EXTERNAL, TOOL_CITY_IMG, "tool city image file");
    }

    /**
     * Checks for the file containing the image of the tool tile which is necessary for the graphics of
     * the game in terms of rendering the correct tile. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyToolTileImg() {
        Path file = Path.of(TOOL_TILE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Tool tile image file already exists");
            return;
        }
        downloadFile(TOOL_TILE_IMG_EXTERNAL, TOOL_TILE_IMG, "tool tile image file");
    }

    /**
     * Checks for the file containing the first image of the two coin which is necessary for the graphics of
     * the game in terms of rendering the correct value coin. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyTwoCoinAImg() {
        Path file = Path.of(TWO_COIN_A_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Two coin image file already exists");
            return;
        }
        downloadFile(TWO_COIN_A_IMG_EXTERNAL, TWO_COIN_A_IMG, "two coin image file");
    }

    /**
     * Checks for the file containing the second image of the two coin which is necessary for the graphics of
     * the game in terms of rendering the correct value coin. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyTwoCoinBImg() {
        Path file = Path.of(TWO_COIN_B_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Two coin image file 2 already exists");
            return;
        }
        downloadFile(TWO_COIN_B_IMG_EXTERNAL, TWO_COIN_B_IMG, "two coin image file 2");
    }

    /**
     * Checks for the file containing the wine city which is necessary for the graphics of the game in terms
     * of rendering the correct city. If the file is not found locally, it will be downloaded from the internet
     * using the {@code downloadFile} method.
     */
    public static void verifyWineCityImg() {
        Path file = Path.of(WINE_CITY_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Wine city image file already exists");
            return;
        }
        downloadFile(WINE_CITY_IMG_EXTERNAL, WINE_CITY_IMG, "wine city image file");
    }

    /**
     * Checks for the file containing the wine tile which is necessary for the graphics of the game in terms
     * of rendering the correct tile. If the file is not found locally, it will be downloaded from the internet
     * using the {@code downloadFile} method.
     */
    public static void verifyWineTileImg() {
        Path file = Path.of(WINE_TILE_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Wine tile image file already exists");
            return;
        }
        downloadFile(WINE_TILE_IMG_EXTERNAL, WINE_TILE_IMG, "wine tile image file");
    }

    /**
     * Checks for the file containing the image of the architect personality card to be bought by the player
     * which is necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyArchitectBoughtPersonalityCardImg() {
        Path file = Path.of(ARCHITECT_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Architect personality card image file already exists");
            return;
        }
        downloadFile(ARCHITECT_BOUGHT_PCARD_IMG_EXTERNAL, ARCHITECT_BOUGHT_PCARD_IMG, "architect personality card image file");
    }

    /**
     * Checks for the file containing the image of the back of the personality cards in the first set
     * which is necessary for the graphics of the game in terms of rendering the correct card back.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPersonalityCardBackIImg() {
        Path file = Path.of(BACK_OF_PCARD_I_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "First set personality card back image file already exists");
            return;
        }
        downloadFile(BACK_OF_PCARD_I_IMG_EXTERNAL, BACK_OF_PCARD_I_IMG, "first set personality card back image file");
    }

    /**
     * Checks for the file containing the image of the back of the personality cards in the second set
     * which is necessary for the graphics of the game in terms of rendering the correct card back.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPersonalityCardBackIIImg() {
        Path file = Path.of(BACK_OF_PCARD_II_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Second set personality card back image file already exists");
            return;
        }
        downloadFile(BACK_OF_PCARD_II_IMG_EXTERNAL, BACK_OF_PCARD_II_IMG, "second set personality card back image file");
    }

    /**
     * Checks for the file containing the image of the back of the personality cards in the third set
     * which is necessary for the graphics of the game in terms of rendering the correct card back.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPersonalityCardBackIIIImg() {
        Path file = Path.of(BACK_OF_PCARD_III_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Third set personality card back image file already exists");
            return;
        }
        downloadFile(BACK_OF_PCARD_III_IMG_EXTERNAL, BACK_OF_PCARD_III_IMG, "third set personality card back image file");
    }

    /**
     * Checks for the file containing the image of the back of the personality cards in the fourth set
     * which is necessary for the graphics of the game in terms of rendering the correct card back.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPersonalityCardBackIVImg() {
        Path file = Path.of(BACK_OF_PCARD_IV_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Fourth set personality card back image file already exists");
            return;
        }
        downloadFile(BACK_OF_PCARD_IV_IMG_EXTERNAL, BACK_OF_PCARD_IV_IMG, "fourth set personality card back image file");
    }

    /**
     * Checks for the file containing the image of the back of the personality cards in the fifth set
     * which is necessary for the graphics of the game in terms of rendering the correct card back.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPersonalityCardBackVImg() {
        Path file = Path.of(BACK_OF_PCARD_V_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Fifth set personality card back image file already exists");
            return;
        }
        downloadFile(BACK_OF_PCARD_V_IMG_EXTERNAL, BACK_OF_PCARD_V_IMG, "fifth set personality card back image file");
    }

    /**
     * Checks for the file containing the image of the colonist personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyColonistBoughtPersonalityCardImg() {
        Path file = Path.of(COLONIST_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Colonist personality card image file already exists");
            return;
        }
        downloadFile(COLONIST_BOUGHT_PCARD_IMG_EXTERNAL, COLONIST_BOUGHT_PCARD_IMG, "colonist personality card image file");
    }

    /**
     * Checks for the diplomat personality card to be bought which is necessary for the graphics of the game
     * in terms of rendering the correct card to be bought. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyDiplomatBoughtPersonalityCardImg() {
        Path file = Path.of(DIPLOMAT_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Diplomat personality card image file already exists");
            return;
        }
        downloadFile(DIPLOMAT_BOUGHT_PCARD_IMG_EXTERNAL, DIPLOMAT_BOUGHT_PCARD_IMG, "diplomat personality card image file");
    }

    /**
     * Checks for the farmer personality card to be bought which is necessary for the graphics of the game
     * in terms of rendering the correct card to be bought. If the file is not found locally, it will be
     * downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyFarmerBoughtPersonalityCardImg() {
        Path file = Path.of(FARMER_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Farmer personality card image file already exists");
            return;
        }
        downloadFile(FARMER_BOUGHT_PCARD_IMG_EXTERNAL, FARMER_BOUGHT_PCARD_IMG, "farmer personality card image file");
    }

    /**
     * Checks for the file containing the image of the mason personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyMasonBoughtPersonalityCardImg() {
        Path file = Path.of(MASON_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Mason personality card image file already exists");
            return;
        }
        downloadFile(MASON_BOUGHT_PCARD_IMG_EXTERNAL, MASON_BOUGHT_PCARD_IMG, "mason personality card image file");
    }

    /**
     * Checks for the file containing the image of the mercator personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyMercatorBoughtPersonalityCardImg() {
        Path file = Path.of(MERCATOR_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Mercator personality card image file already exists");
            return;
        }
        downloadFile(MERCATOR_BOUGHT_PCARD_IMG_EXTERNAL, MERCATOR_BOUGHT_PCARD_IMG, "mercator personality card image file");
    }

    /**
     * Checks for the file containing the image of the prefect personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyPrefectBoughtPersonalityCardImg() {
        Path file = Path.of(PREFECT_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Prefect personality card image file already exists");
            return;
        }
        downloadFile(PREFECT_BOUGHT_PCARD_IMG_EXTERNAL, PREFECT_BOUGHT_PCARD_IMG, "prefect personality card image file");
    }

    /**
     * Checks for the file containing the image of the smith personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifySmithBoughtPersonalityCardImg() {
        Path file = Path.of(SMITH_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Smith personality card image file already exists");
            return;
        }
        downloadFile(SMITH_BOUGHT_PCARD_IMG_EXTERNAL, SMITH_BOUGHT_PCARD_IMG, "smith personality card image file");
    }

    /**
     * Checks for the file containing the image of the vinter personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyVinterBoughtPersonalityCardImg() {
        Path file = Path.of(VINTER_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Vinter personality card image file already exists");
            return;
        }
        downloadFile(VINTER_BOUGHT_PCARD_IMG_EXTERNAL, VINTER_BOUGHT_PCARD_IMG, "vinter personality card image file");
    }

    /**
     * Checks for the file containing the image of the weaver personality card to be bought which is
     * necessary for the graphics of the game in terms of rendering the correct card to be bought.
     * If the file is not found locally, it will be downloaded from the internet using the {@code downloadFile} method.
     */
    public static void verifyWeaverBoughtPersonalityCardImg() {
        Path file = Path.of(WEAVER_BOUGHT_PCARD_IMG);
        if (Files.exists(file)) {
            Logger.info("IOUtils", "Weaver personality card image file already exists");
            return;
        }
        downloadFile(WEAVER_BOUGHT_PCARD_IMG_EXTERNAL, WEAVER_BOUGHT_PCARD_IMG, "weaver personality card image file");
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
    public static void verifyGameFiles() {
        verifyUnfinishedGamesDirectory();
        verifyPreBuiltMapFilesDirectory();
        verifyLoginInformationDirectory();
        verifyPlayerPerformanceInformationDirectory();
        verifySpritesDirectory();
        verifyMainGraphicsDir();
        verifyStartingCardsDir();
        verifyGamePiecesDir();
        verifyBoughtPersonalityCardsDir();
        verifyPreBuiltImperiumNetworkFile();
        verifyPreBuiltImperiumCitiesFile();
        verifyConcordiaBoardImg();
        verifyBlackStoreHouseImg();
        verifyBlueStoreHouseImg();
        verifyConcordiaCardImg();
        verifyConcordiaCardImgBack();
        verifyGreenStoreHouseImg();
        verifyPraefectusMagnusCardImg();
        verifyPraefectusMagnusCardImgBack();
        verifyRedStoreHouseImg();
        verifyReferenceCardAImg();
        verifyReferenceCardBImg();
        verifyYellowStoreHouseImg();
        verifyArchitectPersonalityCardImg();
        verifyBlackBackOfPersonalityCardsImg();
        verifyBlueBackOfPersonalityCardsImg();
        verifyDiplomatPersonalityCardImg();
        verifyGreenBackOfPersonalityCardsImg();
        verifyMercatorPersonalityCardImg();
        verifyPrefectPersonalityCardImg();
        verifyRedBackOfPersonalityCardsImg();
        verifySenatorPersonalityCardImg();
        verifyTribunePersonalityCardImg();
        verifyYellowBackOfPersonalityCardsImg();
        verifyBrickCityImg();
        verifyBrickTileImg();
        verifyClothCityImg();
        verifyClothTileImg();
        verifyCoinImg1();
        verifyCoinImg2();
        verifyFiveCoinAImg();
        verifyFiveCoinBImg();
        verifyFoodCityImg();
        verifyFoodTileImg();
        verifyOneCoinAImg();
        verifyOneCoinBImg();
        verifyTenCoinAImg();
        verifyTenCoinBImg();
        verifyToolCityImg();
        verifyToolTileImg();
        verifyTwoCoinAImg();
        verifyTwoCoinBImg();
        verifyWineCityImg();
        verifyWineTileImg();
        verifyArchitectBoughtPersonalityCardImg();
        verifyPersonalityCardBackIImg();
        verifyPersonalityCardBackIIImg();
        verifyPersonalityCardBackIIIImg();
        verifyPersonalityCardBackIVImg();
        verifyPersonalityCardBackVImg();
        verifyColonistBoughtPersonalityCardImg();
        verifyDiplomatBoughtPersonalityCardImg();
        verifyFarmerBoughtPersonalityCardImg();
        verifyMasonBoughtPersonalityCardImg();
        verifyMercatorBoughtPersonalityCardImg();
        verifyPrefectBoughtPersonalityCardImg();
        verifySmithBoughtPersonalityCardImg();
        verifyVinterBoughtPersonalityCardImg();
        verifyWeaverBoughtPersonalityCardImg();

        shutdownExecutorService();
    }
}
