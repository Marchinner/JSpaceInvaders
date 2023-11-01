package main;

import controllers.KeyboardInput;
import controllers.MouseInput;
import gamestates.MainMenu;
import gamestates.States;

import java.awt.*;

import static utilz.Constants.GAME_ENGINE;

public class Game implements Runnable {

    // DEBUGGING OPTIONS
    private boolean isDebugging = true;

    // Window
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    // Views and States
    private States gameState = States.MENU;
    private MainMenu mainMenu;

    // Input Controllers
    public KeyboardInput keyboardInput;
    public MouseInput mouseInput;

    public Game() {
        loadGameWindow();
        initializeClasses();
        startGameThread();
    }

    private void update() {
        switch (gameState) {
            case MENU -> mainMenu.update();
        }
    }

    /***
     * Loads the game window and the input controllers
     */
    private void loadGameWindow() {
        keyboardInput = new KeyboardInput();
        mouseInput = new MouseInput(this);
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.addKeyListener(keyboardInput);
        gamePanel.addMouseListener(mouseInput);
        gamePanel.addMouseMotionListener(mouseInput);
    }

    private void initializeClasses() {
        mainMenu = new MainMenu(this);
    }

    public States getGameState() {
        return gameState;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void play() {
        gameState = States.PLAYING;
    }

    public void pause() {
        gameState = States.PAUSED;
    }

    public void quit() {
        System.exit(0);
    }

    private void startGameThread() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void render(Graphics graphics) {
        switch (gameState) {
            case MENU -> mainMenu.draw(graphics);
        }
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
                if (isDebugging)
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
