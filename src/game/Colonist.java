/* Caroline Miller
* colonist class (in the works)
* Colonist objects for a Colonist arraylist
*/

public abstract class Colonist {
   private Colonist colonistType;
   
   public Colonist getColonistType() {
      return colonistType; //either landcolonist or seacolonist --> based on subclass that will be made in the future
   }
   
   public abstract void isColonistOnBoard(); //boolean variable to figure out whether the colonist is on the board or not
   public abstract void canMove(); //where the colonists can move --> sea colonist: only on sea; land colonists: only on land
   public abstract void colonistLocation(); //find where exactly the colonists is on the board 
   
}