package src.ngui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class PlayerSetupPanel extends JPanel {
    private static final int MAX_PLAYERS = 5;
    private static final int MIN_PLAYERS = 2;

    private HashSet<String> usedNames;
    private HashSet<String> usedColors;
    private int playerCount;

    private JPanel playerCardsPanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton startButton;
    private Frame frame;

    public PlayerSetupPanel(Frame frame) {
        this.frame = frame;
        usedNames = new HashSet<>();
        usedColors = new HashSet<>();
        playerCount = 0;

        setLayout(new BorderLayout());

        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new BoxLayout(playerCardsPanel, BoxLayout.Y_AXIS));
        addPlayerCard();

        JScrollPane scrollPane = new JScrollPane(playerCardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(scrollPane);

        // Create a wrapper panel to center the centerPanel horizontally
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(centerPanel);

        add(wrapperPanel, BorderLayout.CENTER);

        addButton = new JButton("Add Player");
        removeButton = new JButton("Remove Player");
        startButton = new JButton("Start Game");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerCount < MAX_PLAYERS) {
                    addPlayerCard();
                    validateButtons();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerCount > MIN_PLAYERS) {
                    removePlayerCard();
                    validateButtons();
                }
            }
        });

        startButton.addActionListener(e -> {
            frame.showMainPanel();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);

        validateButtons();
    }

    private void addPlayerCard() {
        if (playerCount < MAX_PLAYERS) {
            JPanel playerCard = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JTextField nameField = new JTextField(10);
            JComboBox<String> colorField = new JComboBox<>(new String[]{"Red", "Yellow", "Blue", "Purple", "Green"});

            nameField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    validateButtons();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    validateButtons();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    validateButtons();
                }
            });

            colorField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    validateButtons();
                }
            });

            playerCard.add(new JLabel("Name:"));
            playerCard.add(nameField);
            playerCard.add(new JLabel("Color:"));
            playerCard.add(colorField);

            playerCardsPanel.add(playerCard);
            playerCount++;
            revalidate();
            repaint();
        }
    }

    private void removePlayerCard() {
        if (playerCount > MIN_PLAYERS) {
            playerCardsPanel.remove(playerCount - 1);
            playerCount--;
            revalidate();
            repaint();
        }
    }

    private void validateButtons() {
        boolean hasDuplicateName = false;
        boolean hasDuplicateColor = false;

        usedNames.clear();
        usedColors.clear();

        Component[] components = playerCardsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel playerCard = (JPanel) component;
                Component[] cardComponents = playerCard.getComponents();
                JTextField nameField = null;
                JComboBox<String> colorField = null;

                for (Component cardComponent : cardComponents) {
                    if (cardComponent instanceof JTextField) {
                        nameField = (JTextField) cardComponent;
                    } else if (cardComponent instanceof JComboBox) {
                        colorField = (JComboBox<String>) cardComponent;
                    }
                }

                if (nameField != null && colorField != null) {
                    String playerName = nameField.getText();
                    String playerColor = (String) colorField.getSelectedItem();

                    if (!usedNames.add(playerName)) {
                        hasDuplicateName = true;
                    }

                    if (!usedColors.add(playerColor)) {
                        hasDuplicateColor = true;
                    }
                }
            }
        }

        addButton.setEnabled(playerCount < MAX_PLAYERS);
        removeButton.setEnabled(playerCount > MIN_PLAYERS);
        startButton.setEnabled(!hasDuplicateName && !hasDuplicateColor && playerCount >= MIN_PLAYERS);
    }
}
