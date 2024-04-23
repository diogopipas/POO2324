/**
 * @version 1.0
 * @author Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RetanguloTest {

    /**
     * Testa o construtor da classe Retangulo.
     */
    @Test
    public void testConstrutor() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(3, 2));
        pontos.add(new Ponto(3, 0));

        Retangulo retangulo = new Retangulo(pontos);

        assertNotNull(retangulo);
    }

    /**
     * Testa o método isRectangle() para verificar se os pontos formam um retângulo.
     */
    @Test
    public void testIsRectangle() {
        ArrayList<Ponto> pontosRetangulo = new ArrayList<>();
        pontosRetangulo.add(new Ponto(0, 0));
        pontosRetangulo.add(new Ponto(0, 2));
        pontosRetangulo.add(new Ponto(3, 2));
        pontosRetangulo.add(new Ponto(3, 0));

        Retangulo retangulo = new Retangulo(pontosRetangulo);

        assertTrue(retangulo.isRectangle());
    }

    /**
     * Testa o método verify() para verificar se os pontos formam um retângulo.
     */
    @Test
    public void testVerify() {
        ArrayList<Ponto> pontosRetangulo = new ArrayList<>();
        pontosRetangulo.add(new Ponto(0, 0));
        pontosRetangulo.add(new Ponto(0, 2));
        pontosRetangulo.add(new Ponto(3, 2));
        pontosRetangulo.add(new Ponto(3, 0));

        Retangulo retangulo = new Retangulo(pontosRetangulo);

        assertEquals("Retangulo: [(0,0), (0,2), (3,2), (3,0)]", retangulo.toString());
    }

    @Test
    public void testRotatePolygon(){
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(4, 2));
        pontos.add(new Ponto(4, 0));

        Poligono retangulo = new Retangulo(pontos);

        Poligono retangulo_rotated = retangulo.rotatePolygon(180);

        assertEquals("Retangulo: [(4,2), (4,0), (0,0), (0,2)]", retangulo_rotated.toString());
    }

    @Test
    public void testTranslatePolygon(){
        ArrayList<Ponto> points = new ArrayList<>();
        points.add(new Ponto(0, 0));
        points.add(new Ponto(0, 2));
        points.add(new Ponto(3, 2));
        points.add(new Ponto(3, 0));
        Retangulo retangulo = new Retangulo(points);

        Ponto currentCentroid = retangulo.findCentroide();

        Ponto newCentroid = new Ponto(5, 5);

        Poligono translatedRetangulo = retangulo.translatePolygon(newCentroid);

        assertEquals("Retangulo: [(3,4), (3,6), (6,6), (6,4)]", translatedRetangulo.toString());
    }

    /**
     * Testa o método toString() para verificar se retorna a representação em string correta do retângulo.
     */
    @Test
    public void testToString() {
        ArrayList<Ponto> pontosRetangulo = new ArrayList<>();
        pontosRetangulo.add(new Ponto(0, 0));
        pontosRetangulo.add(new Ponto(0, 2));
        pontosRetangulo.add(new Ponto(3, 2));
        pontosRetangulo.add(new Ponto(3, 0));

        Poligono retangulo = new Retangulo(pontosRetangulo);

        assertEquals("Retangulo: [(0,0), (0,2), (3,2), (3,0)]", retangulo.toString());
    }
}
