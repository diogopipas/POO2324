/** Classe responsável por definir as cores correspondentes a cada objeto e desenhá-los
 * @version 1.0
 * @author André Santos, Diogo Porto
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class InterfaceGrafica extends JPanel {
    private Simulador sl;
    private ScorePanel scorePanel;
    private static final Color DARK_GREEN = new Color(0, 180, 0);

    public InterfaceGrafica(Simulador sl) {
        this.sl = sl;
        setLayout(new BorderLayout()); // Usa BorderLayout para melhor disposição dos componentes
        setBackground(Color.DARK_GRAY); // Define a cor de fundo


        scorePanel = new ScorePanel(this.sl.getArena());
        add(scorePanel, BorderLayout.NORTH);


        
        setPreferredSize(new Dimension(600, 600)); // Ajusta o tamanho total do painel
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
                        cellSize*this.sl.getArena().getDimensaoCobra(), cellSize*this.sl.getArena().getDimensaoCobra());
            } else {
                g2d.drawRect((int) cabeca.findCentroide().getX() * cellSize,
                        (int) cabeca.findCentroide().getY() * cellSize,
                        cellSize*this.sl.getArena().getDimensaoCobra(), cellSize*this.sl.getArena().getDimensaoCobra());
            }

            // Desenha o resto do corpo
            g2d.setColor(DARK_GREEN);
            for (int i = 1; i < cobra.size(); i++) {
                Quadrado quad = cobra.get(i);
                if (isCompleta()) {
                    g2d.fillRect((int) quad.findCentroide().getX() * cellSize,
                            (int) quad.findCentroide().getY() * cellSize,
                            cellSize * this.sl.getArena().getDimensaoCobra(), cellSize * this.sl.getArena().getDimensaoCobra());
                } else {
                    g2d.drawRect((int) quad.findCentroide().getX() * cellSize,
                            (int) quad.findCentroide().getY() * cellSize,
                            cellSize * this.sl.getArena().getDimensaoCobra(), cellSize * this.sl.getArena().getDimensaoCobra());
                }
            }
        }

        // Desenha a comida
        g2d.setColor(Color.RED);
        Ponto posComida = arena.getComida().getPosicaoComida();
        if (isCompleta()) {
            if(arena.getComida().getTipoComida().equals("quadrado")){
                g2d.fillRect((int) posComida.getX() * cellSize,
                        (int) posComida.getY() * cellSize,
                        cellSize * arena.getDimensaoComida(), cellSize * arena.getDimensaoComida());
            }
            else{
                g2d.fillOval((int)posComida.getX() * cellSize, (int)posComida.getY() * cellSize, cellSize * arena.getDimensaoComida(), cellSize * arena.getDimensaoComida());
            }


        } else {
            if(arena.getComida().getTipoComida().equals("quadrado")){
                g2d.drawRect((int) posComida.getX() * cellSize,
                        (int) posComida.getY() * cellSize,
                        cellSize * arena.getDimensaoComida(), cellSize * arena.getDimensaoComida());
            }
            else{
                g2d.drawOval((int)posComida.getX() * cellSize, (int)posComida.getY() * cellSize, cellSize * arena.getDimensaoComida(), cellSize * arena.getDimensaoComida());
            }

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
