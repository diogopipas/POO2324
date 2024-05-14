import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CobraZarolhaTest {

    @Test
    public void testNextDirection() {
        Simulador simulador = new Simulador();
        CobraZarolha cobraZarolha = new CobraZarolha(simulador);
        assertNotNull(cobraZarolha.nextDirection());
    }
}
