package src.game;
import java.util.ArrayList;

import src.game.cards.PersonalityCard;
import src.game.cards.Prefect;
import src.game.cards.Senator;
import src.game.cards.Architect;
import src.game.cards.Consul;
import src.game.cards.Diplomat;
import src.game.cards.Farmer;
import src.game.cards.Mason;
import src.game.cards.Mercator;
import src.game.cards.ColonistPCard;
import src.game.cards.Smith;
import src.game.cards.Tribune;
import src.game.cards.Vintner;
import src.game.cards.Weaver;
import src.game.player.Player;

import java.util.Random;

public class CardShop extends ArrayList<PersonalityCard>{
    private PersonalityCard card1;
    private PersonalityCard card2;
    private PersonalityCard card3;
    private PersonalityCard card4;
    private PersonalityCard card5;
    Random rand = new Random();

    public CardShop(){
        super(5);
        card1 = setRandCard(card1);
        card2 = setRandCard(card2);
        card3 = setRandCard(card3);
        card4 = setRandCard(card4);
        card5 = setRandCard(card5);
        this.add(0, card1);
        this.add(1, card2);
        this.add(2, card3);
        this.add(3, card4);
        this.add(4, card5);
        
    }

    public PersonalityCard setRandCard(PersonalityCard card){
        int random = (rand.nextInt()*13)+1;
        
        switch (random) {
            case 1:
                    card = (Architect)card;
                    break;
                case 2:
                    card = (ColonistPCard)card;
                    break;
                case 3:
                    card = (Consul)card;
                    break;
                case 4:
                    card = (Diplomat)card;
                    break;
                case 5:
                    card = (Farmer)card;
                    break;
                case 6:
                    card = (Mason)card;
                    break;
                case 7:
                    card = (Mercator)card;
                    break;
                case 8:
                    card = (Prefect)card;
                    break;
                case 9:
                    card = (Senator)card;
                    break;
                case 10:
                    card = (Smith)card;
                    break;
                case 11:
                    card = (Tribune)card;
                    break;
                case 12:
                    card = (Vintner)card;
                    break;
                case 13:
                    card = (Weaver)card;
                    break;

        }
        

        return card;
    }


    public PersonalityCard getCard1() {
        return card1;
    }
    public void setCard1(PersonalityCard card1) {
        this.card1 = card1;
    }
    public PersonalityCard getCard2() {
        return card2;
    }
    public void setCard2(PersonalityCard card2) {
        this.card2 = card2;
    }
    public PersonalityCard getCard3() {
        return card3;
    }
    public void setCard3(PersonalityCard card3) {
        this.card3 = card3;
    }
    public PersonalityCard getCard4() {
        return card4;
    }
    public void setCard4(PersonalityCard card4) {
        this.card4 = card4;
    }
    public PersonalityCard getCard5() {
        return card5;
    }
    public void setCard5(PersonalityCard card5) {
        this.card5 = card5;
    }

    public void buy(Player player,int num){
        switch (num) {
            case 1: 
                player.addCard(card1);
                setCard1(setRandCard(card1));
                break;
            case 2: 
                player.addCard(card2);
                setCard2(setRandCard(card2));
                break;
            case 3: 
                player.addCard(card3);
                setCard3(setRandCard(card3));
                break;
            case 4: 
                player.addCard(card4);
                setCard4(setRandCard(card4));
                break;
            case 5: 
                player.addCard(card5);
                setCard5(setRandCard(card5));
                break;
        }
    }


        
    }


    


