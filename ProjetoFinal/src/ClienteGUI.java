import javax.swing.*;

public class ClienteGUI {
    public static void main(String[] args) {
        Arena arena = new Arena(200, 100);
        // Inicializar outros elementos da arena
        Painel panel = new Painel(arena);
        JFrame frame = new JFrame("OOPS - Object-Oriented Programmed Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        Simulacao simulation = new Simulacao(arena);
        simulation.start();
    }
}
