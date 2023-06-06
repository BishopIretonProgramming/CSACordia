package src.ngui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.game.Game;

public class GameNamePanel extends JPanel {
    private JTextField nameField;
    private JButton startButton;
    private Frame frame;
    private Game game;

    public GameNamePanel(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;
        setLayout(new BorderLayout());

        nameField = new JTextField(20);
        nameField.setFont(nameField.getFont().deriveFont(60f));
        startButton = new JButton("Start Game");
        startButton.setEnabled(false);

        startButton.addActionListener(e -> {
            String gameName = nameField.getText();
            if (!gameName.isEmpty()) {
                game.setName(gameName);
                frame.showMainPanel(game);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.EAST);

        add(panel, BorderLayout.CENTER);

        nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validateButton();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validateButton();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validateButton();
            }
        });
    }

    private void validateButton() {
        String gameName = nameField.getText();
        startButton.setEnabled(!gameName.isEmpty());
    }
}
