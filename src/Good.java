package src;

/* Nora Hixson
* Good Enum
* Enums WINE, BRICK, TOOL, CLOTH, FOOD
* Fields
* int value
* String name
*/


public enum Good{

   WINE(6,"wine"), 
   BRICK(3,"brick"),
   TOOL(5,"tool"), 
   CLOTH(7,"cloth"), 
   FOOD(4,"food");
   
   final int value;
   final String name; 
   
   Good(int v, String n){
      value = v;
      name = n;
   }
}