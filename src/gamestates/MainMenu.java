package gamestates;

import static utilz.Constants.GAME_WINDOW;

import java.awt.*;

public class MainMenu {

    public MainMenu() {

    }

    public void update() {

    }

    public void draw(Graphics graphics) {
        // Set background color
        graphics.setColor(Color.BLACK);
        // Draws background
        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);
    }

}
