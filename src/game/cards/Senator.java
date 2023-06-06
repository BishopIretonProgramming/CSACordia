package src.game.cards;

//  imports
import src.game.cards.godtype.Vesta;
import src.game.player.Player;

//import java.awt.Graphics;

public class Senator extends PersonalityCard implements Vesta {
   
   public Senator(Player myPlayer) {
      super(myPlayer, 0, 0, 0, 0, 0, 1, CardImage.SENATOR.cardImage);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Buy up to two cards from the display
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * vestaScorePoints();
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
}