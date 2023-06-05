package src.game.cards;

import java.awt.Graphics;
import src.game.player.Player;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;

public abstract class PersonalityCard {
   private int winePrice, toolPrice, foodPrice, brickPrice, clothPrice; //The amount of each item that this card costs to buy it
   private int victoryMultiplier; //The mutiplier for the card's victory points
   private boolean played; //Whether this card has been played already
   private Player myPlayer;
   private Point pos;

   public final Dimension SIZE = new Dimension(44, 69); //original: 755px, 1171px
   public final BufferedImage IMAGE = null;
   
   public PersonalityCard(Player myPlayer, int brickPrice, int foodPrice, int toolPrice, int winePrice, int clothPrice, int victoryMultiplier) {
      this.myPlayer = myPlayer;
      this.brickPrice = brickPrice;
      this.foodPrice = foodPrice;
      this.toolPrice = toolPrice;
      this.winePrice = winePrice;
      this.clothPrice = clothPrice;
      this.victoryMultiplier = victoryMultiplier;

      this.pos = new Point(0, 0);
   }
   
   public abstract void doAction();
   public abstract int calculatePoints();
   public abstract void update();
   
   public void draw(Graphics g) {
      g.drawImage(IMAGE, pos.x, pos.y, SIZE.width, SIZE.height, null);
   }
   
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
   public Player getMyPlayer() {return this.myPlayer;}
   public int getWinePrice() {return this.winePrice;}
   public int getToolPrice() {return this.toolPrice;}
   public int getFoodPrice() {return this.foodPrice;}
   public int getBrickPrice() {return this.brickPrice;}
   public int getClothPrice() {return this.clothPrice;}
   public int getVictoryMultiplier() {return this.victoryMultiplier;}
   public boolean getPlayed() {return this.played;}

   /**SETTERS**/
   public void setPlayed(boolean played) {this.played = played;}
   public void setMyPlayer(Player m) {this.myPlayer = m;}
}