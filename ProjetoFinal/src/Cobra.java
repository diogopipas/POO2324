public interface Cobra {
    public void snakeDies();
    public void snakeEats();
    public boolean snakeCollides();
    public void updateSnakePosition();
    public void updateSnakeParts();
    public void addNewSnakePart(double x, double y);

    public void alterSnakeCoordinates(int position, double x, double y);

}