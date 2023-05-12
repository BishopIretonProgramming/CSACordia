package src.game.cards.godtype;

//Imports:
import src.game.Player;

public interface Jvpiter {
   
   Player getPlayer();
   default int scorePoints() {
      //Return points
      return 0;
   }
}