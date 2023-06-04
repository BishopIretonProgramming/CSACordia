package src.game;

/* Nora Hixson
* Good Enum
* Enums WINE, BRICK, TOOL, CLOTH, FOOD
* Fields
* int PRICE
* String NAME
*/

@Storeable public enum Good {

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

   /**
    * Library (static) method to return a Good from a String
    * @param good the String of the Good
    * @return the Good represented by the String
    */
   public static Good fromString(String good) {
      return switch (good.toUpperCase()) {
         case "WINE" -> WINE;
         case "BRICK" -> BRICK;
         case "TOOL" -> TOOL;
         case "CLOTH" -> CLOTH;
         case "FOOD" -> FOOD;
         default -> null;
      };
   }

   /**
    * Getter method to get the price of a good
    *
    * @return the price of the good
    */
   public int price() {
      return this.PRICE;
   }
}
