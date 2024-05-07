package game;

import java.awt.*;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean destroyed;
    public Brick(int x, int y) {
        width = 60;
        height = 20;
        this.x = x;
        this.y = y;
        destroyed = false;
    }
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, width, height);
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    public boolean isDestroyed() {
        return destroyed;
    }
}
