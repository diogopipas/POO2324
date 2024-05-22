import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

class Painel extends JPanel {
    private final JButton play;
    public Painel() {
        this.setBackground(Color.BLACK);
        this.play = new JButton("Play");
        this.play.setBackground(Color.WHITE);
        this.play.setPreferredSize(new Dimension(100, 30));
        this.add(this.play);
    }

    public void addPlayButtonListener(ActionListener playAction) {
        this.play.addActionListener(playAction);
    }

    /*
    public void updatePanelForGameOverMode() {
        this.newGame.setEnabled(true);
        this.play.setEnabled(false);
    }

    public void updatePanelforGameStartMode() {
        this.newGame.setEnabled(true);
        this.play.setEnabled(true);
    }

     */
}
