// represents a stack of cards
// author: jonah cook

package src.game.cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack extends ArrayList<PersonalityCard> {
   
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
    * Returns the top 5 cards which are available to buy
    * @return the top 5 cards of this which are available to buy
    */
   public ArrayList<PersonalityCard> getAvailable() {
      if(this.size() > 5) {
         return (ArrayList<PersonalityCard>)this.subList(0, 5);
      } else {
         return this;
      }
   }
}