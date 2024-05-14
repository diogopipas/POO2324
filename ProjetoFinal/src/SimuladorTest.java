import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimuladorTest {

    @Test
    public void testReadConfigs() {
        Simulador simulador = new Simulador();
        assertDoesNotThrow(simulador::readConfigs);
    }

    @Test
    public void testGenerateObstaculoPoligonos() {
        Simulador simulador = new Simulador();
        assertDoesNotThrow(simulador::generateObstaculoPoligonos);
    }

    @Test
    public void testStringToEnum() {
        Simulador simulador = new Simulador();
        assertEquals(Direcao.DOWN, simulador.stringToEnum("D"));
        assertEquals(Direcao.UP, simulador.stringToEnum("U"));
        assertEquals(Direcao.RIGHT, simulador.stringToEnum("R"));
        assertEquals(Direcao.LEFT, simulador.stringToEnum("L"));
        assertThrows(IllegalArgumentException.class, () -> simulador.stringToEnum("X"));
    }
}
