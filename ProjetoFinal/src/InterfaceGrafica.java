import java.awt.*;
import javax.swing.JPanel;

public class InterfaceGrafica extends JPanel {
    private Simulador sl;
    public static final Color DARK_GREEN = new Color(0, 179, 0);

    public InterfaceGrafica(Simulador sl) {
        this.sl = sl;
        setPreferredSize(new Dimension(600, 600)); // Assegura-se de que o tamanho é suficiente para a arena
        setBackground(Color.DARK_GRAY); // Define a cor de fundo
    }

    public boolean isCompleta() {
        return this.sl.getModoRasterizacao().equals("completa");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Arena arena = sl.getArena();
        int cellSize = Math.min(getWidth() / arena.getLargura(), getHeight() / arena.getAltura());

        // Desenha a grade
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= arena.getLargura(); i++) {
            g2d.drawLine(i * cellSize, 0, i * cellSize, getHeight());
        }
        for (int i = 0; i <= arena.getAltura(); i++) {
            g2d.drawLine(0, i * cellSize, getWidth(), i * cellSize);
        }

        // Desenha a cobra
        java.util.List<Quadrado> cobra = arena.getCobra().getCobra();
        if (!cobra.isEmpty()) {
            // Desenha a cabeça da cobra
            g2d.setColor(Color.GREEN);
            Quadrado cabeca = cobra.get(0);
            if (isCompleta()) {
                g2d.fillRect((int) cabeca.findCentroide().getX() * cellSize,
                        (int) cabeca.findCentroide().getY() * cellSize,
                        cellSize*2, cellSize*2);
            } else {
                g2d.drawRect((int) cabeca.findCentroide().getX() * cellSize,
                        (int) cabeca.findCentroide().getY() * cellSize,
                        cellSize*2, cellSize*2);
            }

            // Desenha o resto do corpo
            g2d.setColor(DARK_GREEN);
            for (int i = 1; i < cobra.size(); i++) {
                Quadrado quad = cobra.get(i);
                if (isCompleta()) {
                    g2d.fillRect((int) quad.findCentroide().getX() * cellSize,
                            (int) quad.findCentroide().getY() * cellSize,
                            cellSize*2, cellSize*2);
                } else {
                    g2d.drawRect((int) quad.findCentroide().getX() * cellSize,
                            (int) quad.findCentroide().getY() * cellSize,
                            cellSize*2, cellSize*2);
                }
            }
        }

        // Desenha a comida
        g2d.setColor(Color.RED);
        Ponto posComida = arena.getComida().getPosicaoComida();
        if (isCompleta()) {
            g2d.fillRect((int) posComida.getX() * cellSize,
                    (int) posComida.getY() * cellSize,
                    cellSize*2, cellSize*2);
        } else {
            g2d.drawRect((int) posComida.getX() * cellSize,
                    (int) posComida.getY() * cellSize,
                    cellSize*2, cellSize*2);
        }

        // Desenha os obstáculos
        g2d.setColor(Color.BLUE);
        for (Obstaculo obstaculo : arena.getObstaculos()) {
            for (Ponto p : obstaculo.getPoligono().getP()) {
                if (isCompleta()) {
                    g2d.fillRect((int) p.getX() * cellSize,
                            (int) p.getY() * cellSize,
                            cellSize*2, cellSize*2);
                } else {
                    g2d.drawRect((int) p.getX() * cellSize,
                            (int) p.getY() * cellSize,
                            cellSize*2, cellSize*2);
                }
            }
        }
    }
}
