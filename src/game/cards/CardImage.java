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
    ARCHITECT("boughtcards" + File.separator + "architect.png"),
    ARCHITECT_ST("startingcards" + File.separator + "architect.png"),
    COLONIST("boughtcards" + File.separator + "colonist.png"),
    CONCORDIA("concordia.png"),
    CONCORDIA_BACK("concordiaBack.png"),
    CONSUL("boughtcards" + File.separator + "consul.png"),
    DIPLOMAT_I("boughtcards" + File.separator + "diplomatSatvrnvsTool.png"),
    DIPLOMAT_III("boughtcards" + File.separator + "diplomatSatvrnvsFood.png"),
    DIPLOMAT_IV("boughtcards" + File.separator + "diplomatMercvrivs.png"),
    DIPLOMAT_V("boughtcards" + File.separator + "diplomatMars.png"),
    DIPLOMAT_ST("startingcards" + File.separator + "diplomat.png"),
    FARMER("boughtcards" + File.separator + "farmer.png"),
    MASON("boughtcards" + File.separator + "mason.png"), 
    MERCATOR("boughtcards" + File.separator + "mercator.png"),
    MERCATOR_ST("startingcards" + File.separator + "mercator.png"),
    PREFECT("boughtcards" + File.separator + "prefect.png"),
    PREFECT_ST("startingcards" + File.separator + "prefect.png"),
    PRAEFECTUS_MAGNUS("praefectusMagnus.png"),
    PRAEFECTUS_MAGNUS_BACK("praefectusMagnusBack.png"),
    SENATOR("startingcards" + File.separator + "senator.png"),
    SMITH("boughtcards" + File.separator + "smith.png"), 
    TRIBUNE("startingcards" + File.separator + "tribune.png"),
    VINTNER("boughtcards" + File.separator + "vintner.png"),
    WEAVER("boughtcards" + File.separator + "weaver.png"),
    BACK_GREEN("startingcards" + File.separator + "greenBack.png"),
    BACK_BLACK("startingcards" + File.separator + "blackBack.png"),
    BACK_RED("startingcards" + File.separator + "redBack.png"),
    BACK_BLUE("startingcards" + File.separator + "blueBack.png"),
    BACK_YELLOW("startingcards" + File.separator + "yellowBack.png"),
    BACK_I("boughtcards" + File.separator + "backI.png"),
    BACK_III("boughtcards" + File.separator + "backIII.png"),
    BACK_IV("boughtcards" + File.separator + "backIV.png"),
    BACK_V("boughtcards" + File.separator + "backV.png");

    public BufferedImage cardImage;

    private CardImage(String cardImageFileName) {
        try {
            cardImage = ImageIO.read(new File(String.format("src%sgui%simages%s"+cardImageFileName, File.separator, File.separator, File.separator)));
        }
        catch(IOException e) {
            System.out.println("Failed to load image");
        }
    }
}