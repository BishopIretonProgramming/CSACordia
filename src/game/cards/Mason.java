package src.game.cards;

//  imports
import src.game.cards.godtype.Minerva;
import src.game.Player;
import src.game.Good;

import java.awt.Graphics;
//Depending on future implementation, may incorporate all Specialist cards under
//one superclass/interface
public class Mason extends PersonalityCard implements Minerva {
   public final CardImage IMAGE = CardImage.MASON;
   
   public Mason(Player myPlayer) {
      super(myPlayer, 0, 1, 0, 0, 0, 3);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //All owned cities of specified type produce
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * minervaScorePoints();
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
   
   @Override
   public Good specialistType() {
      return Good.BRICK;
   }
}