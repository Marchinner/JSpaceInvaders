package entities;

import controllers.KeyboardInput;
import main.Game;

import java.awt.*;
import java.util.ArrayList;

public class Entity {
    protected int x;
    protected int y;
    protected KeyboardInput keyboardInput;
    protected Rectangle hitbox;
    protected Ship ship;
    protected boolean isAlive = true;
    protected int speed = 5;
    protected Game game;

    public boolean isAlive() {
        return isAlive;
    }

    public Entity(int x, int y, Game game, KeyboardInput keyboardInput) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.keyboardInput = keyboardInput;
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

