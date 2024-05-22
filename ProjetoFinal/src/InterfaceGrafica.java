import java.awt.*;

public class InterfaceGrafica {
    private Simulador sl;
    private Ponto topLeftCorner;
    private double dimensao;
    public InterfaceGrafica(Simulador sl) {
        this.sl = sl;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Ponto p;

        for(int i = 0; i < this.sl.getArena().getAltura(); i++) {
            for(int j = 0; j < this.sl.getArena().getLargura(); j++) {
                p = new Ponto(i, j);

                g2d.setColor(Color.WHITE);
                this.dimensao = 1;

                //Cobra
                for(int k = 0; k < this.sl.getArena().getCobra().getCobra().size(); k++) {
                    if (p.equals(this.sl.getArena().getCobra().getCobra().get(k).findCentroide())) {
                        g2d.setColor(Color.GREEN);
                        break;
                    }
                }

                //Comida
                if (p.equals(this.sl.getArena().getComida().getPosicaoComida())) {
                    if(this.sl.getTipoComida().equals("quadrado")){
                        this.topLeftCorner = this.sl.getArena().getVerticesFromCentroid(this.sl.getArena().getComida().getPosicaoComida()).get(0);
                    }
                    else{
                        this.topLeftCorner = p;
                    }
                    g2d.setColor(Color.RED);
                }

                //Obstaculos
                for(int l = 0; l < this.sl.getArena().getObstaculos().size(); l++){
                    if (p.equals(this.sl.getArena().getObstaculos().get(l).getPosicao())){
                        this.topLeftCorner = this.sl.getArena().getObstaculos().get(l).getPoligono().getP().get(0);
                        g2d.setColor(Color.BLUE);
                        break;
                    }
                }

                //g2d.draw(this.snakeGame[i][j].getRect());
                g2d.fill(new Rectangle((int)p.getX(), (int)p.getY(), (int)this.dimensao, (int)this.dimensao));
            }
        }
        //g2d.drawRect(20, 20, 400, 400);
    }


}