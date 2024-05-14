/**
 * @version 1.0
 * @author André Santos, Diogo Porto
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
    public boolean containsPonto(Ponto p){
        Ponto p1 = getP().get(0);
        Ponto p2 = getP().get(1);
        Ponto p3 = getP().get(2);

        double v1x = p.getX() - p1.getX();
        double v1y = p.getY() - p1.getY();
        double v2x = p.getX() - p2.getX();
        double v2y = p.getY() - p2.getY();
        double v3x = p.getX() - p3.getX();
        double v3y = p.getY() - p3.getY();

        double dot1 = (v1x * (p2.getY() - p1.getY())) - (v1y * (p2.getX() - p1.getX()));
        double dot2 = (v2x * (p3.getY() - p2.getY())) - (v2y * (p3.getX() - p2.getX()));
        double dot3 = (v3x * (p1.getY() - p3.getY())) - (v3y * (p1.getX() - p3.getX()));

        return (dot1 >= 0 && dot2 >= 0 && dot3 >= 0) || (dot1 <= 0 && dot2 <= 0 && dot3 <= 0);
    }

    public boolean isOnBorderTriangle(Ponto ponto) {
        Ponto v1 = getP().get(0);
        Ponto v2 = getP().get(1);
        Ponto v3 = getP().get(2);

        double edge1 = v1.dist(v2);
        double edge2 = v2.dist(v3);
        double edge3 = v3.dist(v1);

        double dist1 = ponto.dist(v1);
        double dist2 = ponto.dist(v2);
        double dist3 = ponto.dist(v3);

        boolean onEdge1 = Math.abs(dist1 + dist2 - edge1) < 0.1;
        boolean onEdge2 = Math.abs(dist2 + dist3 - edge2) < 0.1;
        boolean onEdge3 = Math.abs(dist3 + dist1 - edge3) < 0.1;

        return onEdge1 || onEdge2 || onEdge3;
    }

    @Override
    public boolean isOnBorder(Ponto ponto, double dimensao) {
        return isOnBorderTriangle(ponto);
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