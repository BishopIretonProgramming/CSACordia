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

import resources.saves.FileReaderTools;

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
    //private final String guiImageFilePath = String.format("src%sgui%simages%sbutton_images%s", File.separator, File.separator, File.separator, File.separator);

    /* private String[] guiImageFileNames = { //list of file names, temporary until file reading added
        guiImageFilePath + "left_button.png",
        guiImageFilePath + "right_button.png",
        guiImageFilePath + "use_button.png",
        guiImageFilePath + "background_label.png",
        guiImageFilePath + "available_cards.png",
        guiImageFilePath + "available_cards_selected.png",
        guiImageFilePath + "discarded_cards.png",
        guiImageFilePath + "discarded_cards_selected.png"
    }; */

    private JButton leftButton; //Button to cycle left through cards
    private JButton rightButton; //Button to cycle right through cards
    private JButton useButton; //Button to play the shownCard

    private ButtonGroup cardFilters; //Selection to select which type of cards are shown
    private JRadioButton shownCards; //shows cards the player can play (see cards)
    private JRadioButton discardedCards; //shows cards the player has already played (see discardedCards)

    private JLabel backgroundLabel; //Label that displays a nice background for the PlayerDeck section
    private JLabel cardLabel; //Label that displays the shownCard

    private JPanel panel; //The JPanel that holds these graphics

    private final FileReaderTools SAVE_FILE = new FileReaderTools("player_deck_data");

                                        /* Constructors */

    /**
     * Constructor
     * @param player the player
     * @param cards the starting cards the player receives
     * @param panel the panel that holds these graphics
    */
    public PlayerDeck(Player player, JPanel panel) {
        this.player = player;
        this.panel = panel;
        
        availableCards.add(new Architect(player, true));
        availableCards.add(new Diplomat(player, 0, 0, 0, 0, 0, "God"));
        availableCards.add(new Mercator(player, true));
        availableCards.add(new Prefect(player, true));
        availableCards.add(new Prefect(player, true));
        availableCards.add(new Senator(player));
        availableCards.add(new Tribune(player));

        /* //TODO: find a way to make this work
        for(Class<Object> card : SAVE_File.getClasses("cards")) {
            availableCards.add(new card());
        }
        */

        cards = availableCards;
        discardCards = new ArrayList<PersonalityCard>(cards.size());

        guiImages = SAVE_FILE.getImages("gui_image_file_names");

        backgroundLabel = createLabel(guiImages.get(3), 0, 0, 100, 100, "");
        cardLabel = createLabel(guiImages.get(0), 0, 0, 100, 100, "");
        changeCard(0);

        createButtons();
        createRadioButtons();
    }

                                        /* Methods */

    /**
     * Helper method for Constructor
    */
    private void createButtons() {
        leftButton = createButton(guiImages.get(0), 0, 0, 100, 100, "");
        rightButton = createButton(guiImages.get(1), 0, 0, 100, 100, "");
        useButton = createButton(guiImages.get(2), 0, 0, 100, 100, "");

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

        shownCards = createRadioButton(guiImages.get(4), guiImages.get(5), 0, 0, 100, 100, "Available cards");
        discardedCards = createRadioButton(guiImages.get(6), guiImages.get(7), 0, 0, 100, 100, "Discarded cards");

        cardFilters.add(shownCards);
        cardFilters.add(discardedCards);

        shownCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shownCardID = 0;
                cards = availableCards;
                changeCard(0);
            }
        });

        discardedCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shownCardID = 0;
                cards = discardCards;
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
     * @param pressedImage image displayed by JRadioButton when it is selected
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @param text text displayed by JRadioButton
     * @return created JRadioButton
    */
    private JRadioButton createRadioButton(BufferedImage image, BufferedImage pressedImage, int xPosition, int yPosition, int width, int height, String text) {
        JRadioButton radioButton = new JRadioButton();

        radioButton.setIcon(new ImageIcon(image));
        radioButton.setPressedIcon(new ImageIcon(pressedImage));
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
    public void removeCard(PersonalityCard card) {
        cards.remove(card);
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