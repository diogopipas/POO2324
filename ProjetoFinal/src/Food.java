public class Food {
    private Cell position;

    public Food(int x, int y) {
        this.position = new Cell(x, y);
    }

    public Cell getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}
