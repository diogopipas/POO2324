import java.util.Scanner;

public class Simulador {
    Scanner sc;
    private String[] dimensaoArena, obstaculos;
    private String tipoComida, modoJogo, modoRasterizacao, dimensaoCobra, dimensaoComida;
    private Arena arena;
    public Simulador(){
        sc = new Scanner(System.in);
        readConfigs();
        this.arena = new Arena(Integer.parseInt(this.dimensaoArena[0]), Integer.parseInt(this.dimensaoArena[1]));
    }

    public void readConfigs(){
        System.out.println("Introduza a dimensao da arena, ex: 200 100");
        this.dimensaoArena = sc.next().split(" ", 2);
        System.out.println("Introduza a dimensao da cobra, ex: 30");
        this.dimensaoCobra = sc.next();
        System.out.println("Introduza a dimensao da comida, ex: 3");
        this.dimensaoComida = sc.next();
        System.out.println("Introduza o tipo de comida, ex: quadrado. ex: circulo");
        this.tipoComida = sc.next();
        System.out.println("Introduza os obstaculos no seguinte formato, ex: Quadrado Dinamico 90 1 1 2 1 2 2 1 2,Triangulo Estatico 2 2 4 2 3 4 ");
        this.obstaculos = sc.next().split(",");
        System.out.println("Introduza o modo de jogo, ex: manual. ex: automatico");
        this.modoJogo = sc.next();
        System.out.println("Introduza o modo de rasterização, ex: contorno. ex: completa");
        this.modoRasterizacao = sc.next();
    }

    public void setConfigs(){
        readConfigs();
        this.dimensaoArena =
    }

    public void proximoPasso(){

    }

    public void printStep(){

    }

    public void stopGame(){

    }

    public void startGame(){

    }


}
