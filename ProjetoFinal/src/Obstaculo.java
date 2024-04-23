import java.util.ArrayList;

public class Obstaculo {
    private ArrayList<Ponto> vertices;
    private boolean dynamic;
    // Outros atributos e métodos

    public Obstaculo(ArrayList<Ponto> vertices, boolean dynamic) {
        this.vertices = vertices;
        this.dynamic = dynamic;
    }

    // Métodos para verificar colisões, mover obstáculos dinâmicos, etc.
}