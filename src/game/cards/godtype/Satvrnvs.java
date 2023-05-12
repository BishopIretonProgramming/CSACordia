package src.game.cards.godtype;

//Imports:
import src.game.Player;

public interface Satvrnvs {
   
   Player getPlayer();
   default int scorePoints() {
      //Player gets one point for each province they have a city in
      //Return points
      return 0;
   }
}