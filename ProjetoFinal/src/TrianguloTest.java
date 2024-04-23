/**
 * @version 1.0
 * @author Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrianguloTest {

    /**
     * Testa o construtor da classe Triangulo.
     */
    @Test
    public void testConstrutor() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(2, 2));

        Triangulo triangulo = new Triangulo(pontos);

        assertNotNull(triangulo);
    }

    /**
     * Testa o método verify() para verificar se os pontos formam um triângulo.
     */
    @Test
    public void testVerify() {
        ArrayList<Ponto> pontosTriangulo = new ArrayList<>();
        pontosTriangulo.add(new Ponto(0, 0));
        pontosTriangulo.add(new Ponto(0, 2));
        pontosTriangulo.add(new Ponto(2, 2));

        Triangulo triangulo = new Triangulo(pontosTriangulo);

        assertEquals("Triangulo: [(0,0), (0,2), (2,2)]", triangulo.toString());
    }

    @Test
    void rotatePolygonWithAnchorPoint() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(2, 2));

        Triangulo triangulo = new Triangulo(pontos);
        Triangulo rotatedTriangulo = triangulo.rotatePolygon(new Ponto(2, 2), 180);

        assertEquals(new Ponto(4, 4), rotatedTriangulo.getP().get(0));
        assertEquals(new Ponto(0, 4), rotatedTriangulo.getP().get(1));
        assertEquals(new Ponto(2, 2), rotatedTriangulo.getP().get(2));
    }

    @Test
    void translatePolygon() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(2, 4));
        Triangulo triangulo = new Triangulo(pontos);

        Triangulo translatedTriangulo = triangulo.translatePolygon(3, 2);

        Ponto expectedPonto1 = new Ponto(3, 2);
        Ponto expectedPonto2 = new Ponto(7, 2);
        Ponto expectedPonto3 = new Ponto(5, 6);

        assertEquals(expectedPonto1, translatedTriangulo.getP().get(0));
        assertEquals(expectedPonto2, translatedTriangulo.getP().get(1));
        assertEquals(expectedPonto3, translatedTriangulo.getP().get(2));
    }

    /**
     * Testa o método toString() para verificar se retorna a representação em string correta do triângulo.
     */
    @Test
    public void testToString() {
        ArrayList<Ponto> pontosTriangulo = new ArrayList<>();
        pontosTriangulo.add(new Ponto(0, 0));
        pontosTriangulo.add(new Ponto(0, 2));
        pontosTriangulo.add(new Ponto(2, 2));

        Triangulo triangulo = new Triangulo(pontosTriangulo);

        assertEquals("Triangulo: [(0,0), (0,2), (2,2)]", triangulo.toString());
    }
}