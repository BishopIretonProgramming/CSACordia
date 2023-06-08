package src.ngui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import src.game.Game;

/**
 * A basic GUI component to get the name of the game for saving purposes.
 *
 * @author devinlinux.
 */
public class GameNamePanel extends JPanel {
    
    private JTextField field;
    private JButton startButton;

    private Frame frame;
    private Game game;

    public GameNamePanel(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        this.field = new JTextField(20);
        this.startButton = new JButton("Start Game");

        this.startButton.addActionListener(e -> {
            String name = this.field.getText();
            if (!name.isEmpty()) {
                this.game.setName(name);
                this.frame.showMainPanel(game);
            }
        });

        add(this.field, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;

        add(this.startButton, gbc);
    }
}
