/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComidaTest {

    @Test
    public void testConstructor() {
        Comida comida = new Comida(3, 5, 2);
        assertEquals(3, comida.getX());
        assertEquals(5, comida.getY());
        assertEquals(2, comida.getSize());
    }

    @Test
    public void testGenerateNewPosition() {
        Comida comida = new Comida(3, 5, 2);
        comida.generateNewPosition(10, 10);
        assertNotEquals(3, comida.getX());
        assertNotEquals(5, comida.getY());
    }

}
