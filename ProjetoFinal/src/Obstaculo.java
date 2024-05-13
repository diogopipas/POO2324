import java.util.ArrayList;

public class Obstaculo {
    private Poligono poligono;
    private Ponto posicao;
    public Obstaculo(Poligono poligono, Ponto posicao){
        this.poligono = poligono;
        this.posicao = posicao;
    }

    public Poligono getPoligono() {
        return this.poligono;
    }

    public Ponto getPosicao() {
        return posicao;
    }
}