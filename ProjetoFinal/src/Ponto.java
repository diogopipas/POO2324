import java.util.Objects;

/** @version 1.2
 * @author André Santos, Diogo Porto
 */
class Ponto {
    private double x, y;

    /** Construtor da classe ponto
     *
     * @param x coordenada
     * @param y coordenada
     */
    public Ponto(double x, double y) {
        setX(x);
        setY(y);
    }


    /** calcula a distância euclidiana entre dois pontos
     * @param p ponto
     * @return Distância euclidiana
     */
    public double dist(Ponto p) {
        double dx = this.getX() - p.getX();
        double dy = this.getY() - p.getY();
        return Math.sqrt(dx*dx+dy*dy);
    }

    /** getter do x
     *
     * @return x
     */
    public double getX() { return x; }

    /** getter do y
     *
     * @return y
     */
    public double getY() { return y; }


    /** setter da coordenada x
     *
     * @param x coordenada x
     */
    public void setX(double x) {
        check(x);
        this.x = x;
    }

    /** setter da coordenada y
     *
     * @param y coordenada y
     */
    public void setY(double y) {
        check(y);
        this.y = y;
    }

    /** verifica se a coordenada obdece à invariante
     *
     * @param coordenada coordenada a ser verificada
     */
    public void check(double coordenada){
        if(coordenada < 0){
            System.out.println("Ponto:vi");
            System.exit(0);
        }
    }

    /** aplica rotação a um ponto
     *
     * @param anchorPoint ponto de referencia para a rotação
     * @param angleDeg angulo da rotação em graus
     * @return ponto com rotação aplicada
     */

    public Ponto rotatePoint(Ponto anchorPoint, double angleDeg)
    {
        double angleRad = ((angleDeg/180)*Math.PI);
        double cosAngle = Math.cos(angleRad );
        double sinAngle = Math.sin(angleRad );
        double dx = (this.getX()-anchorPoint.getX());
        double dy = (this.getY()-anchorPoint.getY());

        return new Ponto(Math.round(anchorPoint.getX() + dx*cosAngle-dy*sinAngle),
                Math.round(anchorPoint.getY() + dx*sinAngle+dy*cosAngle));
    }

    /**
     *
     * @param dx distância de translação x
     * @param dy distância de translação x
     * @return um ponto com a translação aplicada
     */
    public Ponto translatePoint(double dx, double dy){
        return new Ponto(this.getX() + dx, this.getY() + dy);
    }



    /** transforma um ponto em string
     *
     * @return um ponto em formato de string
     */

    public String toString(){
        return "(" + (int) getX() + "," + (int) getY() + ")";
    }

    /**
     *
     * @param o objeto a ser comparado
     * @return true se o objeto for igual, false se não
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;
        return x == ponto.x && y == ponto.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}