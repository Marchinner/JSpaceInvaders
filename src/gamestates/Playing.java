package gamestates;

import controllers.KeyboardInput;
import entities.Alien;
import entities.Missile;
import entities.Player;
import main.Game;
import utilz.Constants.GAME_WINDOW;
import utilz.Constants.GAME;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Playing {

    private Game game;
    private Player player;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Missile> missiles = new ArrayList<>();
    private KeyboardInput keyboardInput;
    private long timeToRespawnAliens = 1500;    // 3 seconds
    private long respawnCounter = 0L;
    private boolean canCreateAlien = true;
    private long currentRespawnTime = System.currentTimeMillis();

    private int deathAliens = 0;

    public Playing(Game game) {
        this.game = game;
        initializeController();
        initializeClasses();
    }

    private void initializeController() {
        keyboardInput = game.getKeyboardInput();
    }

    public void initializeClasses() {
        player = new Player(GAME_WINDOW.HORIZONTAL_CENTERED, 550, game, keyboardInput);
    }

    private void createAlien() {
        aliens.add(new Alien((int) (Math.random() * GAME_WINDOW.WIDTH), -40, keyboardInput, game));
    }

    public void update() {
        player.update();
        player.checkIfWasHit(missiles);
        for (Alien alien : aliens) {
            alien.update();
            alien.checkIfWasHit(missiles);
            if (!alien.isAlive()) {
                deathAliens++;
            }
        }

        aliens.removeIf(alien -> alien.getHitbox().y >= GAME_WINDOW.HEIGHT);

        if (keyboardInput.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            game.pause();
        }

        if (!missiles.isEmpty()) {
            for (Missile missile : missiles) {
                missile.update();
            }
        }

        if (canCreateAlien) {
            createAlien();
            canCreateAlien = false;
        }

            currentRespawnTime = System.currentTimeMillis();
        if (currentRespawnTime - respawnCounter >= timeToRespawnAliens) {
            respawnCounter = currentRespawnTime;
            canCreateAlien = true;
        }
    }

    public void draw(Graphics graphics) {
        drawBackground(graphics);
        player.draw(graphics);

        for (Alien alien : aliens) {
            alien.draw(graphics);
        }

        if (!missiles.isEmpty()) {
            for (Missile missile : missiles) {
                missile.draw(graphics);
            }
        }
    }

    private void drawBackground(Graphics graphics) {
        graphics.setColor(GAME.BACKGROUND_COLOR);
        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}
