package src.game;

/*
   @Author Joseph Murray
   5/9/2023
   ProvinceGoodsChart - The lil chart with province goods
*/

import java.util.ArrayList;

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

   //Collect goods or sestercii when tribune, prefect or prefectus Magnus are used
   //@Param: the province name as a String, can later modify to accept Province objects if needed
   public void collect(String provinceName) {
      Province province = findProvince(provinceName);
      
      if(province.getStatus() == Province.ResourceStatus.GOODS) {
         ArrayList<Good> goods = province.collectGoods();
      }
      else {
         int sestercii = 0;
         
         for(Province currentProvince : provinceList) {
            if(province.getStatus() == Province.ResourceStatus.SESTERCII) {
               sestercii += province.collectSestercii();
            }
         }
      }
   }
   
   //takes the province name and finds the corresponding Province object
   private Province findProvince(String provinceName) {
      for(Province province : provinceList) {
         if(provinceName == province.getName()) {
            return province;
         }
      }
      
      return null;
   }
   
   //TO-DO: add cities, either in this class or passed from another
   private void addProvinces() {
      provinceList.add(new Province("Britania", new ArrayList<City>(2)));
      provinceList.add(new Province("Gallia", new ArrayList<City>(3)));
      provinceList.add(new Province("Hispania", new ArrayList<City>(3)));
      provinceList.add(new Province("Germania", new ArrayList<City>(2)));
      provinceList.add(new Province("Dacia", new ArrayList<City>(3)));
      provinceList.add(new Province("Italia", new ArrayList<City>(3)));
      provinceList.add(new Province("Hellas", new ArrayList<City>(2)));
      provinceList.add(new Province("Asia", new ArrayList<City>(3)));
      provinceList.add(new Province("Syria", new ArrayList<City>(2)));
      provinceList.add(new Province("Aegyptus", new ArrayList<City>(3)));
      provinceList.add(new Province("Lybia", new ArrayList<City>(2)));
      provinceList.add(new Province("Mauretania", new ArrayList<City>(2)));
   }
}