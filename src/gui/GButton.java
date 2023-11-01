package gui;

import java.awt.*;

public class GButton {

    private final int X;
    private final int Y;
    private final String BUTTON_TEXT;

    private final int PADDING = 4;
    private Rectangle hitbox;
    private Font font;
    private FontMetrics fontMetrics;
    private int textWidth;
    private int textHeight;

    public GButton(int x, int y, String buttonText) {
        this.X = x;
        this.Y = y;
        this.BUTTON_TEXT = buttonText;
        hitbox = new Rectangle();
    }

    public void update() {

    }

    public void draw(Graphics graphics) {
        // Get the metrics and positions
        font = new Font(Font.DIALOG, Font.BOLD, 32);
        fontMetrics = graphics.getFontMetrics(font);
        textWidth = fontMetrics.stringWidth(BUTTON_TEXT);
        textHeight = fontMetrics.getHeight();
        hitbox.x = X - PADDING - (textWidth / 2);
        hitbox.y = Y - PADDING - (textHeight / 2);
        hitbox.width = textWidth + (PADDING * 2);
        hitbox.height = textHeight + (PADDING * 2);

        // Draw the button box
        graphics.setColor(Color.WHITE);
        graphics.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        // Draw the button text
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        graphics.drawString(BUTTON_TEXT, hitbox.x + PADDING, hitbox.y + textHeight - fontMetrics.getDescent());
    }
}
