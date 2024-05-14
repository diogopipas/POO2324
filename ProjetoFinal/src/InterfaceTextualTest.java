import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterfaceTextualTest {

    @Test
    public void testPrintStepCompleta() {
        // Test printing step with complete rasterization
        Simulador simulador = new Simulador();
        InterfaceTextual interfaceTextual = new InterfaceTextual(simulador);
        assertDoesNotThrow(interfaceTextual::printStepCompleta);
    }

    @Test
    public void testPrintStepContorno() {
        // Test printing step with contour rasterization
        Simulador simulador = new Simulador();
        InterfaceTextual interfaceTextual = new InterfaceTextual(simulador);
        assertDoesNotThrow(interfaceTextual::printStepContorno);
    }
}
