package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Missile {
    private int x;
    private int y;
    private Entity shooter;
    private int speed = 1;
    private Rectangle hitbox;

    public Missile(Entity shooter) {
        this.shooter = shooter;
        x = shooter.hitbox.x + shooter.hitbox.width / 2;
        y = shooter.hitbox.y;
        hitbox = new Rectangle(x, y, 2, 8);
    }

    public void update() {
        hitbox.x = x;
        hitbox.y = y;
        y -= speed;
    }

    public Entity getShooter() {
        return shooter;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        drawHitbox(graphics);
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
