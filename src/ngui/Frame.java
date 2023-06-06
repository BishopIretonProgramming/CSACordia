package src.ngui;

//  imports
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class Frame extends JFrame {

    private JPanel content;
    private CardLayout layout;

    public Frame() {
        setTitle("Concordia");
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

    public void showMainPanel() {

    }
}
