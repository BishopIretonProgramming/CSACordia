package src.game;

/*
   @Author Joseph Murray
   5/9/2023
   Province - representation of game provinces, used for good collection from tribune cards
*/

import java.util.ArrayList;

public class Province {
   private ArrayList<City> cityList = new ArrayList<City>();
   private String name;
   private Good provinceGood;
   private ResourceStatus status;
   private int provinceSestercii;
   
   public enum ResourceStatus {
      GOODS,
      SESTERCII
   }
   
                                             //METHODS
                                             
   public Province(String name, ArrayList<City> cityList) {
      this.name = name;
      this.cityList = cityList;
      setProvinceGood();
      
      status = ResourceStatus.GOODS;
   }
   
   public int collectSestercii() {
      status = ResourceStatus.GOODS;
      
      return provinceSestercii;
   }
   
   //TODO: Collect goods based on good of cities in province
   public ArrayList<Good> collectGoods() {
      status = ResourceStatus.SESTERCII;
      
      ArrayList<Good> goodsCollected = new ArrayList<Good>();      
      goodsCollected.add(provinceGood);
      
      for(City city : cityList) {
         //goodsCollected.add(city.getGood());
      }
      
      return goodsCollected;
   }
   
   private void setProvinceGood() {
      Good good = Good.BRICK;
      
      //TODO: select most valued good from province city to become province good
      /*for(City city : cityList) {
         if(city.getGood().PRICE > good.Price) {
            good = city.getGood();
         }
      }*/
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