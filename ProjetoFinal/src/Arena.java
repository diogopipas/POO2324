/** Classe responsável pela inicialização de todos os Objetos presentes na Arena,
 * incluindo a cobra, a comida e os obstáculos
 *
 * @version 1.0
 * @author André Santos, Diogo Porto
 *
 */

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class Arena {


    private int largura, altura;
    private Cobra cobra;
    private String tipoComida;
    private int dimensaoComida;
    private int dimensaoCobra;
    private Comida comida;
    private ArrayList<Poligono> obstaculoPoligonos;
    private ArrayList<Obstaculo> obstaculos;
    private boolean jogoAtivo = true;
    private Quadrado[][] grelha;
    private static final double DIMENSAO_CELULA = 1;


    public Arena(int largura, int altura, int dimensaoComida, String tipoComida, int dimensaoCobra, ArrayList<Poligono> obstaculoPoligonos){
        this.largura = largura;
        this.altura = altura;
        this.dimensaoComida = dimensaoComida;
        this.tipoComida = tipoComida;
        this.dimensaoCobra = dimensaoCobra;
        this.obstaculoPoligonos = obstaculoPoligonos;
        inicializarGrelha();
        this.obstaculos = gerarObstaculos();
        this.comida = gerarComida();
        this.cobra = gerarCobra();

    }

    public void atualizar(Direcao d) {

        this.cobra.direcionarCobra(d);
        if (cobra.getCabeca().getP().contains(this.comida.getPosicaoComida())) { // Verifica se a cabeça da cobra está na comida
            //cobra.come();
            this.comida = new Comida(this.tipoComida, new Ponto(new Random().nextDouble(this.largura), new Random().nextDouble(this.altura)), this.dimensaoComida); // Gera nova comida
        }

    }

    public ArrayList<Obstaculo> gerarObstaculos(){
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        for(int i = 0; i < this.obstaculoPoligonos.size(); i++){
            Obstaculo obstaculo = new Obstaculo(this.obstaculoPoligonos.get(i), this.obstaculoPoligonos.get(i).findCentroide());
            obstaculos.add(obstaculo);
        }
        return obstaculos;
    }

    /*
    public ArrayList<Obstaculo> gerarObstaculos(){
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        Ponto p;
        for(int i = 0; i < this.obstaculoPoligonos.size(); i++){
            do {
                p = new Ponto(new Random().nextDouble(this.obstaculoPoligonos.get(i).getMaiorSegmento(), this.largura - this.obstaculoPoligonos.get(i).getMaiorSegmento()),
                        new Random().nextDouble(this.obstaculoPoligonos.get(i).getMaiorSegmento(), this.altura - this.obstaculoPoligonos.get(i).getMaiorSegmento()));
            } while (intersectsCobra(p) || intersectsComida(p));
            Obstaculo obstaculo = new Obstaculo(this.obstaculoPoligonos.get(i), p);
            obstaculos.add(obstaculo);
        }
        return obstaculos;
    }

     */

    public Comida gerarComida(){
        Ponto p;
        do {
            p = new Ponto(new Random().nextDouble(this.dimensaoComida, this.largura - this.dimensaoComida), new Random().nextDouble(this.dimensaoComida, this.altura-this.dimensaoComida));
        } while (intersectsObstaculo(p));

        return new Comida(this.tipoComida, p, this.dimensaoComida);
    }

    public Cobra gerarCobra(){
        Ponto p;
        do {
            p = new Ponto(new Random().nextDouble(this.dimensaoComida, this.largura - this.dimensaoComida), new Random().nextDouble(this.dimensaoCobra, this.altura-this.dimensaoCobra));
        }while (intersectsObstaculo(p) || intersectsComida(p));
        return new Cobra(this.dimensaoCobra, p);
    }

    public boolean intersectsObstaculo(Ponto p){
        for(int i = 0; i < this.obstaculos.size(); i++){
            if(this.obstaculos.get(i).getPoligono().containsPonto(p)){
                return true;
            }
        }
        return false;
    }

    private boolean intersectsComida(Ponto p){
        if(this.comida.getFormaComida() instanceof Quadrado){
            Quadrado quadrado = new Quadrado(getVerticesFromCentroid(this.comida.getPosicaoComida()));
            return quadrado.containsPonto(p);
        }
        else{
            Circulo circulo = new Circulo(this.comida.getDimensao(), this.comida.getPosicaoComida());
            return circulo.containsPonto(p);
        }
    }


    public void inicializarGrelha(){
        this.grelha = new Quadrado[this.largura][this.altura];
        for(int i = 1; i < this.altura; i++){
            for(int j = 1; j < this.largura; j++){
                this.grelha[j][i] = new Quadrado(getVerticesFromCentroid(new Ponto(j, i)));
            }
        }
    }

    public ArrayList<Ponto> getVerticesFromCentroid(Ponto centroide){
        ArrayList<Ponto> pontos = new ArrayList<>();
        Ponto p1 = new Ponto(centroide.getX()-DIMENSAO_CELULA/2, centroide.getY()+DIMENSAO_CELULA/2); // supperior esqueerdo
        Ponto p2 = new Ponto(centroide.getX()+DIMENSAO_CELULA/2, centroide.getY()+DIMENSAO_CELULA/2); // supperior direito
        Ponto p3 = new Ponto(centroide.getX()+DIMENSAO_CELULA/2, centroide.getY()-DIMENSAO_CELULA/2); // inferior direito
        Ponto p4 = new Ponto(centroide.getX()-DIMENSAO_CELULA/2, centroide.getY()-DIMENSAO_CELULA/2); // inferior esquerdo
        pontos.add(p1);
        pontos.add(p2);
        pontos.add(p3);
        pontos.add(p4);
        return pontos;
    }

    public int getAltura() {
        return altura;
    }

    public Cobra getCobra() {
        return this.cobra;
    }

    public int getDimensaoCobra() {
        return dimensaoCobra;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public int getDimensaoComida() {
        return dimensaoComida;
    }

    public Comida getComida() {
        return comida;
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return this.obstaculos;
    }

    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public int getLargura() {
        return largura;
    }


    public Quadrado[][] getGrelha() {
        return grelha;
    }
}