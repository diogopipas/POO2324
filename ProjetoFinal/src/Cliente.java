import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arena arena = new Arena(); // Supondo que a Arena já está inicializada e configurada
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            System.out.println(arena); // Exibir estado atual da arena
            System.out.print("Digite 'W', 'A', 'S', 'D' para mover ou 'Q' para sair: ");
            String comando = scanner.nextLine().toUpperCase();
            
            switch (comando) {
                case "W":
                    arena.moverCobra(Arena.Direcao.CIMA);
                    break;
                case "S":
                    arena.moverCobra(Arena.Direcao.BAIXO);
                    break;
                case "A":
                    arena.moverCobra(Arena.Direcao.ESQUERDA);
                    break;
                case "D":
                    arena.moverCobra(Arena.Direcao.DIREITA);
                    break;
                case "Q":
                    jogoAtivo = false;
                    break;
                default:
                    System.out.println("Comando inválido!");
            }

            if (arena.verificarColisao()) {
                System.out.println("Jogo terminado! Você colidiu!");
                jogoAtivo = false;
            }
        }
        scanner.close();
    }
}
