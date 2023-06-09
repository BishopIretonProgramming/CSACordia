package src.game.cards;

//  imports
import src.game.cards.godtype.Jvpiter;
import src.game.player.Player;

//import java.awt.Graphics;

public class Consul extends PersonalityCard implements Jvpiter {
   
   public Consul(Player myPlayer) {
      super(myPlayer, 0, 0, 0, 0, 1, 1, CardImage.CONSUL.cardImage);
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
   public Player getPlayer() {
      return super.getMyPlayer();
   }
}