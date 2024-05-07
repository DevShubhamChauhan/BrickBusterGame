package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MySQLDatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASS = "***";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void addUser(String name, String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
    }
    public static boolean validateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            isValid = rs.next();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return isValid;
    }
    public static void updatePlayerMaxScore(String username, int max_score) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String selectSql = "SELECT max_score FROM users WHERE username = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            int currentMaxScore = 0;
            if (rs.next()) {
                currentMaxScore = rs.getInt("max_score");
            }

            if (max_score > currentMaxScore) {
                rs.close();
                stmt.close();

                String updateSql = "UPDATE users SET max_score = ? WHERE username = ?";
                stmt = conn.prepareStatement(updateSql);
                stmt.setInt(1, max_score);
                stmt.setString(2, username);

                stmt.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {

            closeResources(conn, stmt);
        }
    }
    public static List<String> getPlayerRankings() {
        List<String> rankings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT username, max_score FROM users ORDER BY max_score DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                int maxScore = rs.getInt("max_score");
                rankings.add(username + ": " + maxScore);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return rankings;
    }
    private static void closeResources(Connection conn, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    private static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}




