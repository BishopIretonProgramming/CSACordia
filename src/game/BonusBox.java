package src.game;

/* Anthony Amedome
 *  This is a class for the Region Bonus Box on the top left of the board
 */

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Collections;

 public class BonusBox extends ArrayList<ProvinceBonus> {
 
     public Dimension SIZE = new Dimension(4, 3);
     public ArrayList<String> PROVINCES = new ArrayList<String> ();

     private Point pos;

     public BonusBox (int x, int y) {
        super();
        this.pos = new Point(x, y);

        String[] provs = new String[12]; //FILL THIS WITH 12 PROVINCES
        Collections.addAll(PROVINCES, provs);
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
        for (int c = 0; c < SIZE.width; c++) {
            for(int r = 0; r < SIZE.height; r++) {
                this.add(new ProvinceBonus(pos.x + c*(50), pos.y + r*(30), PROVINCES.get(c+r), randomGood(), (math.nextInt()*3) +1));
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

     @Override
     public String toString(){
        String output = "";
        for (ProvinceBonus region : this) {
            output += ( "(" + this.get(1).getProvinceGood() + " / " + region.getSersertiiVal() + ")");
        }
        return output;
     }
 }