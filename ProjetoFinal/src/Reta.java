/** @version 1.2
 * @author André Santos, Diogo Porto
 */

public class Reta  {

    private Ponto a, b;


    /** Construtor da reta
     *
     * @param a ponto incial da reta
     * @param b ponto final da reta
     *
     */
    public Reta(Ponto a, Ponto b) {
        if (a.dist(b) == 0) {
            System.exit(0);
        } else {
            this.a = a;
            this.b = b;
        }
    }

    /**
     *
     * @param c ponto a ser comparado
     * @return o cross product da reta
     */
    public double crossProduct(Ponto c) {
        double ABx = this.b.getX() - this.a.getX();
        double ABy = this.b.getY() - this.a.getY();
        double ACx = c.getX() - this.a.getX();
        double ACy = c.getY() - this.a.getY();

        return ABx * ACy - ABy * ACx;
    }


    /** verifica se os dois pontos da reta são colineares com o ponto c
     *
     * @param c ponto a ser comparado
     * @return true se forem colineares, false se não
     */
    public boolean isCollinear(Ponto c){
        return crossProduct(c) == 0;
    }

    /** Getter para o ponto a
     *
     * @return a
     */
    public Ponto getA() {
        return a;
    }

    /** Getter para o ponto b
     *
     * @return b
     */
    public Ponto getB() {
        return b;
    }

}

