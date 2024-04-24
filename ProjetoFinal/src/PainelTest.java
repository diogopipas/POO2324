/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PainelTest {

    @Test
    public void testPaintComponent() {
        Arena arena = new Arena(800, 600);

        Painel painel = new Painel(arena);

        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();

        try {
            painel.paintComponent(g);
        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
}
