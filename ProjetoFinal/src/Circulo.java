/** Classse responsável pela criação do circulo
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

public class Circulo {
    private double raio;
    private Ponto centro;

    public Circulo(double raio, Ponto centro) {
        this.raio = raio;
        this.centro = centro;
    }

    public boolean containsPonto(Ponto ponto) {
        return ponto.dist(this.centro) <= this.raio;
    }

    public Ponto getCentro(){
        return this.centro;
    }
    public double area() {
        return Math.PI * raio * raio;
    }

    public boolean isOnBorder(Ponto ponto, double dimensao) {
        double x = ponto.getX();
        double y = ponto.getY();

        // Calcula os limites do quadrado baseado no centroide
        double halfDim = dimensao / 2;
        double left = this.centro.getX() - halfDim;
        double right = this.centro.getX() + halfDim;
        double top = this.centro.getY() - halfDim;
        double bottom = this.centro.getY() + halfDim;

        // Margem para considerar um ponto como estando na borda
        double margin = 0.5;

        // Verifica se o ponto está próximo das bordas externas
        boolean onVerticalBorder = ((Math.abs(x - left) <= margin || Math.abs(x - right) <= margin) && y >= top - margin && y <= bottom + margin);
        boolean onHorizontalBorder = ((Math.abs(y - top) <= margin || Math.abs(y - bottom) <= margin) && x >= left - margin && x <= right + margin);

        return onVerticalBorder || onHorizontalBorder;
    }
}
