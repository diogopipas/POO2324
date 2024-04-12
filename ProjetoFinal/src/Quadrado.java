/**
 * @author Diogo Porto
 * @version 1.0
 */

import java.util.ArrayList;

public class Quadrado extends Retangulo{

    /**
     * Construtor do Quadrado
     * @param p array de pontos
     */
    public Quadrado(ArrayList<Ponto> p){
        super(p);
    }

    /**
     *
     * @return o array de pontos em forma de string
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Quadrado: ").append("[");
        for (int i = 0; i < 4; i++) {
            sb.append("(").append((int)getP().get(i).getX()).append(",").append((int)getP().get(i).getY()).append(")");
            if (i < 3) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
