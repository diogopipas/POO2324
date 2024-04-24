/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GrelhaTest {

    @Test
    public void testConstructor() {
        Grelha grelha = new Grelha(5, 7);
        assertEquals(5, grelha.getWidth());
        assertEquals(7, grelha.getHeight());
    }

    @Test
    public void testGetCell() {
        // Teste para verificar se as células são acessadas corretamente
        Grelha grelha = new Grelha(5, 7);
        Celula celula = grelha.getCell(2, 3);
        assertNotNull(celula); // Verifica se a célula não é nula
        assertEquals(2, celula.getX());
        assertEquals(3, celula.getY());
    }

}
