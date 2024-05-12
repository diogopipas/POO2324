/** Classe responsável pela criação da cobra
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */
import java.util.ArrayList;
//import java.util.Random;

public class Cobra {

    private ArrayList<Quadrado> cobra; // Lista de cobra que compõem a cobra

    private double dimensao; // Dimensão da aresta dos quadrados da cobra

    private Direcao ultimaDirecao;

    public Cobra(double dimensao, Ponto pontoIncial){
        ultimaDirecao = Direcao.UP;
        this.dimensao = dimensao;
        this.cobra = new ArrayList<>();
        this.cobra.add(getQuadradoFromCentroid(pontoIncial));
        this.cobra.add(getQuadradoFromCentroid(new Ponto(pontoIncial.getX(), pontoIncial.getY() + dimensao)));
    }

    public Quadrado getQuadradoFromCentroid(Ponto centroide){
        ArrayList<Ponto> pontos = new ArrayList<>();
        double metadeAresta = this.dimensao/2;
        Ponto p1 = new Ponto(centroide.getX()-(metadeAresta), centroide.getY()+metadeAresta); // supperior esqueerdo
        Ponto p2 = new Ponto(centroide.getX()+(metadeAresta), centroide.getY()+metadeAresta); // supperior direito
        Ponto p3 = new Ponto(centroide.getX()+(metadeAresta), centroide.getY()-metadeAresta); // inferior direito
        Ponto p4 = new Ponto(centroide.getX()-(metadeAresta), centroide.getY()-metadeAresta); // inferior esqueerdo~
        pontos.add(p1);
        pontos.add(p2);
        pontos.add(p3);
        pontos.add(p4);
        return new Quadrado(pontos);
    }

    public void moverCobra(int position, double newX, double newY) {
        Quadrado quadAtual = this.cobra.get(position);
        Quadrado quadNovo = (Quadrado) quadAtual.translatePolygon(newX - quadAtual.findCentroide().getX(), newY - quadAtual.findCentroide().getY());
        this.cobra.set(position, quadNovo);
    }

    public void direcionarCobra(Direcao direcao){
        if(isDirecaoValida(direcao)){
            for (int i = this.cobra.size()-1; i > 0; i--) {
                moverCobra(i, this.cobra.get(i-1).findCentroide().getX(), this.cobra.get(i-1).findCentroide().getY());
            }
            switch (direcao) {
                case DOWN:
                    moverCobra(0, this.cobra.get(0).findCentroide().getX(), (this.cobra.get(0).findCentroide().getY())+ this.dimensao);
                    break;
                case UP:
                    moverCobra(0, this.cobra.get(0).findCentroide().getX(), (this.cobra.get(0).findCentroide().getY())- this.dimensao);
                    break;
                case RIGHT:
                    moverCobra(0, (this.cobra.get(0).findCentroide().getX())+ this.dimensao, (this.cobra.get(0).findCentroide().getY()));
                    break;
                case LEFT:
                    moverCobra(0, (this.cobra.get(0).findCentroide().getX())- this.dimensao, (this.cobra.get(0).findCentroide().getY()));
                    break;
                default:
                    break;
            }
            this.ultimaDirecao = direcao;
        }
    }

    /*
    public void snakeEats(){
        addNewSnakePart(new Ponto(cobra.get(cobra.size()-1).findCentroide().getX(), cobra.get(cobra.size()-1).findCentroide().getY()));
    }
    */

    // public void addNewSnakePart(Ponto ponto){
    //     this.cobra.add(new Quadrado(ponto, this.dimensao));    // Em que pontos é que o novo quadrado é adicionado? Deve receber argumentos?
    // }

    private boolean isDirecaoValida(Direcao novaDirecao) {
        if (ultimaDirecao == null) {
            return true;  // Sem restrições para o primeiro movimento
        }
        // Impede movimento na direção oposta
        return !((ultimaDirecao == Direcao.UP && novaDirecao == Direcao.DOWN) ||
                (ultimaDirecao == Direcao.DOWN && novaDirecao == Direcao.UP) ||
                (ultimaDirecao == Direcao.LEFT && novaDirecao == Direcao.RIGHT) ||
                (ultimaDirecao == Direcao.RIGHT && novaDirecao == Direcao.LEFT));
    }


    public Quadrado getCabeca() {
        return this.cobra.get(0);
    }

    public ArrayList<Quadrado> getCobra() {
        return this.cobra;
    }

    public double getDimensao() {
        return this.dimensao;
    }

    public int getTamanho() {
        return this.cobra.size();
    }

    public int getAngulo() {
        switch (ultimaDirecao) {
            case RIGHT:
                return 0;  // Direita
            case UP:
                return 90;  // Cima
            case LEFT:
                return 180;  // Esquerda
            case DOWN:
                return 270;  // Baixo
            default:
                return 0;  // Valor padrão ou erro
        }
    }
}