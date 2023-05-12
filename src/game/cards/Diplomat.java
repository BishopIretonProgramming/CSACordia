package src.game.cards;

//  imports
import src.game.cards.godtype.Jvpiter;
import src.game.Player;

import java.awt.Graphics;

public class Diplomat extends PersonalityCard implements Jvpiter {
   private PersonalityCard copied;

   public Diplomat(Player myPlayer, PersonalityCard copy) {
      super(myPlayer, 0, 0, 0, 0, 0, 2);
      this.copied = copy;
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Play any card shown by any other player
      copied.doAction();
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * scorePoints();
   }
   
   @Override
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