/**
 * @version 1.0
 * @author Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuadradoTest {


    /**
     * Testa o construtor da classe Quadrado.
     */
    @Test
    public void testConstrutor() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(2, 2));
        pontos.add(new Ponto(2, 0));

        Quadrado quadrado = new Quadrado(pontos);

        assertNotNull(quadrado);
    }

    @Test
    public void testRotatePolygon(){
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(2, 2));
        pontos.add(new Ponto(2, 0));

        Poligono quadrado = new Quadrado(pontos);

        Poligono quadrado_rotated = quadrado.rotatePolygon(180);

        assertEquals("Quadrado: [(2,2), (2,0), (0,0), (0,2)]", quadrado_rotated.toString());
    }

    @Test
    public void testTranslatePolygon(){
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(2, 2));
        pontos.add(new Ponto(2, 0));
        Quadrado quadrado = new Quadrado(pontos);

        Ponto currentCentroid = quadrado.findCentroide();

        Ponto newCentroid = new Ponto(5, 5);

        Poligono translatedQuadrado = quadrado.translatePolygon(newCentroid);

        assertEquals("Quadrado: [(3,3), (6,3), (6,7), (3,7)]", translatedQuadrado.toString());
    }


    /**
     * Testa o método toString() para verificar se retorna a representação em string correta do quadrado.
     */
    @Test
    public void testToString() {
        ArrayList<Ponto> pontosQuadrado = new ArrayList<>();
        pontosQuadrado.add(new Ponto(0, 0));
        pontosQuadrado.add(new Ponto(0, 2));
        pontosQuadrado.add(new Ponto(2, 2));
        pontosQuadrado.add(new Ponto(2, 0));

        Quadrado quadrado = new Quadrado(pontosQuadrado);

        assertEquals("Quadrado: [(0,0), (0,2), (2,2), (2,0)]", quadrado.toString());
    }
}

