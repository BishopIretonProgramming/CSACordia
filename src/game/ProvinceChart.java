package src.game;

/**
 * @author Joseph Murray
 * 5/9/2023
 * ProvinceGoodsChart - The lil chart with province goods
*/

import java.util.ArrayList;
import src.game.map.CityNode;

public class ProvinceChart {
   private static ProvinceChart provinceChartInstance = null;
   private ArrayList<Province> provinceList = new ArrayList<Province>();
   
   private ProvinceChart() {
      addProvinces();
   }
   
   //method ensures only one instance of ProvinceChart can be created
   public static ProvinceChart getInstance() {
      if(provinceChartInstance == null) {
         provinceChartInstance = new ProvinceChart();
      }
      
      return provinceChartInstance;
   }
   
                                             //METHODS

   /**
    * Collect goods or sestertii when Prefect or Praefectus Magnus are used
    * @param: String - the province name as a String, can later modify to accept Province objects if needed
    * @param: Player - the player who played the card
    * @param: boolean - false if the card played is Prefect, true if Praefectus Magnus
    */
   public void collect(String provinceName, Player player, boolean isMagnus) {
      Province province = findProvince(provinceName);
      
      if(province.getStatus() == Province.ResourceStatus.GOODS) {
         ArrayList<Good> goods = province.collectGoods(player);
         
         for(Good good : goods) {
            player.storeHouse().add(good);
         }

         if(isMagnus) {
            player.storeHouse().add(goods.get(0));
         }
      }
      else {
         int sestertii = 0;
         
         for(Province currentProvince : provinceList) {
            if(currentProvince.getStatus() == Province.ResourceStatus.SESTERCII) {
               sestertii += currentProvince.collectSestercii();
            }
         }

         player.addSestertii(sestertii);
      }
   }
   
   //takes the province name and finds the corresponding Province object
   private Province findProvince(String provinceName) {
      for(Province province : provinceList) {
         if(provinceName.equals(province.getName())) {
            return province;
         }
      }
      
      return null;
   }
   
   //TODO: add cities, either in this class or passed from another
   private void addProvinces() {
      provinceList.add(new Province("Britania", new ArrayList<CityNode>(2)));
      provinceList.add(new Province("Gallia", new ArrayList<CityNode>(3)));
      provinceList.add(new Province("Hispania", new ArrayList<CityNode>(3)));
      provinceList.add(new Province("Germania", new ArrayList<CityNode>(2)));
      provinceList.add(new Province("Dacia", new ArrayList<CityNode>(3)));
      provinceList.add(new Province("Italia", new ArrayList<CityNode>(3)));
      provinceList.add(new Province("Hellas", new ArrayList<CityNode>(2)));
      provinceList.add(new Province("Asia", new ArrayList<CityNode>(3)));
      provinceList.add(new Province("Syria", new ArrayList<CityNode>(2)));
      provinceList.add(new Province("Aegyptus", new ArrayList<CityNode>(3)));
      provinceList.add(new Province("Lybia", new ArrayList<CityNode>(2)));
      provinceList.add(new Province("Mauretania", new ArrayList<CityNode>(2)));
   }
}