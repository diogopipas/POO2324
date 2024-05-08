import java.util.Scanner;

public class Jogo {
    private Arena arena;
    private boolean jogoAtivo;
    private Scanner scanner;

    public Jogo(int largura, int altura) {
        arena = new Arena(largura, altura);
        jogoAtivo = true;
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (jogoAtivo) {
            arena.exibirEstado();
            System.out.println("Digite 'W', 'A', 'S', 'D' para mover ou 'Q' para sair:");
            String comando = scanner.nextLine().toUpperCase();
            if (comando.equals("Q")) {
                jogoAtivo = false;
            } else {
                arena.atualizar(comando.charAt(0));
            }
            // Implementar a verificação de colisões aqui
        }
        scanner.close();
    }
}
