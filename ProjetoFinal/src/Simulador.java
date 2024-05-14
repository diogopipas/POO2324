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
        /*
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
         */
        boolean validInput = false;
        while(!validInput){
            try {
                System.out.println("Introduza a dimensao da arena, ex: 30 30");
                String[] dimensaoArenaInput = sc.nextLine().split(" ", 2);
                if (dimensaoArenaInput.length != 2 || !dimensaoArenaInput[0].matches("\\d+") || !dimensaoArenaInput[1].matches("\\d+")) {
                    throw new IllegalArgumentException("Dimensão da arena inválida. Deve ser no formato 'número número'.");
                }
                this.dimensaoArena = dimensaoArenaInput;

                System.out.println("Introduza a dimensao da cobra, ex: 3");
                String dimensaoCobraInput = sc.nextLine();
                if (!dimensaoCobraInput.matches("\\d+")) {
                    throw new IllegalArgumentException("Dimensão da cobra inválida. Deve ser um número.");
                }
                this.dimensaoCobra = dimensaoCobraInput;

                System.out.println("Introduza a dimensao da comida, ex: 2");
                String dimensaoComidaInput = sc.nextLine();
                if (!dimensaoComidaInput.matches("\\d+")) {
                    throw new IllegalArgumentException("Dimensão da comida inválida. Deve ser um número.");
                }
                this.dimensaoComida = dimensaoComidaInput;

                System.out.println("Introduza o tipo de comida, ex: quadrado. ex: circulo");
                String tipoComidaInput = sc.nextLine();
                if (!tipoComidaInput.equals("quadrado") && !tipoComidaInput.equals("circulo")) {
                    throw new IllegalArgumentException("Tipo de comida inválido. Deve ser 'quadrado' ou 'circulo'.");
                }
                this.tipoComida = tipoComidaInput;

                System.out.println("Introduza os obstaculos no seguinte formato, ex: Quadrado Estatico 4 4 6 4 6 6 4 6,Triangulo Estatico 8 8 10 8 9 10. ex: Triangulo Dinamico 8 8 10 8 9 10 ");
                this.obstaculos = sc.nextLine().split(",");
                for (String obstaculo : this.obstaculos) {
                    String[] obstaculoParts = obstaculo.split(" ", 2);
                    if (obstaculoParts.length != 2 || (!obstaculoParts[0].equals("Quadrado") && !obstaculoParts[0].equals("Triangulo") && !obstaculoParts[0].equals("Retangulo") && !obstaculoParts[0].equals("Poligono")) || !obstaculoParts[1].matches("(Estatico|Dinamico) (\\d+ \\d+ \\d+ \\d+ \\d+ \\d+|\\d+ \\d+ \\d+ \\d+ \\d+ \\d+ \\d+ \\d+)")) {
                        throw new IllegalArgumentException("Formato de obstáculo inválido. Deve ser 'Tipo Estado Pontos'.");
                    }
                }

                System.out.println("Introduza o modo de jogo, ex: manual. ex: automatico (NOT AVAILABLE)");
                String modoJogoInput = sc.nextLine();
                if (!modoJogoInput.equals("manual")) {
                    throw new IllegalArgumentException("Modo de jogo inválido. Apenas 'manual' é suportado atualmente.");
                }
                this.modoJogo = modoJogoInput;

                System.out.println("Introduza o modo de rasterização, ex: completa. ex: contorno.");
                String modoRasterizacaoInput = sc.nextLine();
                if (!modoRasterizacaoInput.equals("completa") && !modoRasterizacaoInput.equals("contorno")) {
                    throw new IllegalArgumentException("Modo de rasterização inválido. Deve ser 'completa' ou 'contorno'.");
                }
                this.modoRasterizacao = modoRasterizacaoInput;

                System.out.println("Introduza o modo de interface, ex: textual. ex: grafica (NOT AVAILABLE). ");
                String modoInterfaceInput = sc.nextLine();
                if (!modoInterfaceInput.equals("textual")) {
                    throw new IllegalArgumentException("Modo de interface inválido. Apenas 'textual' é suportado atualmente.");
                }
                this.modoInterface = modoInterfaceInput;

                validInput = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Por favor, tente novamente.");
            }
        }


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
