package src.game.cards;

//  imports
import src.game.cards.godtype.Satvrnvs;
import src.game.player.Player;

//import java.awt.Graphics;

public class Prefect extends PersonalityCard implements Satvrnvs {
   public final boolean ISSTARTING;
   
   public Prefect(Player myPlayer, boolean isStarting) {
      super(myPlayer, 0, 0, 0, isStarting ? 0 : 1, 0, 1, isStarting ? CardImage.PREFECT_ST.cardImage : CardImage.PREFECT.cardImage);
      this.ISSTARTING = isStarting;
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Pick a Province
      //Add Production Bonus
      //Produce chosen province
      //OR earn back all displayed sestertii
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * satvrnvsScorePoints();
   }
   
   @Override
   public void update() {
      
   }
   
   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
}