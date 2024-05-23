import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Timer;

class Controlador {

    /*
    private Simulador sl;
    private final InterfaceGrafica view;
    private Painel painel;
    private Timer gameTimer = null;

    public Controlador(Simulador sl, InterfaceGrafica view, Painel painel) {
        this.sl = sl;
        this.view = view;
        this.painel = painel;
        this.updateGameViewDisplay();
        this.initialpanelViewListeners();
    }


    private void updateGameViewDisplay() {
        this.view.repaint();
    }

    private void performOneStep() {
        this.sl.getArena().atualizar();
        this.painel.setScoreLabel(this.sl.getArena().getPontuacao());
    }

    private void initialpanelViewListeners() {
        this.painel.addPlayButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                    Controlador.this.model.setArrowKey(Controlador.this.model.randomizeArrow());
                    Controlador.this.startGame();
                    Controlador.this.painel.updatePanelForPlayMode(true);
            }
        });

        this.view.addKeyArrowListener(38, "up", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                Controlador.this.model.setArrowKey("up");
            }
        });
        this.view.addKeyArrowListener(40, "down", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                Controlador.this.model.setArrowKey("down");
            }
        });
        this.view.addKeyArrowListener(39, "right", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                Controlador.this.model.setArrowKey("right");
            }
        });
        this.view.addKeyArrowListener(37, "left", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                Controlador.this.model.setArrowKey("left");
            }
        });
    }

    private void stopTime() {
        if (this.gameTimer != null) {
            this.gameTimer.stop();
        }

    }

    private void startGame() {
        this.gameTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                startOneStepThread();
            }
        });
        this.gameTimer.start();
    }

    private void startOneStepThread() {
        (new Thread(new Runnable() {
            public void run() {
                synchronized(Controlador.this.sl) {
                    Controlador.this.performOneStep();
                    Controlador.this.updateGameViewDisplay();
                }
            }
        })).start();
    }

     */

}