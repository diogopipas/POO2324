import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450, 550));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("OOPS! The Snake Game");
        this.getContentPane().setBackground(Color.BLACK);
    }
}
