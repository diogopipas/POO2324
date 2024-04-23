import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Arena {
    private int width;
    private int height;
    private ArrayList<Obstaculo> obstacles;
    private Celula cobra;
    private Comida comida;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.obstacles = new ArrayList<>();
        // Inicializar outros elementos da arena
    }

    // Métodos para adicionar/remover elementos, detectar colisões, etc.
}