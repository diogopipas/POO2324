/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {

    @Test
    public void testConstructor() {
        Arena arena = new Arena(200, 100);
        assertEquals(200, arena.getWidth());
        assertEquals(100, arena.getHeight());
        assertTrue(arena.getObstacles().isEmpty());
        assertNull(arena.getSnake());
        assertNull(arena.getFood());
    }

    @Test
    public void testAddObstacle() {
        ArrayList<Ponto> p = new ArrayList<>();
        p.add(new Ponto(1,1));
        p.add(new Ponto(2,1));
        p.add(new Ponto(3,1));
        p.add(new Ponto(4,1));
        Arena arena = new Arena(200, 100);
        Poligono obstacle = new Poligono(p);
        arena.addObstacle(obstacle);
        assertTrue(arena.getObstacles().contains(obstacle));
    }

    @Test
    public void testRemoveObstacle() {
        ArrayList<Ponto> p = new ArrayList<>();
        p.add(new Ponto(1,1));
        p.add(new Ponto(2,1));
        p.add(new Ponto(3,1));
        p.add(new Ponto(4,1));
        Arena arena = new Arena(200, 100);
        Poligono obstacle = new Poligono(p);
        arena.addObstacle(obstacle);
        assertTrue(arena.getObstacles().contains(obstacle));
        arena.removeObstacle(obstacle);
        assertFalse(arena.getObstacles().contains(obstacle));
    }

    @Test
    public void testDetectCollision() {
        ArrayList<Ponto> p = new ArrayList<>();
        p.add(new Ponto(1,1));
        p.add(new Ponto(2,1));
        p.add(new Ponto(3,1));
        p.add(new Ponto(4,1));
        Arena arena = new Arena(200, 100);
        Poligono obstacle = new Poligono(p);
        arena.addObstacle(obstacle);
        Cobra cobra = new Cobra(0, 0, 0, 0);
        Comida comida = new Comida(0, 0, 0);
    }

}
