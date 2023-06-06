package src.game.cards;

import src.game.player.Player;
import src.game.Good;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;
import java.util.HashMap;

public abstract class PersonalityCard {
   private int winePrice, toolPrice, foodPrice, brickPrice, clothPrice; //The amount of each item that this card costs to buy it
   private int clothModifier = 0; //The additional price you may have to pay if the card is in a certain position
   private int victoryMultiplier; //The mutiplier for the card's victory points
   private boolean played; //Whether this card has been played already
   private Player myPlayer;
   private Point pos;
   private HashMap<Object, Integer> price;

   public final Dimension SIZE = new Dimension(79, 123); //original: 755px, 1171px
   public final BufferedImage IMAGE;
   
   public PersonalityCard(Player myPlayer, int brickPrice, int foodPrice, int toolPrice, int winePrice, int clothPrice, int victoryMultiplier, BufferedImage img) {
      this.myPlayer = myPlayer;
      this.brickPrice = brickPrice;
      this.foodPrice = foodPrice;
      this.toolPrice = toolPrice;
      this.winePrice = winePrice;
      this.clothPrice = clothPrice;
      this.victoryMultiplier = victoryMultiplier;
      this.IMAGE = img;

      this.pos = new Point(0, 0);

      price = new HashMap<Object, Integer> ();
      price.put(Good.BRICK, brickPrice);
      price.put(Good.FOOD, foodPrice);
      price.put(Good.TOOL, toolPrice);
      price.put(Good.WINE, winePrice);
      price.put(Good.CLOTH, clothPrice);
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
   public HashMap<Object, Integer> getPrice() {return this.price;}

   /**SETTERS**/
   public void setPlayed(boolean played) {this.played = played;}
   public void setMyPlayer(Player m) {this.myPlayer = m;}
   public void setPos(int x, int y) {this.pos.setLocation(x, y);}
   public void setClothModifier(int x) {
      clothModifier = x;
      price.put(Good.CLOTH, clothPrice + clothModifier);
   }
}