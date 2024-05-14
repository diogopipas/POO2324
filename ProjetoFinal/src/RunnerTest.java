import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {

    @Test
    public void testRun() {
        Simulador simulador = new Simulador();
        Runner runner = new Runner(simulador);
        assertDoesNotThrow(runner::run);
    }
}
