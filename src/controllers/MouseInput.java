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
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (game.getGameState()) {
            case MENU -> {
                if (game.getMainMenu().getPlayButton().isMouseInside()) {
                    game.getMainMenu().getPlayButton().setMouseClicking(true);
                } else if (game.getMainMenu().getQuitButton().isMouseInside()) {
                    game.getMainMenu().getQuitButton().setMouseClicking(true);
                }
            }
            case PAUSED -> {
                if (game.getPaused().getResumeButton().isMouseInside()) {
                    game.getPaused().getResumeButton().setMouseClicking(true);
                } else if (game.getPaused().getRestartButton().isMouseInside()) {
                    game.getPaused().getRestartButton().setMouseClicking(true);
                } else if (game.getPaused().getMainMenuButton().isMouseInside()) {
                    game.getPaused().getMainMenuButton().setMouseClicking(true);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
                game.getMainMenu().getPlayButton().setMouseClicking(false);
                game.getMainMenu().getQuitButton().setMouseClicking(false);
                game.getPaused().getRestartButton().setMouseClicking(false);
                game.getPaused().getMainMenuButton().setMouseClicking(false);
                game.getPaused().getResumeButton().setMouseClicking(false);
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
            case PAUSED -> {
                if (game.getPaused().getResumeButton().getHitbox().contains(e.getX(), e.getY())) {
                    game.getPaused().getResumeButton().setMouseInside(true);
                } else if (game.getPaused().getRestartButton().getHitbox().contains(e.getX(), e.getY())) {
                    game.getPaused().getRestartButton().setMouseInside(true);
                } else if (game.getPaused().getMainMenuButton().getHitbox().contains(e.getX(), e.getY())) {
                    game.getPaused().getMainMenuButton().setMouseInside(true);
                } else {
                    game.getPaused().getResumeButton().setMouseInside(false);
                    game.getPaused().getRestartButton().setMouseInside(false);
                    game.getPaused().getMainMenuButton().setMouseInside(false);
                }
            }
        }
    }
}
