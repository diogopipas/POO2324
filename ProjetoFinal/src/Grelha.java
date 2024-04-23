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

    // Métodos para acessar células, verificar colisões, etc.
}