import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestComida {

    private Comida comida;

    @Before
    public void setUp() {
        comida = new Comida("quadrado", new Ponto(20, 20), 10);
    }

    @Test
    public void testInicializacaoComida() {
        assertNotNull("A comida não deveria ser nula", comida);
        assertNotNull("A posição da comida não deveria ser nula", comida.getPosicaoComida());
        assertEquals("A dimensão da comida deve ser a especificada", 10, comida.getDimensao(), 0);
    }

    @Test
    public void testGeracaoFormaComidaQuadrado() {
        Comida comidaQuadrado = new Comida("quadrado", new Ponto(30, 30), 15);
        assertTrue("A forma da comida deve ser um quadrado", comidaQuadrado.getFormaComida() instanceof Quadrado);
    }

    @Test
    public void testGeracaoFormaComidaCirculo() {
        Comida comidaCirculo = new Comida("circulo", new Ponto(40, 40), 12);
        assertTrue("A forma da comida deve ser um círculo", comidaCirculo.getFormaComida() instanceof Circulo);
    }
}
