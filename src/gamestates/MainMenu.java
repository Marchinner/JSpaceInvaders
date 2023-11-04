package gamestates;

import gui.GButton;
import gui.GText;
import main.Game;
import utilz.Constants;

import javax.imageio.ImageIO;

import static utilz.Constants.GAME_WINDOW;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class MainMenu {

    private Game game;

    private GButton playButton;
    private GButton quitButton;

    private BufferedImage backgroundImage = null;

    public MainMenu(Game game) {
        this.game = game;
        createButtons();
        loadBackgroundImage();
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
        graphics.drawImage(backgroundImage, 0, 0, null);
        // Set background color
//        graphics.setColor(Color.BLACK);
        // Draws background
//        graphics.fillRect(0, 0, GAME_WINDOW.WIDTH, GAME_WINDOW.HEIGHT);

        // Draw game title
//        GText.drawText(
//                GAME_WINDOW.HORIZONTAL_CENTERED,
//                50,
//                GAME_WINDOW.TITLE,
//                50,
//                Color.RED,
//                graphics
//        );

        // Draw the buttons
        drawButtons(graphics);
    }

    private void loadBackgroundImage() {
        try (InputStream inputStream = MainMenu.class.getResourceAsStream(Constants.SPRITE_ATLAS.BACKGROND_MENU)) {
            assert inputStream != null;
            backgroundImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
