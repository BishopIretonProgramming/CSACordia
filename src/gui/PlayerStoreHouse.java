package src.gui;

/*
 * @Author: Joseph Murray
 * Created: 6/2/2023
 * PlayerStoreHouse: GUI representation of a player's storehouse
*/

import src.game.Player;
import src.game.StoreHouse;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import resources.default_data.FileReaderTools;

import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PlayerStoreHouse {

                                        /* Fields */

    private Player player;
    private StoreHouse storeHouse;
    private JPanel panel;

    private ArrayList<BufferedImage> slotImages;
    private ArrayList<BufferedImage> guiImages;

    private JLabel storeHouseImage;

    //the twelve slots that display items in the storehouse
    private JLabel slotOne;
    private JLabel slotTwo;
    private JLabel slotThree;
    private JLabel slotFour;
    private JLabel slotFive;
    private JLabel slotSix;
    private JLabel slotSeven;
    private JLabel slotEight;
    private JLabel slotNine;
    private JLabel slotTen;
    private JLabel slotEleven;
    private JLabel slotTwelve;

    private final FileReaderTools SAVE_FILE = new FileReaderTools("resources" + File.separatorChar + "default_data" + File.separatorChar + "player_deck_data");

                                        /* Constructors */

    /**
     * Constructor
     * @param player the player the storehouse belongs to
     * @param panel the content pane that holds this graphic display
    */
    public PlayerStoreHouse(Player player, JPanel panel) {
        this.player = player;
        this.panel = panel;
        storeHouse = player.storeHouse();

        slotImages = SAVE_FILE.getImages("");
        guiImages = SAVE_FILE.getImages("");

        createLabels();
    }

                                        /* Methods */

    /**
     * helper method for constructor, creates all the labels
    */
    private void createLabels() {
        storeHouseImage = GUITools.createLabel(panel, guiImages.get(0), 100, 100, 100, 100);

        slotOne = GUITools.createLabel(panel, slotImages.get(0), 100, 100, 100, 100);
        slotTwo = GUITools.createLabel(panel, slotImages.get(1), 100, 100, 100, 100);
        slotThree = GUITools.createLabel(panel, slotImages.get(2), 100, 100, 100, 100);
        slotFour = GUITools.createLabel(panel, slotImages.get(3), 100, 100, 100, 100);
        slotFive = GUITools.createLabel(panel, slotImages.get(4), 100, 100, 100, 100);
        slotSix = GUITools.createLabel(panel, slotImages.get(5), 100, 100, 100, 100);
        slotSeven = GUITools.createLabel(panel, slotImages.get(6), 100, 100, 100, 100);
        slotEight = GUITools.createLabel(panel, slotImages.get(7), 100, 100, 100, 100);
        slotNine = GUITools.createLabel(panel, slotImages.get(8), 100, 100, 100, 100);
        slotTen = GUITools.createLabel(panel, slotImages.get(9), 100, 100, 100, 100);
        slotEleven = GUITools.createLabel(panel, slotImages.get(10), 100, 100, 100, 100);
        slotTwelve = GUITools.createLabel(panel, slotImages.get(11), 100, 100, 100, 100);
    }

    /**
    * updates the storehouse display based on the current contents of the player's storehouse
    */
    public void updateStoreHouse() {
        slotOne.setIcon(new ImageIcon(slotImages.get(0)));
        slotTwo.setIcon(new ImageIcon(slotImages.get(1)));
        slotThree.setIcon(new ImageIcon(slotImages.get(2)));
        slotFour.setIcon(new ImageIcon(slotImages.get(3)));
        slotFive.setIcon(new ImageIcon(slotImages.get(4)));
        slotSix.setIcon(new ImageIcon(slotImages.get(5)));
        slotSeven.setIcon(new ImageIcon(slotImages.get(6)));
        slotEight.setIcon(new ImageIcon(slotImages.get(7)));
        slotNine.setIcon(new ImageIcon(slotImages.get(8)));
        slotTen.setIcon(new ImageIcon(slotImages.get(9)));
        slotEleven.setIcon(new ImageIcon(slotImages.get(10)));
        slotTwelve.setIcon(new ImageIcon(slotImages.get(11)));
    }

                                        /* Getters / Setters / ToString */

}