package src.ngui;

//  imports
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import src.game.Game;
import src.game.map.Map;
import src.game.player.Player;

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
        startButton = new JButton("Continue");

        addButton.addActionListener(e -> {
            if (playerCount < MAX_PLAYERS) {
                addPlayerCard();
                validateButtons();
            }
        });

        removeButton.addActionListener(e -> {
            if (playerCount > MIN_PLAYERS) {
                removePlayerCard();
                validateButtons();
            }
        });

        startButton.addActionListener(e -> {
            frame.showNamePanel(new Game("", new Map(Map.IMPERIUM), getPlayerNames(), getPlayerColors(), getPlayerNames().get(0)));
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

            colorField.addActionListener(e -> validateButtons());

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

    public List<Player> getPlayerNames() {
        List<Player> playerNames = new ArrayList<>();

        Component[] components = playerCardsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel playerCard = (JPanel) component;
                Component[] cardComponents = playerCard.getComponents();
                JTextField nameField = null;

                for (Component cardComponent : cardComponents) {
                    if (cardComponent instanceof JTextField) {
                        nameField = (JTextField) cardComponent;
                        break;
                    }
                }

                if (nameField != null) {
                    String playerName = nameField.getText();
                    playerNames.add(new Player(playerName));
                }
            }
        }

        return playerNames;
    }

    public List<Color> getPlayerColors() {
        List<Color> playerColors = new ArrayList<>();

        Component[] components = playerCardsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel playerCard = (JPanel) component;
                Component[] cardComponents = playerCard.getComponents();
                JComboBox<String> colorField = null;

                for (Component cardComponent : cardComponents) {
                    if (cardComponent instanceof JComboBox) {
                        colorField = (JComboBox<String>) cardComponent;
                        break;
                    }
                }

                if (colorField != null) {
                    String playerColorString = (String) colorField.getSelectedItem();
                    Color playerColor = getColorFromString(playerColorString);
                    playerColors.add(playerColor);
                }
            }
        }

        return playerColors;
    }

    private Color getColorFromString(String colorString) {
        return switch (colorString.toLowerCase()) {
            case "red" -> Color.RED;
            case "yellow" -> Color.YELLOW;
            case "blue" -> Color.BLUE;
            case "purple" -> new Color(128, 0, 128);
            case "green" -> Color.GREEN;
            default -> Color.BLACK;
        };
    }
}
