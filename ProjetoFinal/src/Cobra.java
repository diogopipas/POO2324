
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
import java.util.List;

public class Cobra {
    private List<Quadrado> segmentos; // Lista de segmentos que compõem a cobra
    private int tamanhoSegmento; // Tamanho de cada segmento da cobra

    public Cobra(Ponto posicaoInicial, int tamanhoSegmento) {
        this.segmentos = new ArrayList<>();
        this.tamanhoSegmento = tamanhoSegmento;
        // Inicialmente a cobra tem apenas a cabeça
        this.segmentos.add(criarQuadrado(posicaoInicial));
    }

    private Quadrado criarQuadrado(Ponto centro) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(centro.getX() - tamanhoSegmento / 2, centro.getY() - tamanhoSegmento / 2));
        pontos.add(new Ponto(centro.getX() + tamanhoSegmento / 2, centro.getY() - tamanhoSegmento / 2));
        pontos.add(new Ponto(centro.getX() + tamanhoSegmento / 2, centro.getY() + tamanhoSegmento / 2));
        pontos.add(new Ponto(centro.getX() - tamanhoSegmento / 2, centro.getY() + tamanhoSegmento / 2));
        return new Quadrado(pontos);
    }

    public void mover(char direcao) {
        Ponto novaCabeçaPos = novaPosicaoCabeça(direcao);
        segmentos.add(0, criarQuadrado(novaCabeçaPos)); // Adiciona um novo quadrado na frente
        segmentos.remove(segmentos.size() - 1); // Remove o último quadrado para simular o movimento
    }

    private Ponto novaPosicaoCabeça(char direcao) {
        Ponto cabeçaAtual = segmentos.get(0).getP().get(0);
        switch (direcao) {
            case 'W': // Cima
                return new Ponto(cabeçaAtual.getX(), cabeçaAtual.getY() - tamanhoSegmento);
            case 'S': // Baixo
                return new Ponto(cabeçaAtual.getX(), cabeçaAtual.getY() + tamanhoSegmento);
            case 'A': // Esquerda
                return new Ponto(cabeçaAtual.getX() - tamanhoSegmento, cabeçaAtual.getY());
            case 'D': // Direita
                return new Ponto(cabeçaAtual.getX() + tamanhoSegmento, cabeçaAtual.getY());
            default:
                return cabeçaAtual; // Se nenhum comando válido, não move
        }
    }

    public void crescer() {
        Ponto últimaPos = segmentos.get(segmentos.size() - 1).getP().get(0);
        segmentos.add(criarQuadrado(últimaPos)); // Adiciona um novo quadrado no final sem mover os outros
    }
}
