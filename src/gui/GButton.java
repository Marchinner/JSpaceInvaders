package gui;

import controllers.MouseInput;

import java.awt.*;

public class GButton {

    private final int X;
    private final int Y;
    private final String BUTTON_TEXT;

    private Color buttonColor = Color.BLACK;
    private Color textColor = Color.WHITE;
    private final int PADDING = 4;
    private Rectangle hitbox;
    private Font font;
    private FontMetrics fontMetrics;
    private int textWidth;
    private int textHeight;

    // Buttons controllers
    private boolean isMouseInside = false;
    private boolean isMouseClicking = false;

    public GButton(int x, int y, String buttonText) {
        this.X = x;
        this.Y = y;
        this.BUTTON_TEXT = buttonText;
        hitbox = new Rectangle();
    }

    public void update() {

        // Apply on hover effects
        if (isMouseInside) {
            buttonColor = Color.WHITE;
            textColor = Color.BLACK;
        } else {
            buttonColor = Color.BLACK;
            textColor = Color.WHITE;
        }
    }

    public boolean isMouseClicking() {
        return isMouseClicking;
    }

    public void setMouseClicking(boolean mouseClicking) {
        isMouseClicking = mouseClicking;
    }

    public boolean isMouseInside() {
        return isMouseInside;
    }

    public void setMouseInside(boolean mouseInside) {
        isMouseInside = mouseInside;
    }

    public Rectangle getHitbox() {
        return hitbox;
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
        graphics.setColor(buttonColor);
        graphics.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        // Draw the button text
        graphics.setColor(textColor);
        graphics.setFont(font);
        graphics.drawString(BUTTON_TEXT, hitbox.x + PADDING, hitbox.y + textHeight - fontMetrics.getDescent());

        // DEBUG: Draw button box
//        drawButtonHitbox(graphics);
    }

    private void drawButtonHitbox(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
