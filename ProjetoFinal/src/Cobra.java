import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public interface Cobra {
    public void snakeDies();
    public void snakeEats();
    public boolean snakeCollides(Quadrado q2);
    public void updateSnakePosition();
    public void updateSnakeParts();
    public void addNewSnakePart(double x, double y);

    public void alterSnakeCoordinates(int position, double x, double y);

}
