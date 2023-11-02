package entities;

import controllers.KeyboardInput;

import java.awt.*;

public class Entity {
    private int x;
    private int y;
    private KeyboardInput keyboardInput;
    private Rectangle hitbox;

    public Entity(int x, int y, KeyboardInput keyboardInput) {
        this.x = x;
        this.y = y;
        this.keyboardInput = keyboardInput;
        hitbox = new Rectangle();
    }
}
