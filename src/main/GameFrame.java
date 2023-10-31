package main;

import static utilz.Constants.GAME_WINDOW;

import javax.swing.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setTitle(GAME_WINDOW.TITLE);
        add(gamePanel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
