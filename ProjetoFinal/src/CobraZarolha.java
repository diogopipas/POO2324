/** Classe responsável pelo movimento da cobra no modo automático
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.util.Random;

public class CobraZarolha {
    
    public CobraZarolha(Simulador sl){
    }

    public String nextDirection(){
        Random random = new Random();
        int x = random.nextInt(0, 3);
        return switch (x) {
            case 0 -> "L";
            case 1 -> "R";
            case 2 -> "U";
            default -> "D";
        };
    }
}
