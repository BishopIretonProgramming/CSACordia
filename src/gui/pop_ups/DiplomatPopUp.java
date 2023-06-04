package src.gui.pop_ups;

/*
 * @Author: Joseph Murray
 * Created: 6/4/2023
 * DiplomatPopUp: 
*/

import javax.swing.JButton;
import javax.swing.JPanel;

import src.gui.GUITools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.game.cards.*;
import src.game.Game;

import resources.default_data.FileReaderTools;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;

public class DiplomatPopUp extends PopUp {
    private Game game;

    private ArrayList<PersonalityCard> cards;
    private PersonalityCard shownCard;
    private int shownCardID;

    private ArrayList<BufferedImage> guiImages;

    private JButton leftButton;
    private JButton rightButton;
    private JButton useButton;

    private JLabel cardLabel;

    private final FileReaderTools SAVE_FILE = new FileReaderTools("resources" + File.separatorChar + "default_data" + File.separatorChar + "diplomat_pop_up_data.txt");

    public DiplomatPopUp(Game game) {
        super("Diplomat");

        this.game = game;

        for(PersonalityCard card : game.getTopFacingDiscardedCards()) {
            cards.add(card);
        }

        guiImages = SAVE_FILE.getImages("gui_image_file_names");
        cardLabel = GUITools.createLabel((JPanel) getContentPane(), guiImages.get(0), 100, 100, 100, 100);

        changeCard(0);
        createButtons();
    }

    private void createButtons() {
        leftButton = GUITools.createButton((JPanel) getContentPane(), guiImages.get(0), 100, 100, 100, 50);
        rightButton = GUITools.createButton((JPanel) getContentPane(), guiImages.get(1), 300, 100, 100, 50);
        useButton = GUITools.createButton((JPanel) getContentPane(), guiImages.get(2), 200, 200, 100, 50);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard(-1);
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCard(1);
            }
        });

        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                shownCard.doAction();
            }
        });
    }

        /**
     * changes the shownCard
     * @param indexChange the change in the shownCard, 1 to go left one, -1 to go right one
    */
    private void changeCard(int indexChange) {
        int newShownCardID = shownCardID + indexChange;

        if(newShownCardID <= -1) {
            newShownCardID = cards.size() - 1;
        } 
        else if(newShownCardID >= cards.size()) {
            newShownCardID = 0;
        }

        shownCardID = newShownCardID;
        shownCard = cards.get(shownCardID);
        
        cardLabel.setIcon(new ImageIcon(shownCard.IMAGE.cardImage));
    }
}