package src.game.cards;

/*
 * @Author: Joseph Murray
 * Created: 5/20/2023
 * CardImage: enum representing the associated image for each card type
*/

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import src.util.IOUtils;

public enum CardImage {
    ARCHITECT("src\\gui\\images\\architect.png"),
    COLONIST("src\\gui\\images\\colonist.png"),
    CONSUL("src\\gui\\images\\consul.png"),
    DIPLOMAT("src\\gui\\images\\diplomat.png"),
    FARMER("src\\gui\\images\\architect.png"), //TODO: create Farmer image
    MASON("src\\gui\\images\\architect.png"), //TODO: create Mason image
    MERCATOR("src\\gui\\images\\mercator.png"),
    PREFECT("src\\gui\\images\\prefect.png"),
    PRAEFECTUS_MAGNUS("src\\gui\\images\\architect.png"), //TODO: create Praefectus Magnus image and class
    SENATOR("src\\gui\\images\\senator.png"),
    SMITH("src\\gui\\images\\architect.png"), //TODO: create Smith image
    TRIBUNE("src\\gui\\images\\tribune.png"),
    VINTNER("src\\gui\\images\\vitner.png"),
    WEAVER("src\\gui\\images\\weaver.png");

    public BufferedImage cardImage;

    private CardImage(String cardImageFileName) {
        try {
            cardImage = ImageIO.read(new File(cardImageFileName));
        }
        catch(IOException e) {
            System.out.println("Failed to load image");
        }
    }
}