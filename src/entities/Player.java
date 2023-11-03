package entities;

import controllers.KeyboardInput;
import utilz.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    public Player(int x, int y, KeyboardInput keyboardInput) {
        super(x, y, keyboardInput);
        ship = Ship.PLAYER_SHIP;
        hitbox.width = 15;
        hitbox.height = 15;
    }

    public void update() {
        if (isAlive) {
            updatePosition();
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
        drawHitbox(graphics);
    }

    private void drawHitbox(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void drawPlayer(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
