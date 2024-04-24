/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SegmentoRetaTest {

    /** Testa se o método onSegment funciona corretamente para um ponto dentro do segmento */
    @Test
    public void testOnSegmentInside() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        SegmentoReta segmento = new SegmentoReta(a, b);
        Ponto p = new Ponto(2, 3);
        assertTrue(segmento.onSegment(p));
    }

    /** Testa se o método onSegment funciona corretamente para um ponto fora do segmento */
    @Test
    public void testOnSegmentOutside() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        SegmentoReta segmento = new SegmentoReta(a, b);
        Ponto p = new Ponto(5, 6);
        assertFalse(segmento.onSegment(p));
    }

    /** Testa se o método orientation retorna 0 para três pontos colineares */
    @Test
    public void testOrientationCollinear() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(7, 9);
        SegmentoReta segmento = new SegmentoReta(a, b);
        assertEquals(0, segmento.orientation(c));
    }

    /** Testa se o método orientation retorna 1 para uma orientação no sentido do relógio */
    @Test
    public void testOrientationClockwise() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(5, 4);
        SegmentoReta segmento = new SegmentoReta(a, b);
        assertEquals(1, segmento.orientation(c));
    }

    /** Testa se o método orientation retorna 2 para uma orientação no sentido anti-horário */
    @Test
    public void testOrientationCounterClockwise() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(0, 2);
        SegmentoReta segmento = new SegmentoReta(a, b);
        assertEquals(2, segmento.orientation(c));
    }

    /** Testa se o método intersects retorna true para dois segmentos de reta que se intersectam */
    @Test
    public void testIntersectsTrue() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(2, 3);
        Ponto d = new Ponto(3, 2);
        SegmentoReta segmento1 = new SegmentoReta(a, b);
        SegmentoReta segmento2 = new SegmentoReta(c, d);
        assertTrue(segmento1.intersects(segmento2));
    }

    /** Testa se o método intersects retorna false para dois segmentos de reta que não se intersectam */
    @Test
    public void testIntersectsFalse() {
        Ponto a = new Ponto(1, 1);
        Ponto b = new Ponto(4, 5);
        Ponto c = new Ponto(6, 6);
        Ponto d = new Ponto(7, 7);
        SegmentoReta segmento1 = new SegmentoReta(a, b);
        SegmentoReta segmento2 = new SegmentoReta(c, d);
        assertFalse(segmento1.intersects(segmento2));
    }
}
