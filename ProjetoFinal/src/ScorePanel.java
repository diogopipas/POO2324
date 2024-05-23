/** Classe responsável pela criação do painel de pontuação
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private Arena arena;

    public ScorePanel(Arena arena) {
        this.arena = arena;
        setOpaque(false);
        setPreferredSize(new Dimension(600, 60));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        applyQualityRenderingHints(g2);

        // Draw background with a subtle drop shadow and more transparency
        int x = 10;
        int y = 10;
        int width = getWidth() - 20;
        int height = getHeight() - 20;
        int arc = 20;
        g2.setColor(new Color(0, 0, 0, 50)); // Lighter shadow for subtlety
        g2.fillRoundRect(x, y + 2, width, height, arc, arc);

        // Draw main rounded rectangle with higher transparency
        g2.setColor(new Color(30, 30, 30, 180)); // Semi-transparent
        g2.fillRoundRect(x, y, width, height, arc, arc);

        // Draw the score text
        String scoreText = "Score: " + arena.getPontuacao();
        Font font = new Font("Arial", Font.BOLD, 24);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);
        int textX = (width - metrics.stringWidth(scoreText)) / 2;
        int textY = ((height - metrics.getHeight())) + metrics.getAscent();
        g2.setColor(Color.WHITE); // Ensure text is clearly visible
        g2.drawString(scoreText, textX, textY);

        g2.dispose();
    }

    private void applyQualityRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    }
}