//package game;
//
//import java.awt.*;
//
//public class Paddle {
//    // Paddle properties
//    private int x; // Paddle's x-coordinate
//    private int width;
//    private int height;
//    private int speed; // Paddle movement speed
//    private int gameWidth; // Width of the game panel
//
//    // Constructor
//    public Paddle() {
//        width = 100; // Paddle width
//        height = 20; // Paddle height
//        speed = 10; // Paddle speed
//        x = 250; // Initial x-coordinate
//    }
//
//    // Set the game panel width (needed for movement constraints)
//    public void setGameWidth(int gameWidth) {
//        this.gameWidth = gameWidth;
//    }
//
//    // Draw the paddle
//    public void draw(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(x, 600, width, height); // Draw the paddle at a fixed y-coordinate (600)
//    }
//
//    // Move the paddle left
//    public void moveLeft() {
//        x = Math.max(0, x - speed); // Prevent moving outside the left edge
//    }
//
//    // Move the paddle right
//    public void moveRight() {
//        x = Math.min(gameWidth - width, x + speed); // Prevent moving outside the right edge
//    }
//
//    // Stop the paddle movement (not needed for this example, but useful for more complex control)
//    public void stop() {
//        // No action needed here
//    }
//
//    // Get the paddle's bounding rectangle for collision detection
//    public Rectangle getBounds() {
//        return new Rectangle(x, 600, width, height);
//    }
//    public void resetPosition() {
//        // Reset the paddle's position to the starting position
//        x = 250; // Initial x-coordinate
//    }
//
//
//}
package game;

import java.awt.*;

public class Paddle {
    private int x;
    private int width;
    private int height;
    private int speed;
    private int gameWidth;
    public Paddle() {
        width = 100;
        height = 20;
        speed = 10;
        x = 250;
    }
    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, 600, width, height);
    }
    public void moveLeft() {
        x = Math.max(0, x - speed);
    }
    public void moveRight() {
        x = Math.min(gameWidth - width, x + speed);
    }
    public void stop() {
    }
    public Rectangle getBounds() {
        return new Rectangle(x, 600, width, height);
    }
    public void reduceSize() {
        width = Math.max(40, width - 12);
        System.out.println("Paddle width reduced: " + width);
    }
    public void resetPosition() {
        x = 250;
    }
}

