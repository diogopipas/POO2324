/** Classe reponsável por correr o jogo, ou seja, a leitura dos inputs que vão movimentar a cobra,
 * assim como o chamamento dos metodos que efetuam e imprimem os proximos passos da simulação
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Runner {
    private Scanner sc;
    private Simulador sl;
    private InterfaceTextual it;
    private CobraZarolha cz;
    private InterfaceGrafica ig;
    private Painel painel;
    private GameFrame frame;

    public Runner(Simulador sl){
        this.sl = sl;
        this.sc = new Scanner(System.in);
        this.it = new InterfaceTextual(sl);
        this.cz = new CobraZarolha(sl);
        this.ig = new InterfaceGrafica(sl);
    }


    public void run(){
        if(this.sl.getModoInterface().equals("textual")){
            runTextual();
        }
        else{
            runGrafica();
        }
    }
    public void runTextual() {
        boolean isRunning = true;
        if (this.sl.getModoRasterizacao().equals("completa")) {
            this.it.printStepCompleta();
        } else if (this.sl.getModoRasterizacao().equals("contorno")) {
            this.it.printStepContorno();
        }
        if(this.sl.getModoJogo().equals("manual")){
            while (isRunning) {
                System.out.println("Introduza a próxima direção da cobra, ex: L (esquerda), R (direita), U (cima), D (baixo), ou Q para sair:");
                String input = sc.next();
                if ("Q".equals(input)) {
                    isRunning = false;
                } else {
                    try {
                        this.sl.proximoPasso(input);
                        if (this.sl.getModoRasterizacao().equals("completa")) {
                            this.it.printStepCompleta();
                        } else if (this.sl.getModoRasterizacao().equals("contorno")) {
                            this.it.printStepContorno();
                        }

                    }catch(IllegalArgumentException e){
                        System.out.println("Input inválido, tente novamente");
                    }
                }
            }
        }
        else{
            System.out.println("ATENÇÃO! A COBRA ZAROLHA COMEÇARÁ O SEU MOVIMENTO");
            while (isRunning) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if ("Q".equals(cz.nextDirection())) {
                    isRunning = false;
                } else {
                    try {
                        this.sl.proximoPasso(cz.nextDirection());
                        if (this.sl.getModoRasterizacao().equals("completa")) {
                            this.it.printStepCompleta();
                        } else if (this.sl.getModoRasterizacao().equals("contorno")) {
                            this.it.printStepContorno();
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Input inválido, tente novamente");
                    }

                }
            }
        }
    }
        public void runGrafica() {
        /*
            this.frame = new GameFrame();
            this.painel = new Painel();

         */
            // Cria a janela principal para a interface gráfica
            JFrame frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400); // Definir o tamanho com base na largura e altura da arena, possivelmente escalado
            frame.setResizable(false);

            // Adiciona a interface gráfica ao frame
            frame.add(this.ig);
            frame.pack();
            frame.setVisible(true);

            // Adiciona o KeyListener para capturar entrada do teclado
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            sl.proximoPasso("L");
                            break;
                        case KeyEvent.VK_RIGHT:
                            sl.proximoPasso("R");
                            break;
                        case KeyEvent.VK_UP:
                            sl.proximoPasso("U");
                            break;
                        case KeyEvent.VK_DOWN:
                            sl.proximoPasso("D");
                            break;
                        case KeyEvent.VK_ESCAPE:
                            System.exit(0);  // Encerra o jogo
                            break;
                    }
                    frame.repaint();  // Solicita a re-renderização da janela após cada movimento
                }
            });

            // Loop do jogo para modo automático
            if (this.sl.getModoJogo().equals("automatico")) {
                boolean isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(300); // Intervalo entre os movimentos da cobra
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    String nextDirection = cz.nextDirection(); // Obtém a próxima direção da cobra zarolha
                    if ("Q".equals(nextDirection)) {
                        isRunning = false;
                    } else {
                        sl.proximoPasso(nextDirection);
                    }
                    frame.repaint();  // Re-renderiza após cada passo automático
                }
            }
        }
}
