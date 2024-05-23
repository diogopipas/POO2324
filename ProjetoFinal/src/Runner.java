/** Classe reponsável por correr o jogo, ou seja, a leitura dos inputs que vão movimentar a cobra,
 * assim como o chamamento dos metodos que efetuam e imprimem os proximos passos da simulação
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.*;

public class Runner {
    private Scanner sc;
    private Simulador sl;
    private InterfaceTextual it;
    private CobraZarolha cz;
    private InterfaceGrafica ig;

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
        // Cria a janela principal para a interface gráfica
        JFrame frame = new JFrame("OOPS!: The Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame.setLocation(screenWidth/3, screenHeight/3);


        boolean isRunning = true;
        // Adiciona a interface gráfica ao frame
        frame.add(this.ig);
        frame.pack();
        frame.setVisible(true);

        // Create and display the leaderboard frame
        LeaderBoardFrame leaderBoardFrame = new LeaderBoardFrame();
        leaderBoardFrame.setVisible(true);

        // Position the leaderboard frame to the right of the game frame
        leaderBoardFrame.setLocation(screenWidth/3 + frame.getWidth(), screenHeight/4);
    
        // Adiciona o KeyListener para capturar entrada do teclado
        AtomicReference<String> currentDirection = new AtomicReference<>("");  // Guarda a direção atual
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        currentDirection.set("L");
                        break;
                    case KeyEvent.VK_RIGHT:
                        currentDirection.set("R");
                        break;
                    case KeyEvent.VK_UP:
                        currentDirection.set("U");
                        break;
                    case KeyEvent.VK_DOWN:
                        currentDirection.set("D");
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);  // Encerra o jogo
                        break;
                }
            }
        });

        if (this.sl.getModoJogo().equals("automatico")) {
            while (isRunning) {
                try {
                    Thread.sleep(500); // Intervalo entre os movimentos da cobra
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

        else {
            // Loop do jogo que mantém a cobra movendo-se na direção atual até que uma nova seja escolhida
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(200);  // Velocidade de atualização da movimentação da cobra
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    if (!currentDirection.get().isEmpty()) {
                        sl.proximoPasso(currentDirection.get());
                        frame.repaint();  // Re-renderiza após cada passo automático
                    }
                }
            }).start();
        }
    }
}    