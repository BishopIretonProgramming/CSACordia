package src.gui.pop_ups;

/*
 * @Author: Joseph Murray
 * Created: 6/3/2023
 * PopUp: Creates a pop-up JFrame
*/

import javax.swing.JPanel;
import javax.swing.JFrame;

public class PopUp extends JFrame {
    private JPanel contentPane = new JPanel();

    public PopUp() {
        this("Pop-up");
    }

    public PopUp(String name) {
        super(name);

        setContentPane(contentPane);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        toFront();
        setBounds(200, 200, 100, 200);
    }
}
