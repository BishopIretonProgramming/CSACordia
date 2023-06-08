package src.ngui;

//  imports
import src.game.Game;
import src.gui.MainFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

/**
 * A basic class to hold all of the content of the game on one {@code JFrame}.
 *
 * @author devinlinux
 */
public class Frame extends JFrame {

    private JPanel content;
    private CardLayout layout;

    public Frame() {
        setTitle("CSACORDIA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.content = new JPanel();
        this.layout = new CardLayout();

        this.content.setLayout(layout);
        setContentPane(content);

        content.add(new StartPanel(this));
        pack();
        setVisible(true);
    }

    public void showLoadGamePanel() {

    }

    public void showPlayerCreationPanel() {
        content.add(new PlayerSetupPanel(this));
        layout.next(content);
    }

    public void showNamePanel(Game game) {
        content.add(new GameNamePanel(this, game));
        layout.next(content);
    }

    public void showMainPanel(Game game) {
        new MainFrame(game);
    }
}
