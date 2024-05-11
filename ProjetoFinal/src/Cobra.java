
import java.util.ArrayList;
//import java.util.Random;

public class Cobra {

    private double dimensao; // Dimensão da aresta dos quadrados
    private ArrayList<Quadrado> partesCobra; // Lista de cobra que compõem a cobra
    private ArrayList<Ponto> posicoesCobra;

    public Cobra(double dimensao, Ponto posicaoInicial){
        this.dimensao = dimensao;
        this.partesCobra = new ArrayList<>(1);
        this.posicoesCobra = new ArrayList<>(1);
        Quadrado cabeca = new Quadrado(getVerticesFromCentroid(posicaoInicial));
        this.posicoesCobra.add(cabeca.findCentroide());
        partesCobra.add(cabeca);
    }

    public void moverCobra(int index, double newX, double newY){
        this.partesCobra.remove(index);
        Quadrado q = (Quadrado) this.partesCobra.get(index).translatePolygon(newX, newY);
        this.posicoesCobra.add(index, new Ponto(newX, newY));
        this.partesCobra.add(index, q);
    }

    public void direcionarCobra(Direcao d){
        for (int i = partesCobra.size()-1; i > 0; i--) {
            moverCobra(i, partesCobra.get(i-1).findCentroide().getX(), partesCobra.get(i-1).findCentroide().getY());
        }
        switch (d) {
            case DOWN:
                moverCobra(0, partesCobra.get(0).findCentroide().getX(), (partesCobra.get(0).findCentroide().getY())+1);
                break;
            case UP:
                moverCobra(0, partesCobra.get(0).findCentroide().getX(), (partesCobra.get(0).findCentroide().getY())-1);
                break;
            case RIGHT:
                moverCobra(0, (partesCobra.get(0).findCentroide().getX())+1, (partesCobra.get(0).findCentroide().getY()));
                break;
            case LEFT:
                moverCobra(0, (partesCobra.get(0).findCentroide().getX())-1, (partesCobra.get(0).findCentroide().getY()));
                break;
            default:
                break;
        }
    }

    public void addNewSnakePart(double x, double y){
        Quadrado q = (Quadrado) partesCobra.get(partesCobra.size()-1).translatePolygon(x, y);
        this.partesCobra.add(q);
    }

    public void come(){
        addNewSnakePart(partesCobra.get(partesCobra.size()-1).findCentroide().getX(), partesCobra.get(partesCobra.size()-1).findCentroide().getY());
    }

    public ArrayList<Ponto> getVerticesFromCentroid(Ponto centroide){
        ArrayList<Ponto> pontos = new ArrayList<>();
        Ponto p1 = new Ponto(centroide.getX()-(this.dimensao/2), centroide.getY()+this.dimensao/2); // supperior esqueerdo
        Ponto p2 = new Ponto(centroide.getX()+(this.dimensao/2), centroide.getY()+this.dimensao/2); // supperior direito
        Ponto p3 = new Ponto(centroide.getX()+(this.dimensao/2), centroide.getY()-this.dimensao/2); // inferior direito
        Ponto p4 = new Ponto(centroide.getX()-(this.dimensao/2), centroide.getY()-this.dimensao/2); // inferior esqueerdo~
        pontos.add(p1);
        pontos.add(p2);
        pontos.add(p3);
        pontos.add(p4);
        return pontos;
    }


    public Quadrado getCabeca() {
        return this.partesCobra.get(0); // ?
    }

    public int getTamanho() {
        return this.partesCobra.size();
    }

    public ArrayList<Quadrado> getPartesCobra() {
        return this.partesCobra;
    }

    public ArrayList<Ponto> getPosicoesCobra() {
        return this.posicoesCobra;
    }

    public double getDimensao() {
        return this.dimensao;
    }

}