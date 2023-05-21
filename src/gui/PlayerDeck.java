package src.gui;

/*
    @Author Joseph Murray
    created 5/18/2023
    PlayerDeck: GUI representation of the players hand of personality cards
*/

import src.game.Player;
import src.game.cards.*;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Insets;

import java.awt.image.BufferedImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public class PlayerDeck {
    private Player player;

    private ArrayList<PersonalityCard> cards;
    private ArrayList<PersonalityCard> discardCards;
    private PersonalityCard shownCard;
    private int shownCardID;

    private ArrayList<BufferedImage> cardImages;
    private String[] cardImageFileNames = { //TODO: add praefectus magnus image and class
        "architect.png",
        "consul.png",
        "diplomat.png",
        "mercator.png",
        "mercator2.png",
        "prefect.png",
        "tribune.png",
        "vintner.png",
        "weaver.png"
    };

    private JButton leftButton;
    private JButton rightButton;
    private JButton infoButton;
    private JButton useButton;

    private final String LEFT_BUTTON_IMAGE_FILE_NAME = "";
    private final String RIGHT_BUTTON_IMAGE_FILE_NAME = "";
    private final String INFO_BUTTON_IMAGE_FILE_NAME = "";
    private final String USE_BUTTON_IMAGE_FILE_NAME = "";

    private int leftButtonWidth = 50;
    private int leftButtonHeight = 50;
    private int leftButtonX = 0;
    private int leftButtonY = 0;

    private int rightButtonWidth = 50;
    private int rightButtonHeight = 50;
    private int rightButtonX = 0;
    private int rightButtonY = 0;

    private int infoButtonWidth = 50;
    private int infoButtonHeight = 150;
    private int infoButtonX = 0;
    private int infoButtonY = 0;

    private int useButtonWidth = 100;
    private int useButtonHeight = 50;
    private int useButtonX = 0;
    private int useButtonY = 0;

    private JRadioButton cardFilters;
    private JPanel panel;

    public PlayerDeck(Player player, ArrayList<PersonalityCard> cards, JPanel panel) {
        this.player = player;
        this.cards = cards;
        this.panel = panel;

        discardCards = new ArrayList<PersonalityCard>(cards.size());

        createButtons();
        addCardImages();
    }

    private void createButtons() {
        leftButton = new JButton(loadImage(LEFT_BUTTON_IMAGE_FILE_NAME));
        rightButton = new JButton(loadImage(RIGHT_BUTTON_IMAGE_FILE_NAME));
        infoButton = new JButton(loadImage(INFO_BUTTON_IMAGE_FILE_NAME));
        useButton = new JButton(loadImage(USE_BUTTON_IMAGE_FILE_NAME));

        panel.add(leftButton);
        panel.add(rightButton);
        panel.add(infoButton);
        panel.add(useButton);

        leftButton.setBounds(leftButtonX, leftButtonY, leftButtonWidth, leftButtonHeight);
        rightButton.setBounds(rightButtonX, rightButtonY, rightButtonWidth, rightButtonHeight);
        infoButton.setBounds(infoButtonX, infoButtonY, infoButtonWidth, infoButtonHeight);
        useButton.setBounds(useButtonX, useButtonY, useButtonWidth, useButtonHeight);

        leftButton.setMargin(new Insets(-1, -1, -1, -1));
        rightButton.setMargin(new Insets(-1, -1, -1, -1));
        infoButton.setMargin(new Insets(-1, -1, -1, -1));
        useButton.setMargin(new Insets(-1, -1, -1, -1));

        infoButton.setMultiClickThreshhold(50);
        useButton.setMultiClickThreshhold(50);

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

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Show information");
            }
        });

        useButton.addActionListener(new ActionListener() {
            @Override            
            public void actionPerformed(ActionEvent e) {
                System.out.println("Use card");
            }
        });
    }

    private void addCardImages() {
        for(String cardImageFileName : cardImageFileNames) {
            cardImages.add(loadImage(cardImageFileName));
        }
    }

    private BufferedImage loadImage(String fileName) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(fileName));
        }
        catch(IOException e) {
            System.out.println("Failed to load image");
        }

        return image;
    }

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
    }

    private void addCard(PersonalityCard card) {
        cards.add(card);
    }

    private void removeCard(PersonalityCard card) {
        cards.remove(card);
    }

    public void drawGraphics(Graphics g) {
        
    }
}