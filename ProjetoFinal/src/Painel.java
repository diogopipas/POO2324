import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

class Painel extends JPanel {
    private final JButton play;
    private final JButton newGame;

    public Painel() {
        this.setBackground(Color.BLACK);
        this.play = new JButton("Play");
        this.play.setBackground(Color.WHITE);
        this.play.setPreferredSize(new Dimension(100, 30));
        this.add(this.play);
        this.newGame = new JButton("New Game");
        this.newGame.setBackground(Color.WHITE);
        this.newGame.setPreferredSize(new Dimension(100, 30));
        this.add(this.newGame);
    }

    public void addPlayButtonListener(ActionListener playAction) {
        this.play.addActionListener(playAction);
    }

    public void addNewGameButtonListener(ActionListener newGameAction) {
        this.newGame.addActionListener(newGameAction);
    }

    public void updatePanelForPlayMode(boolean isPlaying) {
        if (isPlaying) {
            this.newGame.setEnabled(false);
            this.play.setText("Pause");
        } else {
            this.newGame.setEnabled(true);
            this.play.setText("Play");
        }

    }

    public void updatePanelForGameOverMode() {
        this.newGame.setEnabled(true);
        this.play.setEnabled(false);
    }

    public void updatePanelforGameStartMode() {
        this.newGame.setEnabled(true);
        this.play.setEnabled(true);
    }
}
