import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CobraTest {

    @Test
    public void testMove() {
        Cobra cobra = new Cobra(3, new Ponto(5, 5));
        assertDoesNotThrow(() -> cobra.direcionarCobra(Direcao.DOWN));
        assertDoesNotThrow(() -> cobra.direcionarCobra(Direcao.UP));
        assertDoesNotThrow(() -> cobra.direcionarCobra(Direcao.LEFT));
        assertDoesNotThrow(() -> cobra.direcionarCobra(Direcao.RIGHT));
    }
}
