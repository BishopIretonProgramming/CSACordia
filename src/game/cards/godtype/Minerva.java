package src.game.cards.godtype;

//Imports:
import src.game.Player;
import src.game.Good;

public interface Minerva {
   
   Player getPlayer();
   Good specialistType();
   default int scorePoints() {
      //Good good = specialistType();
      //Player gets 1 point for each city of the type of good that they own
      //Return points
      return 0;
   }
}