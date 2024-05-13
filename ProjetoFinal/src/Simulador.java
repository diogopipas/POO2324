/** Classe responsável por ler as configurações do jogo, inicializar e atualizar a arena
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Simulador {
    Scanner sc;
    private String[] dimensaoArena, obstaculos;
    private String tipoComida, modoJogo, modoRasterizacao, modoInterface, dimensaoCobra, dimensaoComida;
    private Arena arena;


    public Simulador(){
        this.sc = new Scanner(System.in);
        readConfigs();
        this.arena = new Arena(Integer.parseInt(this.dimensaoArena[0]), Integer.parseInt(this.dimensaoArena[1]), Integer.parseInt(this.dimensaoComida), this.tipoComida, Integer.parseInt(this.dimensaoCobra), generateObstaculoPoligonos(), parseObstaculoTypes());
    }

    public void readConfigs(){
        System.out.println("Introduza a dimensao da arena, ex: 30 30");
        this.dimensaoArena = sc.nextLine().split(" ", 2);
        System.out.println("Introduza a dimensao da cobra, ex: 3");
        this.dimensaoCobra = sc.nextLine();
        System.out.println("Introduza a dimensao da comida, ex: 2");
        this.dimensaoComida = sc.nextLine();
        System.out.println("Introduza o tipo de comida, ex: quadrado. ex: circulo");
        this.tipoComida = sc.nextLine();
        System.out.println("Introduza os obstaculos no seguinte formato, ex: Quadrado Estatico 4 4 6 4 6 6 4 6,Triangulo Estatico 8 8 10 8 9 10. ex: Triangulo Dinamico 8 8 10 8 9 10 ");
        this.obstaculos = sc.nextLine().split(",");
        System.out.println("Introduza o modo de jogo, ex: manual. ex: automatico (NOT AVAILABLE)");
        this.modoJogo = sc.nextLine();
        System.out.println("Introduza o modo de rasterização, ex: completa. ex: contorno.");
        this.modoRasterizacao = sc.nextLine();
        System.out.println("Introduza o modo de interface, ex: textual. ex: grafica (NOT AVAILABLE). ");
        this.modoInterface = sc.nextLine();
    }


    public ArrayList<Poligono> generateObstaculoPoligonos(){
        ArrayList<Poligono> poligonos = new ArrayList<>();
        Class<?> obstaculoClass;
        Constructor<?> obstaculoConstrutor;

        for(int i = 0; i < this.obstaculos.length; i++){
            try {
                obstaculoClass = Class.forName(parseObstaculosName().get(i));
                obstaculoConstrutor = obstaculoClass.getConstructor(ArrayList.class);
                Poligono poligono = (Poligono) obstaculoConstrutor.newInstance(parseObstaculosPontos(i));
                poligonos.add(poligono);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return poligonos;
    }

    public ArrayList<String> parseObstaculosName(){
        ArrayList<String> obstaculosNome = new ArrayList<>();
        for(int i = 0; i < this.obstaculos.length; i++){
            obstaculosNome.add(this.obstaculos[i].split(" ", 3)[0]);
        }
        return obstaculosNome;
    }

    public ArrayList<Ponto> parseObstaculosPontos(int index){
        ArrayList<Ponto> obstaculosPonto;
        String pontosStr = this.obstaculos[index].split(" ", 3)[2];
        obstaculosPonto = strToPontos(pontosStr);
        return obstaculosPonto;
    }

    public ArrayList<String> parseObstaculoTypes(){
        ArrayList<String> obstaculoTypes = new ArrayList<>();
        for(int i = 0; i < this.obstaculos.length; i++){
            obstaculoTypes.add(this.obstaculos[i].split(" ", 3)[1]);
        }
        return obstaculoTypes;
    }


    public static ArrayList<Ponto> strToPontos(String s){
        ArrayList<Ponto> pontos = new ArrayList<>();
        String[] aos = s.split(" ", s.length());

        for(int i = 0; i < aos.length; i+=2){
            Ponto p = new Ponto(Integer.parseInt(aos[i]), Integer.parseInt(aos[i + 1]));
            pontos.add(p);
        }
        return pontos;
    }

    public void proximoPasso(String input){
        arena.atualizar(stringToEnum(input));
    }

    public Direcao stringToEnum(String d){
        return switch (d) {
            case "D" -> Direcao.DOWN;
            case "U" -> Direcao.UP;
            case "R" -> Direcao.RIGHT;
            case "L" -> Direcao.LEFT;
            default -> throw new IllegalArgumentException("Direção invalida: " + d);
        };
    }

    public Arena getArena() {
        return arena;
    }

    public String getModoRasterizacao(){
        return this.modoRasterizacao;
    }

}
