import java.awt.*;

import javax.swing.JPanel;

public class InterfaceGrafica extends JPanel{
    private Simulador sl;
    private Ponto topLeftCorner;
    private double dimensao;
    public InterfaceGrafica(Simulador sl) {
        this.sl = sl;
        setPreferredSize(new Dimension(300, 300));  // Assegura-se de que o tamanho é suficiente para a arena
        setBackground(Color.BLACK);  // Define a cor de fundo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Arena arena = sl.getArena();
        int cellSize = Math.min(getWidth() / arena.getLargura(), getHeight() / arena.getAltura());

        // Desenha a cobra
        g2d.setColor(Color.GREEN);
        for (Quadrado quad : arena.getCobra().getCobra()) {
            g2d.fillRect((int) quad.findCentroide().getX() * cellSize,
                         (int) quad.findCentroide().getY() * cellSize,
                         cellSize, cellSize);
        }

        // Desenha a comida
        g2d.setColor(Color.RED);
        Ponto posComida = arena.getComida().getPosicaoComida();
        g2d.fillRect((int) posComida.getX() * cellSize,
                     (int) posComida.getY() * cellSize,
                     cellSize, cellSize);

        // Desenha os obstáculos
        g2d.setColor(Color.BLUE);
        for (Obstaculo obstaculo : arena.getObstaculos()) {
            for (Ponto p : obstaculo.getPoligono().getP()) {
                g2d.fillRect((int) p.getX() * cellSize,
                             (int) p.getY() * cellSize,
                             cellSize, cellSize);
            }
        }
    }
}