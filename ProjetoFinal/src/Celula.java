import java.awt.geom.Ellipse2D;

class Celula {
    private TipoCelula cellType;
    private Ellipse2D.Double circle = new Ellipse2D.Double();

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
        }

    }

    public int getCellType() {
        switch (this.cellType) {
            case SNAKE:
                return 1;
            case FOOD:
                return 2;
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

    public void setCircle(double x, double y, double w, double h) {
        this.circle.setFrame(x, y, w, h);
    }

    public Ellipse2D getCircle() {
        return this.circle;
    }
}
