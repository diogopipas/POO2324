/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 * Esta classe é responsável pela criação da grelha de células, e pela deteção de colisão entre a cobra e as
 * paredes da arena
 */

<<<<<<< HEAD
// import java.awt.*;
/* 
public interface Arena {
    public int getArenaWidth();
    public int getArenaHeight();
    public boolean detectCollision();
}
*/
=======
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;
>>>>>>> parent of fbfdde2 (Push para o G)
import java.util.ArrayList;
import java.util.Random;

public class Arena {
    private int largura, altura;
    private Cobra cobra;
    private Comida comida;
    private ArrayList<Poligono> obstaculos;

    public Arena(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.cobra = new Cobra(new Ponto(5, 5)); // Posição inicial da cobra
        this.comida = gerarComida();
        this.obstaculos = new ArrayList<>();
    }

    private Ponto gerarComida() {
        Random random = new Random();
        Ponto novaComida;
        do {
            novaComida = new Ponto(random.nextInt(largura), random.nextInt(altura));
        } while (cobra.getCorpo().stream().anyMatch(quadrado -> quadrado.getP().contains(novaComida)));
        return novaComida;
    }

    public void atualizar(char direcao) {
        cobra.move(direcao);
        if (cobra.getCorpo().get(0).getP().contains(comida)) { // Verifica se a cabeça da cobra está na comida
            cobra.crescer();
            comida = gerarComida(); // Gera nova comida
        }
    }

    public void exibirEstado() {
        System.out.println("Cobra: " + cobra.getCorpo());
        System.out.println("Comida: " + comida);
        System.out.println("Obstáculos: " + obstaculos);
    }

    public void verificarColisoes() {
        Quadrado cabeca = cobra.getCorpo().get(0);
        // Verificar colisão com as bordas da arena
        if (cabeca.getP().get(0).getX() < 0 || cabeca.getP().get(0).getX() >= largura ||
            cabeca.getP().get(0).getY() < 0 || cabeca.getP().get(0).getY() >= altura) {
            jogoAtivo = false;
            System.out.println("Game Over: Cobra colidiu com a borda!");
        }
        
        // Verificar colisão com o corpo
        for (int i = 1; i < cobra.getCorpo().size(); i++) {
            if (cabeca.getP().contains(cobra.getCorpo().get(i).getP().get(0))) {
                jogoAtivo = false;
                System.out.println("Game Over: Cobra colidiu consigo mesma!");
            }
        }
    }
}
