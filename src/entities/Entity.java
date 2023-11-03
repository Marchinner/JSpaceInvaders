package entities;

import controllers.KeyboardInput;

import java.awt.*;

public class Entity {
    protected int x;
    protected int y;
    protected KeyboardInput keyboardInput;
    protected Rectangle hitbox;
    protected Ship ship;
    protected boolean isAlive = true;
    protected int speed = 5;

    public Entity(int x, int y, KeyboardInput keyboardInput) {
        this.x = x;
        this.y = y;
        this.keyboardInput = keyboardInput;
        hitbox = new Rectangle();
    }
}
