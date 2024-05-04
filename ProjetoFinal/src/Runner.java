import javax.swing.JFrame;
import java.awt.*;

public class Runner {
    public static void run(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(450, 550));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("OOPS - The Snake Game");
        frame.getContentPane().setBackground(Color.BLACK);
    }
}
