package src.game.cards;

//  imports
import src.game.cards.godtype.Mars;
import src.game.Player;

import java.awt.Graphics;

public class Tribune extends PersonalityCard implements Mars {
   
   public Tribune(Player myPlayer) {
      super(myPlayer, 0, 0, 0, 0, 0, 2);
   }
   
   @Override
   public void doAction() {
      setPlayed(true);
      int count = 0;
      for(int i = 0; i < getMyPlayer().cards().size(); i ++) {
         if(getMyPlayer().cards().get(i).getPlayed())
            count ++;
         getMyPlayer().cards().get(i).setPlayed(false);
      }
      
      //TO DO: Allow player to create a colonist for 1 food + one tool
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