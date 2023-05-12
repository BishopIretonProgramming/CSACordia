package src.game.cards.godtype;

//Imports:
import src.game.Player;

public interface Mars {
   
   Player getPlayer();
   default int scorePoints() {
      //For each owned colonist on the board, the player gets 2 points
      //Return points
      return 0;
   }
}