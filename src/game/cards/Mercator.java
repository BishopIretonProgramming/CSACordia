package src.game.cards;

//  imports
import src.game.cards.godtype.Mercvrivs;
import src.game.player.Player;

//import java.awt.Graphics;

public class Mercator extends PersonalityCard implements Mercvrivs {
   public final boolean ISSTARTING;
   
   public Mercator(Player myPlayer, boolean isStarting) {
      super(myPlayer, 0, 0, 0, isStarting ? 0 : 1, 0, 2, isStarting ? CardImage.MERCATOR_ST.cardImage : CardImage.MERCATOR.cardImage);
      this.ISSTARTING = isStarting;
   }
   
   @Override
   public void doAction() {
      //TO DO
      Player p = getMyPlayer();
      //add 3 if starting, 5 if bought
      p.addSestertii(ISSTARTING ? 3 : 5);
      //buy/sell two different types of goods
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * mercvrivsScorePoints();
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
}