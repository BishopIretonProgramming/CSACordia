package src.game.cards.godtype;

//Imports:
import src.game.player.Player;

public interface Jvpiter {
   
   Player getPlayer(); // Method to get the card's player
   default int jvpiterScorePoints() {
      //Player gets 1 point for each non-brick city
      //Return points
      return 0;
   }
}