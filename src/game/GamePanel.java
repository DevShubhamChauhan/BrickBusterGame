package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import gui.LoginPanel;
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private Timer timer;
    private int score;
    private int currentLevel;
    private static final int GAME_SPEED = 10;
    private BrickBusterGame parentFrame;
    public GamePanel(BrickBusterGame parentFrame) {

        this.parentFrame = parentFrame;
        paddle = new Paddle();
        ball = new Ball();
        bricks = new ArrayList<>();
        currentLevel = 1;
        initializeBricks(currentLevel);
        score = 0;
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }
    private void initializeBricks(int level) {
        bricks.clear();

        int brickWidth = 60;
        int brickHeight = 20;
        int horizontalSpacing = 10;
        int verticalSpacing = 10;

        switch (4) {
            case 1:
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 8; col++) {
                        int x = col * (brickWidth + horizontalSpacing) + 17;
                        int y = row * (brickHeight + verticalSpacing) + 50;
                        Brick brick = new Brick(x, y);
                        bricks.add(brick);
                    }
                }
                break;
            case 2:

                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 8; col++) {
                        int x = (row % 2 == 0 ? col * (brickWidth + horizontalSpacing) : col * (brickWidth + horizontalSpacing) + brickWidth / 2);
                        int y = row * (brickHeight + verticalSpacing) + 50;
                        Brick brick = new Brick(x, y);
                        bricks.add(brick);
                    }
                }
                break;

            case 3:
                for (int row = 0; row < 5; row++) {
                    int colsInRow = 8 - row;
                    for (int col = 0; col < colsInRow; col++) {
                        int x = col * (brickWidth + horizontalSpacing) + row * (brickWidth / 2)+17;
                        int y = row * (brickHeight + verticalSpacing) + 50;
                        Brick brick = new Brick(x, y);
                        bricks.add(brick);
                    }
                }
                break;

            case 4:
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 8; col++) {
                        if ((row + col) % 2 == 0) {
                            int x = col * (brickWidth + horizontalSpacing)+17;
                            int y = row * (brickHeight + verticalSpacing) + 50;
                            Brick brick = new Brick(x, y);
                            bricks.add(brick);
                        }
                    }
                }
                break;

            case 5:
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 8; col++) {
                        if ((row == col) || (row + col == 9)) {
                            int x = col * (brickWidth + horizontalSpacing)+17;
                            int y = row * (brickHeight + verticalSpacing) + 50;
                            Brick brick = new Brick(x, y);
                            bricks.add(brick);
                        }
                    }
                }
                break;

            default:
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 10; col++) {
                        int x = col * (brickWidth + horizontalSpacing);
                        int y = row * (brickHeight + verticalSpacing) + 50;
                        Brick brick = new Brick(x, y);
                        bricks.add(brick);
                    }
                }
                break;
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            if (!brick.isDestroyed()) {
                brick.draw(g);
            }
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Level: " + currentLevel, 10, 40);
        paddle.setGameWidth(getWidth());
    }
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddle.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    public void actionPerformed(ActionEvent e) {
        ball.move();
        checkCollisions();
        repaint();
    }
    private void checkCollisions() {
        ball.checkPaddleCollision(paddle);
        for (Brick brick : bricks) {
            if (!brick.isDestroyed() && ball.checkBrickCollision(brick)) {
                brick.setDestroyed(true);
                score += 10;
            }
        }
        if (bricks.stream().allMatch(Brick::isDestroyed)) {
            JOptionPane.showMessageDialog(this, "Level completed! Advancing to the next level.");
            timer.stop();
            advanceLevel();
        }

        if (ball.getY() > getHeight()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Your score is: " + score);

            if (parentFrame != null) {
                String username = LoginPanel.generateUsername();
                parentFrame.gameEnded(username, score);
            }
        }
    }
    private void advanceLevel() {
        currentLevel++;
        initializeBricks(currentLevel);

        ball.resetPosition();
        paddle.resetPosition();
        ball.increaseSpeed();
        paddle.reduceSize();
        timer.start();
        repaint();
    }
    public void restartGame() {
        currentLevel = 1;
        ball.resetPosition();
        paddle.resetPosition();
        paddle.resetPosition();
        initializeBricks(currentLevel);
        score = 0;
        timer.start();
        requestFocusInWindow();
        repaint();
    }
}
