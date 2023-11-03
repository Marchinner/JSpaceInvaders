package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Missile {
    private float x;
    private float y;
    private Entity shooter;
    private float speed = 2.5f;
    private Rectangle2D hitbox;

    public Missile(Entity shooter) {
        this.shooter = shooter;
        x = (float) shooter.hitbox.x + (float) shooter.hitbox.width / 2;
        y = (float) shooter.hitbox.y;
        hitbox = new Rectangle2D.Float(x, y, 2f, 2f);
    }

    public void update() {
        y -= speed;
        hitbox.setRect(x, y, 2f, 2f);
    }

    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.RED);
        graphics2D.draw(hitbox);
    }
}
