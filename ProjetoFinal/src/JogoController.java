import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

class JogoController {
    private final JogoModel model;
    private final JogoView view;
    private final PainelSul spanel;
    private final PainelNorte npanel;
    private Timer gameTimer = null;

    public JogoController(JogoModel model, JogoView view, PainelNorte npanel, PainelSul spanel) {
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
        int numOfRows = this.model.getGridHeight();
        int numOfColumn = this.model.getGridWidth();
        Celula[][] Cells = new Celula[numOfRows][numOfColumn];

        for(int i = 0; i < Cells.length; ++i) {
            double curretYPos = (double)i * CellWidth + (double)starty;

            for(int j = 0; j < Cells[i].length; ++j) {
                double curentXPos = (double)j * CellWidth + (double)startx;
                int currentCellType = this.model.getCellType(i, j);
                Cells[i][j] = new Celula(currentCellType);
                Cells[i][j].setCircle(curentXPos, curretYPos, CellWidth, CellWidth);
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

        if (this.model.getCurrentLive() == 0) {
            this.npanel.updatePanelForGameOverMode();
            JLabel label = new JLabel("Game Over");
            label.setFont(new Font("Arial", 1, 18));
            JOptionPane.showMessageDialog((Component)null, label, (String)null, 2);
        }

    }

    private void initialpanelViewListeners() {
        this.npanel.addPlayButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JogoController.this.model.setPlayingMode(!JogoController.this.model.getPlayingMode());
                JogoController.this.stopTime();
                if (JogoController.this.model.getPlayingMode()) {
                    JogoController.this.model.setArrowKey("right");
                    JogoController.this.startGame();
                    JogoController.this.npanel.updatePanelForPlayMode(true);
                } else {
                    JogoController.this.npanel.updatePanelForPlayMode(false);
                }

            }
        });

        this.npanel.addNewGameButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JLabel label = new JLabel("New Game");
                label.setFont(new Font("Arial", 1, 18));
                JOptionPane.showMessageDialog((Component)null, label, "Are you sure?", 1);
                JogoController.this.model.setReset(true);
                JogoController.this.model.NextStep();
                JogoController.this.spanel.setScoreLabel(JogoController.this.model.getCurrentScore());
                JogoController.this.updateGameViewDisplay();
                JogoController.this.model.setReset(false);
                JogoController.this.model.setPlayingMode(false);
                JogoController.this.model.changeFoodPosition();
                JogoController.this.npanel.updatePanelforGameStartMode();
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
                    if (JogoController.this.model.getCurrentLive() == 0) {
                        JogoController.this.model.removeFood();
                    } else {
                        JogoController.this.performOneStep();
                    }

                    JogoController.this.updateGameViewDisplay();
                }
            }
        })).start();
    }
}
