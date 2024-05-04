import java.util.Random;

public interface Comida {
    public Ponto getPosicao();
    public void reposicionar(Arena arena);
    public Ponto gerarPosicaoAleatoria(Arena arena);
}
