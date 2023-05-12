package src.game.cards;

//  imports
import src.game.cards.godtype.Mars;
import src.game.Player;

import java.awt.Graphics;

public class Colonist extends PersonalityCard implements Mars {
   
   public Colonist(Player myPlayer, int foodPrice) {
      super(myPlayer, 0, foodPrice, 0, 0, 0, 2);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Either: Place colonists in any owned city or Roma for 1 food, 1 wheat each
      //OR: gain 5 sestertii + 1 per owned colonist on the board
      //Build Houses
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