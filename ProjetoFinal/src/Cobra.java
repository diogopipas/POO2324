
import java.util.ArrayList;
//import java.util.Random;

public class Cobra {

    private ArrayList<Quadrado> cobra; // Lista de cobra que compõem a cobra
    private double dimensao; // Dimensão da aresta dos quadrados

    public Cobra(double dimensao, Ponto posicaoInicial){
        this.dimensao = dimensao;
        this.cobra = new ArrayList<>(1);
        Quadrado cabeca = new Quadrado(getQuadradoFromCentroid(posicaoInicial));
        cobra.add(cabeca);
    }

    public void moverCobra(int position, double newX, double newY){
        this.cobra.remove(position);
        Quadrado q = (Quadrado) this.cobra.get(position).translatePolygon(newX, newY);
        this.cobra.add(position, q);
    }

    public void direcionarCobra(Direcao d){
        for (int i = cobra.size()-1; i > 0; i--) {
            moverCobra(i, cobra.get(i-1).findCentroide().getX(), cobra.get(i-1).findCentroide().getY());
        }
        switch (d) {
            case DOWN:
                moverCobra(0, cobra.get(0).findCentroide().getX(), (cobra.get(0).findCentroide().getY())+1);
                break;
            case UP:
                moverCobra(0, cobra.get(0).findCentroide().getX(), (cobra.get(0).findCentroide().getY())-1);
                break;
            case RIGHT:
                moverCobra(0, (cobra.get(0).findCentroide().getX())+1, (cobra.get(0).findCentroide().getY()));
                break;
            case LEFT:
                moverCobra(0, (cobra.get(0).findCentroide().getX())-1, (cobra.get(0).findCentroide().getY()));
                break;
            default:
                break;
        }
    }

    public void addNewSnakePart(double x, double y){
        Quadrado q = new Quadrado(null);
        q = (Quadrado) cobra.get(cobra.size()-1).translatePolygon(x, y);
        this.cobra.add(q);
    }

    public void snakeEats(){
        addNewSnakePart(cobra.get(cobra.size()-1).findCentroide().getX(), cobra.get(cobra.size()-1).findCentroide().getY());
    }

    public Quadrado getCabeca() {
        return cobra.get(0); // ?
    }

    public int getTamanho() {
        return cobra.size();
    }

    public ArrayList<Ponto> getQuadradoFromCentroid(Ponto centroide){
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
}