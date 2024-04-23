import javax.swing.*;

public class ClientGUI extends JFrame {
    private Arena arena;

    public ClientGUI(Arena arena) {
        this.arena = arena;
        setTitle("OOPS - Object-Oriented Programmed Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(arena.getWidth() * 20, arena.getHeight() * 20); // Tamanho das células
        setResizable(false);
        add(new Panel());
        setLocationRelativeTo(null); // Centralizar a janela
        setVisible(true);
    }
}


