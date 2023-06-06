// represents a stack of cards
// author: jonah cook
// edited by Rory McGuire

package src.game.cards;

import src.game.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Graphics;

public class CardStack extends ArrayList<PersonalityCard> {
   
   public final int[] CLOTHMODIFIERS = {0, 1, 1, 1, 1, 2, 2}; //the additional cloth price players might need to pay for cards

   public CardStack() {
      super();
      init();
   }

   public void init() {
      this.addAll(phaseI());
      this.addAll(phaseII());
      this.addAll(phaseIII());
      this.addAll(phaseIV());
      this.addAll(phaseV());

      updateDisplayedCards();
   }

   /**
    * Attempts to purchase a card and returns true and adds the card to the player's card list if able to purchase
    * @param x the index of the card (must be within the range of displayed cards)
    * @param p the player attempting to purchase the card
    * @return whether the purchase was successful
    */
   public boolean purchaseCard(int x, Player p) {
      if(x >= getAvailable().size() || x < 0) throw new IllegalArgumentException("index x is outside accepted range");
      PersonalityCard pc = getAvailable().get(x);
      
      if(p.canAfford(pc.getPrice())) {
         pc.setMyPlayer(p);
         p.cards().add(pc);
         this.remove(x);

         updateDisplayedCards();
         return true;
      }
      return false;
   }

   private void updateDisplayedCards() {
      ArrayList<PersonalityCard> top = getAvailable();
      for(int i = 0; i < top.size(); i ++) {
         top.get(i).setPos(434 + i*85, 50);
         top.get(i).setClothModifier(CLOTHMODIFIERS[i]);
      }
   }

   public void draw(Graphics g) {
      for(PersonalityCard p : getAvailable()) {
         System.out.println("H");
         p.draw(g);
      }
   }

   public ArrayList<PersonalityCard> phaseI() {
      ArrayList<PersonalityCard> a = new ArrayList<PersonalityCard> ();
      a.add(new Mason(null));
      a.add(new Smith(null));
      a.add(new Mercator(null, false));
      a.add(new Colonist(null));
      a.add(new Diplomat(null, 0, 0, 1, 0, 0, "Satvrnvs"));
      a.add(new Farmer(null));
      a.add(new Architect(null, false));
      a.add(new Prefect(null, false));
      Collections.shuffle(a);
      return a;
   }
   public ArrayList<PersonalityCard> phaseII() {
      ArrayList<PersonalityCard> a = new ArrayList<PersonalityCard> ();
      a.add(new Mercator(null, false));
      a.add(new Colonist(null));
      a.add(new Prefect(null, false));
      a.add(new Weaver(null));
      a.add(new Architect(null, false));
      a.add(new Consul(null));
      a.add(new Vintner(null));
      Collections.shuffle(a);
      return a;
   }
   public ArrayList<PersonalityCard> phaseIII() {
      ArrayList<PersonalityCard> a = new ArrayList<PersonalityCard> ();
      a.add(new Consul(null));
      a.add(new Architect(null, false));
      a.add(new Colonist(null));
      a.add(new Diplomat(null, 0, 1, 0, 0, 0, "Satvrnvs"));
      a.add(new Mercator(null, false));
      a.add(new Prefect(null, false));
      Collections.shuffle(a);
      return a;
   }
   public ArrayList<PersonalityCard> phaseIV() {
      ArrayList<PersonalityCard> a = new ArrayList<PersonalityCard> ();
      a.add(new Consul(null));
      a.add(new Prefect(null, false));
      a.add(new Colonist(null));
      a.add(new Diplomat(null, 0, 0, 1, 0, 0, "Mercvrivs"));
      a.add(new Architect(null, false));
      Collections.shuffle(a);
      return a;
   }
   public ArrayList<PersonalityCard> phaseV() {
      ArrayList<PersonalityCard> a = new ArrayList<PersonalityCard> ();
      a.add(new Consul(null));
      a.add(new Prefect(null, false));
      a.add(new Diplomat(null, 0, 1, 0, 0, 0, "Mars"));
      a.add(new Mercator(null, false));
      Collections.shuffle(a);
      return a;
   }

   /**
    * Returns the top 7 cards which are available to buy
    * @return the top 7 cards of this which are available to buy
    */
   public ArrayList<PersonalityCard> getAvailable() {
      if(this.size() > 7) {
         return new ArrayList<PersonalityCard>(this.subList(0, 7));
      } else {
         return this;
      }
   }
}