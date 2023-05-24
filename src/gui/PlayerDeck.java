package src.gui;

/*
    @Author Joseph Murray
    created 5/18/2023
    PlayerDeck: GUI representation of the players hand of personality cards
*/

//TODO: add positioning and sizing for all GUI components
//TODO: add file reading for appropriate data

import src.game.Player;
import src.game.cards.*;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import java.util.ArrayList;

public class PlayerDeck {

                                        /* Fields */

    private Player player; //The player whose deck this is

    private ArrayList<PersonalityCard> cards; //The cards the player can see in the PlayerDeck display
    private ArrayList<PersonalityCard> availableCards; //The cards the player can play
    private ArrayList<PersonalityCard> discardCards; //The cards the player have already played
    private PersonalityCard shownCard; //The card that is currently displayed and visible in PlayerHandDisplay
    private int shownCardID; //The index of the shownCard; used for changing cards

    private ArrayList<BufferedImage> guiImages; //List of button and label images

    private String[] guiImageFileNames = { //list of file names, temporary until file reading added //TODO: add images of buttons and labels
        "info_button.png",
        "left_button.png",
        "right_button.png",
        "use_button.png",
        "background_label.png"
    };

    private JButton leftButton; //Button to cycle left through cards
    private JButton rightButton; //Button to cycle right through cards
    private JButton infoButton; //Button to display more info on shownCard
    private JButton useButton; //Button to play the shownCard

    private ButtonGroup cardFilters; //Selection to select which type of cards are shown
    private JRadioButton shownCards; //shows cards the player can play (see cards)
    private JRadioButton discardedCards; //shows cards the player has already played (see discardedCards)

    private JLabel infoLabel; //Label that displays info on the shownCard
    private JLabel backgroundLabel; //Label that displays a nice background for the PlayerDeck section
    private JLabel cardLabel; //Label that displays the shownCard

    private JPanel panel; //The JPanel that holds these graphics

    private boolean infoShown; //Whether or not the info for each card is shown

                                        /* Constructors */

    /**
     * Constructor
     * @param player the player
     * @param cards the starting cards the player receives
     * @param panel the panel that holds these graphics
    */
    public PlayerDeck(Player player, ArrayList<PersonalityCard> cards, JPanel panel) {
        this.player = player;
        this.panel = panel;
        availableCards = cards;
        this.cards = availableCards;
        discardCards = new ArrayList<PersonalityCard>(cards.size());

        guiImages = loadImage(guiImageFileNames);

        infoLabel = createLabel(guiImages.get(0), 0, 0, 0, 0, "info");
        backgroundLabel = createLabel(guiImages.get(0), 0, 0, 0, 0, "");
        cardLabel = createLabel(guiImages.get(0), 0, 0, 0, 0, "");

        updateInfoShown(false);
        createButtons();
        createRadioButtons();
    }

                                        /* Methods */

    /**
     * Helper method for Constructor
    */
    private void createButtons() {
        leftButton = createButton(guiImages.get(0), 0, 0, 0, 0, "");
        rightButton = createButton(guiImages.get(0), 0, 0, 0, 0, "");
        infoButton = createButton(guiImages.get(0), 0, 0, 0, 0, "");
        useButton = createButton(guiImages.get(0), 0, 0, 0, 0, "");

        infoButton.setMultiClickThreshhold(50);
        useButton.setMultiClickThreshhold(50);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInfoShown(false);
                changeCard(-1);
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInfoShown(false);
                changeCard(1);
            }
        });

        infoButton.addActionListener(new ActionListener() {//TODO: create info cards for each card
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInfoShown(!infoShown);
            }
        });

        useButton.addActionListener(new ActionListener() {
            @Override            
            public void actionPerformed(ActionEvent e) {
                discardCard(shownCard);
                changeCard(1);

                shownCard.doAction();
            }
        });
    }

    /**
     * Helper method for Constructor 
    */
    private void createRadioButtons() {
        cardFilters = new ButtonGroup();

        shownCards = createRadioButton(guiImages.get(0), 0, 0, 0, 0, "Available cards");
        discardedCards = createRadioButton(guiImages.get(0), 0, 0, 0, 0, "Discarded cards");

        cardFilters.add(shownCards);
        cardFilters.add(discardedCards);

        shownCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shownCardID = 0;
                cards = availableCards;
                updateInfoShown(false);
                changeCard(0);
            }
        });

        discardedCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shownCardID = 0;
                cards = discardCards;
                updateInfoShown(false);
                changeCard(0);
            }
        });
    } 

    /**
     * Helper method that initializes common values and settings for JButtons
     * @param image image displayed by JButton, what it looks like
     * @param xPosition 
     * @param yPosition
     * @param width
     * @param height
     * @param text text displayed by JButton
     * @return created JButton
    */
    private JButton createButton(BufferedImage image, int xPosition, int yPosition, int width, int height, String text) {
        JButton button = new JButton();

        button.setIcon(new ImageIcon(image));
        button.setBounds(xPosition, yPosition, width , height);
        button.setText(text);
        button.setMargin(new Insets(-1, -1, -1, -1));

        panel.add(button);

        return button;
    }

    /**
     * Helper method that initializes common values and settings for JRadioButtons
     * @param image image displayed by JRadioButton, what it looks like
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @param text text displayed by JRadioButton
     * @return created JRadioButton
    */
    private JRadioButton createRadioButton(BufferedImage image, int xPosition, int yPosition, int width, int height, String text) {
        JRadioButton radioButton = new JRadioButton();

        radioButton.setIcon(new ImageIcon(image));
        radioButton.setBounds(xPosition, yPosition, width , height);
        radioButton.setText(text);
        radioButton.setMargin(new Insets(-1, -1, -1, -1));

        panel.add(radioButton);

        return radioButton;
    }

    /**
     * Helper method that initializes common values and seetings for JLabels
     * @param image image displayed by JLabel, what it looks like
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @param text text displayed by JLabel
     * @return created JLabel
    */
    private JLabel createLabel(BufferedImage image, int xPosition, int yPosition, int width, int height, String text) {
        JLabel label = new JLabel();

        label.setIcon(new ImageIcon(image));
        label.setBounds(xPosition, yPosition, width, height);
        label.setText(text);

        panel.add(label);

        return label;
    }

    /**
     * loads an array of image files into BufferedImages
     * @param imageFileNames list of file names of images
     * @return an array of BufferedImages
    */
    private ArrayList<BufferedImage> loadImage(String[] imageFileNames) {
        ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>(imageFileNames.length);

        for(String fileName : imageFileNames) {
            imageList.add(loadImage(fileName));
        }

        return imageList;
    }

    /**
     * loads an image file into a BufferedImage
     * @param fileName file name of image
     * @return BufferedImage
    */
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

    /**
     * adds a card to the player's deck, adds it to cards
     * @param card Personality card to be added
    */
    public void addCard(PersonalityCard card) {
        cards.add(card);
    }

    /**
     * moves a card to the discard pile, removes it from cards, adds it to discardedCards
     * @param card Personality card that is to be moved
    */
    private void discardCard(PersonalityCard card) {
        cards.remove(shownCardID);
        discardCards.add(card);
    }

    /**
     * removes a card from the player's deck entirely (not to be confused with discardCard, which moves it to the discard pile)
     * @param card Personality card to be removed
    */
    private void removeCard(PersonalityCard card) {
        cards.remove(card);
    }

    /**
     * method that switches between showing the card and showing extra information on said card 
     * @param showInfo if info shown, true to show info, false to show card
    */
    private void updateInfoShown(boolean showInfo) {
        infoShown = showInfo;
        infoLabel.setVisible(infoShown);
        cardLabel.setVisible(!infoShown);
    }

                                        /* Getters / Setters / ToString */

    /**
     * @return the last played card, the one at the top of the discard pile
    */
    public PersonalityCard getLastDiscardedCard() {
        return discardCards.get(discardCards.size() - 1);
    }

    /**
     * @return this players cards, not including those in the discard pile
    */
    public ArrayList<PersonalityCard> getCards() {
        return cards;
    }

    /**
     * @return the Player that this deck belongs to
    */
    public Player getPlayer() {
        return player;
    }
}