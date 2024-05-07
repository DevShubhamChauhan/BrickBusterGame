package game;
import java.awt.*;
public class Ball {
    private int x;
    private int y;
    private final int diameter;
    private int speedX;
    private int speedY;
    public Ball() {
        diameter = 20;
        x = 300;
        y = 580;
        speedX = 2;
        speedY = -3;
    }
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }
    public void move() {
        x += speedX;
        y += speedY;
        if (x <= 0 || x + diameter >= 600) {
            speedX = -speedX;
        }
        if (y <= 0) {
            speedY = -speedY;
        }
    }
    public void checkPaddleCollision(Paddle paddle) {
        Rectangle ballBounds = new Rectangle(x, y, diameter, diameter);
        Rectangle paddleBounds = paddle.getBounds();
        if (ballBounds.intersects(paddleBounds)) {
            speedY = -speedY;
            y = paddleBounds.y - diameter;
        }
    }
    public boolean checkBrickCollision(Brick brick) {
        Rectangle ballBounds = new Rectangle(x, y, diameter, diameter);
        Rectangle brickBounds = brick.getBounds();
        if (ballBounds.intersects(brickBounds)) {
            if (ballBounds.x < brickBounds.x || ballBounds.x + diameter > brickBounds.x + brickBounds.width) {
                speedX = -speedX;
            } else {
                speedY = -speedY;
            }
            return true;
        }
        return false;
    }
    public int getY() {
        return y;
    }
    public void increaseSpeed() {
        if (speedX > 0) {
            speedX += 1;
        } else {
            speedX -= 1;
        }

        if (speedY > 0) {
            speedY += 1;
        } else {
            speedY -= 1;
        }
        System.out.println("Ball speed increased: speedX = " + speedX + ", speedY = " + speedY);
    }
    public void resetPosition() {
        x = 300;
        y = 580;
    }
}
