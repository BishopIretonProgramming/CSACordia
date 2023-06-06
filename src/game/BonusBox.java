package src.game;

/* Anthony Amedome
 *  This is a class for the Region Bonus Box on the top left of the board
 */

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

 public class BonusBox extends ArrayList<ProvinceBonus> {
 
     public Dimension SIZE = new Dimension(4, 3);
     public final String[] PROVINCES;

     private Point pos;

     public BonusBox (int x, int y) {
        super();
        this.pos = new Point(x, y);

        PROVINCES = new String[]{"Britannia", "Germania", "Dacia", "Asia",
                           "Gallia", "Italia", "Hellas", "Syria",
                           "Hispania", "Mavretania", "Lybia", "Aegyptvs"};
         fillBox();
     }
     
     public Good randomGood() {
       int goodValue = (int)(Math.random()*5) + 1;
       
       switch (goodValue) {
          case 1:
             return Good.BRICK;
             
          case 2: 
             return Good.FOOD;   
             
          case 3:
             return Good.TOOL;
             
          case 4: 
             return Good.WINE;
             
          case 5:
             return Good.CLOTH;
              
       }
       
     return null;
    
     }
     
     public void fillBox() {
        Random math = new Random();
        for (int c = 0; c < SIZE.width; c++) {
            for(int r = 0; r < SIZE.height; r++) {
                this.add(new ProvinceBonus(pos.x + c*(93), pos.y + r*(40), PROVINCES[c+r], randomGood(), (math.nextInt()*3) +1));
            }
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

     public void draw(Graphics g) {
         for(ProvinceBonus p : this) {
            if(p.getFlipped()) {
               System.out.println("X");
               p.paintSerSide(g);
            } else {
               p.paintGoodSide(g);
            }
         }
     }

     @Override
     public String toString(){
        String output = "";
        for (ProvinceBonus region : this) {
            output += ( "(" + this.get(1).getProvinceGood() + " / " + region.getSestertiiVal() + ")");
        }
        return output;
     }
 }