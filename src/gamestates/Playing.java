package gamestates;

import controllers.KeyboardInput;
import entities.Player;
import main.Game;
import utilz.Constants.GAME_WINDOW;
import utilz.Constants.GAME;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing {

    private Game game;
    private Player player;
    private KeyboardInput keyboardInput;

    public Playing(Game game) {
        this.game = game;
        initializeController();
        initializeClasses();
    }

    private void initializeController() {
        keyboardInput = game.getKeyboardInput();
    }

    public void initializeClasses() {
        player = new Player(GAME_WINDOW.HORIZONTAL_CENTERED, 550, keyboardInput);
    }

    public void update() {
        player.update();

        if (keyboardInput.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            game.pause();
        }
    }

    public void draw(Graphics graphics) {
        drawBackground(graphics);
        player.draw(graphics);
    }

    private void drawBackground(Graphics graphics) {
        graphics.setColor(GAME.BACKGROUND_COLOR);
        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);
    }
}
