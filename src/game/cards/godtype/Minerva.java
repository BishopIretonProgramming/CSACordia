package src.game.cards.godtype;

//Imports:
import src.game.player.Player;
import src.game.Good;

public interface Minerva {
   
   Player getPlayer();
   Good specialistType();
   default int minervaScorePoints() {
      //Good good = specialistType();
      //Player gets 1 point for each city of the type of good that they own
      //Return points
      return 0;
   }
}