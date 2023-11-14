package entities;

import utilz.Constants;

import javax.imageio.ImageIO;

import static utilz.Constants.ENTITIES.MISSILE_SPEED;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Struct;

public class Missile {
    private float x;
    private float y;
    private Entity shooter;
    private float speed = MISSILE_SPEED;
    private Rectangle2D.Float hitbox;
    private boolean hitTarget = false;
    private long explosionTime = 0L;
    private long explosionDurationTime = 2000;
    private boolean exploded;
    private BufferedImage explosionImage = null;

    public Missile(Entity shooter) throws IOException {
        this.shooter = shooter;
        x = shooter.hitbox.x + shooter.hitbox.width / 2;

        switch (shooter.ship) {
            case PLAYER_SHIP -> y = shooter.hitbox.y;
            case ALIEN_SHIP -> y = shooter.hitbox.y + shooter.hitbox.height;
        }

        hitbox = new Rectangle2D.Float(x, y, 2f, 8f);

        try (InputStream inputStream = Missile.class.getResourceAsStream(Constants.SPRITE_ATLAS.EXPLOSION)) {
            assert inputStream != null;
            explosionImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (exploded) {
            drawExplosion(graphics);
        }
//            drawHitbox(graphics);
    }

    private void drawExplosion(Graphics graphics) {
        graphics.drawImage(explosionImage, (int) hitbox.x - explosionImage.getWidth() / 2, (int) hitbox.y - explosionImage.getHeight() / 2, explosionImage.getWidth(), explosionImage.getHeight(), null);
        hitTarget = true;
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.WHITE);
//        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public boolean hasHitTarget() {
        return hitTarget;
    }

    public void setHitTarget(boolean hitTarget) {
        if (hitTarget) {
            exploded = true;
        }
    }
}
