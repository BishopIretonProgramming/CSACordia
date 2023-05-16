package src.game;


/* Anthony Amedome
 *  This is a class for the top left of the board it is an array list of region bonuses
 */

 import java.util.ArrayList;
 import java.util.Random;
 import java.awt.Shape;
 import java.awt.Graphics;
 import java.awt.Color;


 class ProvinceBonus {
     private boolean flipped;
     private Good provinceGood;
     private int sersertiiVal;
 //     public String regionName;
 
     public ProvinceBonus (/*String c,*/ Good g, int sV){
        // regionName = c;
         flipped = false;
         provinceGood = g;
         sersertiiVal = sV;
     }
 
     public Good getProvinceGood() {
         return provinceGood;
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
 public class BonusBox extends ArrayList<ProvinceBonus> {
 
     private int capacity;
     
     public BonusBox (int c) {
         super(c);
         capacity = c;
     }
     
     public Good randomGood() {
       Random math = new Random();
       int goodValue = (math.nextInt()*5) + 3;
       
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
            this.add(new ProvinceBonus(randomGood(), (math.nextInt()*3) +1));
        }
     }
 
     public int flipBox() {
         int totalSestertii = 0;
         for (ProvinceBonus province: this) {
             if (province.getFlipped()) {
                 totalSestertii += province.flip();
             }
         }
         return totalSestertii;
     }

     @Override
     public String toString(){
        String output = "";
        for (ProvinceBonus region : this) {
            output += ( "(" + this.get(1).getProvinceGood() + " / " + region.getSersertiiVal() + ")");
        }
        return output;
     }


 }
//putting this here for now since I dont know where to put it
class ProvinceBonusGraphics {
    public void paintSerSide(Graphics g, int w, int h, int serAmount){
        g.setColor(new Color(166,166,166));
        switch (serAmount) {
            case 1:               
                g.fillOval(w/2, h/2, 10, 10);
                break;
            case 2:
                g.fillOval(w*3/4, h *1/4, 10,10);
        }
    }
//for now rudimentary vway of representing teh goods will fix later probably
    public void paintGoodSide(Graphics g,int w, int h, ProvinceBonus pb) {
        Good good = pb.getProvinceGood();
        switch (good) {
            case BRICK:
                g.setColor(new Color(66,10,9));
                g.fillRect(w,h,15,10);
                break;
            case FOOD:
                int[] xCords = {0,w,w/2};
                int[] yCords = {0,0,h};
                g.setColor(new Color(230,177,32));
                g.fillPolygon(xCords,yCords,3);
                break;
            case TOOL:
                g.setColor(new Color(166,166,166));
                g.fillRect(w, h, 15, 10);
                break;
            case WINE:
                int[] xCord = {0,w,w/2};
                int[] yCord = {0,0,h};
                g.setColor(new Color(182,99,255));
                g.fillPolygon(xCord,yCord,3);
                break;
            case CLOTH:
                g.setColor(new Color(0,0,255));
                g.fillRect(w,h,10,10);
                break;
        }
    }
}