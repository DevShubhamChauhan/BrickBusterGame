package game;

import database.MySQLDatabaseManager;
import database.RankingDisplay;

import javax.swing.*;
import java.awt.*;
public class BrickBusterGame extends JFrame {
    private GamePanel gamePanel;
    private JButton restartButton;
    private String username;

    public BrickBusterGame(String username) {
        this.username = username;
        setTitle("Brick Buster Game");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        gamePanel = new GamePanel(this);
        add(gamePanel, BorderLayout.CENTER);

        restartButton = new JButton("Restart");
        restartButton.setBackground(Color.RED);
        restartButton.setForeground(Color.WHITE);
        restartButton.addActionListener(e -> gamePanel.restartGame());
        add(restartButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void gameEnded(String username, int score) {
        MySQLDatabaseManager.updatePlayerMaxScore(username, score);
        RankingDisplay rankingDisplay = new RankingDisplay();
        rankingDisplay.setVisible(true);
    }
    public static void main(String[] args) {
        new BrickBusterGame("User Name");
    }
}

