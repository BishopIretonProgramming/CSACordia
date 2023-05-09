// represents a colonist
// author: jonah cook

public class Colonist {
   
   private Player player;
   private PathNode position;
   private String type;
   
   public Colonist(Player p, PathNode pn, String s) {
   
      player = p;
      position = pn;
      if (!(s.equals("sea") || s.equals("land")) throw new IllegalArgumentException("invalid colonist type");
      type = s;
   
   }

}