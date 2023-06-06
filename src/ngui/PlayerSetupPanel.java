package src.ngui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class PlayerSetupPanel extends JPanel {
    private static final int MAX_PLAYERS = 5;
    private static final int MIN_PLAYERS = 2;
    private int playerCount = 2;
    private JPanel playersPanel;
    private HashSet<String> usedNames;
    private HashSet<String> usedColors;
    private Frame frame;

    public PlayerSetupPanel(Frame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(0, 2));
        addPlayers();

        JScrollPane scrollPane = new JScrollPane(playersPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Player");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remove Player");
        removeButton.addActionListener(e -> {

        });
        buttonPanel.add(removeButton);

        JPanel startButtonPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startButtonPanel.add(startButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(startButtonPanel, BorderLayout.SOUTH);

        usedNames = new HashSet<>();
        usedColors = new HashSet<>();
    }

    private void addPlayers() {
        for (int i = 0; i < playerCount; i++) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new FlowLayout());

            JTextField nameField = new JTextField(10);
            playerPanel.add(nameField);

            JComboBox<String> colorCombo = new JComboBox<>(new String[]{"Red", "Blue", "Green"});
            playerPanel.add(colorCombo);

            playersPanel.add(playerPanel);
        }
    }

    private void addPlayer() {
        if (playerCount < MAX_PLAYERS) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new FlowLayout());

            JTextField nameField = new JTextField(10);
            playerPanel.add(nameField);

            JComboBox<String> colorCombo = new JComboBox<>(new String[]{"Red", "Blue", "Green"});
            playerPanel.add(colorCombo);

            playersPanel.add(playerPanel);
            playersPanel.revalidate();
            playersPanel.repaint();

            playerCount++;
        } else {
            JOptionPane.showMessageDialog(this, "Maximum player limit reached!");
        }
    }

    private void removePlayer() {
        if (playerCount > MIN_PLAYERS) {
            Component[] components = playersPanel.getComponents();
            if (components.length > 0) {
                playersPanel.remove(components[components.length - 1]);
                playersPanel.revalidate();
                playersPanel.repaint();

                playerCount--;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Minimum player limit reached!");
        }
    }
}
