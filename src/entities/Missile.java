package entities;

import java.awt.*;

public class Missile {
    private int x;
    private int y;
    private Entity shooter;
    private int speed = 2;
    private Rectangle hitbox;
    private boolean hitTarget = false;

    public Missile(Entity shooter) {
        this.shooter = shooter;
        x = shooter.hitbox.x + shooter.hitbox.width / 2;

        switch (shooter.ship) {
            case PLAYER_SHIP -> y = shooter.hitbox.y;
            case ALIEN_SHIP -> y = shooter.hitbox.y + shooter.hitbox.height;
        }

        hitbox = new Rectangle(x, y, 2, 8);
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void draw(Graphics graphics) {
            graphics.setColor(Color.RED);
            graphics.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
//            drawHitbox(graphics);
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public boolean hasHitTarget() {
        return hitTarget;
    }

    public void setHitTarget(boolean hitTarget) {
        this.hitTarget = hitTarget;
    }
}
