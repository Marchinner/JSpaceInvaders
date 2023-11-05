package entities;

import controllers.KeyboardInput;
import main.Game;
import utilz.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Alien extends Entity {

    private BufferedImage alienImage = null;
    private Game game;

    public Alien(float x, float y, KeyboardInput keyboardInput, Game game) {
        super(x, y, game, keyboardInput);
        this.game = game;
        ship = Ship.ALIEN_SHIP;
        speed = Constants.ENTITIES.ALIEN_SPEED;

        try (InputStream inputStream = Player.class.getResourceAsStream(Constants.SPRITE_ATLAS.ALIEN_SHIP)) {
            assert inputStream != null;
            alienImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hitbox = new Rectangle2D.Float(x, y, alienImage.getWidth(), alienImage.getHeight());
    }

    public void update() {
        if (isAlive) {
            updatePosition();
            if (isOnLineOfAttack(game.getPlaying().getPlayer()))
                shoot();
        }
    }

    private boolean isOnLineOfAttack(Player player) {
        return hitbox.x + hitbox.width / 2 >= player.hitbox.x &&
                hitbox.x + hitbox.width / 2 <= player.hitbox.x + player.hitbox.width;
    }

    private void updatePosition() {
        hitbox.x = x;
        hitbox.y = y;

        y += Constants.ENTITIES.ALIEN_SPEED;
    }

    public void draw(Graphics graphics) {
        if (isAlive) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            drawAlien(graphics2D);
//            drawHitbox(graphics);
        }
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.RED);
//        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void drawAlien(Graphics2D graphics2D) {
        graphics2D.drawImage(alienImage, (int) hitbox.x, (int) hitbox.y, null);
    }
}
