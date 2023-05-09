package src.game.cards;

//  imports
import src.game.cards.godtype.Mercvrivs;
import src.game.Player;

import java.awt.Graphics;

public class Mercator extends PersonalityCard implements Mercvrivs {
   
   Mercator(Player myPlayer, int winePrice) {
      super(myPlayer, 0, 0, 0, winePrice, 0, 2);
   }
   
   @Override
   public void doAction() {
      //TO DO
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * scorePoints();
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public void draw(Graphics g) {
      
   }
   
}