package src.game;

/**
 * @author Joseph Murray
 * 5/9/2023
 * Province - representation of game provinces, used for good collection from tribune cards
 */

import java.util.ArrayList;
import src.game.map.CityNode;

public class Province {
   private ArrayList<CityNode> cityList = new ArrayList<CityNode>();
   private String name;
   private Good provinceGood;
   private ResourceStatus status;
   private int provinceSestercii;
   
   public enum ResourceStatus {
      GOODS,
      SESTERCII
   }
   
                                             //METHODS
                                             
   public Province(String name, ArrayList<CityNode> cityList) {
      this.name = name;
      this.cityList = cityList;   

      status = ResourceStatus.GOODS;

      setProvinceGood();
   }
   
   public int collectSestercii() {
      status = ResourceStatus.GOODS;
      
      return provinceSestercii;
   }
   
   //TODO: Collect goods based on good of cities in province
   public ArrayList<Good> collectGoods(Player player) {
      status = ResourceStatus.SESTERCII;
      
      ArrayList<Good> goodsCollected = new ArrayList<Good>();      
      goodsCollected.add(provinceGood);
      
      for(CityNode city : cityList) {
         for(House house : city.houses()) {
            if(house.player().equals(player)) {
               goodsCollected.add(city.good());
            }
         }
      }
      
      return goodsCollected;
   }
   
   private void setProvinceGood() {
      Good good = Good.BRICK;
      
      for(CityNode city : cityList) {
         if(city.good().PRICE > good.PRICE) {
            good = city.good();
         }
      }
   }
   
                                             //GETTERS - SETTERS
                                             
   public String getName() {
      return name;
   }
   
   public ResourceStatus getStatus() {
      return status;
   }
   
   public void setGood(Good good) {
      provinceGood = good;
   }
   
   public void setSestercii(int sestercii) {
      provinceSestercii = sestercii;
   }
}