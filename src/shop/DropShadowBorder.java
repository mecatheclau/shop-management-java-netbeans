package shop;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class DropShadowBorder extends AbstractBorder {
    private static final long serialVersionUID = 1L;
    private final int shadowSize;
    private final Color shadowColor;

    public DropShadowBorder(int shadowSize, Color shadowColor) {
        this.shadowSize = shadowSize;
        this.shadowColor = shadowColor;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(shadowSize, shadowSize, shadowSize, shadowSize);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(shadowColor);
        for (int i = 0; i < shadowSize; i++) {
            g.drawRect(x + i, y + i, width - i - 1, height - i - 1);
        }
    }
}
