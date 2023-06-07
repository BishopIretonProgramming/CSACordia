package src.gui;

import src.game.Game;
import src.game.cards.CardStack;
import src.game.BonusBox;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.event.MouseListener;
//import java.awt.event.MouseEvent;

public class MainFrame extends JComponent implements ActionListener/*, MouseListener*/ {
    private JFrame f;
    private Game game;
    private Dimension SIZE = new Dimension(1080, 758); //original: 1200px x 842px

    public MainFrame(Game game) {
        this.game = game;
        
        f = new JFrame("Concordia");
        f.setSize(SIZE.width+17, SIZE.height+40);//to more accurately set the size
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        Container c = f.getContentPane();
        c.setBackground(Color.WHITE);
        c.add(this);

        Timer t = new Timer(1, this); //timer to assist with updating the frame
        t.start();

        //f.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(PieceImage.BOARD.pieceImage, 0, 0, SIZE.width, SIZE.height, null);
        //CardStack c = new CardStack();
        //c.draw(g);
        //BonusBox b = new BonusBox(55, 55);
        //b.draw(g);
        game.getDrawPile().draw(g);
        game.getBonusBox().draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //update if needed
        //repaint();
    }

    //For testing positions
    /*public void mousePressed(MouseEvent e) {
        System.out.println("(" + e.getX() + ", " + e.getY() + ")");
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}*/
}

class TestMainFrame {
    public static void main(String[] args) {
        Game g = null;
        MainFrame m = new MainFrame(g);
    }
}