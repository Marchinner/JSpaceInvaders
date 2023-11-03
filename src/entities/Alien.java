package entities;

import controllers.KeyboardInput;
import main.Game;
import utilz.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Alien extends Entity {

    private BufferedImage alienImage = null;
    private Game game;

    public Alien(int x, int y, KeyboardInput keyboardInput, Game game) {
        super(x, y, game, keyboardInput);
        this.game = game;
        ship = Ship.ALIEN_SHIP;

        try (InputStream inputStream = Player.class.getResourceAsStream(Constants.SPRITE_ATLAS.ALIEN_SHIP)) {
            assert inputStream != null;
            alienImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hitbox = new Rectangle(x, y, alienImage.getWidth(), alienImage.getHeight());
    }

    public void update() {
        if (isAlive) {
            updatePosition();
        }
    }

    private void updatePosition() {
        hitbox.x = x;
        hitbox.y = y;

        y += 1;
    }

    public void draw(Graphics graphics) {
        drawAlien(graphics);
        drawHitbox(graphics);
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void drawAlien(Graphics graphics) {
        graphics.drawImage(alienImage, hitbox.x, hitbox.y, null);
    }
}
