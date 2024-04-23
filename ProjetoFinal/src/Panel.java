import javax.swing.*;
import java.awt.*;

class Panel extends JPanel {
    Arena arena;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenhar a arena
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                Cell cell = arena.getCell(x, y);
                if (cell == Cell.SNAKE_HEAD) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * 20, y * 20, 20, 20);
                } else if (cell == Cell.SNAKE_BODY) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * 20, y * 20, 20, 20);
                } else if (cell == Cell.FOOD) {
                    g.setColor(Color.RED);
                    g.fillOval(x * 20, y * 20, 20, 20);
                } else if (cell == Cell.OBSTACLE) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * 20, y * 20, 20, 20);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(x * 20, y * 20, 20, 20);
                }
            }
        }
    }
}
