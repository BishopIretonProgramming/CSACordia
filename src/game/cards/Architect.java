package src.game.cards;

//  imports
import src.game.cards.godtype.Jvpiter;
import src.game.Player;

import java.awt.Graphics;

public class Architect extends PersonalityCard implements Jvpiter {
   public final CardImage IMAGE = CardImage.ARCHITECT;
   public final boolean ISSTARTING;
   
   public Architect(Player myPlayer, boolean isStarting) {
      super(myPlayer, 0, 0, isStarting ? 0 : 1, 0, 0, 1);
      this.ISSTARTING = isStarting;
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Move colonists
      //Build Houses
      setPlayed(true);
   }
   
   @Override // calculates points
   public int calculatePoints() {
      return getVictoryMultiplier() * jvpiterScorePoints();
   }
   
   @Override // updates card
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