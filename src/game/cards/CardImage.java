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

public enum CardImage {
    ARCHITECT("architect.png"),
    COLONIST("colonist.png"),
    CONSUL("consul.png"),
    DIPLOMAT("diplomat.png"),
    FARMER("architect.png"), //TODO: create Farmer image
    MASON("architect.png"), //TODO: create Mason image
    MERCATOR("mercator.png"),
    PREFECT("prefect.png"),
    PRAEFECTUS_MAGNUS("architect.png"), //TODO: create Praefectus Magnus image and class
    SENATOR("senator.png"),
    SMITH("architect.png"), //TODO: create Smith image
    TRIBUNE("tribune.png"),
    VINTNER("vitner.png"),
    WEAVER("weaver.png");

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