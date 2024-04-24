/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import java.util.ArrayList;

public class Retangulo extends Poligono{

    /**
     * Construtor do retangulo
     * @param p array de pontos
     */
    public Retangulo(ArrayList<Ponto> p){
        super(p);
        verify();
    }

    /**
     *
     * @return true se for um retangulo, false se não
     */
     public boolean isRectangle() {

        double side1 = getP().get(0).dist(getP().get(1));
        double side2 = getP().get(1).dist(getP().get(2));
        double side3 = getP().get(2).dist(getP().get(3));
        double side4 = getP().get(3).dist(getP().get(0));


        // Calculate the diagonals
        double diagonal1 = getP().get(0).dist(getP().get(2));
        double diagonal2 = getP().get(1).dist(getP().get(3));


        // Check if opposite sides are equal in length and angles between them are right angles
        return side1 == side3 && side2 == side4 && diagonal1 == diagonal2;
     }


    /**
     * verifica se os pontos dados formam um retangulo
     */
    public void verify() {
        if(getP().size() != 4 || !isRectangle()){
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
    }

    @Override
    protected Poligono initPolygon(ArrayList<Ponto> p){
        return new Retangulo(p);
    }

    /**
     *
     * @return o array de pontos em forma de string
     */
    @Override
    public String toString(){
        return "Retangulo: " + getP();

    }

}
