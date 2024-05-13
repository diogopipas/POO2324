<<<<<<< HEAD
import java.util.Random;
/* 
public interface Comida {
    public Ponto getFoodPosition();
    public void removeFood();
    public void changeFoodPosition();
}
*/

public class Comida {
    
=======
W/** Classe responsável pela criação da comida
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Comida{
>>>>>>> parent of fbfdde2 (Push para o G)
    private Ponto posicaoComida;

    public Comida(){
        Random generator = new Random();        // ????
        this.posicaoComida = new Ponto(generator.nextInt(1, 100), generator.nextInt(1, 100));
    }

    public Ponto getFoodPosition(){
        return this.posicaoComida;
    }

    public void removeFood(){
        this.posicaoComida = null;
    }

    public void changeFoodPosition(){
        Random generator = new Random();
        int x = generator.nextInt(1, 100);
        int y = generator.nextInt(1, 100);
        this.posicaoComida = new Ponto(x, y);
    }
}