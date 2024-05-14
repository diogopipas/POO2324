import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PoligonoTest {
    @Test
    public void testPerimetro() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 1));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(1, 0));
        Poligono poligono = new Poligono(pontos);
        assertEquals(4, poligono.perimetro());
    }

    @Test
    public void testSegmentsIntersect() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 1));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(1, 0));
        Poligono poligono = new Poligono(pontos);
        assertFalse(poligono.segmentsIntersect());
    }
}
