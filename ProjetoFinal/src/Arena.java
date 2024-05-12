/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 * Esta classe é responsável pela criação da grelha de células, e pela deteção de colisão entre a cobra e as
 * paredes da arena
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
    private ArrayList<Obstaculo> obstaculos;
    private boolean jogoAtivo = true;
    private Quadrado[][] grelha;
    private static final double DIMENSAO_CELULA = 1;


    public Arena(int largura, int altura, int dimensaoComida, String tipoComida, int dimensaoCobra){
        this.largura = largura;
        this.altura = altura;
        this.dimensaoComida = dimensaoComida;
        this.tipoComida = tipoComida;
        this.dimensaoCobra = dimensaoCobra;
        inicializarGrelha();
        this.cobra = new Cobra(dimensaoCobra, new Ponto(new Random().nextDouble(1, largura), new Random().nextDouble(1, altura))); // Posição inicial da cobra
        this.comida = new Comida(tipoComida, new Ponto(new Random().nextDouble(1, largura), new Random().nextDouble(1, altura)), dimensaoComida);
        this.obstaculos = new ArrayList<>();

    }

    public void atualizar(Direcao d) {

        this.cobra.direcionarCobra(d);
        if (cobra.getCabeca().getP().contains(this.comida.getPosicaoComida())) { // Verifica se a cabeça da cobra está na comida
            //cobra.come();
            this.comida = new Comida(this.tipoComida, new Ponto(new Random().nextDouble(this.largura), new Random().nextDouble(this.altura)), this.dimensaoComida); // Gera nova comida
        }

    }

    public void verificarColisoes() {
        Quadrado cabeca = cobra.getCabeca();
        // Verificar colisão com as bordas da arena
        if (cabeca.getP().get(0).getX() < 0 || cabeca.getP().get(0).getX() >= largura ||
                cabeca.getP().get(0).getY() < 0 || cabeca.getP().get(0).getY() >= altura) {
            jogoAtivo = false;
            System.out.println("OOPS!: Cobra colidiu com a borda!");
            System.exit(0);
        }
        // Verificar colisão com o corpo
        for (int i = 1; i < cobra.getTamanho() ; i++) {
            if (cabeca.getP().contains(cobra.getCabeca().getP().get(0))) {
                jogoAtivo = false;
                System.out.println("OOPS!: Cobra colidiu consigo mesma!");
                System.exit(0);
            }
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