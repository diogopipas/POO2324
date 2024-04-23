import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> body;
    private Direction direction;

    public Snake() {
        body = new LinkedList<>();
        // Inicializa a cobra com uma cabeça na posição (0, 0) e direção para a direita
        body.add(new Cell(0, 0));
        direction = Direction.RIGHT;
    }

    public LinkedList<Cell> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        // Move a cobra uma célula na direção atual
        Cell head = body.getFirst();
        int newX = head.getX();
        int newY = head.getY();
        switch (direction) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }
        // Adiciona uma nova cabeça à frente da cobra
        body.addFirst(new Cell(newX, newY));
        // Remove a última célula da cauda
        body.removeLast();
    }
}
