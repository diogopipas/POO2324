/**
 * @version 1.2
 * @author André Santos, Diogo Porto
 */
public class SegmentoReta extends Reta{
    private Ponto a, b;

    /** Construtor da classe SegmentoReta
     *
     * @param a ponto incial do segmento de reta
     * @param b ponto final do segmento de reta
     */
    public SegmentoReta(Ponto a, Ponto b){
        super(a, b);
        if(a.dist(b) == 0){
            System.out.println("Segmento:vi");
            System.exit(0);
        } else{
            this.a = a;
            this.b = b;
        }
    }

    /** Verifica se o ponto b se encontra no segmento ac
     *
     * @param p ponto que se pode ou não encontrar no segmento
     * @return true se o ponto b se encontra no segmento, falso se não
     */
    public boolean onSegment(Ponto p)
    {
        return this.a.getX() <= Math.max(p.getX(), this.b.getX()) && p.getX() >= Math.min(this.a.getX(), this.b.getX()) &&
                p.getY() <= Math.max(this.a.getY(), this.b.getY()) && p.getY() >= Math.min(this.a.getY(), this.b.getY());
    }

    /** Verifica a orientação da linha criada por 3 pontos
     *
     * @param r ponto
     * @return 0 se os 3 pontos forem colineares, 1 se a orientação for em sentido do relógio, 2 se for ao contrário
     */
    public int orientation(Ponto r)
    {
        double val = (this.getB().getY() - this.getA().getY()) * (r.getX() - this.getB().getX()) -
                (this.getB().getX() - this.getA().getX()) * (r.getY() - this.getB().getY());

        if (val == 0) return 0;

        return (val > 0)? 1: 2;
    }


    /** Verifica se o segmento de reta o segmento de reta "this" se interceta com sr
     *
     * @param sr segmento de reta a comparar com o reciever
     * @return true se intercetar, false se não
     */
    public boolean intersects(SegmentoReta sr){

        int o1 = orientation(sr.getA());
        int o2 = orientation(sr.getB());
        int o3 = sr.orientation(this.getA());
        int o4 = sr.orientation(this.getB());


        if (o1 == 0 && onSegment(sr.getA())) return false;

        if (o2 == 0 && onSegment(sr.getB())) return false;

        if (o3 == 0 && sr.onSegment(this.getA())) return false;

        if (o4 == 0 && sr.onSegment(this.getB())) return false;

        if (o1 != o2 && o3 != o4){
            return true;
        }


        return false;
    }


    /** getter do ponto a
     *
     * @return a
     */
    public Ponto getA() {
        return a;
    }

    /** getter do ponto b
     *
     * @return b
     */
    public Ponto getB() {
        return b;
    }

}
