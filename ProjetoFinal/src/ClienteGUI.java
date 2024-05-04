import javax.swing.*;
import java.awt.*;

public class ClienteGUI {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(450, 550));
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setTitle("Snake Game");
                frame.getContentPane().setBackground(Color.BLACK);
            }
        });
    }
}

class Frame extends JFrame {

    private JogoModel model;
    private JogoController controller;
    private JogoView view;
    private PainelNorte npanel;
    private PainelSul spanel;


    public Frame() {

        model = new JogoModel();

        view = new JogoView();
        add(view, BorderLayout.CENTER);

        npanel = new PainelNorte();
        add(npanel, BorderLayout.NORTH);

        spanel = new PainelSul();
        add(spanel, BorderLayout.SOUTH);

        controller = new JogoController(model, view, npanel, spanel);


    }
}