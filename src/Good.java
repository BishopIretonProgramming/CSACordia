package src;

/* Nora Hixson
* Good Enum
* Enums WINE, BRICK, TOOL, CLOTH, FOOD
* Fields
* int PRICE
* String NAME
*/

public enum Good{

   WINE(6,"wine"), 
   BRICK(3,"brick"),
   TOOL(5,"tool"), 
   CLOTH(7,"cloth"), 
   FOOD(4,"food");
   
   final int PRICE;
   final String NAME; 
   
   Good(int v, String n){
      VALUE = v;
      NAME = n;
   }
}
