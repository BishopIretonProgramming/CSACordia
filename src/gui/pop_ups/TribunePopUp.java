package src.gui.pop_ups;

/*
 * @Author: Joseph Murray
 * Created: 6/3/2023
 * TribunePopUp: Pop-up for personality cards
*/

import javax.swing.JButton;
import javax.swing.JPanel;

import src.gui.buttons.GUITools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TribunePopUp extends PopUp {
    private JButton buyColonistButton;
    private JButton doNotBuyColonistButton;

    public TribunePopUp() {
        super("Tribune");

        createButtons();
    }

    private void createButtons() {
        buyColonistButton = GUITools.createButton((JPanel) getContentPane(), "Buy Colonist", 100, 100, 100, 50);
        doNotBuyColonistButton = GUITools.createButton((JPanel) getContentPane(), "Don't Buy Colonist", 300, 100, 100, 50);

        buyColonistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buy a Colonist
                dispose();
            }
        });

        doNotBuyColonistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Continue without buying a colonist
                dispose();
            }
        });
    }
}