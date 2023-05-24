package src.game.cards;

//  imports
import src.game.cards.godtype.Jvpiter;
import src.game.Player;

import java.awt.Graphics;

public class Consul extends PersonalityCard implements Jvpiter {
   public final CardImage image = CardImage.CONSUL;
   
   public Consul(Player myPlayer, boolean isStarting) {
      super(myPlayer, 0, 0, 0, 0, isStarting ? 0 : 1, 1);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Buy up to one card, do not pay additional fees (cloth, etc.)
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * jvpiterScorePoints();
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