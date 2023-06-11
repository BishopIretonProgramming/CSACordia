package src.game.player;

import java.util.HashSet;
import java.util.List;

import src.game.Colonist;
import src.game.Good;
import src.game.House;
import src.game.cards.Diplomat;
import src.game.cards.Farmer;
import src.game.cards.Mason;
import src.game.cards.PersonalityCard;
import src.game.cards.Smith;
import src.game.cards.Vintner;
import src.game.cards.Weaver;
import src.game.cards.godtype.Jvpiter;
import src.game.cards.godtype.Mars;
import src.game.cards.godtype.Mercvrivs;
import src.game.cards.godtype.Minerva;
import src.game.cards.godtype.Satvrnvs;
import src.game.cards.godtype.Vesta;
import src.game.map.CityNode;
import src.game.map.Map;
import src.game.map.Province;

public class Scoring {
    private final static int CONCORDIA_CARD_POINTS = 7;

    private Player player;
    private Map map;

    // good counts are used for Jupiter, Minerva cards and Mercvrivs cards
    private int foodCount = 0;
    private int toolCount = 0;
    private int wineCount = 0;
    private int clothCount = 0;
    private int brickCount = 0;

    public Scoring(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    /** 
      Calculates the score in the following way
        Sells all of the goods in the store house
        Counts the type of goods from the houses played on the board

        Counts the number of each type of card (God based)
            As the minerva cards are found, the score for those are added right away as there is only one of each
         
        For each of the non-Minerva Gods, increment score by
            Calling the appropriate getGodVP method sending in the number of cards of this God Type
        
        After all personality cards are added, if the Player has concordia card, add those points
    */
    
    public void setScore() {
        sellGoods();    // sell the goods the player has in the storehouse
        int goodTypes = countGoods();   // count the houses by type of good

        int score = 0;

        int vesta = 0, jvpiter = 0, satvrnvs = 0, mercvrivs = 0, mars = 0;

    // Count the number of cards for each god type
      for (PersonalityCard card : player.cards()) {
         if (card instanceof Diplomat) { // A diplomat card can be one of several god types)
            switch (((Diplomat)card).GOD) {
               case "Mars": mars++; 
                  break;
               case "Jupiter": jvpiter++; 
                  break;
               case "Mercvrivs": mercvrivs++; 
                  break;
               case "Satvrnvs": satvrnvs++; 
                  break;
            }
               
         } else {
            if (card instanceof Vesta) vesta++;
            if (card instanceof Mercvrivs) mercvrivs++;
            if (card instanceof Jvpiter) jvpiter++;
            if (card instanceof Satvrnvs) satvrnvs++;
            if (card instanceof Mars) mars++;
            if (card instanceof Minerva) { // There is only one card for each type of Minerva, grab those points here
                if (card instanceof Mason) score += brickCount * card.getVictoryMultiplier(); 
                if (card instanceof Farmer) score += foodCount * card.getVictoryMultiplier(); 
                if (card instanceof Smith) score += toolCount * card.getVictoryMultiplier(); 
                if (card instanceof Vintner) score += wineCount * card.getVictoryMultiplier();
                if (card instanceof Weaver)  score += clothCount * card.getVictoryMultiplier();
            }
         }
      }

      // increment the score for all of the non-Minerva god types
        score += getVestaVP(vesta);
        score += getJvpiterVP(jvpiter);
        score += getSatvrnvsVP(satvrnvs);
        score += getMercvrivsVP(mercvrivs, goodTypes);
        score += getMarsVP(mars);

        if (player.hasConcordiaCard()) score += CONCORDIA_CARD_POINTS;

        player.setVictoryPoints(score);
    }
    
    /** 
     * Iterates through storehouse and sells all of the goods. Used when game is over to sell all
     * of the goods before calculating the Vesti score
     */

    private void sellGoods() {
        int goodsValue = 0;
        for (Object item : player.storeHouse().getResources()) {
            if (item instanceof Good) {   
                Good good = (Good) item;
                goodsValue += good.price();
                player.storeHouse().removeGood(good);
            }
        }
        player.addSestertii(goodsValue);
    }

     /*SCORING - EMILY  and Mrs. Kelly*/ 

    /**
     * Vesta calculations: gets total amout of sestertii from money + storehouse
     * @return number of victory points from Vesta
     */
     public int getVestaVP(int vesta) {
        
         return vesta * player.sestertii()/10;
    }

    /**
     * Jvpiter calculations: number of non brick house muliplied by number of Jvpiter cards
     * Must be called after the countGoods() method is called
     * 
     * @return number of victory points from Jvpiter
     */
    public int getJvpiterVP(int jvpiterCards){
      
        return brickCount * jvpiterCards; // TODO - multiply by the getVictoryPoints for the card
    }

    /**
     * Satvrnvs calculations: number of provinces that have a players city in it muliplied by number of Satvrnvs cards
     * @return number of victory points from Satvrnvs
     */
    public int getSatvrnvsVP(int satvrnvsCards) {
        return satvrnvsCards * getProvinces(); // TODO - multiply by the getVictoryPoints for the card
    }

    public int getProvinces() {

        // can't get provinces?????
        // Provinces have an array list of city nodes, but the cities are never instantiated ... so ...
        // added a github issue

        // Player has an array list of Houses, House as city
        // Iterate through the players houses, grab the city
        // find the province from either Provinces OR Map
        // get a count of the unique provinces

        HashSet<Province> provinces = new HashSet<Province>();
        List<CityNode> cities = map.cities(); // So, map cities do not have provinces - need to establish where province is stored

        for (House house : player.houses()) {
            CityNode city = house.city();
            for (CityNode mapCity : cities) {
                if (city.equals(mapCity)) {
              //    provinces.add();
             }
            }           
        }
        
        return provinces.size(); 
    }

    /**
     * Mercvrivs calculations: number of goods produced muliplied by number of Mercvrivs cards
     * @return number of victory points from Mercvrivs
     */
    public int getMercvrivsVP(int mercvrivsCards, int goodTypes){
        return 2 * mercvrivsCards * goodTypes; // TODO - replace 2 with the getVictoryPoints for the card
    }

    /**
     * counts all the individual types of good production for use with both mercvrivs and minerva cards
     * 
     * @return number of different types of goods a user produces
     */

    private int countGoods() {
        List<House> houses = player.houses();
        int goodTypes = 0;
        for (House house: houses){
            switch(house.good()) {
                case BRICK : brickCount++; if (brickCount==1) goodTypes++; break;
                case FOOD: foodCount++; if (foodCount==1) goodTypes++; break;
                case TOOL: toolCount++; if (toolCount==1) goodTypes++; break;
                case WINE: wineCount++; if (wineCount==1) goodTypes++; break;
                case CLOTH: clothCount++; if (clothCount==1) goodTypes++; break;
            }
        }
        return goodTypes;
    }

    /**
     * Mars calculations: number of colonists on the board produced muliplied by number of Mars cards
     * count the number of colonists in the store house and subtract that from the total number of colonists
     * 
     * @return number of victory points from Mars
     */
    public int getMarsVP (int marsCards) {
        int colonistCount = 0;

         for (Object item : player.storeHouse().getResources()) {
            if (item instanceof Colonist) { 
                colonistCount++;
            }
         }
        return 2 * marsCards * (6 - colonistCount); 
        // TODO - replace 6 with a global value for total number of colonists
        // TODO - multiply by the getVictoryPoints for the card
    }   
}
