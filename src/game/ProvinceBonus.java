package src.game;

/* Anthony Amedome
 *  This is a class for the Region Bonuses on the top left of the board
 */

import src.gui.PieceImage;
import java.awt.Graphics;
import java.awt.Point;

 class ProvinceBonus {
     private boolean flipped;
     private Good provinceGood;
     private int sestertiiVal;
     private Point pos;

     public String regionName;
     public int SIZE = 30; //may change later

 
     public ProvinceBonus (int x, int y, String c, Good g, int sV){
        pos = new Point(x, y); 
        regionName = c;
         flipped = false;
         provinceGood = g;
         sestertiiVal = sV;
     }
 
     public Good getProvinceGood() {
         return provinceGood;
     }
 
     public boolean getFlipped() {
         return flipped;
     }
 
     public int getSestertiiVal() {
         return sestertiiVal;
     }
 
      public String getRegion() {
          return regionName;
      }
 
     public int flip(){
         flipped = !flipped;
         return getSestertiiVal();
     }

     public void paintSerSide(Graphics g) {
        g.drawImage((sestertiiVal == 1) ? PieceImage.TILE_COIN_1.pieceImage : PieceImage.TILE_COIN_2.pieceImage,
        pos.x, pos.y, SIZE, SIZE, null);
    }
//for now rudimentary way of representing the goods will fix later probably
    public void paintGoodSide(Graphics g) {
        switch (provinceGood) {
            case BRICK:
                g.drawImage(PieceImage.TILE_BRICK.pieceImage, pos.x, pos.y, SIZE, SIZE, null);
                break;
            case FOOD:
                g.drawImage(PieceImage.TILE_FOOD.pieceImage, pos.x, pos.y, SIZE, SIZE, null);
                break;
            case TOOL:
                g.drawImage(PieceImage.TILE_TOOL.pieceImage, pos.x, pos.y, SIZE, SIZE, null);
                break;
            case WINE:
                g.drawImage(PieceImage.TILE_WINE.pieceImage, pos.x, pos.y, SIZE, SIZE, null);
                break;
            case CLOTH:
                g.drawImage(PieceImage.TILE_CLOTH.pieceImage, pos.x, pos.y, SIZE, SIZE, null);
                break;
        }
    }
 }