/* Caroline 
* inspired by: Nora :)
* enum for sestertii
*/
public enum Sestertii {
   
   ONESESTERTIUS(1, "one sestertii"),
   TWOSESTERTII(2, "two sestertii"),
   FIVESESTERTII(5, "five sestertii"),
   TENSESTERTII(10, "ten sestertii");
   
   
   final int COIN_WORTH;
   final String COIN_NAME;
   
   Sestertii(int c, String n) {
      COIN_WORTH = c;
      COIN_NAME = n;
   }
   
}