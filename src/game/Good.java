package src.game;

/* Nora Hixson
* Good Enum
* Enums WINE, BRICK, TOOL, CLOTH, FOOD
* Fields
* int PRICE
* String NAME
*/

public enum Good {

   WINE(6,"wine"), 
   BRICK(3,"brick"),
   TOOL(5,"tool"), 
   CLOTH(7,"cloth"), 
   FOOD(4,"food");
   
   /**
    * The price of the good
    */
   final int PRICE;

   /**
    * The name of the good
    */
   final String NAME; 
   
   /**
    * Constructor to make a new Good
    * @param v the price of the Good
    * @param n the name of the good
    */
   Good(int v, String n) {
      PRICE = v;
      NAME = n;
   }
}
