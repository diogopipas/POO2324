/**
 * @author Diogo Porto
 * @version 1.0
 */

import java.util.ArrayList;

public class Triangulo extends Poligono{

    /**
     * construtor do triangulo
     * @param p array de pontos
     */
    public Triangulo(ArrayList<Ponto> p){
        super(p);
        verify();
    }

    /**
     * verifica se 3 pontos formam um triangulo
     */

    public void verify(){
        int AB = (int) getP().get(0).dist(getP().get(1));
        int BC = (int) getP().get(1).dist(getP().get(2));
        int AC = (int) getP().get(0).dist(getP().get(2));
        int ABBC = AB + BC;
        int ABAC = AB + AC;
        int ACBC = AC + BC;
        if(getP().size() != 3 || (ABBC < AC && ABAC < BC && ACBC < AB)){
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
    }

    @Override
    protected Poligono initPolygon(ArrayList<Ponto> p){
        return new Triangulo(p);
    }

    /**
     *
     * @return o array de pontos em forma de string
     */
    @Override
    public String toString(){
        return "Triangulo: " + getP();

    }

}
