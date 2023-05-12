// represents a stack of cards
// author: jonah cook

package src.game.cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
   
   private ArrayList<PersonalityCard> stack;
   
   public CardStack() {
      stack = new ArrayList<PersonalityCard>();
   }
   
   // adds cards to the top of the pile in order
   public void addToTop(PersonalityCard... o) {
      for (PersonalityCard obj : o) stack.add(obj);
   }
   
   // adds cards to the bottom of the pile in order
   public void addToBottom(PersonalityCard... o) {
      for (PersonalityCard obj : o) stack.add(0, obj);
   }
   
   // randomly adds cards to the pile
   public void addRandomly(PersonalityCard... o) {
      for (PersonalityCard obj : o) stack.add((int) (Math.random() * stack.size()), obj);
   }
      
   // pass an instance of the card you want to check if the stack contains
   // parameter is a class type, so pass the type of the card you want like this: Diplomat.class
   // might be Diplomat.getClass() idk I dont like generics, you figure it out
   public boolean contains(Class<?> c) {
      for (PersonalityCard obj : stack) if (obj.getClass() == c) return true;
      return false;
   }
   
   // returns the total amount of cards in the stack that are a certain type
   public int count(Class<?> c) {
      int total = 0;
      for (PersonalityCard obj : stack) if (obj.getClass() == c) total++;
      return total;
   }
   
   // shuffles the card stack
   public void shuffle() {
      Collections.shuffle(stack);
   }
   
   // returns the amount of cards in the stack
   public int size() {
      return stack.size();
   }
   
   // returns the card stack
   public ArrayList<PersonalityCard> getCards() {
      return stack;
   }
   
   // returns a specific card
   public PersonalityCard get(int i) {
      return stack.get(i);
   }
   
   // clears the array
   public void clear() {
      stack = new ArrayList<PersonalityCard>();
   }

}