//package src.gui;
//
///*
// * @Author: Joseph Murray
// * Created: 6/2/2023
// * PlayerStoreHouse: GUI representation of a player's storehouse
//*/
//
//import src.game.player.Player;
//import src.game.player.StoreHouse;
//import src.gui.buttons.GUITools;
//
//import javax.swing.ImageIcon;
//import javax.swing.JPanel;
//
//import resources.default_data.FileReaderTools;
//
//import javax.swing.JLabel;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.util.ArrayList;
//
//public class PlayerStoreHouse {
//
//                                        /* Fields */
//
//    private Player player;
//    private StoreHouse storeHouse;
//    private JPanel panel;
//
//    private JLabel storeHouseImage;
//
//    //the twelve slots that display items in the storehouse
//    private JLabel slotOne;
//    private JLabel slotTwo;
//    private JLabel slotThree;
//    private JLabel slotFour;
//    private JLabel slotFive;
//    private JLabel slotSix;
//    private JLabel slotSeven;
//    private JLabel slotEight;
//    private JLabel slotNine;
//    private JLabel slotTen;
//    private JLabel slotEleven;
//    private JLabel slotTwelve;
//
//    private final FileReaderTools SAVE_FILE = new FileReaderTools("resources" + File.separatorChar + "default_data" + File.separatorChar + "player_deck_data");
//
//                                        /* Constructors */
//
//    /**
//     * Constructor
//     * @param player the player the storehouse belongs to
//     * @param panel the content pane that holds this graphic display
//    */
//    public PlayerStoreHouse(Player player, JPanel panel) {
//        this.player = player;
//        this.panel = panel;
//        storeHouse = player.storeHouse();
//
//        createLabels();
//    }
//
//                                        /* Methods */
//
//    /**
//     * helper method for constructor, creates all the labels
//    */
//    private void createLabels() {
//        storeHouseImage = GUITools.createLabel(panel, player.storeHouse().PieceImage.pieceImage, 100, 100, 100, 100);
//
//        slotOne = GUITools.createLabel(panel, player.storeHouse().get(0).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotTwo = GUITools.createLabel(panel, player.storeHouse().get(1).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotThree = GUITools.createLabel(panel, player.storeHouse().get(2).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotFour = GUITools.createLabel(panel, player.storeHouse().get(3).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotFive = GUITools.createLabel(panel, player.storeHouse().get(4).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotSix = GUITools.createLabel(panel, player.storeHouse().get(5).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotSeven = GUITools.createLabel(panel, player.storeHouse().get(6).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotEight = GUITools.createLabel(panel, player.storeHouse().get(7).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotNine = GUITools.createLabel(panel, player.storeHouse().get(8).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotTen = GUITools.createLabel(panel, player.storeHouse().get(9).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotEleven = GUITools.createLabel(panel, player.storeHouse().get(10).PieceImage.pieceImage, 100, 100, 100, 100);
//        slotTwelve = GUITools.createLabel(panel, player.storeHouse().get(11).PieceImage.pieceImage, 100, 100, 100, 100);
//    }
//
//    /**
//    * updates the storehouse display based on the current contents of the player's storehouse
//    */
//    public void updateStoreHouse() {
//        slotOne.setIcon(new ImageIcon(player.storeHouse().get(0).PieceImage.pieceImage));
//        slotTwo.setIcon(new ImageIcon(player.storeHouse().get(1).PieceImage.pieceImage));
//        slotThree.setIcon(new ImageIcon(player.storeHouse().get(2).PieceImage.pieceImage));
//        slotFour.setIcon(new ImageIcon(player.storeHouse().get(3).PieceImage.pieceImage));
//        slotFive.setIcon(new ImageIcon(player.storeHouse().get(4).PieceImage.pieceImage));
//        slotSix.setIcon(new ImageIcon(player.storeHouse().get(5).PieceImage.pieceImage));
//        slotSeven.setIcon(new ImageIcon(player.storeHouse().get(6).PieceImage.pieceImage));
//        slotEight.setIcon(new ImageIcon(player.storeHouse().get(7).PieceImage.pieceImage));
//        slotNine.setIcon(new ImageIcon(player.storeHouse().get(8).PieceImage.pieceImage));
//        slotTen.setIcon(new ImageIcon(player.storeHouse().get(9).PieceImage.pieceImage));
//        slotEleven.setIcon(new ImageIcon(player.storeHouse().get(10).PieceImage.pieceImage));
//        slotTwelve.setIcon(new ImageIcon(player.storeHouse().get(11).PieceImage.pieceImage));
//    }
//
//                                        /* Getters / Setters / ToString */
//
//}