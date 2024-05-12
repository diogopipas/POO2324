/** Classse responsável pela criação do circulo
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.util.ArrayList;

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
}
