package gamestates;

import controllers.KeyboardInput;
import entities.Alien;
import entities.AmmoBox;
import entities.Missile;
import entities.Player;
import main.Game;
import utilz.Constants.GAME_WINDOW;
import utilz.Constants.GAME;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import static gui.GText.drawText;

public class Playing {

    private Game game;
    private Player player;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Missile> missiles = new ArrayList<>();
    private ArrayList<AmmoBox> ammoBoxes = new ArrayList<>();
    private KeyboardInput keyboardInput;
    private long timeToRespawnAliens = 2000;    // 3 seconds
    private long respawnCounter = 0L;
    private boolean canCreateAlien = true;
    private long currentRespawnTime = System.currentTimeMillis();
    private int playerScore = 0;
    private int ammoBoxCount = 0;

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
        playerScore = 0;
        ammoBoxCount = 0;
        player = new Player(GAME_WINDOW.HORIZONTAL_CENTERED, 550, game, keyboardInput);

        if (!aliens.isEmpty()) {
            aliens.clear();
        }
        if (!missiles.isEmpty()) {
            missiles.clear();
        }
        if (!ammoBoxes.isEmpty()) {
            ammoBoxes.clear();
        }


    }

    private void createAlien() {
        aliens.add(new Alien((int) (Math.random() * GAME_WINDOW.WIDTH - 20), -40, keyboardInput, game));
        if (playerScore % 5 == 0) {
            createAmmoBox();
            System.out.println("AMMO BOX CREATED");
            ammoBoxCount++;
        }
    }

    private void createAmmoBox() {
        ammoBoxes.add(new AmmoBox((int) (Math.random() * GAME_WINDOW.WIDTH - 10), -20));
    }

    public void update() throws IOException {
        player.update();
        player.checkIfWasHit(missiles);
        for (Alien alien : aliens) {
            alien.update();
            alien.checkIfWasHit(missiles);
            if (!alien.isAlive()) {
                deathAliens++;
                playerScore++;
                ammoBoxCount--;
            }
        }


        if (keyboardInput.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            game.pause();
        }

        if (!missiles.isEmpty()) {
            for (Missile missile : missiles) {
                missile.update();
            }
        }

        if (!ammoBoxes.isEmpty()) {
            for (AmmoBox ammoBox : ammoBoxes) {
                ammoBox.update();
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

        aliens.removeIf(alien -> alien.getHitbox().y >= GAME_WINDOW.HEIGHT || !alien.isAlive());
        missiles.removeIf(Missile::hasHitTarget);
    }

    public void draw(Graphics graphics) throws IOException, FontFormatException {
        drawBackground(graphics);
        player.draw(graphics);

        for (Alien alien : aliens) {
            alien.draw(graphics);
        }

        if (!ammoBoxes.isEmpty()) {
            for (AmmoBox ammoBox : ammoBoxes) {
                ammoBox.draw(graphics);
            }
        }

        if (!missiles.isEmpty()) {
            for (Missile missile : missiles) {
                missile.draw(graphics);
            }
        }

        drawText(40, 590, "Score: " + playerScore, 22, Color.WHITE, graphics);
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
