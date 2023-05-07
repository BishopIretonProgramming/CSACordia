package src.Cards;


public class Mercator extends PersonalityCard implements Mercvrivs {
   
   Mercator(int winePrice) {
      super(winePrice, 0, 0, 0, 0, 2);
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