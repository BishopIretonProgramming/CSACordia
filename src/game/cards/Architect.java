package src.game.cards;

//  imports
import src.game.cards.godtype.Jvpiter;
import src.game.Player;

import java.awt.Graphics;

public class Architect extends PersonalityCard implements Jvpiter {
   public final CardImage IMAGE = CardImage.ARCHITECT;
   
   public Architect(Player myPlayer, int toolPrice) {
      super(myPlayer, 0, 0, toolPrice, 0, 0, 2);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Move colonists
      //Build Houses
      setPlayed(true);
   }
   
   @Override // calculates points
   public int calculatePoints() {
      return getVictoryMultiplier() * scorePoints();
   }
   
   @Override // updates card
   public void update() {
      
   }
   
   @Override
   public void draw(Graphics g) {
      
   }

   @Override
   public Player getPlayer() {
      return super.getMyPlayer();
   }
   
}