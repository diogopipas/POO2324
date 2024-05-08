import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


/* 
public interface Cobra {
    public void snakeDies();
    public void snakeEats();
    public boolean snakeCollides(Quadrado q2);
    public void updateSnakePosition();
    public void updateSnakeParts();
    public void addNewSnakePart(double x, double y);

    public void alterSnakeCoordinates(int position, double x, double y);

}
*/

import java.util.ArrayList;

public class Cobra {
    private ArrayList<Quadrado> corpo; // Cada segmento do corpo da cobra é um quadrado

    public Cobra(Ponto inicial) {
        corpo = new ArrayList<>();
        // Define o ponto inicial como a cabeça e assume que cada segmento da cobra tem tamanho 1x1
        addSegmento(inicial);
    }

    private void addSegmento(Ponto ponto) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(ponto);
        pontos.add(new Ponto(ponto.getX() + 1, ponto.getY()));
        pontos.add(new Ponto(ponto.getX() + 1, ponto.getY() + 1));
        pontos.add(new Ponto(ponto.getX(), ponto.getY() + 1));
        corpo.add(new Quadrado(pontos));
    }

    public void move(char direcao) {
        Quadrado cabecaAtual = corpo.get(0);
        Ponto pontoCabeca = cabecaAtual.getP().get(0); // Ponto de referência da cabeça

        Ponto novaPosicao = switch (direcao) {
            case 'W' -> new Ponto(pontoCabeca.getX(), pontoCabeca.getY() - 1); // Cima
            case 'S' -> new Ponto(pontoCabeca.getX(), pontoCabeca.getY() + 1); // Baixo
            case 'A' -> new Ponto(pontoCabeca.getX() - 1, pontoCabeca.getY()); // Esquerda
            case 'D' -> new Ponto(pontoCabeca.getX() + 1, pontoCabeca.getY()); // Direita
            default -> null;
        };

        if (novaPosicao != null) {
            corpo.add(0, new Quadrado(gerarPontos(novaPosicao)));
            corpo.remove(corpo.size() - 1); // Remove a cauda, simulando o movimento
        }
    }

    private ArrayList<Ponto> gerarPontos(Ponto base) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(base);
        pontos.add(new Ponto(base.getX() + 1, base.getY()));
        pontos.add(new Ponto(base.getX() + 1, base.getY() + 1));
        pontos.add(new Ponto(base.getX(), base.getY() + 1));
        return pontos;
    }

    public void crescer() {
        Ponto caudaAtual = corpo.get(corpo.size() - 1).getP().get(0);
        addSegmento(new Ponto(caudaAtual.getX(), caudaAtual.getY() + 1)); // Simples adição para crescimento
    }

    public ArrayList<Quadrado> getCorpo() {
        return corpo;
    }
}
