/**
 * @version 1.2
 * @author Diogo Porto
 */

import java.awt.*;
import java.util.ArrayList;


public class Poligono{
    private final ArrayList<Ponto> p;
    private ArrayList<SegmentoReta> sr;


    /** Construtor do poligono
     *
     * @param p Arraylist de pontos
     */
    public Poligono(ArrayList<Ponto> p){
        this.p = p;
        verify();
    }

    /** Calcula o perimetro do poligono
     *
     * @return perimetro do poligono
     */
    public int perimetro(){
        int total = 0;
        for(int i = 1; i < this.p.size(); i++){
            total += this.p.get(i-1).dist(this.p.get(i));
        }
        total += this.p.get(this.p.size() - 1).dist(
                this.p.get(0));
        return total;
    }


    /** Cria um Arraylist de segmentos de reta a partir do Arraylist de pontos p
     *
     */
    public void createSegmentList(){
        ArrayList<SegmentoReta> arr = new ArrayList<>();
        for(int i = 1; i < this.p.size(); i++){
            arr.add(new SegmentoReta(new Ponto(p.get(i-1).getX(), p.get(i-1).getY()),
                    new Ponto(p.get(i).getX(), p.get(i).getY())));
        }
        arr.add(new SegmentoReta(new Ponto(p.get(p.size()-1).getX(), p.get(p.size()-1).getY()),
                new Ponto(p.get(0).getX(), p.get(0).getY())));
        this.sr = arr;
    }


    /** Verifica se algum dos segmentos de reta se intercetam
     *
     * @return true se intercetam, falso se não
     */
    public boolean segmentsIntersect(){
        for(int i = 0; i < sr.size(); i++){
            for(int j = i + 1; j < sr.size(); j++){
                if(sr.get(i).intersects(sr.get(j)) &&
                        ((sr.get(i).getA().getX() != sr.get(j).getB().getX()) && (sr.get(i).getB().getX() != sr.get(j).getA().getX()))){
                    return true;
                }
            }
        }
        return false;
    }

    /** Invoca o método de criação do Arraylist de segmentos de reta e verifica as invariantes do poligono
     *
     */
    private void verify(){
        createSegmentList();
        if(p.size() <= 2 || segmentsIntersect() ){
            System.out.println("Poligono:vi");
            System.exit(0);
        }
        for(int i = 0; i < p.size(); i++){
            Reta reta = new Reta(p.get(i), p.get((i+1) % p.size()));
            if(reta.isCollinear(p.get((i+2) % p.size()))){
                System.out.println("Poligono:vi");
                System.exit(0);
            }
        }
    }


    /** Getter para o arraylist de segmentos do poligono
     *
     * @return arraylist de segmentos do poligono
     */
    public ArrayList<SegmentoReta> getSr(){
        return this.sr;
    }


    /**
     *
     * @param p1 ArrayList de pontos do primeiro retangulo
     * @param p2 ArrayList de pontos do segundo retangulo
     * @return true se os retangulos envolventes se intercetam, false se não
     */
    public static boolean rectangleIntersects(ArrayList<Ponto> p1, ArrayList<Ponto> p2) {

        double minX1 = Integer.MAX_VALUE, minY1 = Integer.MAX_VALUE;
        double maxX1 = Integer.MIN_VALUE, maxY1 = Integer.MIN_VALUE;

        for (Ponto ponto : p1) {
            double x = ponto.getX();
            double y = ponto.getY();
            minX1 = Math.min(minX1, x);
            minY1 = Math.min(minY1, y);
            maxX1 = Math.max(maxX1, x);
            maxY1 = Math.max(maxY1, y);
        }

        double minX2 = Integer.MAX_VALUE, minY2 = Integer.MAX_VALUE;
        double maxX2 = Integer.MIN_VALUE, maxY2 = Integer.MIN_VALUE;

        for (Ponto ponto : p2) {
            double x = ponto.getX();
            double y = ponto.getY();
            minX2 = Math.min(minX2, x);
            minY2 = Math.min(minY2, y);
            maxX2 = Math.max(maxX2, x);
            maxY2 = Math.max(maxY2, y);
        }

        Rectangle retangulo1 = new Rectangle((int)minX1, (int)minY1, (int)(maxX1 - minX1), (int)(maxY1 - minY1));
        Rectangle retangulo2 = new Rectangle((int)minX2, (int)minY2, (int)(maxX2 - minX2), (int)(maxY2 - minY2));

        return retangulo1.intersects(retangulo2);
    }

    /**
     *
     * @param pol poligono a ser testado para interceção
     * @return true se os poligonos intercetam, false se não
     */
    public boolean polygonIntersects(Poligono pol){
        for(int i = 0; i < pol.getSr().size(); i++){
            for(int j = 0; j < sr.size(); j++){
                if(pol.getSr().get(i).intersects(sr.get(j))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param that poligono
     * @return true se os polionos forem duplicados, false se não
     */
    public boolean isDuplicated(Poligono that){
        int counter = 0;
        if(this.p.size() == that.p.size()){
            for(int i = 0; i < this.p.size(); i++){
                for(int k = 0; k < this.p.size(); k++){
                    if(this.p.get(i).equals(that.p.get(k)) ){
                        counter++;
                    }
                }
            }
        }
        return counter == this.p.size();
    }


    /** Aplica rotação no poligono a partir de um ponto de referência que não o centroide do poligono
     *
     * @param anchorPoint ponto de referência para a rotação
     * @param angleDeg angulo de rotação em graus
     * @return um array com pontos com a rotação aplicada
     */
    public Poligono rotatePolygon(Ponto anchorPoint, int angleDeg){
        if(angleDeg % 360 == 0){
            return new Poligono(this.p);
        }
        ArrayList<Ponto> rotatedPoints = new ArrayList<>();
        for (Ponto ponto : this.p) {
            rotatedPoints.add(ponto.rotatePoint(anchorPoint, angleDeg));
        }

        return initPolygon(rotatedPoints);
    }

    /**
     *
     * @return o centroide do poligono em forma de ponto
     */
    public Ponto findCentroide(){
        double xsum = 0, ysum = 0, centroideX, centroideY;
        for(int i = 0; i < this.p.size(); i++){
            xsum += this.p.get(i).getX();
            ysum += this.p.get(i).getY();
        }
        centroideX = xsum/this.p.size();
        centroideY = ysum/this.p.size();
        return new Ponto(centroideX, centroideY);
    }

    /** Aplica rotação no poligono a partir do centroide do poligono
     *
     * @param angleDeg ponto de referência para a rotação
     * @return um array com pontos com a rotação aplicada
     */
    public Poligono rotatePolygon(int angleDeg){
        if(angleDeg % 360 == 0){
            return initPolygon(this.p);
        }
        ArrayList<Ponto> rotatedPoints = new ArrayList<>();
        Ponto centroide = findCentroide();
        for (Ponto ponto : this.p) {
            rotatedPoints.add(ponto.rotatePoint(centroide, angleDeg));
        }
        return initPolygon(rotatedPoints);
    }

    /**
     *
     * @param dx distância de translação x
     * @param dy distância de translação y
     * @return arraylist com pontos com a translação aplicada
     */
    public Poligono translatePolygon(double dx, double dy){
        ArrayList<Ponto> translatedPoints = new ArrayList<>();

        for(Ponto ponto: this.p){
            translatedPoints.add(ponto.translatePoint(dx, dy));
        }

        return initPolygon(translatedPoints);
    }

    public Poligono translatePolygon(Ponto centroideNovo){
        ArrayList<Ponto> translatedPoints = new ArrayList<>();
        Ponto centroideAtual = findCentroide();
        double dx = centroideNovo.getX() - centroideAtual.getX();
        double dy = centroideNovo.getY() - centroideAtual.getY();

        for(Ponto ponto: this.p){
            translatedPoints.add(ponto.translatePoint(dx, dy));
        }
        return initPolygon(translatedPoints);
    }

    protected Poligono initPolygon(ArrayList<Ponto> p){
        return new Poligono(p);
    }

    @Override
    public String toString() {
        return "Poligono de " + this.p.size() + " vertices: " + this.p;
    }

    public ArrayList<Ponto> getP() {
        return this.p;
    }
}

