package src.game;

/* Anthony Amedome
 *  This is a class for the top left of the board it is an array list of region bonuses
 */

 import java.util.ArrayList;
 import java.util.Random;
 
 class RegionBonus {
     private boolean flipped;
     private Good regionGood;
     private int sersertiiVal;
 //     public String regionName;
 
     public RegionBonus (/*String c,*/ Good g, int sV){
        // regionName = c;
         flipped = false;
         regionGood = g;
         sersertiiVal = sV;
     }
 
     public Good getRegionGood() {
         return regionGood;
     }
 
     public boolean getFlipped() {
         return flipped;
     }
 
     public int getSersertiiVal() {
         return sersertiiVal;
     }
 
 //     public String getRegion() {
 //         return regionName;
 //     }
 
     public int flip(){
         flipped = !flipped;
         return getSersertiiVal();
     }
 }
 public class BonusBox extends ArrayList<RegionBonus> {
 
     private int capacity;
     
     public BonusBox (int c) {
         super(c);
         capacity = c;
     }
     
     public Good randomGood() {
       Random math = new Random();
       int goodValue = (int)((math.nextInt()*5) + 3);
       
       switch (goodValue) {
          case 3:
             return Good.BRICK;
             
          case 4: 
             return Good.FOOD;   
             
          case 5:
             return Good.TOOL;
             
          case 6: 
             return Good.WINE;
             
          case 7:
             return Good.CLOTH;
              
       }
       
     return null;
    
     }
     
     public void fillBox() {
     Random math = new Random();
        for (int i = 0; i < capacity; i++) {
            this.add(new RegionBonus(randomGood(), (int)((math.nextInt()*3) +1) ));
        }
     }
 
     public int flipBox() {
         int totalSestertii = 0;
         for (RegionBonus region: this) {
             if (region.getFlipped() == true) {   
                 totalSestertii += region.flip();
             }
         }
         return totalSestertii;
     }

     @Override
     public String toString(){
        String output = "";
        for (RegionBonus region : this) {
            output += ( "(" + /*this.get(1).getRegionGood()*/ " / " + region.getSersertiiVal() + ")");
        }
        return output;
     }


 }