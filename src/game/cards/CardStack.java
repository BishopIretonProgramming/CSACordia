// represents a stack of cards
// author: jonah cook

package src.game.cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
   
   private ArrayList<Object> stack;
   
   public CardStack() {
      stack = new ArrayList<Object>();
   }
   
   // adds cards to the top of the pile in order
   public void addToTop(Object... o) {
      for (Object obj : o) if (checkCard(obj)) stack.add(obj);
   }
   
   // adds cards to the bottom of the pile in order
   public void addToBottom(Object... o) {
      for (Object obj : o) if (checkCard(obj)) stack.add(0, obj);
   }
   
   // randomly adds cards to the pile
   public void addRandomly(Object... o) {
      for (Object obj : o) if (checkCard(obj)) stack.add((int) (Math.random() * stack.size()), obj);
   }
   
   // checks if an object is eligible to be added to the stack
   public boolean checkCard(Object o) {
      if (o instanceof PersonalityCard) return true;
      return false;
   }
   
   // pass an instance of the card you want to check if the stack contains
   // parameter is a class type, so pass the type of the card you want like this: Diplomat.class
   // might be Diplomat.getClass() idk I dont like generics, you figure it out
   public boolean contains(Class<?> c) {
      for (Object obj : stack) if (obj.getClass() == c) return true;
      return false;
   }
   
   // returns the total amount of cards in the stack that are a certain type
   public int count(Class<?> c) {
      int total = 0;
      for (Object obj : stack) if (obj.getClass() == c) total++;
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
   
   public void clear() {
      stack = new ArrayList<Object>();
   }

}