import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {

    @Test
    public void testRun() {
        // Test run method by simulating input
        Simulador simulador = new Simulador();
        Runner runner = new Runner(simulador);
        assertDoesNotThrow(runner::run);
    }
}
