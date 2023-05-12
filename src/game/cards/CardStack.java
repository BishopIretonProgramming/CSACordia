// represents a stack of cards
// author: jonah cook

package src.game.cards;

import java.util.ArrayList;

public class CardStack {
   
   private ArrayList<Object> stack;
   
   public CardStack() {
      stack = new ArrayList<Object>();
   }
   
   public void addToTop(Object... o) {
      for (Object obj : o) if (checkCard(obj)) stack.add(obj);
   }
   
   public void addToBottom(Object... o) {
      for (Object obj : o) if (checkCard(obj)) stack.add(0, obj);
   }
   
   public void addRandomly(Object... o) {
      for (Object obj : o) if (checkCard(obj)) stack.add((int) (Math.random() * stack.size()), obj);
   }
   
   public boolean checkCard(Object o) {
      if (o instanceof PersonalityCard) return true;
      return false;
   }
   
   // pass an instance of the card you want to check if the stack contains
   public boolean contains(Class<?> c) {
      for (Object obj : stack) if (obj.getClass() == c) return true;
      return false;
   }

}