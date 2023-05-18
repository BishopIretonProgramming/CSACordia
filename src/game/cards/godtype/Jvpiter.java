package src.game.cards.godtype;

//Imports:
import src.game.Player;

public interface Jvpiter {
   
   Player getPlayer(); // Method to get the card's player
   default int scorePoints() {
      //Player gets 1 point for each non-brick city
      //Return points
      return 0;
   }
}