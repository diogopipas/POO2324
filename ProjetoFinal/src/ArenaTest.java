import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {
    @Test
    public void testConstructorAndGetters() {
        Arena arena = new Arena(10, 10, 1, "quadrado", 3, new ArrayList<>(), new ArrayList<>());
        assertEquals(10, arena.getAltura());
        assertEquals(10, arena.getLargura());
        assertEquals(1, arena.getDimensaoComida());
        assertEquals("quadrado", arena.getTipoComida());
        assertEquals(3, arena.getDimensaoCobra());
        assertNotNull(arena.getCobra());
        assertNotNull(arena.getComida());
    }

    @Test
    public void testAtualizar() {
        Arena arena = new Arena(10, 10, 1, "quadrado", 3, new ArrayList<>(), new ArrayList<>());
        assertDoesNotThrow(() -> arena.atualizar(Direcao.DOWN));
        assertDoesNotThrow(() -> arena.atualizar(Direcao.UP));
        assertDoesNotThrow(() -> arena.atualizar(Direcao.LEFT));
        assertDoesNotThrow(() -> arena.atualizar(Direcao.RIGHT));
    }
}
