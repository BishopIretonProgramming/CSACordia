package src.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.game.cards.Architect;
import src.game.cards.ColonistPCard;
import src.game.CardShop;
import src.game.cards.CardImage;
import src.game.cards.PersonalityCard;
import src.game.cards.Prefect;
import src.game.cards.Smith;
import src.game.cards.Tribune;
import src.game.cards.Vintner;
import src.game.cards.Weaver;
import src.game.cards.Consul;
import src.game.cards.Diplomat;
import src.game.cards.Farmer;
import src.game.cards.Mason;
import src.game.cards.Mercator;
import src.game.cards.Senator;

public class ShopPanel extends JPanel {
    private ArrayList<BufferedImage> cardImgList;
    public ShopPanel (CardShop cShop) {
        for (PersonalityCard card : cShop) {
            if (card instanceof Architect) {
                cardImgList.add(CardImage.ARCHITECT.cardImage);
            } else if (card instanceof ColonistPCard) {
                cardImgList.add(CardImage.COLONIST.cardImage);
            } else if (card instanceof Consul) {
                cardImgList.add(CardImage.CONSUL.cardImage);
            } else if (card instanceof Diplomat) {
                cardImgList.add(CardImage.DIPLOMAT_I.cardImage);
            } else if (card instanceof Farmer) {
                cardImgList.add(CardImage.FARMER.cardImage);
            } else if (card instanceof Mason) {
                cardImgList.add(CardImage.MASON.cardImage);
            } else if (card instanceof Mercator) {
                cardImgList.add(CardImage.MERCATOR.cardImage);
            } else if (card instanceof Prefect) {
                cardImgList.add(CardImage.PREFECT.cardImage);
            } else if (card instanceof Senator) {
                cardImgList.add(CardImage.SENATOR.cardImage);
            } else if (card instanceof Smith) {
                cardImgList.add(CardImage.SMITH.cardImage);
            } else if (card instanceof Tribune) {
                cardImgList.add(CardImage.TRIBUNE.cardImage);
            } else if (card instanceof Vintner) {
                cardImgList.add(CardImage.VINTNER.cardImage);
            } else if (card instanceof Weaver) {
                cardImgList.add(CardImage.WEAVER.cardImage);
            }
            
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int spacing = 10; 
        int x = 5;

        for (int i = 0; i < 5; i++) {
            g.drawImage(cardImgList.get(i), x, 5, null);
            x = cardImgList.get(i).getWidth() + spacing;
        }
            
       
    }
}
