package database;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RankingDisplay extends JFrame {
    private final JTextArea rankingTextArea;

    public RankingDisplay() {
        setTitle("Player Rankings");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        Font customFont = new Font("Arial", Font.BOLD, 30);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);
        JLabel titleLabel = new JLabel("Player's Ranking", SwingConstants.CENTER);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(customFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        rankingTextArea = new JTextArea();
        rankingTextArea.setEditable(false);
        rankingTextArea.setFont(customFont);
        rankingTextArea.setForeground(Color.WHITE);
        rankingTextArea.setBackground(Color.DARK_GRAY);

        JScrollPane scrollPane = new JScrollPane(rankingTextArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        List<String> rankings = MySQLDatabaseManager.getPlayerRankings();
        displayRankings(rankings);
        setVisible(true);
    }

    private void displayRankings(List<String> rankings) {
        StringBuilder builder = new StringBuilder();
        for (String ranking : rankings) {
            builder.append(ranking).append("\n");
        }
        rankingTextArea.setText(builder.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RankingDisplay::new);
    }
}

