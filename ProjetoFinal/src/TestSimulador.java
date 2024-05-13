import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestSimulador {
    private Simulador simulador;

    @Before
    public void setUp() {
        simulador = new Simulador();
    }

    @Test
    public void testGenerateObstaculoPoligonos() {
        ArrayList<Poligono> poligonos = simulador.generateObstaculoPoligonos();
        assertNotNull(poligonos);
        assertFalse(poligonos.isEmpty());
    }

    @Test
    public void testParseObstaculosName() {
        ArrayList<String> nomes = simulador.parseObstaculosName();
        assertNotNull(nomes);
        assertFalse(nomes.isEmpty());
    }

    @Test
    public void testParseObstaculosPontos() {
        ArrayList<Ponto> pontos = simulador.parseObstaculosPontos(0);
        assertNotNull(pontos);
        assertFalse(pontos.isEmpty());
    }

    @Test
    public void testStrToPontos() {
        ArrayList<Ponto> pontos = Simulador.strToPontos("4 4 6 4 6 6 4 6");
        assertNotNull(pontos);
        assertFalse(pontos.isEmpty());
    }

    @Test
    public void testStringToEnum() {
        assertEquals(Direcao.UP, simulador.stringToEnum("U"));
        assertEquals(Direcao.DOWN, simulador.stringToEnum("D"));
        assertEquals(Direcao.LEFT, simulador.stringToEnum("L"));
        assertEquals(Direcao.RIGHT, simulador.stringToEnum("R"));
    }
}
