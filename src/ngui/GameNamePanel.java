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
        startButton = new JButton("Start Game");
        startButton.setEnabled(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameName = nameField.getText();
                if (!gameName.isEmpty()) {
                    frame.showMainPanel();
                }
            }
        });

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(nameField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(startButton);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

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
