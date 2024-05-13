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
    private ArrayList<Poligono> obstaculos;
    private boolean jogoAtivo = true;
    private Quadrado[][] grelha;
    private static final double DIMENSAO_CELULA = 1;
    private int pontuacao;


    public Arena(int largura, int altura, int dimensaoComida, String tipoComida, int dimensaoCobra){
        this.largura = largura;
        this.altura = altura;
        this.dimensaoComida = dimensaoComida;
        this.tipoComida = tipoComida;
        this.dimensaoCobra = dimensaoCobra;
        inicializarGrelha();
        this.cobra = gerarCobra();
        this.comida = gerarComida();
        this.obstaculos = obstaculos;
        this.pontuacao = 0;
    }

    public void atualizar(Direcao d) {
        cobra.direcionarCobra(d);
    
        if (cobra.getCabeca().containsPonto(comida.getPosicaoComida())) { 
            cobra.addNewSnakePart();  
            gerarComida(); 
            pontuacao++;
        }
    
        verificarColisoes();  
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
        if (cobra.verificarColisaoComCorpo()) {
            jogoAtivo = false;
            System.out.println("OOPS!: Cobra colidiu consigo mesma!");
            System.exit(0);// Encerra a função se uma colisão consigo mesma for detectada
        }
    }

    public Comida gerarComida() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(dimensaoComida,this.largura - dimensaoComida);  // gera um valor entre 0 (inclusive) e largura (exclusive)
            y = random.nextInt(dimensaoComida,this.altura - dimensaoComida);   // gera um valor entre 0 (inclusive) e altura (exclusive)
        } while (intersectsSnake(new Ponto(x, y)));  // Garante que a comida não apareça dentro da cobra
    
        comida = new Comida(tipoComida, new Ponto(x, y), dimensaoComida);
        return comida;
    }
    public Cobra gerarCobra(){
        return new Cobra(this.dimensaoCobra, new Ponto(new Random().nextDouble(this.dimensaoComida, this.largura - this.dimensaoComida), new Random().nextDouble(this.dimensaoCobra, this.altura-this.dimensaoCobra)));
    }

    private boolean intersectsSnake(Ponto p) {
        // Check if the point intersects with any part of the snake
        for (Quadrado corpo : cobra.getCobra()) {
            if (corpo.containsPonto(p)) {
                return true;
            }
        }
        return false;
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

    public ArrayList<Poligono> getObstaculos() {
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

    public int getPontuacao() {
        return this.pontuacao;
    }
}