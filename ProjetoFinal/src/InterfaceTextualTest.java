import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterfaceTextualTest {

    @Test
    public void testPrintStepCompleta() {
        Simulador simulador = new Simulador();
        InterfaceTextual interfaceTextual = new InterfaceTextual(simulador);
        assertDoesNotThrow(interfaceTextual::printStepCompleta);
    }

    @Test
    public void testPrintStepContorno() {
        Simulador simulador = new Simulador();
        InterfaceTextual interfaceTextual = new InterfaceTextual(simulador);
        assertDoesNotThrow(interfaceTextual::printStepContorno);
    }
}
