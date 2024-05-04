import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class PainelSul extends JPanel {
    private final JLabel scorelabel;

    public PainelSul() {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(0, 2));
        this.scorelabel = new JLabel();
        this.scorelabel.setForeground(Color.white);
        this.setScoreLabel(0);
        this.scorelabel.setFont(new Font("Monospaced", 0, 18));
        this.scorelabel.setPreferredSize(new Dimension(100, 30));
        this.add(this.scorelabel);
    }

    public void setScoreLabel(int score) {
        this.scorelabel.setText(" Score: " + score);
    }
}
