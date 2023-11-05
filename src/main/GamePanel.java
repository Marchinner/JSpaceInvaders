package main;

import static utilz.Constants.GAME_WINDOW;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(GAME_WINDOW.DIMENSION);
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        try {
            game.render(graphics);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
