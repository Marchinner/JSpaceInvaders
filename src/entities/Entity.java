package entities;

import controllers.KeyboardInput;
import main.Game;
import static utilz.Constants.ENTITIES;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Entity {
    protected float x;
    protected float y;
    protected KeyboardInput keyboardInput;
    protected Rectangle2D.Float hitbox;
    protected Ship ship;
    protected boolean isAlive = true;
    protected float speed;
    protected Game game;
    protected boolean reloading = false;
    protected long actionTime = 0L;
    protected long reloadingCooldownTime = 1000;

    public boolean isAlive() {
        return isAlive;
    }

    public Entity(float x, float y, Game game, KeyboardInput keyboardInput) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.keyboardInput = keyboardInput;
    }

    protected void shoot() {
        long currentActionTime = System.currentTimeMillis();
        if (currentActionTime - actionTime >= reloadingCooldownTime) {
            actionTime = currentActionTime;
            reloading = false;
        }

        if (!reloading) {
            game.getPlaying().getMissiles().add(new Missile(this));
            reloading = true;
        }
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void checkIfWasHit(ArrayList<Missile> missiles) {
        if (!missiles.isEmpty()) {
            for (Missile missile : missiles) {
                if (missile.getShooter().ship != ship) {
                    if (missile.getHitbox().intersects(hitbox)) {
                        missile.setHitTarget(true);
                        isAlive = false;
                    }
                }
            }
        }
    }
}

