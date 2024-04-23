public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int xChange;
    private final int yChange;

    Direction(int xChange, int yChange) {
        this.xChange = xChange;
        this.yChange = yChange;
    }

    public int getXChange() {
        return xChange;
    }

    public int getYChange() {
        return yChange;
    }

    public boolean isOpposite(Direction other) {
        return this == UP && other == DOWN ||
                this == DOWN && other == UP ||
                this == LEFT && other == RIGHT ||
                this == RIGHT && other == LEFT;
    }
}
