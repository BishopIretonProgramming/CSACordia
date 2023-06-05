package src.gui;

/*
 * @Author: Joseph Murray
 * @Author: Rory
 * Created: 6/4/2023
 * PieceImage: enum representing the associated image for each game piece
*/

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public enum PieceImage {
    
    COIN_1A("pieces" + File.separator + "oneCoinA.png"),
    COIN_1B("pieces" + File.separator + "oneCoinB.png"),
    COIN_2A("pieces" + File.separator + "twoCoinA.png"),
    COIN_2B("pieces" + File.separator + "twoCoinB.png"),
    COIN_5A("pieces" + File.separator + "fiveCoinA.png"),
    COIN_5B("pieces" + File.separator + "fiveCoinB.png"),
    COIN_10A("pieces" + File.separator + "tenCoinA.png"),
    COIN_10B("pieces" + File.separator + "tenCoinB.png"),
    TILE_COIN_1("pieces" + File.separator + "coinTile1.png"),
    TILE_COIN_2("pieces" + File.separator + "coinTile2.png"),
    TILE_BRICK("pieces" + File.separator + "brickTile.png"),
    TILE_CLOTH("pieces" + File.separator + "clothTile.png"),
    TILE_TOOL("pieces" + File.separator + "toolTile.png"),
    TILE_FOOD("pieces" + File.separator + "foodTile.png"),
    TILE_WINE("pieces" + File.separator + "wineTile.png"),
    CITY_BRICK("pieces" + File.separator + "brickCity.png"),
    CITY_FOOD("pieces" + File.separator + "foodCity.png"),
    CITY_CLOTH("pieces" + File.separator + "clothCity.png"),
    CITY_TOOL("pieces" + File.separator + "toolCity.png"),
    CITY_WINE("pieces" + File.separator + "wineCity.png"),
    BOARD("Concordia board.jpg"),
    STOREHOUSE_BLACK("blackStorehouse.png"),
    STOREHOUSE_RED("blackStorehouse.png"),
    STOREHOUSE_YELLOW("blackStorehouse.png"),
    STOREHOUSE_BLUE("blackStorehouse.png"),
    STOREHOUSE_GREEN("blackStorehouse.png");

    public BufferedImage pieceImage;

    private PieceImage(String pieceImageFileName) {
        try {
            pieceImage = ImageIO.read(new File(String.format("resources%simages%s"+pieceImageFileName, File.separator, File.separator, File.separator)));
        }
        catch(IOException e) {
            System.out.println("Failed to load image");
        }
    }
}