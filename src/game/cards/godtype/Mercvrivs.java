package src.game.cards.godtype;

//Imports:
import src.game.Player;

public interface Mercvrivs {
   
   Player getPlayer();
   default int mercvrivsScorePoints() {
      //For each TYPE of good that the player can produce with houses, they get 2 points (max. 10)
      //Return points
      return 0;
   }
}