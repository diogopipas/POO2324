import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class Celula {
    private TipoCelula cellType;
    private Ellipse2D.Double circle = new Ellipse2D.Double();
    private Rectangle rect = new Rectangle();

    public Celula() {
        this.cellType = TipoCelula.NONE;
    }

    public Celula(int type) {
        this.setCellType(type);
    }

    public void setCellType(int i) {
        switch (i) {
            case 0:
                this.cellType = TipoCelula.NONE;
                break;
            case 1:
                this.cellType = TipoCelula.SNAKE;
                break;
            case 2:
                this.cellType = TipoCelula.FOOD;
                break;
            case 3:
                this.cellType = TipoCelula.OBSTACLE;
        }

    }

    public int getCellType() {
        switch (this.cellType) {
            case SNAKE:
                return 1;
            case FOOD:
                return 2;
            case OBSTACLE:
                return 3;
            default:
                return 0;
        }
    }

    public boolean isSnake() {
        return this.cellType.equals(TipoCelula.SNAKE);
    }

    public boolean isFood() {
        return this.cellType.equals(TipoCelula.FOOD);
    }

    public boolean isNone() {
        return this.cellType.equals(TipoCelula.NONE);
    }
    public boolean isObstacle() {
        return this.cellType.equals(TipoCelula.OBSTACLE);
    }

    public void setCircle(double x, double y, double w, double h) {
        this.circle.setFrame(x, y, w, h);
    }

    public void setRect(double x, double y, double w, double h) {
        this.rect.setFrame(x, y, w, h);
    }

    public Ellipse2D getCircle() {
        return this.circle;
    }

    public Rectangle getRect() {
        return this.rect;
    }


}
