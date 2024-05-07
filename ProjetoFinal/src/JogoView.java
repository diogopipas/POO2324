import java.awt.*;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

class JogoView extends JComponent {
    private Celula[][] snakeGame;

    public void addKeyArrowListener(int keyCode, String Name, Action action) {
        this.getInputMap(2).put(KeyStroke.getKeyStroke(keyCode, 0), Name);
        this.getActionMap().put(Name, action);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for(int i = 0; i < this.snakeGame.length; ++i) {
            for(int j = 0; j < this.snakeGame[i].length; ++j) {
                if (this.snakeGame[i][j].isSnake()) {
                    g2d.setColor(Color.GREEN);
                } else if (this.snakeGame[i][j].isFood()) {
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                //g2d.draw(this.snakeGame[i][j].getRect());
                g2d.fill(this.snakeGame[i][j].getRect());
            }
        }

        g2d.setColor(Color.RED);
        g2d.drawRect(20, 20, 400, 400);
    }

    public void setCells(Celula[][] allCells) {
        this.snakeGame = allCells;
    }

    public Celula[][] getCells() {
        return this.snakeGame;
    }
}
