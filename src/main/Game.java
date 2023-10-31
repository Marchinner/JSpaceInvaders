package main;

import java.awt.*;

import static utilz.Constants.GAME_ENGINE;

public class Game implements Runnable {

    private GameFrame gameFrame;
    private GamePanel gamePanel;

    public Game() {
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
    }

    private void update() {

    }

    public void render(Graphics graphics) {

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / GAME_ENGINE.FPS_LIMIT;
        double timePerUpdate = 1000000000.0 / GAME_ENGINE.UPS_LIMIT;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
