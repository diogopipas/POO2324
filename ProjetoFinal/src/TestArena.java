import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestArena {


    @Test
    public void testGerarCobra() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        assertNotNull(arena.gerarCobra());
    }

    @Test
    public void testGerarComida() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        assertNotNull(arena.gerarComida());
    }


    @Test
    public void testGerarObstaculos() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        obstaculos.add(new Quadrado(20, new Ponto(30, 30)));
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        assertEquals(1, arena.gerarObstaculos().size());
    }

    @Test
    public void testIntersectsObstaculo() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        obstaculos.add(new Quadrado(20, new Ponto(30, 30)));
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        assertTrue(arena.intersectsObstaculo(new Ponto(30, 30)));
    }



    @Test
    public void testIntersectsComida() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        assertFalse(arena.intersectsComida(new Ponto(30, 30)));
    }

    @Test
    public void testIntersectsCobra() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        assertFalse(arena.intersectsCobra(new Ponto(30, 30)));
    }

    @Test
    public void testInicializarGrelha() {
        ArrayList<Poligono> obstaculos = new ArrayList<>();
        Arena arena = new Arena(100, 100, 10, "Quadrado", 10, obstaculos);
        arena.inicializarGrelha();
        assertNotNull(arena.getGrelha());
    }

     
}
