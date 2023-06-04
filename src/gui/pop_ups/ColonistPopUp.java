package src.gui.pop_ups;

/*
 * @Author: Joseph Murray
 * Created: 6/4/2023
 * ColonistPopUp: 
*/

import javax.swing.JButton;
import javax.swing.JPanel;

import src.gui.GUITools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColonistPopUp extends PopUp {
    private JButton buyColonistButton;
    private JButton collectSestertiiButton;

    public ColonistPopUp() {
        super("Colonist");

        createButtons();
    }

    private void createButtons() {
        buyColonistButton = GUITools.createButton((JPanel) getContentPane(), "Buy Colonist", 100, 100, 100, 50);
        collectSestertiiButton = GUITools.createButton((JPanel) getContentPane(), "Collect Sestertii", 300, 100, 100, 50);

        buyColonistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buy a Colonist
                dispose();
            }
        });

        collectSestertiiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Continue without buying a colonist
                dispose();
            }
        });
    }
}