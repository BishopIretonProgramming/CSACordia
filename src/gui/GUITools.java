package src.gui;

/*
 * @Author: Joseph Murray
 * Created: 6/2/2023
 * GUITools: helpful methods for using default Java Graphics
 * Note: This class is optimized for my own preferences, 
 * and while these graphics can be used with a java layout manager, 
 * they are optimized to be used alone
*/

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.image.BufferedImage;

public class GUITools {

    private GUITools() {

    }

    /**
     * Creates a JLabel with custom image
     * @param panel
     * @param image
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @return JLabel with set features
    */
    public static JLabel createLabel(JPanel panel, BufferedImage image, int xPosition, int yPosition, int width, int height) {
        JLabel label = new JLabel();

        label.setIcon(new ImageIcon(image));
        label.setBounds(xPosition, yPosition, width, height);

        panel.add(label);

        return label;
    }

    /**
     * Creates a default JLabel with custom text
     * @param panel
     * @param text
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @return JLabel with set features
    */
    public static JLabel createLabel(JPanel panel, String text, int xPosition, int yPosition, int width, int height) {
        JLabel label = new JLabel();

        label.setBounds(xPosition, yPosition, width, height);
        label.setText(text);

        panel.add(label);

        return label;
    }

    /**
     * Creates a JButton with custom image
     * @param panel
     * @param image
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @return JButton with set features
    */
    public static JButton createButton(JPanel panel, BufferedImage image, int xPosition, int yPosition, int width, int height) {
        JButton button = new JButton();

        button.setIcon(new ImageIcon(image));
        button.setBounds(xPosition, yPosition, width , height);
        button.setMargin(new Insets(-1, -1, -1, -1));

        panel.add(button);

        return button;
    }

    /**
     * creates a default JButton with custom text
     * @param panel
     * @param text
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @return JButton with set features
    */
    public static JButton createButton(JPanel panel, String text, int xPosition, int yPosition, int width, int height) {
        JButton button = new JButton();

        button.setBounds(xPosition, yPosition, width , height);
        button.setText(text);

        panel.add(button);

        return button;
    }

    /**
     * Creates a JRadioButton with custom Image
     * @param panel
     * @param image image shown when unselected
     * @param pressedImage image shown when selected
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @return JRadioButton with set preferences
    */
    public static JRadioButton createRadioButton(JPanel panel, BufferedImage image, BufferedImage pressedImage, int xPosition, int yPosition, int width, int height) {
        JRadioButton radioButton = new JRadioButton();

        radioButton.setIcon(new ImageIcon(image));
        radioButton.setPressedIcon(new ImageIcon(pressedImage));
        radioButton.setBounds(xPosition, yPosition, width , height);
        radioButton.setMargin(new Insets(-1, -1, -1, -1));

        panel.add(radioButton);

        return radioButton;
    }

    /**
     * Creates a default JRadioButton with custom text
     * @param panel
     * @param text
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     * @return JRadioButton with set preferences
    */
    public static JRadioButton createRadioButton(JPanel panel, String text, int xPosition, int yPosition, int width, int height) {
        JRadioButton radioButton = new JRadioButton();

        radioButton.setBounds(xPosition, yPosition, width , height);
        radioButton.setText(text);

        panel.add(radioButton);

        return radioButton;
    }
}