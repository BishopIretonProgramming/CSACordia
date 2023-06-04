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
import java.net.HttpURLConnection;

import java.util.concurrent.*;

import src.game.Game;

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
    private IOUtils() {
    }

    /**
     * The maximum number of concurrent downloads represented by an
     * {@code ExecutorService}
     */
    private static final ExecutorService executorService = Executors.newFixedThreadPool(20);

    /**
     * The fields and constants that will be used throughout this class,
     * most of the fields represent a file or directory that should be
     * present with the game for saving files or reading files associated
     * with the game.
     *
     * Other fields may include links to where the files can be downloaded
     * from in case necessary files for launching the game are not present
     * and must be acquired from the internet.
     */
    public static final String // 
    UNFINISHED_GAME_SAVE_DIR = String.format("resources%csaves%cunfinished_games", separatorChar, separatorChar), // 
    PRE_BUILT_MAP_DIR = String.format("resources%csaves%cpre_built_maps", separatorChar, separatorChar), // 
    LOGIN_INFORMATION_DIR = String.format("resources%clog_in%cuap", separatorChar, separatorChar), // 
    PLAYER_PERFORMANCE_INFORMATION_DIR = String.format("resources%cplayers%cperformance", separatorChar, separatorChar), // 
    SPRITE_IMAGES_DIR = String.format("resources%csprites", separatorChar), // 
    MAIN_GRAPHICS_IMAGES_DIR = String.format("src%cgui%cimages", separatorChar, separatorChar), // 
    STARTING_CARDS_IMAGES_DIR = String.format("src%cgui%cimages%cstartingcards", separatorChar, separatorChar, separatorChar), // 
    GAME_PIECES_IMAGES_DIR = String.format("src%cgui%cimages%cpieces", separatorChar, separatorChar, separatorChar), // 
    BOUGHT_PCARDS_IMAGES_DIR = String.format("src%cgui%cimages%cboughtcards", separatorChar, separatorChar, separatorChar), // 
    IMPERIUM_PREBUILT_NETWORK_FILE = String.format("resources%cdefault%cmaps%cimperium.nw", separatorChar, separatorChar, separatorChar), // 
    IMPERIUM_PREBUILT_NETWORK_FILE_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/resources/saves/pre_built_maps/imperium.nw", // 
    IMPERIUM_PREBUILT_CITIES_FILE = String.format("resources%cdefault%cmaps%cimperium_cities.cnw", separatorChar, separatorChar, separatorChar), // 
    IMPERIUM_PREBUILT_CITIES_FILE_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/resources/saves/pre_built_maps/imperium_cities.cnw", // 
    CONCORDIA_BOARD_IMG = String.format("src%cgui%cimages%cConcordia board.jpg", separatorChar, separatorChar, separatorChar), // 
    CONCORDIA_BOARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/Concordia%20board.jpg", // 
    BLACK_STOREHOUSE_IMG = String.format("src%cgui%cimages%cblackStorehouse.png", separatorChar, separatorChar, separatorChar), // 
    BLACK_STOREHOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/blackStorehouse.png", // 
    BLUE_STOREHOUSE_IMG = String.format("src%cgui%cimages%cblueStorehouse.png", separatorChar, separatorChar, separatorChar), // 
    BLUE_STOREHOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/blueStorehouse.png", // 
    CONCORDIA_CARD_IMG = String.format("src%cgui%cimages%cconcordia.png", separatorChar, separatorChar, separatorChar), // 
    CONCORDIA_CARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/concordia.png", // 
    CONCORDIA_CARD_BACK_IMG = String.format("src%cgui%cimages%cconcordiaBack.png", separatorChar, separatorChar, separatorChar), // 
    CONCORDIA_CARD_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/concordiaBack.png", // 
    GREEN_STOREHOUSE_IMG = String.format("src%cgui%cimages%cgreenStorehouse.png", separatorChar, separatorChar, separatorChar), // 
    GREEN_STOREHOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/greenStorehouse.png", // 
    PRAEFECTUS_MAGNUS_CARD_IMG = String.format("src%cgui%cimages%cpraefectusMagnus.png", separatorChar, separatorChar, separatorChar), // 
    PRAEFECTUS_MAGNUS_CARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/praefectusMagnus.png", // 
    PRAEFECTUS_MAGNUS_CARD_BACK_IMG = String.format("src%cgui%cimages%cpraefectusMagnusBack.png", separatorChar, separatorChar, separatorChar), // 
    PRAEFECTUS_MAGNUS_CARD_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/praefectusMagnusBack.png", // 
    RED_STOREHOUSE_IMG = String.format("src%cgui%cimages%credStorehouse.png", separatorChar, separatorChar, separatorChar), // 
    RED_STOREHOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/redStorehouse.png", // 
    REFERENCE_CARD_A_IMG = String.format("src%cgui%cimages%creferenceCardA.png", separatorChar, separatorChar, separatorChar), // 
    REFERENCE_CARD_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/referenceCardA.png", // 
    REFERENCE_CARD_B_IMG = String.format("src%cgui%cimages%creferenceCardB.png", separatorChar, separatorChar, separatorChar), // 
    REFERENCE_CARD_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/referenceCardB.png", // 
    YELLOW_STOREHOUSE_IMG = String.format("src%cgui%cimages%cyellowStorehouse.png", separatorChar, separatorChar, separatorChar), // 
    YELLOW_STOREHOUSE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/yellowStorehouse.png", // 
    ARCHITECT_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%carchitect.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    ARCHITECT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/architect.png", // 
    PCARD_BLACK_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cblackBack.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PCARD_BLACK_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/blackBack.png", // 
    PCARD_BLUE_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cblueBack.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PCARD_BLUE_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/blueBack.png", // 
    DIPLOMAT_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%cdiplomat.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    DIPLOMAT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/diplomat.png", // 
    PCARD_GREEN_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cgreenBack.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PCARD_GREEN_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/greenBack.png", // 
    MERCATOR_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%cmercator.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    MERCATOR_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/mercator.png", // 
    PREFECT_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%cprefect.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PREFECT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/prefect.png", // 
    PCARD_RED_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%credBack.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PCARD_RED_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/redBack.png", // 
    SENATOR_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%csenator.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    SENATOR_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/senator.png", // 
    TRIBUNE_PCARD_IMG = String.format("src%cgui%cimages%cstartingcards%ctribune.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TRIBUNE_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/tribune.png", // 
    PCARD_YELLOW_BACK_IMG = String.format("src%cgui%cimages%cstartingcards%cyellowBack.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PCARD_YELLOW_BACK_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/startingcards/yellowBack.png", // 
    BRICK_CITY_IMG = String.format("src%cgui%cimages%cpieces%cbrickCity.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BRICK_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/brickCity.png", // 
    BRICK_TILE_IMG = String.format("src%cgui%cimages%cpieces%cbrickTile.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BRICK_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/brickTile.png", // 
    CLOTH_CITY_IMG = String.format("src%cgui%cimages%cpieces%cclothCity.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    CLOTH_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/clothCity.png", // 
    CLOTH_TILE_IMG = String.format("src%cgui%cimages%cpieces%cclothTile.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    CLOTH_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/clothTile.png", // 
    COIN_TILE_IMG_1 = String.format("src%cgui%cimages%cpieces%ccoinTile1.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    COIN_TILE_IMG_1_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/coinTile1.png", // 
    COIN_TILE_IMG_2 = String.format("src%cgui%cimages%cpieces%ccoinTile2.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    COIN_TILE_IMG_2_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/coinTile2.png", // 
    FIVE_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%cfiveCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    FIVE_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/fiveCoinA.png", // 
    FIVE_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%cfiveCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    FIVE_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/fiveCoinB.png", // 
    FOOD_CITY_IMG = String.format("src%cgui%cimages%cpieces%cfoodCity.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    FOOD_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/foodCity.png", // 
    FOOD_TILE_IMG = String.format("src%cgui%cimages%cpieces%cfoodTile.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    FOOD_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/foodTile.png", // 
    ONE_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%coneCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    ONE_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/oneCoinA.png", // 
    ONE_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%coneCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    ONE_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/oneCoinB.png", // 
    TEN_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%ctenCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TEN_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/tenCoinA.png", // 
    TEN_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%ctenCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TEN_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/tenCoinB.png", // 
    TOOL_CITY_IMG = String.format("src%cgui%cimages%cpieces%ctoolCity.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TOOL_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/toolCity.png", // 
    TOOL_TILE_IMG = String.format("src%cgui%cimages%cpieces%ctoolTile.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TOOL_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/toolTile.png", // 
    TWO_COIN_A_IMG = String.format("src%cgui%cimages%cpieces%ctwoCoinA.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TWO_COIN_A_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/twoCoinA.png", // 
    TWO_COIN_B_IMG = String.format("src%cgui%cimages%cpieces%ctwoCoinB.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    TWO_COIN_B_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/twoCoinB.png", // 
    WINE_CITY_IMG = String.format("src%cgui%cimages%cpieces%cwineCity.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    WINE_CITY_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/wineCity.png", // 
    WINE_TILE_IMG = String.format("src%cgui%cimages%cpieces%cwineTile.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    WINE_TILE_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/pieces/wineTile.png", // 
    ARCHITECT_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%carchitect.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    ARCHITECT_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/architect.png", // 
    BACK_OF_PCARD_I_IMG = String.format("src%cgui%cimages%cboughtcards%cbackI.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BACK_OF_PCARD_I_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backI.png", // 
    BACK_OF_PCARD_II_IMG = String.format("src%cgui%cimages%cboughtcards%cbackII.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BACK_OF_PCARD_II_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backII.png", // 
    BACK_OF_PCARD_III_IMG = String.format("src%cgui%cimages%cboughtcards%cbackIII.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BACK_OF_PCARD_III_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backIII.png", // 
    BACK_OF_PCARD_IV_IMG = String.format("src%cgui%cimages%cboughtcards%cbackIV.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BACK_OF_PCARD_IV_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backIV.png", // 
    BACK_OF_PCARD_V_IMG = String.format("src%cgui%cimages%cboughtcards%cbackV.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    BACK_OF_PCARD_V_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/backV.png", // 
    COLONIST_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%ccolonist.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    COLONIST_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/colonist.png", // 
    DIPLOMAT_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cdiplomat.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    DIPLOMAT_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/diplomat.png", // 
    FARMER_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cfarmer.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    FARMER_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/farmer.png", // 
    MASON_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cmason.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    MASON_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/mason.png", // 
    MERCATOR_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cmercator.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    MERCATOR_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/mercator.png", // 
    PREFECT_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cprefect.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    PREFECT_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/prefect.png", // 
    SMITH_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%csmith.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    SMITH_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/smith.png", // 
    VINTER_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cvinter.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    VINTER_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/vinter.png", // 
    WEAVER_BOUGHT_PCARD_IMG = String.format("src%cgui%cimages%cboughtcards%cweaver.png", separatorChar, separatorChar, separatorChar, separatorChar), // 
    WEAVER_BOUGHT_PCARD_IMG_EXTERNAL = "https://raw.githubusercontent.com/Flambrew/CSACordia/main/src/gui/images/boughtcards/weaver.png";

    public enum FileData {
        IMPERIUM_NETWORK("pre-built imperium network file", IMPERIUM_PREBUILT_NETWORK_FILE_EXTERNAL, IMPERIUM_PREBUILT_NETWORK_FILE),
        IMPERIUM_CITIES("pre-built imperium cities file", IMPERIUM_PREBUILT_CITIES_FILE_EXTERNAL, IMPERIUM_PREBUILT_CITIES_FILE),
        CONCORDIA_BOARD("concordia board", CONCORDIA_BOARD_IMG_EXTERNAL, CONCORDIA_BOARD_IMG),
        CONCORDIA_CARD_FRONT("concordia card", CONCORDIA_CARD_IMG_EXTERNAL, CONCORDIA_CARD_IMG),
        CONCORDIA_CARD_BACK("back of concordia card", CONCORDIA_CARD_BACK_IMG_EXTERNAL, CONCORDIA_CARD_BACK_IMG),
        PRAEFECTUS_MAGNUS_FRONT("praefectus magnus card", PRAEFECTUS_MAGNUS_CARD_IMG_EXTERNAL, PRAEFECTUS_MAGNUS_CARD_IMG),
        PRAEFECTUS_MAGNUS_BACK("back of praefectus magnus card", PRAEFECTUS_MAGNUS_CARD_BACK_IMG_EXTERNAL, PRAEFECTUS_MAGNUS_CARD_BACK_IMG),
        REFERENCE_CARD_A("first reference card", REFERENCE_CARD_A_IMG_EXTERNAL, REFERENCE_CARD_A_IMG),
        REFERENCE_CARD_B("second reference card", REFERENCE_CARD_B_IMG_EXTERNAL, REFERENCE_CARD_B_IMG),
        STOREHOUSE_BLACK("black store house", BLACK_STOREHOUSE_IMG_EXTERNAL, BLACK_STOREHOUSE_IMG),
        STOREHOUSE_GREEN("green store house", GREEN_STOREHOUSE_IMG_EXTERNAL, GREEN_STOREHOUSE_IMG),
        STOREHOUSE_YELLOW("yellow store house", YELLOW_STOREHOUSE_IMG_EXTERNAL, YELLOW_STOREHOUSE_IMG),
        STOREHOUSE_RED("red store house", RED_STOREHOUSE_IMG_EXTERNAL, RED_STOREHOUSE_IMG),
        STOREHOUSE_BLUE("blue store house", BLUE_STOREHOUSE_IMG_EXTERNAL, BLUE_STOREHOUSE_IMG),
        PCARD_BACK_BLACK("black back of personality cards", PCARD_BLACK_BACK_IMG_EXTERNAL, PCARD_BLACK_BACK_IMG),
        PCARD_BACK_GREEN("green back of personality cards", PCARD_GREEN_BACK_IMG_EXTERNAL, PCARD_GREEN_BACK_IMG),
        PCARD_BACK_RED("red back of personality cards", PCARD_RED_BACK_IMG_EXTERNAL, PCARD_RED_BACK_IMG),
        PCARD_BACK_YELLOW("yellow back of personality cards", PCARD_YELLOW_BACK_IMG_EXTERNAL, PCARD_YELLOW_BACK_IMG),
        PCARD_BACK_BLUE("blue back of personality cards", PCARD_BLUE_BACK_IMG_EXTERNAL, PCARD_BLUE_BACK_IMG),
        ARCHITECT_PCARD("architect personality card", ARCHITECT_PCARD_IMG_EXTERNAL, ARCHITECT_PCARD_IMG),
        DIPLOMAT_PCARD("diplomat personality card", DIPLOMAT_PCARD_IMG_EXTERNAL, DIPLOMAT_PCARD_IMG),
        MERCATOR_PCARD("mercator personality card", MERCATOR_PCARD_IMG_EXTERNAL, MERCATOR_PCARD_IMG),
        PREFECT_PCARD("prefect personality card", PREFECT_PCARD_IMG_EXTERNAL, PREFECT_PCARD_IMG),
        SENATOR_PCARD("senator personality card", SENATOR_PCARD_IMG_EXTERNAL, SENATOR_PCARD_IMG),
        TRIBUNE_PCARD("tribune personality card", TRIBUNE_PCARD_IMG_EXTERNAL, TRIBUNE_PCARD_IMG),
        COIN_1("coin 1", COIN_TILE_IMG_1_EXTERNAL, COIN_TILE_IMG_1),
        COIN_2("coin 2", COIN_TILE_IMG_2_EXTERNAL, COIN_TILE_IMG_2),
        ONE_COIN_A("one coin 1", ONE_COIN_A_IMG_EXTERNAL, ONE_COIN_A_IMG),
        ONE_COIN_B("one coin 2", ONE_COIN_B_IMG_EXTERNAL, ONE_COIN_B_IMG),
        TWO_COIN_A("two coin 1", TWO_COIN_A_IMG_EXTERNAL, TWO_COIN_A_IMG),
        TWO_COIN_B("two coin 2", TWO_COIN_B_IMG_EXTERNAL, TWO_COIN_B_IMG),
        FIVE_COIN_A("five coin 1", FIVE_COIN_A_IMG_EXTERNAL, FIVE_COIN_A_IMG),
        FIVE_COIN_B("five coin 2", FIVE_COIN_B_IMG_EXTERNAL, FIVE_COIN_B_IMG),
        TEN_COIN_A("ten coin 1", TEN_COIN_A_IMG_EXTERNAL, TEN_COIN_A_IMG),
        TEN_COIN_B("ten coin 2", TEN_COIN_B_IMG_EXTERNAL, TEN_COIN_B_IMG),
        FOOD_CITY("food city", FOOD_CITY_IMG_EXTERNAL, FOOD_CITY_IMG),
        FOOD_TILE("food tile", FOOD_TILE_IMG_EXTERNAL, FOOD_TILE_IMG),
        TOOL_CITY("tool city", TOOL_CITY_IMG_EXTERNAL, TOOL_CITY_IMG),
        TOOL_TILE("tool tile", TOOL_TILE_IMG_EXTERNAL, TOOL_TILE_IMG),
        WINE_CITY("wine city", WINE_CITY_IMG_EXTERNAL, WINE_CITY_IMG),
        WINE_TILE("wine tile", WINE_TILE_IMG_EXTERNAL, WINE_TILE_IMG),
        BRICK_CITY("brick city", BRICK_CITY_IMG_EXTERNAL, BRICK_CITY_IMG),
        BRICK_TILE("brick tile", BRICK_TILE_IMG_EXTERNAL, BRICK_TILE_IMG),
        CLOTH_CITY("cloth city", CLOTH_CITY_IMG_EXTERNAL, CLOTH_CITY_IMG),
        CLOTH_TILE("cloth tile", CLOTH_TILE_IMG_EXTERNAL, CLOTH_TILE_IMG),
        PCARD_BACK_I("first set personality card back", BACK_OF_PCARD_I_IMG_EXTERNAL, BACK_OF_PCARD_I_IMG),
        PCARD_BACK_II("second set personality card back", BACK_OF_PCARD_II_IMG_EXTERNAL, BACK_OF_PCARD_II_IMG),
        PCARD_BACK_III("third set personality card back", BACK_OF_PCARD_III_IMG_EXTERNAL, BACK_OF_PCARD_III_IMG),
        PCARD_BACK_IV("fourth set personality card back", BACK_OF_PCARD_IV_IMG_EXTERNAL, BACK_OF_PCARD_IV_IMG),
        PCARD_BACK_V("fifth set personality card back", BACK_OF_PCARD_V_IMG_EXTERNAL, BACK_OF_PCARD_V_IMG),
        ARCHITECT_BOUGHT_PCARD("architect personality card", ARCHITECT_BOUGHT_PCARD_IMG_EXTERNAL, ARCHITECT_BOUGHT_PCARD_IMG),
        COLONIST_BOUGHT_PCARD("colonist personality card", COLONIST_BOUGHT_PCARD_IMG_EXTERNAL, COLONIST_BOUGHT_PCARD_IMG),
        DIPLOMAT_BOUGHT_PCARD("diplomat personality card", DIPLOMAT_BOUGHT_PCARD_IMG_EXTERNAL, DIPLOMAT_BOUGHT_PCARD_IMG),
        FARMER_BOUGHT_PCARD("farmer personality card", FARMER_BOUGHT_PCARD_IMG_EXTERNAL, FARMER_BOUGHT_PCARD_IMG),
        MASON_BOUGHT_PCARD("mason personality card", MASON_BOUGHT_PCARD_IMG_EXTERNAL, MASON_BOUGHT_PCARD_IMG),
        MERCATOR_BOUGHT_PCARD("mercator personality card", MERCATOR_BOUGHT_PCARD_IMG_EXTERNAL, MERCATOR_BOUGHT_PCARD_IMG),
        PREFECT_BOUGHT_PCARD("Prefect personality card", PREFECT_BOUGHT_PCARD_IMG_EXTERNAL, PREFECT_BOUGHT_PCARD_IMG),
        SMITH_BOUGHT_PCARD("Smith personality card", SMITH_BOUGHT_PCARD_IMG_EXTERNAL, SMITH_BOUGHT_PCARD_IMG),
        VINTNER_BOUGHT_PCARD("Vintner personality card", VINTER_BOUGHT_PCARD_IMG_EXTERNAL, VINTER_BOUGHT_PCARD_IMG),
        WEAVER_BOUGHT_PCARD("Weaver personality card", WEAVER_BOUGHT_PCARD_IMG_EXTERNAL, WEAVER_BOUGHT_PCARD_IMG);

        private String name, link, path;

        FileData(String name, String link, String path) {
            this.name = name;
            this.link = link;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public String getPath() {
            return path;
        }
    }

    public enum DirectoryData {
        UNFINISHED_GAMES_DIR("unfinished games", UNFINISHED_GAME_SAVE_DIR),
        MAP_FILES_DIR("pre-built map files", PRE_BUILT_MAP_DIR),
        LOGIN_INFO_DIR("login information", LOGIN_INFORMATION_DIR),
        PLAYER_PERFORMANCE_INFO_DIR("player performance imformation", PLAYER_PERFORMANCE_INFORMATION_DIR),
        SPRITES_DIR("sprites", SPRITE_IMAGES_DIR),
        MAIN_GRAPHICS_DIR("main graphics images", MAIN_GRAPHICS_IMAGES_DIR),
        STARTING_CARDS_DIR("starting cards images", STARTING_CARDS_IMAGES_DIR),
        GAME_PIECES_DIR("game pieces images", GAME_PIECES_IMAGES_DIR),
        BOUGHT_PCARDS_DIR("bought personality cards images", BOUGHT_PCARDS_IMAGES_DIR);

        private final String name, path;

        DirectoryData(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }
    }

    // TODO Bob write a javadoc
    public static void verifyFile(FileData file) {
        if (Files.exists(Path.of(file.getPath())))
            Logger.info("IOUtils", file.getName() + " already exists");
        else
            downloadFile(file.getLink(), file.getPath(), file.getName());
    }

    /**
     * Method to create a directory if it does not exist. This method will
     * check to see if the directory exists and if it does not, it will
     * create the directory. This method will be used in all the methods
     * that check to ensure the existence of the necessary game file directories
     * to reduce the amount of repeated code and to improve readability in the
     * entirety of the IOUtils class.
     *
     * @param directory     The directory to check for and create if it does not
     *                      exist
     */
    public static void ensureDirExistence(DirectoryData directory) {
        if (Files.exists(Paths.get(directory.getPath()))) {
            Logger.info("IOUtils", "The " + directory.getName() + " directory already exists");
        } else {
            try {
                Files.createDirectories(Paths.get(directory.getPath()));
                Logger.info("IOUtils", "Successfully created the " + directory.getName() + " directory");
            } catch (IOException e) {
                Logger.error("IOUtils",
                        "Could not create the " + directory.getName() + " directory: " + e.getMessage());
            }
        }
    }

    /**
     * Method to check if internet is available to start a download. This method
     * will be used
     * by the {@code downloadFile} method to check if internet is available before
     * starting a
     * download.
     *
     * @return {@code true} if internet is available, {@code false} otherwise
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
     * Method to download a file from the internet if it is not found locally. This
     * method will
     * be used by each of the individual methods that check for the existence of the
     * necessary
     * game files to reduce the amount of repeated code and to improve readability
     * in the entirety
     * of the IOUtils class. This method uses asynchronous I/O along with concurrent
     * downloads to
     * download the fast.
     *
     * @param downloadLink    the link to the file that should be downloaded
     * @param destinationPath the path to where the file should be stored
     * @param fileName        the name of the file
     */
    private static void downloadFile(String downloadLink, String destinationPath, String fileName) {
        if (!isInternetAvailable()) {
            Logger.error("IOUtils", "Internet is not available. Cannot download the " + fileName);
            return;
        }
        try {
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
                            Logger.error("IOUtils", "Error occurred while closing the file channel for " + fileName
                                    + ": " + e.getMessage());
                        }
                    } else {
                        buffer.flip();
                        fileChannel.write(buffer, 0, null, this);
                        buffer.clear();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    Logger.error("IOUtils",
                            "Error occurred while downloading the " + fileName + ": " + exc.getMessage());
                }
            };

            Callable<Boolean> downloadTask = () -> {
                try {
                    fileChannel.write(buffer, 0, null, completionHandler);
                    return true;
                } catch (Exception e) {
                    Logger.error("IOUtils",
                            "Error occurred while initiating the download for " + fileName + ": " + e.getMessage());
                    return false;
                }
            };

            Future<Boolean> future = executorService.submit(downloadTask);
            future.get();

            Logger.info("IOUtils", "Successfully downloaded the " + fileName);
        } catch (IOException | InterruptedException | ExecutionException e) {
            Logger.error("IOUtils", "Error occurred while downloading the " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Method to shut down the {@code ExecutorService} that is used to download the
     * necessary
     * game files concurrently. This will ensure that the program will not continue
     * to run after
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
     * Method to check for all the necessary files and directories to play the game
     * and either creates them or downloads them depending on the occasion. This is
     * the main method that should be called on game startup to ensure that the game
     * does not experience any errors in creation and initialization. While the
     * client is able to call the other methods individually, it is not recommended
     * as this method will be constantly updated to ensure that it creates or
     * downloads all necessary files and directories for the game.
     */
    public static void verifyGameFiles() {
        for (DirectoryData data : DirectoryData.values())
            ensureDirExistence(data);
        for (FileData data : FileData.values())
            verifyFile(data);
        shutdownExecutorService();
    }

    /**
     * Method to begin synchronously saving the current game state.
     *
     * @param game the {@code Game} to save.
     * @param path the path to where the {@code Game} should be saved.
     */
    public static void synchronousSave(Game game, String path) {
        SynchronousSaver saver = new SynchronousSaver(game, path);
    }

    private static class SynchronousSaver extends Thread {

        /**
         * The {@code Game} to save.
         */
        private final Game GAME;

        /**
         * The path to where the {@code Game} should be saved.
         */
        private String path;

        /**
         * The lock object to be used with synchronization
         */
        private final Object LOCK;

        /**
         * Whether the thread is running and should keep saving the {@code Game}.
         */
        private boolean running;

        /**
         * Constructor to make a new SynchronousSaver
         *
         * @param GAME the {@code Game} to save.
         * @param path where the {@code Game} should be saved.
         */
        protected SynchronousSaver(Game GAME, String path) {
            this.GAME = GAME;
            this.path = path;
            this.running = true;
            LOCK = new Object();
        }

        /**
         * Method to stop the thread and thus stop saving the {@code Game}.
         */
        protected void stopSaving() {
            this.running = false;
        }

        /**
         * Method to run the thread and save the {@code Game} every 30 seconds.
         */
        @Override
        public void run() {
            while (running) {
                synchronized (LOCK) {
                    //  GAME.save(path);  //  TODO: finish the game implementation after graphics and add save method
                    //  we could also do this: IOUtils.saveGame(GAME, path);
                    try {
                        LOCK.wait(30000);
                    } catch (InterruptedException e) {
                        Logger.error("IOUtils.SynchronousSaver", String.format("Error saving game to %s: %s", path, e.getMessage()));
                    }
                }
            }
        }

        /**
         * Getter method to return if this SynchronousSaver has been stopped
         */
        public boolean isStopped() {
            return !running;
        }
    }
}