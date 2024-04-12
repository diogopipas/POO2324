/**
 * @version 1.1
 * @author Diogo Porto
 */
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PoligonoTest {

    /**
     * Testa o construtor da classe Poligono.
     */
    @Test
    public void testConstrutor() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(0, 2));
        pontos.add(new Ponto(2, 2));

        Poligono poligono = new Poligono(pontos);

        assertNotNull(poligono);
    }

    /**
     * Testa o método perimetro() para calcular o perímetro do polígono.
     */
    @Test
    public void testPerimetro() {
        ArrayList<Ponto> pontosPoligono = new ArrayList<>();
        pontosPoligono.add(new Ponto(0, 0));
        pontosPoligono.add(new Ponto(0, 2));
        pontosPoligono.add(new Ponto(2, 2));

        Poligono poligono = new Poligono(pontosPoligono);

        assertEquals(6, poligono.perimetro());
    }

    /**
     * Testa o método segmentsIntersect() para verificar se os segmentos de reta do polígono se intersectam.
     */
    @Test
    public void testSegmentsIntersect() {
        ArrayList<Ponto> pontosPoligono = new ArrayList<>();
        pontosPoligono.add(new Ponto(0, 0));
        pontosPoligono.add(new Ponto(0, 2));
        pontosPoligono.add(new Ponto(2, 2));

        Poligono poligono = new Poligono(pontosPoligono);
        poligono.createSegmentList();

        assertFalse(poligono.segmentsIntersect());
    }

    /**
     * Testa o método polygonIntersects() para verificar se dois polígonos se intersectam.
     */
    @Test
    public void testPolygonIntersects() {
        ArrayList<Ponto> pontosPoligono1 = new ArrayList<>();
        pontosPoligono1.add(new Ponto(0, 0));
        pontosPoligono1.add(new Ponto(0, 2));
        pontosPoligono1.add(new Ponto(2, 2));

        ArrayList<Ponto> pontosPoligono2 = new ArrayList<>();
        pontosPoligono2.add(new Ponto(1, 1));
        pontosPoligono2.add(new Ponto(1, 3));
        pontosPoligono2.add(new Ponto(3, 3));

        Poligono poligono1 = new Poligono(pontosPoligono1);
        Poligono poligono2 = new Poligono(pontosPoligono2);

        assertTrue(poligono1.polygonIntersects(poligono2));
    }

    /**
     * Testa o método isDuplicated() para verificar se dois polígonos são duplicados.
     */
    @Test
    public void testIsDuplicated() {
        ArrayList<Ponto> pontosPoligono1 = new ArrayList<>();
        pontosPoligono1.add(new Ponto(0, 0));
        pontosPoligono1.add(new Ponto(0, 2));
        pontosPoligono1.add(new Ponto(2, 2));

        ArrayList<Ponto> pontosPoligono2 = new ArrayList<>();
        pontosPoligono2.add(new Ponto(0, 0));
        pontosPoligono2.add(new Ponto(0, 2));
        pontosPoligono2.add(new Ponto(2, 2));

        Poligono poligono1 = new Poligono(pontosPoligono1);
        Poligono poligono2 = new Poligono(pontosPoligono2);

        assertTrue(poligono1.isDuplicated(poligono2));
    }

    /**
     * Testa o método rotatePolygon() para verificar a rotação de todos os pontos do polígono.
     */
    @Test
    public void testRotatePolygon() {
        ArrayList<Ponto> pontosPoligono = new ArrayList<>();
        pontosPoligono.add(new Ponto(2, 1));
        pontosPoligono.add(new Ponto(4, 1));
        pontosPoligono.add(new Ponto(3, 4));

        Poligono poligono = new Poligono(pontosPoligono);

        ArrayList<Ponto> pontosRotacionados = poligono.rotatePolygon(180);
        System.out.println(pontosRotacionados.get(0));
        System.out.println(pontosRotacionados.get(1));
        System.out.println(pontosRotacionados.get(2));

        assertEquals("Coordenada x: 4.0\nCoordenada y: 3.0\n", pontosRotacionados.get(0).toString());
        assertEquals("Coordenada x: 2.0\nCoordenada y: 3.0\n", pontosRotacionados.get(1).toString());
        assertEquals("Coordenada x: 3.0\nCoordenada y: 0.0\n", pontosRotacionados.get(2).toString());

    }

    /**
     * Testa o método findCentroide() para verificar o cálculo do centroide do polígono.
     */
    @Test
    public void testFindCentroide() {
        ArrayList<Ponto> pontosPoligono = new ArrayList<>();
        pontosPoligono.add(new Ponto(1, 1));
        pontosPoligono.add(new Ponto(3, 1));
        pontosPoligono.add(new Ponto(3, 5));
        pontosPoligono.add(new Ponto(1, 5));


        Poligono poligono = new Poligono(pontosPoligono);


        Ponto centroide = poligono.findCentroide();


        assertEquals(2, centroide.getX());
        assertEquals(3, centroide.getY());

    }

    /**
     * Testa o método translatePolygon
     */
    @Test
    public void testTranslatePolygon() {

        ArrayList<Ponto> points = new ArrayList<>();
        points.add(new Ponto(0, 0));
        points.add(new Ponto(3, 0));
        points.add(new Ponto(3, 4));
        points.add(new Ponto(0, 4));

        Poligono polygon = new Poligono(points);


        double dx = 2.0;
        double dy = 3.0;

        ArrayList<Ponto> translatedPoints = polygon.translatePolygon(dx, dy);


        ArrayList<Ponto> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Ponto(2, 3));
        expectedPoints.add(new Ponto(5, 3));
        expectedPoints.add(new Ponto(5, 7));
        expectedPoints.add(new Ponto(2, 7));


        assertEquals(expectedPoints.size(), translatedPoints.size());
        for (int i = 0; i < expectedPoints.size(); i++) {
            assertEquals(expectedPoints.get(i).getX(), translatedPoints.get(i).getX());
            assertEquals(expectedPoints.get(i).getY(), translatedPoints.get(i).getY());
        }
    }

    @Test
    public void testTranslatePolygonWithCentroid() {
        ArrayList<Ponto> points = new ArrayList<>();
        points.add(new Ponto(0, 0));
        points.add(new Ponto(3, 0));
        points.add(new Ponto(3, 4));
        points.add(new Ponto(0, 4));

        Poligono polygon = new Poligono(points);

        Ponto currentCentroid = polygon.findCentroide();

        Ponto newCentroid = new Ponto(5, 5);

        ArrayList<Ponto> translatedPoints = polygon.translatePolygon(newCentroid);

        ArrayList<Ponto> expectedTranslatedPoints = new ArrayList<>();
        for (Ponto point : points) {
            expectedTranslatedPoints.add(point.translatePoint(newCentroid.getX() - currentCentroid.getX(),
                    newCentroid.getY() - currentCentroid.getY()));
        }

        assertEquals(expectedTranslatedPoints.size(), translatedPoints.size());
        for (int i = 0; i < expectedTranslatedPoints.size(); i++) {
            assertEquals(expectedTranslatedPoints.get(i).getX(), translatedPoints.get(i).getX());
            assertEquals(expectedTranslatedPoints.get(i).getY(), translatedPoints.get(i).getY());
        }
    }
}
