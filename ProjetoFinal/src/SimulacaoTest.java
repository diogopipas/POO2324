/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SimulacaoTest {

    @Test
    public void testStartAndStop() {
        Arena arena = new Arena(800, 600);

        Simulacao simulacao = new Simulacao(arena);

        assertFalse(simulacao.isRunning());

        simulacao.start();

        assertTrue(simulacao.isRunning());

        simulacao.stop();

        assertFalse(simulacao.isRunning());
    }
}
