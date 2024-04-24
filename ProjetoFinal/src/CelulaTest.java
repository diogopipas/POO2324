/**
 * @version 1.0
 * @author André Santo, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CelulaTest {

    @Test
    public void testConstructor() {
        Celula celula = new Celula(3, 5);
        assertEquals(3, celula.getX());
        assertEquals(5, celula.getY());
        assertFalse(celula.isOccupied());
    }

}
