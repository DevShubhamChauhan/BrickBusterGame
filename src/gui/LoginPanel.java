package gui;
import game.BrickBusterGame;
import database.MySQLDatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginPanel {
    private JButton submitButton;
    private JButton signupButton;
    private JLabel titleLabel;
    private JLabel userLabel;
    private JLabel passLabel;
    private static JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame frame;
    LoginPanel() {

        frame = new JFrame("Login Form");
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.lightGray);
        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        titleLabel.setBounds(250, 60, 200, 40);
        frame.add(titleLabel);
        userLabel = new JLabel("Username:");
        userLabel.setBounds(80, 200, 120, 40);
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passLabel = new JLabel("Password:");
        passLabel.setBounds(80, 300, 120, 40);
        passLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(userLabel);
        frame.add(passLabel);

        usernameField = new JTextField();
        usernameField.setBounds(220, 200, 250, 40);
        passwordField = new JPasswordField();
        passwordField.setBounds(220, 300, 250, 40);
        frame.add(usernameField);
        frame.add(passwordField);
        submitButton = new JButton("Login");
        submitButton.setBounds(160, 400, 120, 50);
        signupButton = new JButton("Signup");
        signupButton.setBounds(300, 400, 120, 50);
        frame.add(submitButton);
        frame.add(signupButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupAction();
            }
        });
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static String generateUsername(){
        return usernameField.getText();
    }
    private void loginAction() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (MySQLDatabaseManager.validateUser(username, password)) {
            BrickBusterGame game = new BrickBusterGame(username);
            game.setVisible(true);
            JOptionPane.showMessageDialog(null, "Welcome: " + username);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
        }
    }

    private void signupAction() {
        SignupPanel signupPanel = new SignupPanel();
        signupPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPanel();
            }
        });
    }
}