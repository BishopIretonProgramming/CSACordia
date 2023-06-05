package src.game.cards;

//  imports
import src.game.cards.godtype.Mars;
import src.game.player.Player;

//import java.awt.Graphics;

public class Colonist extends PersonalityCard implements Mars {
   public final CardImage IMAGE = CardImage.COLONIST;

   public Colonist(Player myPlayer) {
      super(myPlayer, 0, 1, 0, 0, 0, 2);
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
      return getVictoryMultiplier() * marsScorePoints();
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
}