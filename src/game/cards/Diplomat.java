package src.game.cards;

//  imports
import src.game.cards.godtype.*;
import src.game.player.Player;

import java.awt.image.BufferedImage;

//import java.awt.Graphics;

public class Diplomat extends PersonalityCard implements Satvrnvs, Mars, Mercvrivs, Jvpiter {
   public final String GOD;
   private PersonalityCard copied;

   public Diplomat(Player myPlayer, int brickPrice, int foodPrice, int toolPrice, int winePrice, int clothPrice, String god) {
      super(myPlayer, brickPrice, foodPrice, toolPrice, winePrice, clothPrice, 1, findImage(god, foodPrice));
      this.GOD = god;
      this.copied = null;
   }

   public static BufferedImage findImage(String GOD, int foodPrice) {
      //set the correct image
      switch(GOD) {
         case "Satvrnvs":
         case "satvrnvs":
         case "Saturnus":
         case "saturnus":
            if(foodPrice > 0)
               return CardImage.DIPLOMAT_I.cardImage;
            else
               return CardImage.DIPLOMAT_III.cardImage;
         case "Mercvrivs":
         case "mercvrivs":
         case "Mercurius":
         case "mercurius":
            return CardImage.DIPLOMAT_IV.cardImage;
         case "Mars":
         case "mars":
            return CardImage.DIPLOMAT_V.cardImage;
         case "Jvpiter":
         case "jvpiter":
         case "Jupiter":
         case "jupiter":
            return CardImage.DIPLOMAT_ST.cardImage;
         default:
            System.out.println("Invalid godtype entered into Diplomat constructor");
            return null;
      }
   }
   
   @Override
   public void doAction() {
      //TO DO: Pick a Card
      //Play any card shown by any other player
      copied.doAction();
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      switch(GOD) {
         case "Satvrnvs":
         case "satvrnvs":
         case "Saturnus":
         case "saturnus":
            return satvrnvsScorePoints() * 1;
         case "Mercvrivs":
         case "mercvrivs":
         case "Mercurius":
         case "mercurius":
            return mercvrivsScorePoints() * 2;
         case "Mars":
         case "mars":
            return marsScorePoints() * 2;
         case "Jvpiter":
         case "jvpiter":
         case "Jupiter":
         case "jupiter":
            return jvpiterScorePoints() * 1;
         default:
            System.out.println("Invalid godtype entered into Diplomat constructor");
            
      }
      return 0;
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
   
   public void setCopied(PersonalityCard c) {
      copied = c;
   }
}