import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Cell[][] grid;
    private Snake snake;
    private Food food;
    private Obstacle[] obstacles;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        initializeGrid();
        this.snake = new Snake();
        this.food = generateFood();
        this.obstacles = generateObstacles();
    }

    private void initializeGrid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    private Food generateFood() {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        while (!grid[x][y].isEmpty()) {
            x = random.nextInt(width);
            y = random.nextInt(height);
        }
        grid[x][y].setFood(true);
        return new Food(x, y);
    }

    private Obstacle[] generateObstacles() {
        // Implemente a geração de obstáculos conforme necessário
        return null;
    }

    // Outros métodos da classe Arena
}
