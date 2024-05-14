/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de testes para a classe Ponto.
 */
public class PontoTest {
    private Ponto ponto1, ponto2, ponto3;

    @BeforeEach
    void setUp() {
        // Configuração comum para todos os testes
        ponto1 = new Ponto(0, 0);
        ponto2 = new Ponto(3, 4);
        ponto3 = new Ponto(1, 5);
    }

    /**
     * Teste para o método dist().
     */
    @Test
    void distTest() {
        assertEquals(5.0, ponto1.dist(ponto2));
        assertEquals(Math.sqrt(5), ponto2.dist(ponto3));
    }

    /**
     * Teste para o método getX().
     */
    @Test
    void getXTest() {
        assertEquals(0, ponto1.getX());
        assertEquals(3, ponto2.getX());
    }

    /**
     * Teste para o método getY().
     */
    @Test
    void getYTest() {
        assertEquals(0, ponto1.getY());
        assertEquals(4, ponto2.getY());
    }

    /**
     * Teste para o método setX().
     */
    @Test
    void setXTest() {
        ponto1.setX(5);
        assertEquals(5, ponto1.getX());
    }

    /**
     * Teste para o método setY().
     */
    @Test
    void setYTest() {
        ponto2.setY(2);
        assertEquals(2, ponto2.getY());
    }

    /**
     * Testa o método rotatePoint() para verificar a rotação de um ponto em torno de outro.
     */
    @Test
    public void testRotatePoint() {

        Ponto ponto = new Ponto(2, 2);
        Ponto anchorPoint = new Ponto(0, 0);
        Ponto pontoRotacionado = ponto.rotatePoint(anchorPoint, -45);
        assertEquals(3, pontoRotacionado.getX());
        assertEquals(0, pontoRotacionado.getY());


    }

    /**
     * Teste para o método toString().
     */
    @Test
    void toStringTest() {
        assertEquals("(0,0)", ponto1.toString());
        assertEquals("(3,4)", ponto2.toString());
    }

    @Test
    public void testTranslatePoint() {
        Ponto point = new Ponto(3, 5);
        double dx = 2.0;
        double dy = 3.0;
        Ponto translatedPoint = point.translatePoint(dx, dy);
        Ponto expectedPoint = new Ponto(5, 8);
        assertEquals(expectedPoint.getX(), translatedPoint.getX());
        assertEquals(expectedPoint.getY(), translatedPoint.getY());
    }
}

