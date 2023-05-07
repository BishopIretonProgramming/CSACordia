package src.cards;


public abstract class PersonalityCard {
   private int winePrice, toolPrice, foodPrice, brickPrice, clothPrice; //The amount of each item that this card costs to buy it
   private int victoryMultiplier; //The mutiplier for the card's victory points
   private boolean played; //Whether this card has been played already
   
   PersonalityCard(int winePrice, int toolPrice, int foodPrice, int brickPrice, int clothPrice, int victoryMultiplier) {
      this.winePrice = winePrice;
      this.toolPrice = toolPrice;
      this.foodPrice = foodPrice;
      this.brickPrice = brickPrice;
      this.clothPrice = clothPrice;
      this.victoryMultiplier = victoryMultiplier;
   }
   
   public abstract void doAction();
   public abstract int calculatePoints();
   
   
   /*
   For use with Tribune ability; returns the card to the player's hand 
   returns true if the card had already been played, false otherwise*/
   public boolean reset() {
      if(played) {
         played = false;
         return true;
      }
      return false;
   }
   
   /**GETTERS**/
   public int getWinePrice() {return this.winePrice;}
   public int getToolPrice() {return this.toolPrice;}
   public int getFoodPrice() {return this.foodPrice;}
   public int getBrickPrice() {return this.brickPrice;}
   public int getClothPrice() {return this.clothPrice;}
   public int getVictoryMultiplier() {return this.victoryMultiplier;}
   public boolean getPlayed() {return this.played;}
   
   /**SETTERS**/
   public void setPlayed(boolean played) {this.played = played;}
}