/** Classe responsável por ler as configurações do jogo, inicializar e atualizar a arena
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.lang.reflect.Constructor;
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
        this.arena = new Arena(Integer.parseInt(this.dimensaoArena[0]), Integer.parseInt(this.dimensaoArena[1]), Integer.parseInt(this.dimensaoComida), this.tipoComida, Integer.parseInt(this.dimensaoCobra));
    }

    public void readConfigs(){
        System.out.println("Introduza a dimensao da arena, ex: 200 100");
        this.dimensaoArena = sc.nextLine().split(" ", 2);
        System.out.println("Introduza a dimensao da cobra, ex: 30");
        this.dimensaoCobra = sc.next();
        System.out.println("Introduza a dimensao da comida, ex: 3");
        this.dimensaoComida = sc.next();
        System.out.println("Introduza o tipo de comida, ex: quadrado. ex: circulo");
        this.tipoComida = sc.next();
        /*System.out.println("Introduza os obstaculos no seguinte formato, ex: Quadrado 1 1 2 1 2 2 1 2,Triangulo 2 2 4 2 3 4 ");
        this.obstaculos = sc.next().split(",");
        System.out.println("Introduza o modo de jogo, ex: manual. ex: automatico (NOT AVAILABLE)");
        this.modoJogo = sc.next();*/
        System.out.println("Introduza o modo de rasterização, ex: contorno (TODOS OS OBJETOS TÊM DE TER DIMENSÃO MAIOR QUE 3). ex: completa");
        this.modoRasterizacao = sc.next();
        //System.out.println("Introduza o modo de interface, ex: grafica (NOT AVAILABLE). ex: textual");
        //this.modoInterface = sc.next();
    }

    /*
    public ArrayList<Poligono> parseObstaculos(){
        Class<?> obstaculoClass;
        Constructor<?> obstaculoConstrutor;
        String[] obstaculosParsed;
        for(int i = 0; i < this.obstaculos.length; i++){
            obstaculoClass = Class.forName(this.obstaculos[0].split(" "), 2);
        }
    }

     */

    public void proximoPasso(String input){
        arena.atualizar(stringToEnum(input));
        //arena.verificarColisoes();
    }

    public Direcao stringToEnum(String d){
        return switch (d) {
            case "D" -> Direcao.DOWN;
            case "U" -> Direcao.UP;
            case "R" -> Direcao.RIGHT;
            case "L" -> Direcao.LEFT;
            default -> null;
        };
    }

    public Arena getArena() {
        return arena;
    }

    public String getModoRasterizacao(){
        return this.modoRasterizacao;
    }

}
