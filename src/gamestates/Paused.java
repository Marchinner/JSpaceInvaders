package gamestates;

import gui.GButton;
import gui.GText;
import main.Game;
import utilz.Constants.GAME_WINDOW;

import java.awt.*;
import java.io.IOException;

public class Paused {

    private GButton resumeButton;
    private GButton restartButton;
    private GButton mainMenuButton;
    private Game game;

    public Paused(Game game) {
        this.game = game;
        createButtons();
    }

    private void createButtons() {
        resumeButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                300,
                "CONTINUAR"
        );

        restartButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                400,
                "REINICIAR JOGO"
        );

        mainMenuButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                500,
                "MENU PRINCIPAL"
        );
    }


    private void updateButtons() {
        resumeButton.update();
        restartButton.update();
        mainMenuButton.update();
    }

    public void update() {
        updateButtons();

        if (resumeButton.isMouseClicking()) {
            game.play();
        } else if (restartButton.isMouseClicking()) {
            game.restart();
        } else if (mainMenuButton.isMouseClicking()) {
            game.toMainMenu();
        }
    }

    public GButton getResumeButton() {
        return resumeButton;
    }

    public GButton getRestartButton() {
        return restartButton;
    }

    public GButton getMainMenuButton() {
        return mainMenuButton;
    }

    public void draw(Graphics graphics) throws IOException, FontFormatException {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);
        GText.drawText(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                100,
                "JOGO PAUSADO",
                50,
                Color.RED,
                graphics
        );

        resumeButton.draw(graphics);
        restartButton.draw(graphics);
        mainMenuButton.draw(graphics);
    }

}
