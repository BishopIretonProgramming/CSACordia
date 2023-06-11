package src.ngui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.login.LoginSystem;
import src.login.PasswordException;
import src.login.UsernameException;

/** @author Mrs. Terri Kelly
 *  
 *  Allows user to register or login
 */
public class LoginDialog extends JDialog {

    private final static Color BACKGROUND_COLOR = Color.BLACK;
    private final static Color FOREGROUND_COLOR = Color.WHITE;

    private JTextField username, passwordTextField;
    private JPasswordField password;
    private JLabel messageLabel;
    public boolean success;
    
    public LoginDialog(Frame game) {
        super(game, "Enter Username and Password", true);

        success = false;

        this.add(createPanel());

        setDialogProperties();
    }

    public boolean getSuccess() { return success;}
    public String getUsername() { return username.getText();}

    private JPanel createPanel() {
        Dimension margin = new Dimension(20, 0);
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
        outerPanel.setBackground(BACKGROUND_COLOR);
 
        outerPanel.add(Box.createRigidArea(margin));

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createFieldsPanel());
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createButtonsPanel());
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createMessagePanel());
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        outerPanel.add(panel);
        outerPanel.add(Box.createRigidArea(margin));
        return outerPanel;
    }

    private void setDialogProperties() {
        setSize(550, 200);
        setLocation(400, 250);
        pack();
        setVisible(true);
    }

    private JPanel createFieldsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createUserPanel());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createPasswordPanel());
        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel();       
        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(createLabel("Username: "));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        username = createTextField(9);
        panel.add(username);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(FOREGROUND_COLOR);
        return label;
    }

    private JTextField createTextField(int size) {
        JTextField field = new JTextField(size);
        return field;
    }

    private JPasswordField createPasswordField(int size) {
        JPasswordField field = new JPasswordField(size);
        return field;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));          
        panel.add(createLabel("Password: "));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        password = createPasswordField(9);
        passwordTextField = createTextField(9);
        panel.add(passwordTextField);
        panel.add(Box.createRigidArea(new Dimension(50,0)));          
        return panel;
    }

    /**
     * @return JPanel with the login information and buttons
     */

    private JPanel createButtonsPanel() {
        Dimension margin = new Dimension(10, 0);
        JPanel panel = new JPanel();

        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(margin));
        panel.add(createRegisterButton());
        panel.add(Box.createRigidArea(margin));
        panel.add(createLoginButton());
        panel.add(Box.createRigidArea(margin));
        panel.add(createCancelButton());
        panel.add(Box.createRigidArea(margin));
        return panel;
    }

    private JButton createRegisterButton() {
        JDialog me = this;
        JButton register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("");
                String user = username.getText();
            //    String pass = password.getPassword().toString();
                String passTF = passwordTextField.getText();
                try {
                    // This is causing an error with a JPassword.getPassword() and a JTextField.getText() for the password
                    success = LoginSystem.createUser(user, passTF); 
                } catch (UsernameException ue) {
                    messageLabel.setText("Username issue");
                    messageLabel.setVisible(true);
                    System.out.println(ue.getMessage());
                } catch (PasswordException pe) {
                    messageLabel.setText("Password issue");
                    messageLabel.setVisible(true);;
                    System.out.println(pe.getMessage());
                }
                if (success) {
                    me.setVisible(false);
                } else {
                    messageLabel.setText("Register User Failed" + " - " + messageLabel.getText());
                    messageLabel.setVisible(true);
                    System.out.println("Register User Failed");
                }
            }
        });
        return register;
    }
     private JButton createLoginButton() {

        JDialog me = this;
        JButton button = new JButton("Login");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("");
                String user = username.getText();
                String pass = password.getPassword().toString();
                success = LoginSystem.verifyLogin(user, pass);
                if (success) {
                    me.setVisible(false);
                } else {
                    messageLabel.setText("Login Failed" + " - " + messageLabel.getText());
                    messageLabel.setVisible(true);
                    System.out.println("Login Failed");
            }

            }
        });
        return button;
    }
     private JButton createCancelButton() {
        JDialog me = this;
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success = false;
                me.dispose();
            }
        });
        return cancel;
    }

       private JPanel createMessagePanel() {
        Dimension margin = new Dimension(10, 0);
        messageLabel = createLabel("Errors will be displayed here");
        messageLabel.setVisible(false);
        JPanel panel = new JPanel();

        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(margin));
        panel.add(messageLabel);
       panel.add(Box.createRigidArea(margin));
       return panel;
       }
 
    
}

class TestDialog {
    public static void main(String[] args) {
        Frame game = new Frame();
        LoginDialog login = new LoginDialog(game); // The way this is written, a separate call will be used for each user.
         if (! login.getSuccess()) {
            System.out.println("Login Failed");
            System.exit(0);
        } else {
            System.out.printf("User %s successfully logged in\n", login.getUsername());
        }

    }
}