import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComidaTest {

    @Test
    public void testConstructorAndGetters() {
        Comida comida = new Comida( "quadrado", new Ponto(3, 3), 1);
        assertEquals(1, comida.getDimensao());
        assertEquals("quadrado", comida.getTipoComida());
        assertEquals(3, comida.getPosicaoComida().getX());
        assertEquals(3, comida.getPosicaoComida().getY());
    }
}
