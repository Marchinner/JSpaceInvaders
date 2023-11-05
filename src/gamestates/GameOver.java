package gamestates;

import gui.GButton;
import main.Game;
import utilz.Constants;

import java.awt.*;
import java.io.IOException;

import static gui.GText.drawText;

public class GameOver {

    private Game game;
    private GButton restartButton;
    private GButton mainMenuButton;

    public GameOver(Game game) {
        this.game = game;
        createButtons();
    }

    public void update() {
        restartButton.update();
        mainMenuButton.update();

        if (restartButton.isMouseClicking()) {
            game.restart();
        } else if (mainMenuButton.isMouseClicking()) {
            game.toMainMenu();
        }
    }

    private void createButtons() {
        restartButton = new GButton(
                Constants.GAME_WINDOW.HORIZONTAL_CENTERED,
                300,
                "REINICIAR"
        );

        mainMenuButton = new GButton(
                Constants.GAME_WINDOW.HORIZONTAL_CENTERED,
                500,
                "MENU PRINCIPAL"
        );
    }

    public void draw(Graphics graphics) throws IOException, FontFormatException {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Constants.GAME_WINDOW.WIDTH, Constants.GAME_WINDOW.HEIGHT);
        drawText(
                Constants.GAME_WINDOW.HORIZONTAL_CENTERED,
                100,
                "GAME OVER",
                82,
                Color.RED,
                graphics
        );

        drawButtons(graphics);

    }

    public GButton getRestartButton() {
        return restartButton;
    }

    public GButton getMainMenuButton() {
        return mainMenuButton;
    }

    private void drawButtons(Graphics graphics) throws IOException, FontFormatException {
        restartButton.draw(graphics);
        mainMenuButton.draw(graphics);
    }
}
