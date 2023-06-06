package src.ngui;

//  imports
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {

    private Frame frame;

    public StartPanel(Frame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("CSACORDIA");
        title.setAlignmentX(CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton loadGame = new JButton("Load Game");
        JButton newGame = new JButton("New Game");

        loadGame.addActionListener(e -> {
            frame.showLoadGamePanel();
        });

        newGame.addActionListener(e -> {
            frame.showPlayerCreationPanel();
        });

        buttonPanel.add(loadGame);
        buttonPanel.add(newGame);

        JLabel boxImage = new JLabel();
        try {
            boxImage.setIcon(new ImageIcon(ImageIO.read(new File(String.format("resources%simages%sConcordia board.jpg", File.separator, File.separator)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boxImage.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalGlue());
        add(boxImage);
        add(Box.createVerticalGlue());
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }
}
