/**
 * @version 1.0
 * @author André Santos, Diogo Porto
 */
public class Simulacao {
    private Arena arena;
    private boolean running;

    public Simulacao(Arena arena) {
        this.arena = arena;
    }

    public void start() {
        running = true;
        // Loop principal da simulação
        while (running) {
            // Atualizar o estado da arena, verificar colisões, etc.
            // Renderizar a arena
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return true;
    }
}
