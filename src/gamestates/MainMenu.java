package gamestates;

import gui.GButton;
import gui.GText;

import static utilz.Constants.GAME_WINDOW;

import java.awt.*;

public class MainMenu {

    private GButton playButton;
    private GButton quitButton;

    public MainMenu() {
        createButtons();
    }

    public void update() {

    }

    private void createButtons() {
        playButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                150,
                "JOGAR"
        );

        quitButton = new GButton(
                GAME_WINDOW.HORIZONTAL_CENTERED,
                250,
                "SAIR"
        );
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
