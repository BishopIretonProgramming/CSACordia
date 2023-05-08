package src.cards;


public class Mercator extends PersonalityCard implements Mercvrivs {
   
   Mercator(int winePrice) {
      super(0, 0, 0, winePrice, 0, 2);
   }
   
   @Override
   public void doAction() {
      //TO DO
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * scorePoints();
   }
   
}