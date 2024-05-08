import java.util.Random;
/* 
public interface Comida {
    public Ponto getFoodPosition();
    public void removeFood();
    public void changeFoodPosition();
}
*/

public class Comida {
    
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