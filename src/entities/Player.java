package entities;

import controllers.KeyboardInput;
import main.Game;
import utilz.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {

    private BufferedImage playerImage = null;

    public Player(int x, int y, Game game, KeyboardInput keyboardInput) {
        super(x, y, game, keyboardInput);
        ship = Ship.PLAYER_SHIP;
        speed = Constants.ENTITIES.PLAYER_SPEED;

        try (InputStream inputStream = Player.class.getResourceAsStream(Constants.SPRITE_ATLAS.PLAYER_SHIP)) {
            assert inputStream != null;
            playerImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hitbox = new Rectangle2D.Float(x, y, playerImage.getWidth(), playerImage.getHeight());
    }

    public void update() throws IOException {
        if (isAlive) {
            updatePosition();
            if (keyboardInput.getKeyPressed(KeyEvent.VK_SPACE)) {
                shoot();
            }
        } else {
            game.gameOver();
        }
    }

    private void updatePosition() {
        hitbox.x = x;
        hitbox.y = y;

        if (keyboardInput.getKeyPressed(KeyEvent.VK_D)) {
            if (canMoveRight())
                moveRight();
        } else if (keyboardInput.getKeyPressed(KeyEvent.VK_A)) {
            if (canMoveLeft())
                moveLeft();
        }
    }

    private boolean canMoveRight() {
        return hitbox.x + speed + hitbox.width <= Constants.GAME_WINDOW.WIDTH;
    }

    private void moveRight() {
        x += speed;
    }

    private boolean canMoveLeft() {
        return hitbox.x - speed >= 0;
    }

    private void moveLeft() {
        x -= speed;
    }

    public void draw(Graphics graphics) {
        drawPlayer(graphics);
//        drawHitbox(graphics);
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.RED);
//        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void drawPlayer(Graphics graphics) {
        graphics.drawImage(playerImage, (int) hitbox.x, (int) hitbox.y, null);
    }
}
