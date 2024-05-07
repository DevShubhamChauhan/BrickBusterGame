package gui;
import database.MySQLDatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupPanel extends JFrame {
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signupButton;

    public SignupPanel() {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setTitle("Signup Form");
        setLayout(null);
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Signup");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setBounds(250, 40, 150, 50);
        add(titleLabel);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 140, 100, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(nameLabel);

        nameField = new JTextField("Enter your name..");
        nameField.setBounds(250, 140, 250, 30);
        nameField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (nameField.getText().equals("Enter your name..")) {
                    nameField.setText("");
                }
            }
        });
        add(nameField);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 220, 150, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(250, 220, 250, 30);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 300, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 300, 250, 30);
        add(passwordField);

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 380, 190, 30);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250, 380, 250, 30);
        add(confirmPasswordField);

        signupButton = new JButton("Signup");
        signupButton.setBounds(220, 470, 160, 40);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupAction();
            }
        });
        add(signupButton);
    }
    private void signupAction() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
            return;
        }
        MySQLDatabaseManager.addUser(name, username, password);
        JOptionPane.showMessageDialog(null, "Signup successful!\nName: " + name + "\nUsername: " + username);
        dispose();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignupPanel().setVisible(true);
            }
        });
    }
}
