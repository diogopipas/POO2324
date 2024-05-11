/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 * Esta classe é responsável pela criação da grelha de células, e pela deteção de colisão entre a cobra e as
 * paredes da arena
 */

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Arena {
    private int largura, altura;
    private Cobra cobra;
    private int dimensaoCobra;
    private String tipoComida;
    private int dimensaoComida;
    private Comida comida;
    private ArrayList<Obstaculo> obstaculo;
    private boolean jogoAtivo = true;



    public Arena(String tipoComida, int dimensaoCobra, int dimensaoComida, int largura, int altura){
        this.largura = largura;
        this.altura = altura;
        this.dimensaoComida = dimensaoComida;
        this.dimensaoCobra = dimensaoCobra;
        this.cobra = new Cobra(dimensaoCobra, new Ponto(new Random().nextDouble(largura), new Random().nextDouble(altura))); // Posição inicial da cobra
        this.tipoComida = tipoComida;
        this.comida = new Comida(tipoComida, new Ponto(new Random().nextDouble(largura), new Random().nextDouble(altura)), dimensaoComida);
        this.obstaculo = new ArrayList<>();

    }

    public void atualizar(Direcao d) {
        cobra.direcionarCobra(d);
        if (cobra.getCabeca().getP().contains(this.comida)) { // Verifica se a cabeça da cobra está na comida
            //cobra.crescer();
            this.comida = new Comida(this.tipoComida, new Ponto(new Random().nextDouble(this.largura), new Random().nextDouble(this.altura)), this.dimensaoComida); // Gera nova comida
        }
    }

    public void verificarColisoes() {
        Quadrado cabeca = cobra.getCabeca();
        // Verificar colisão com as bordas da arena
        if (cabeca.getP().get(0).getX() < 0 || cabeca.getP().get(0).getX() >= largura ||
                cabeca.getP().get(0).getY() < 0 || cabeca.getP().get(0).getY() >= altura) {
            jogoAtivo = false;
            System.out.println("Game Over: Cobra colidiu com a borda!");
        }
        // Verificar colisão com o corpo
        for (int i = 1; i < cobra.getTamanho() ; i++) {
            if (cabeca.getP().contains(cobra.getCabeca().getP().get(0))) {
                jogoAtivo = false;
                System.out.println("Game Over: Cobra colidiu consigo mesma!");
            }
        }
    }
}