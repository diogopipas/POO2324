/**
 * @version 1.0
 * @author Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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