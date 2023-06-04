package src.gui.pop_ups;

/*
 * @Author: Joseph Murray
 * Created: 6/4/2023
 * PrefectPopUp:
*/

import javax.swing.JButton;
import javax.swing.JPanel;

import src.gui.GUITools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrefectPopUp extends PopUp {
    private JButton collectSestertiiButton;
    private JButton collectGoodsButton;

    public PrefectPopUp() {
        super("Prefect");

        createButtons();
    }

    private void createButtons() {
        collectSestertiiButton = GUITools.createButton((JPanel) getContentPane(), "Collect Sestertii", 100, 100, 100, 50);
        collectGoodsButton = GUITools.createButton((JPanel) getContentPane(), "Collect Goods", 300, 100, 100, 50);

        collectSestertiiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //collect
                dispose();
            }
        });

        collectGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //collect
                dispose();
            }
        });
    }
}