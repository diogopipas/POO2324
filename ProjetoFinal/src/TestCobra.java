import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestCobra {

    @Test
    public void testInitialSnake() {
        Cobra snake = new Cobra(10, new Ponto(50, 50));
        assertEquals(2, snake.getTamanho());
    }

    @Test
    public void testSnakeMovement() {
        Cobra snake = new Cobra(10, new Ponto(50, 50));
        snake.direcionarCobra(Direcao.RIGHT);
        snake.addNewSnakePart();
        snake.direcionarCobra(Direcao.UP);
        snake.addNewSnakePart();
        assertEquals(4, snake.getTamanho());
    }

    @Test
    public void testCollisionDetection() {
        Cobra snake = new Cobra(10, new Ponto(50, 50));
        snake.direcionarCobra(Direcao.RIGHT);
        snake.addNewSnakePart();
        snake.direcionarCobra(Direcao.UP);
        snake.addNewSnakePart();
        snake.direcionarCobra(Direcao.LEFT);
        snake.addNewSnakePart();
        snake.direcionarCobra(Direcao.DOWN);
        snake.addNewSnakePart();
        snake.direcionarCobra(Direcao.RIGHT);
        assertTrue(snake.verificarColisaoComCorpo());
    }

    @Test
    public void testSnakeGrowth() {
        Cobra snake = new Cobra(10, new Ponto(50, 50));
        snake.direcionarCobra(Direcao.RIGHT);
        snake.addNewSnakePart();
        snake.direcionarCobra(Direcao.RIGHT);
        snake.addNewSnakePart();
        snake.addNewSnakePart();
        snake.addNewSnakePart();
        assertEquals(5, snake.getTamanho());
    }
}

