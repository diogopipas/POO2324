import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

class Controller {
    /*
    private final JogoModel model;
    private final JogoView view;
    private final PainelSul spanel;
    private final PainelNorte npanel;
    private Timer gameTimer = null;

    public Controller(JogoModel model, JogoView view, PainelNorte npanel, PainelSul spanel) {
        this.model = model;
        this.view = view;
        this.npanel = npanel;
        this.spanel = spanel;
        this.updateGameViewDisplay();
        this.initialpanelViewListeners();
    }

    private void updateGameViewDisplay() {
        int startx = 20;
        int starty = 20;
        double CellWidth = 20.0;
        int numOfRows = this.model.getArenaHeight();
        int numOfColumn = this.model.getArenaWidth();
        Celula[][] Cells = new Celula[numOfRows][numOfColumn];
        for(int i = 0; i < Cells.length; i++) {
            double curretYPos = (double)i * CellWidth + (double)starty;

            for(int j = 0; j < Cells[i].length; j++) {
                double curentXPos = (double)j * CellWidth + (double)startx;
                int currentCellType = this.model.getCellType(i, j);
                Cells[i][j] = new Celula(currentCellType);
                Cells[i][j].setRect(curentXPos, curretYPos, CellWidth, CellWidth);
            }
        }

        this.view.setCells(Cells);
        this.view.repaint();
    }

    private void performOneStep() {
        this.model.NextStep();
        this.spanel.setScoreLabel(this.model.getCurrentScore());
        if (!this.model.getPlayingMode()) {
            this.stopTime();
            this.npanel.updatePanelForPlayMode(false);
        }
    }

    private void initialpanelViewListeners() {
        this.npanel.addPlayButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JogoController.this.model.setPlayingMode(!JogoController.this.model.getPlayingMode());
                JogoController.this.stopTime();
                if (JogoController.this.model.getPlayingMode()) {
                    JogoController.this.model.setArrowKey(JogoController.this.model.randomizeArrow());
                    JogoController.this.startGame();
                    JogoController.this.npanel.updatePanelForPlayMode(true);
                } else {
                    JogoController.this.npanel.updatePanelForPlayMode(false);
                }

            }
        });

        this.view.addKeyArrowListener(38, "up", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                JogoController.this.model.setArrowKey("up");
            }
        });
        this.view.addKeyArrowListener(40, "down", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                JogoController.this.model.setArrowKey("down");
            }
        });
        this.view.addKeyArrowListener(39, "right", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                JogoController.this.model.setArrowKey("right");
            }
        });
        this.view.addKeyArrowListener(37, "left", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                JogoController.this.model.setArrowKey("left");
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
                JogoController.this.startOneStepThread();
            }
        });
        this.gameTimer.start();
    }

    private void startOneStepThread() {
        (new Thread(new Runnable() {
            public void run() {
                synchronized(JogoController.this.model) {
                    JogoController.this.performOneStep();
                    JogoController.this.updateGameViewDisplay();
                }
            }
        })).start();
    }

     */
}