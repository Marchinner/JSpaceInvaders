package entities;

import static utilz.Constants.ENTITIES.MISSILE_SPEED;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Missile {
    private float x;
    private float y;
    private Entity shooter;
    private float speed = MISSILE_SPEED;
    private Rectangle2D.Float hitbox;
    private boolean hitTarget = false;

    public Missile(Entity shooter) {
        this.shooter = shooter;
        x = shooter.hitbox.x + shooter.hitbox.width / 2;

        switch (shooter.ship) {
            case PLAYER_SHIP -> y = shooter.hitbox.y;
            case ALIEN_SHIP -> y = shooter.hitbox.y + shooter.hitbox.height;
        }

        hitbox = new Rectangle2D.Float(x, y, 2f, 8f);
    }

    public void update() {
        if (!hitTarget) {
            hitbox.x = x;
            hitbox.y = y;
            switch (shooter.ship) {
                case ALIEN_SHIP -> y += speed;
                case PLAYER_SHIP -> y -= speed;
            }
        }
    }

    public Entity getShooter() {
        return shooter;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void draw(Graphics graphics) {
            graphics.setColor(Color.RED);
            graphics.fillRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
//            drawHitbox(graphics);
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.WHITE);
//        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public boolean hasHitTarget() {
        return hitTarget;
    }

    public void setHitTarget(boolean hitTarget) {
        this.hitTarget = hitTarget;
    }
}
