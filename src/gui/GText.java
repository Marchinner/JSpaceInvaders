package gui;

import java.awt.*;

public class GText {

    /***
     * Draws a given text in the center of specified X position, given the size and color
     * @param x the x position coordinate
     * @param y the y position coordinate
     * @param text the desired text to draw
     * @param fontSize the desired font size to draw the text
     * @param textColor the desired text color to draw
     * @param graphics the graphics used to draw the text
     */
    public static void drawText(int x, int y, String text, int fontSize, Color textColor, Graphics graphics) {
        Font font = new Font(Font.DIALOG, Font.BOLD, fontSize);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        graphics.setColor(textColor);
        graphics.setFont(font);
        graphics.drawString(text, x - (textWidth / 2), y);
    }
}
