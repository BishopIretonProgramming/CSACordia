package src.ngui;

import javax.swing.*;
import java.awt.*;

import src.game.Game;

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
