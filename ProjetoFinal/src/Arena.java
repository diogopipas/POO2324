/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Arena {
    private int width;
    private int height;
    private ArrayList<Poligono> obstacles;
    private Celula snake;
    private Comida food;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.obstacles = new ArrayList<>();
        // Inicializar outros elementos da arena
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public ArrayList<Poligono> getObstacles() {
        return null;
    }

    public Cobra getSnake() {
        return new Cobra(0, 0, 0, 0);
    }

    public Comida getFood() {
        return new Comida(0, 0, 0);
    }

    public void addObstacle(Poligono obstacle) {

    }

    public void removeObstacle(Poligono obstacle) {
    }

    // Métodos para adicionar/remover elementos, detectar colisões, etc.
}