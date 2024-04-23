public class Cell {
    private boolean isEmpty;
    private boolean isSnake;
    private boolean isObstacle;
    private boolean isFood;

    public Cell() {
        isEmpty = true;
        isSnake = false;
        isObstacle = false;
        isFood = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isSnake() {
        return isSnake;
    }

    public void setSnake(boolean snake) {
        isSnake = snake;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public boolean isFood() {
        return isFood;
    }

    public void setFood(boolean food) {
        isFood = food;
    }
}
