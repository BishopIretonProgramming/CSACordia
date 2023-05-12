package src.game.cards.godtype;

//Imports:
import src.game.Player;

public interface Vesta {
   
   Player getPlayer();
   default int scorePoints() {
      //First, need to add the value of all goods in the storehouse to the sestertii
      //However, this can only happen once for ALL vesta cards (no continuously adding sestertii)
      //This might be best included in a universal gameEnd() method somewhere else
      
      //Return points
      return getPlayer().sestertii() / 10;
   }
}