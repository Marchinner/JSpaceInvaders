package main;

import controllers.KeyboardInput;
import controllers.MouseInput;
import gamestates.*;

import java.awt.*;
import java.io.IOException;

import static utilz.Constants.GAME_ENGINE;

public class Game implements Runnable {

    // DEBUGGING OPTIONS
    private boolean isDebugging = true;

    // DeltaTime
    private double deltaTime;

    // Window
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    // Views and States
    private States gameState = States.MENU;
    private MainMenu mainMenu;
    private Playing playing;
    private Paused paused;
    private GameOver gameOver;

    // Input Controllers
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;

    public Game() {
        initializeClasses();
        loadGameWindow();
        startGameThread();
    }

    private void update() {
        switch (gameState) {
            case MENU -> mainMenu.update();
            case PLAYING -> playing.update();
            case PAUSED -> paused.update();
            case GAME_OVER -> gameOver.update();
        }
    }

    /***
     * Loads the game window and the input controllers
     */
    private void loadGameWindow() {
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.addKeyListener(keyboardInput);
        gamePanel.addMouseListener(mouseInput);
        gamePanel.addMouseMotionListener(mouseInput);
    }

    private void initializeClasses() {
        mouseInput = new MouseInput(this);
        keyboardInput = new KeyboardInput();
        mainMenu = new MainMenu(this);
        playing = new Playing(this);
        paused = new Paused(this);
        gameOver = new GameOver(this);
    }

    public States getGameState() {
        return gameState;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Paused getPaused() {
        return paused;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public void play() {
        playing.initializeClasses();
        gameState = States.PLAYING;
    }

    public void restart() {
        playing.initializeClasses();
        play();
    }

    public void pause() {
        gameState = States.PAUSED;
    }

    public void gameOver() {
        gameState = States.GAME_OVER;
    }

    public void toMainMenu() {
        gameState = States.MENU;
    }

    public void quit() {
        System.exit(0);
    }

    private void startGameThread() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void render(Graphics graphics) throws IOException, FontFormatException {
        switch (gameState) {
            case MENU -> mainMenu.draw(graphics);
            case PLAYING -> playing.draw(graphics);
            case PAUSED -> paused.draw(graphics);
            case GAME_OVER -> gameOver.draw(graphics);
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
            deltaTime = deltaF;

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
