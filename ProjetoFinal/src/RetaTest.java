import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */
public class RetaTest {

    /** Testa se o método crossProduct retorna 0 para três pontos colineares */
    @Test
    public void testCrossProductCollinear() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(7, 9);
        Reta reta = new Reta(a, b);
        assertEquals(0, reta.crossProduct(c));
    }

    /** Testa se o método crossProduct retorna um valor positivo para uma orientação no sentido anti-horário */
    @Test
    public void testCrossProductCounterClockwise() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(5, 4);
        Reta reta = new Reta(a, b);
        assertTrue(reta.crossProduct(c) < 0);
    }

    /** Testa se o método crossProduct retorna um valor negativo para uma orientação no sentido do relógio */
    @Test
    public void testCrossProductClockwise() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(0, 2);
        Reta reta = new Reta(a, b);
        assertTrue(reta.crossProduct(c) > 0);
    }

    /** Testa se o método isCollinear retorna true para três pontos colineares */
    @Test
    public void testIsCollinearTrue() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(7, 9);
        Reta reta = new Reta(a, b);
        assertTrue(reta.isCollinear(c));
    }

    /** Testa se o método isCollinear retorna false para três pontos não colineares */
    @Test
    public void testIsCollinearFalse() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(0, 2);
        Reta reta = new Reta(a, b);
        assertFalse(reta.isCollinear(c));
    }
}


