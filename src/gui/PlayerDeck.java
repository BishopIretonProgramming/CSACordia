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
import java.awt.image.BufferedImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class PlayerDeck {
    Player player;

    ArrayList<PersonalityCard> cards;
    ArrayList<PersonalityCard> discardCards;
    PersonalityCard shownCard;

    ArrayList<BufferedImage> cardImages;

    JButton leftButton;
    JButton rightButton;
    JButton infoButton;
    JButton useButton;

    JRadioButton cardFilters;
    JPanel panel;

    public PlayerDeck(Player player, ArrayList<PersonalityCard> cards, JPanel panel) {
        this.player = player;
        this.cards = cards;
        this.panel = panel;

        discardCards = new ArrayList<PersonalityCard>(cards.size());

        createButtons();
    }

    private void loadStartImages() {
        //TODO: call loadImage() for all starting images
    }

    private void createButtons() {
        leftButton = new JButton();
        rightButton = new JButton();
        infoButton = new JButton();
        useButton = new JButton();

        panel.add(leftButton);
        panel.add(rightButton);
        panel.add(infoButton);
        panel.add(useButton);

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

    private void addStartCards(ArrayList<PersonalityCard> cards) {}

    private void loadImage(BufferedImage image) {
        try {
            //load image
            //TODO: create images of buttons and cards
        }
        catch(IOException e) {
            System.out.println("Failed to load image");
        }
    }

    private void changeCard(int indexChange) {}
    private void addCard(PersonalityCard card) {}
    private void removeCard(PersonalityCard card) {}

    public void drawGraphics(Graphics g) {
        
    }
}