package src.game.cards;

//  imports
import src.game.cards.godtype.Vesta;
import src.game.Player;

import java.awt.Graphics;

public class Senator extends PersonalityCard implements Vesta {
   public final CardImage IMAGE = CardImage.SENATOR;
   
   public Senator(Player myPlayer) {
      super(myPlayer, 0, 0, 0, 0, 0, 1);
   }
   
   @Override
   public void doAction() {
      //TO DO
      //Buy up to two cards from the display
      setPlayed(true);
   }
   
   @Override
   public int calculatePoints() {
      return getVictoryMultiplier() * vestaScorePoints();
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