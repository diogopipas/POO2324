/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */
public class Grelha {
    private int width;
    private int height;
    private Celula[][] cells;

    public Grelha(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Celula[width][height];
        // Inicializar as células da grade
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public Celula getCell(int x, int y) {
        return new Celula(x, y);
    }

    // Métodos para acessar células, verificar colisões, etc.
}