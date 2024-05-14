/** Classe responsável pela criação da comida
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.util.ArrayList;


public class Comida{
    private Ponto posicaoComida;
    private String tipoComida;
    private double dimensao;
    private Object formaComida;


    public Comida(String tipoComida, Ponto posicaoComida, double dimensao){
        this.posicaoComida = posicaoComida;
        this.dimensao = dimensao;
        this.tipoComida = tipoComida;
        if(tipoComida.equals("quadrado")){
            this.formaComida = new Quadrado(getVerticesFromCentroid(posicaoComida));
        }
        else if(tipoComida.equals("circulo")){
            this.formaComida = new Circulo(dimensao, posicaoComida);
        }
    }

    public ArrayList<Ponto> getVerticesFromCentroid(Ponto centroide){
        ArrayList<Ponto> pontos = new ArrayList<>();
        Ponto p1 = new Ponto(centroide.getX()-(this.dimensao/2), centroide.getY()+this.dimensao/2); // superior esquerdo
        Ponto p2 = new Ponto(centroide.getX()+(this.dimensao/2), centroide.getY()+this.dimensao/2); // superior direito
        Ponto p3 = new Ponto(centroide.getX()+(this.dimensao/2), centroide.getY()-this.dimensao/2); // inferior direito
        Ponto p4 = new Ponto(centroide.getX()-(this.dimensao/2), centroide.getY()-this.dimensao/2); // inferior esquerdo
        pontos.add(p1);
        pontos.add(p2);
        pontos.add(p3);
        pontos.add(p4);
        return pontos;
    }

    public Ponto getPosicaoComida() {
        return posicaoComida;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public double getDimensao() {
        return dimensao;
    }

    public Object getFormaComida() {
        return formaComida;
    }

}