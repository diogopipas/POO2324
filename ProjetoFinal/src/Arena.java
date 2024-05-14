/** Classe responsável pela inicialização de todos os Objetos presentes na Arena, incluindo a cobra, a comida e os obstáculos
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
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
    private ArrayList<String> obstaculoTypes;
    private Quadrado[][] grelha;
    private static final double DIMENSAO_CELULA = 1;
    private int pontuacao;


    public Arena(int largura, int altura, int dimensaoComida, String tipoComida, int dimensaoCobra, ArrayList<Poligono> obstaculoPoligonos, ArrayList<String> obstaculoTypes){
        this.largura = largura;
        this.altura = altura;
        this.dimensaoComida = dimensaoComida;
        this.tipoComida = tipoComida;
        this.dimensaoCobra = dimensaoCobra;
        this.obstaculoPoligonos = obstaculoPoligonos;
        this.obstaculoTypes = obstaculoTypes;
        inicializarGrelha();
        this.cobra = gerarCobra();
        this.comida = gerarComida();
        this.obstaculos = gerarObstaculos();
        this.pontuacao = 0;


    }

    public void atualizar(Direcao d) {
        cobra.direcionarCobra(d);
        rotateObstacle();
        verificarColisoes();
    }

    public void verificarColisoes() {
        Quadrado cabeca = cobra.getCabeca();
        // Verificar colisão com as bordas da arena
        if (cabeca.getP().get(0).getX() < 0 || cabeca.getP().get(0).getX() >= largura ||
                cabeca.getP().get(0).getY() < 0 || cabeca.getP().get(0).getY() >= altura) {
            System.out.println("OOPS!: Cobra colidiu com a borda!");
            System.exit(0);
        }
        // Verificar colisão com o corpo
        if (cobra.verificarColisaoComCorpo()) {
            System.out.println("OOPS!: Cobra colidiu consigo mesma!");
            System.exit(0);// Encerra a função se uma colisão consigo mesma for detectada
        }

        //verificar colisão com a comida
        if (cobra.getCabeca().containsPonto(comida.getPosicaoComida())){
            cobra.addNewSnakePart();
            gerarComida();
            this.pontuacao++;
        }


        // Verificar colisão com o obstaculo
        for(int i = 0; i < this.obstaculos.size(); i++){
            for(int j = 0; j < this.obstaculos.get(i).getPoligono().getP().size(); j++){
                if (this.cobra.getCabeca().containsPonto(this.obstaculos.get(i).getPoligono().getP().get(j))){
                    System.out.println("OOPS!: Cobra colidiu com um objeto!");
                    System.exit(0);
                }
            }
        }


        /*
        // Verificar colisão com o obstaculo
        for(int i = 0; i < this.obstaculos.size(); i++){
            if (this.cobra.getCabeca().containsPonto(this.obstaculos.get(i).getPoligono().findCentroide())){
                System.out.println("OOPS!: Cobra colidiu com um objeto!");
                System.exit(0);
            }
        }

         */
    }

    public void rotateObstacle(){
        for(int i = 0; i < this.obstaculoTypes.size(); i++){
            if(this.obstaculoTypes.get(i).equals("Dinamico")){
                this.obstaculos.set(i, new Obstaculo(this.obstaculos.get(i).getPoligono().rotatePolygon(40), this.obstaculos.get(i).getPoligono().findCentroide()));
            }
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




    public Comida gerarComida() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(dimensaoComida,this.largura - dimensaoComida);  // gera um valor entre 0 (inclusive) e largura (exclusive)
            y = random.nextInt(dimensaoComida,this.altura - dimensaoComida);   // gera um valor entre 0 (inclusive) e altura (exclusive)
        } while (intersectsCobra(new Ponto(x, y)));  // Garante que a comida não apareça dentro da cobra
        comida = new Comida(tipoComida, new Ponto(x, y), dimensaoComida);
        return comida;
    }


    public Cobra gerarCobra(){
        Ponto p;
        p = new Ponto(new Random().nextDouble(this.dimensaoComida, this.largura - this.dimensaoComida), new Random().nextDouble(this.dimensaoCobra, this.altura-this.dimensaoCobra));
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

    public boolean intersectsComida(Ponto p){
        if(this.comida.getFormaComida() instanceof Quadrado){
            Quadrado quadrado = new Quadrado(getVerticesFromCentroid(this.comida.getPosicaoComida()));
            return quadrado.containsPonto(p);
        }
        else{
            Circulo circulo = new Circulo(this.comida.getDimensao(), this.comida.getPosicaoComida());
            return circulo.containsPonto(p);
        }
    }

    public boolean intersectsCobra(Ponto p) {
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

    public ArrayList<Obstaculo> getObstaculos() {
        return this.obstaculos;
    }

    public int getLargura() {
        return largura;
    }


    public Quadrado[][] getGrelha() {
        return grelha;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int incrementPontuacao() {
        return this.pontuacao++;
    }
}