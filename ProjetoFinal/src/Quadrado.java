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


    @Override
    protected Poligono initPolygon(ArrayList<Ponto> p){
        return new Quadrado(p);
    }

    /**
     *
     * @return o array de pontos em forma de string
     */
    @Override
    public String toString(){
        return "Quadrado: " + getP();
    }
}
