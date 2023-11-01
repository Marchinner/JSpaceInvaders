package controllers;

import main.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getMainMenu().getPlayButton().getHitbox().contains(e.getX(), e.getY())) {
            game.getMainMenu().getPlayButton().setMouseClicking(true);
        } else if (game.getMainMenu().getQuitButton().getHitbox().contains(e.getX(), e.getY())) {
            game.getMainMenu().getQuitButton().setMouseClicking(true);
        } else {
            game.getMainMenu().getPlayButton().setMouseClicking(false);
            game.getMainMenu().getQuitButton().setMouseClicking(false);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (game.getGameState()) {
            case MENU -> {
                if (game.getMainMenu().getPlayButton().getHitbox().contains(e.getX(), e.getY())) {
                    game.getMainMenu().getPlayButton().setMouseInside(true);
                } else if (game.getMainMenu().getQuitButton().getHitbox().contains(e.getX(), e.getY())) {
                    game.getMainMenu().getQuitButton().setMouseInside(true);
                } else {
                    game.getMainMenu().getPlayButton().setMouseInside(false);
                    game.getMainMenu().getQuitButton().setMouseInside(false);
                }
            }
        }
    }
}
