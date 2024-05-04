/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 * Esta classe é responsável pela criação da grelha de células, e pela deteção de colisão entre a cobra e as
 * paredes da arena
 */

import java.awt.*;

public interface Arena {
    public void initArena();
    public int getLargura();
    public int getAltura();
    public boolean collidesWith();
}
