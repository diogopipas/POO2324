import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Comida{
    private Ponto posicaoComida;
    private String tipoComida;
    private double dimensao;
    private Object formaComida;
    public Comida(String tipoComida, Ponto posicaoComida, double dimensao){
        this.posicaoComida = posicaoComida;
        this.dimensao = dimensao;
        if(tipoComida.equals("quadrado")){
            this.formaComida = new Quadrado(getQuadradoFromCentroid(posicaoComida));
        }
        else if(tipoComida.equals("circulo")){
            this.formaComida = new Circulo(dimensao, posicaoComida);
        }
    }

    public ArrayList<Ponto> getQuadradoFromCentroid(Ponto centroide){
        ArrayList<Ponto> pontos = new ArrayList<>();
        Ponto p1 = new Ponto(centroide.getX()-(this.dimensao/2), centroide.getY()+this.dimensao/2); // supperior esqueerdo
        Ponto p2 = new Ponto(centroide.getX()+(this.dimensao/2), centroide.getY()+this.dimensao/2); // supperior direito
        Ponto p3 = new Ponto(centroide.getX()+(this.dimensao/2), centroide.getY()-this.dimensao/2); // inferior direito
        Ponto p4 = new Ponto(centroide.getX()-(this.dimensao/2), centroide.getY()-this.dimensao/2); // inferior esqueerdo~
        pontos.add(p1);
        pontos.add(p2);
        pontos.add(p3);
        pontos.add(p4);
        return pontos;
    }
}