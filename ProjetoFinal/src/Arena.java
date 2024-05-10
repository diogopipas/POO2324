/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 * Esta classe é responsável pela criação da grelha de células, e pela deteção de colisão entre a cobra e as
 * paredes da arena
 */

// import java.awt.*;
/*
public interface Arena {
    public int getArenaWidth();
    public int getArenaHeight();
    public boolean detectCollision();
}
*/
import java.util.ArrayList;
import java.util.Random;

public class Arena {
    private int largura, altura;
    private Cobra cobra;
    private Comida comida;
    private ArrayList<Obstaculo> obstaculo;
    private boolean jogoAtivo = true;

    static final int TOTAL_GAME_AREA = 20;

    public Arena(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.cobra = new Cobra(3, TOTAL_GAME_AREA,TOTAL_GAME_AREA); // Posição inicial da cobra
        this.comida = new Comida();
        this.obstaculo = new ArrayList<>();

    }

    public void atualizar(Direcao d) {
        cobra.direcionarCobra(d);
        if (cobra.getCabeca().getP().contains(this.comida)) { // Verifica se a cabeça da cobra está na comida
            //cobra.crescer();
            comida = new Comida(); // Gera nova comida
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