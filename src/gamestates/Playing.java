package gamestates;

import main.Game;
import utilz.Constants.GAME_WINDOW;
import utilz.Constants.GAME;

import java.awt.*;

public class Playing {

    private Game game;

    public Playing(Game game) {
        this.game = game;
    }

    public void update() {

    }

    public void draw(Graphics graphics) {
        drawBackground(graphics);
    }

    private void drawBackground(Graphics graphics) {
        graphics.setColor(GAME.BACKGROUND_COLOR);
        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);
    }
}
