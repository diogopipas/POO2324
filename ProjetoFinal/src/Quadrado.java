/**
 * @version 1.0
 * @author André Santos, Diogo Porto
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

    public boolean isOnBorder(Ponto ponto, double dimensao) {
        double x = ponto.getX();
        double y = ponto.getY();
    
        // Calcula os limites do quadrado baseado no centroide
        Ponto centro = this.findCentroide();
        double halfDim = dimensao / 2;
        double left = centro.getX() - halfDim;
        double right = centro.getX() + halfDim;
        double top = centro.getY() - halfDim;
        double bottom = centro.getY() + halfDim;
    
        // Margem para considerar um ponto como estando na borda
        double margin = 0.5;
    
        // Verifica se o ponto está próximo das bordas externas
        boolean onVerticalBorder = ((Math.abs(x - left) <= margin || Math.abs(x - right) <= margin) && y >= top - margin && y <= bottom + margin);
        boolean onHorizontalBorder = ((Math.abs(y - top) <= margin || Math.abs(y - bottom) <= margin) && x >= left - margin && x <= right + margin);
    
        return onVerticalBorder || onHorizontalBorder;
    }
}