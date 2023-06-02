package src.gui;

/*
 * @Author: Joseph Murray
 * Created: 6/2/2023
 * PlayerStoreHouse: GUI representation of a player's storehouse
*/

import src.game.Player;
import src.game.StoreHouse;
import src.gui.images.*;

import assets.sprites.*;

import resources.saves.FileReaderTools;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class PlayerStoreHouse {
    private Player player;
    private StoreHouse storeHouse;
    private JPanel panel;

    private ArrayList<BufferedImage> slotImages;

    /*
     * 1 4 5 8 9 12
     * 2 3 6 7 10 11
     */

    private JLabel storeHouseImage;
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

    private final FileReaderTools SAVE_FILE = new FileReaderTools("player_deck_data");

    public PlayerStoreHouse(Player player, JPanel panel) {
        this.player = player;
        this.panel = panel;
        storeHouse = player.storeHouse();
    }
}
