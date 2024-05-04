import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public interface Cobra {
    public void initCobra();
    public ArrayList<Quadrado> getCobra();
    public void move();
    public void die();
    public void eat();
    public boolean collidesWithObstacle(Quadrado q2);
    public void updateSnakePosition();
    public void setDirecao(int novaDirecao);

}
