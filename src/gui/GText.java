package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GText {

    private static Path fontPath = Paths.get("res/dpcomic.ttf");

    /***
     * Draws a given text in the center of specified X position, given the size and color
     * @param x the x position coordinate
     * @param y the y position coordinate
     * @param text the desired text to draw
     * @param fontSize the desired font size to draw the text
     * @param textColor the desired text color to draw
     * @param graphics the graphics used to draw the text
     */
    public static void drawText(int x, int y, String text, int fontSize, Color textColor, Graphics graphics) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(String.valueOf(fontPath))).deriveFont((float) fontSize);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        graphics.setColor(textColor);
        graphics.setFont(font);
        graphics.drawString(text, x - (textWidth / 2), y);
    }
}
