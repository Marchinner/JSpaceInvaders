package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AmmoBox extends Collectible {

    private int ammoQuantity = 5;
    private Rectangle2D.Float hitbox;

    public AmmoBox(float x, float y) {
        super(x, y);
        hitbox = new Rectangle2D.Float(x, y, 10F, 10F);
    }

    public void update() {
        hitbox.y += 0.5F;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
}
