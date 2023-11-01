package gamestates;

import gui.GButton;
import gui.GText;
import main.Game;

import static utilz.Constants.GAME_WINDOW;

import java.awt.*;

public class MainMenu {

    private Game game;

    private GButton playButton;
    private GButton quitButton;

    public MainMenu(Game game) {
        this.game = game;
        createButtons();
    }

    public void update() {
        updateButtons();
        if (playButton.isMouseClicking()) {
            game.play();
        } else if (quitButton.isMouseClicking()) {
            game.quit();
        }
    }

    private void createButtons() {
        playButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                300,
                "JOGAR"
        );

        quitButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                550,
                "SAIR"
        );
    }

    private void updateButtons() {
        playButton.update();
        quitButton.update();
    }

    public GButton getPlayButton() {
        return playButton;
    }

    public GButton getQuitButton() {
        return quitButton;
    }

    private void drawButtons(Graphics graphics) {
        playButton.draw(graphics);
        quitButton.draw(graphics);
    }

    public void draw(Graphics graphics) {
        // Set background color
        graphics.setColor(Color.BLACK);
        // Draws background
        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);

        // Draw game title
        GText.drawText(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                50,
                GAME_WINDOW.TITLE,
                50,
                Color.RED,
                graphics
        );

        // Draw the buttons
        drawButtons(graphics);
    }

}
