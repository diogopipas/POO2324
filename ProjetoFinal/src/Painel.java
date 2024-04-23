import javax.swing.*;
import java.awt.*;

public class Painel extends JPanel {
    private Arena arena;

    public Painel(Arena arena) {
        this.arena = arena;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenhar a arena, cobra, comida, obstáculos, etc.
    }
}