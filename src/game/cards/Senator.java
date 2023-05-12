package src.game.cards;

//  imports
import src.game.cards.godtype.Vesta;
import src.game.Player;

import java.awt.Graphics;

public class Senator extends PersonalityCard implements Vesta {
   
   public Senator(Player myPlayer) {
      super(myPlayer, 0, 0, 0, 0, 0, 2);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Buy up to two cards from the display
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