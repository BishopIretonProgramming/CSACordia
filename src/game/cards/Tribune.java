package src.game.cards;

//  imports
import src.game.cards.godtype.Mars;
import src.game.Player;

import java.awt.Graphics;

public class Tribune extends PersonalityCard implements Mars {
   public final CardImage IMAGE = CardImage.TRIBUNE;
   
   public Tribune(Player myPlayer) {
      super(myPlayer, 0, 0, 0, 0, 0, 2);
   }
   
   @Override
   public void doAction() {
      Player p = getMyPlayer();
      setPlayed(true);
      int count = 0;
      for(int i = 0; i < p.cards().size(); i ++) {
         if(p.cards().get(i).getPlayed())
            count ++;
         p.cards().get(i).setPlayed(false);
      }
      if(count >= 3) count -= 3; else count = 0;
      p.addSestertii(count);
      //TO DO: Allow player to create a single colonist for 1 food + one tool
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * marsScorePoints();
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public void draw(Graphics g) {
      
   }
   
   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
}