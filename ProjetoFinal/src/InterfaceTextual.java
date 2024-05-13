/**
 * Classe responsável pelo tratamento das operações da interface textual, incluindo a impressão em contorno e a impressão completa
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.util.ArrayList;

public class InterfaceTextual {
    private Simulador sl;
    private Arena arena;
    private ArrayList<Quadrado> partesCobraCopia; //Esta copia é feita para poder remover a cabeça
    // da cobra sem influenciar a verdadeira lista
    private ArrayList<Quadrado> corpoCobra;

    public InterfaceTextual(Simulador sl) {
        this.sl = sl;
        this.arena = sl.getArena();
        this.partesCobraCopia = arena.getCobra().getCobra();
        this.corpoCobra = this.partesCobraCopia;
    }

    public void printStepCompleta() {
        for (int i = 1; i < this.arena.getAltura(); i++) {
            for (int j = 1; j < this.arena.getLargura(); j++) {
                Ponto ponto = new Ponto(j, i);
                boolean printed = false;
                if (arena.getCobra().getCobra().get(0).containsPonto(ponto)) {
                    System.out.print("H");
                    printed = true;
                } else {
                    for (Quadrado corpo : this.corpoCobra) {
                        if (corpo.containsPonto(ponto)) {
                            System.out.print("T");
                            printed = true;
                            break;
                        }
                    }

                    if (arena.getComida().getFormaComida() instanceof Circulo) {
                        Circulo circulo = new Circulo(arena.getComida().getDimensao(), arena.getComida().getPosicaoComida());
                        if (circulo.containsPonto(ponto)) {
                            System.out.print("F");
                            printed = true;
                        }
                    } else {
                        Quadrado quadrado = new Quadrado(arena.getComida().getVerticesFromCentroid(arena.getComida().getPosicaoComida()));
                        if (quadrado.containsPonto(ponto)) {
                            System.out.print("F");
                            printed = true;
                        }
                    }

                    for (Obstaculo obstaculo : arena.getObstaculos()) {
                        if (obstaculo.getPoligono().containsPonto(ponto)) {
                            System.out.print("O");
                            printed = true;
                            break;
                        }
                    }
                }
                if (!printed) {
                    System.out.print(".");
                }
            }
            System.out.println(); // Move to the next line after printing each row
        }
        System.out.print("Dir H: " + this.arena.getCobra().getAngulo());
        for(int i = 0; i < this.arena.getLargura(); i+=7){
            System.out.print("\t");
        }
        System.out.println(this.arena.getPontuacao());
    }

    public void printStepContorno() {
            for (int i = 1; i < this.arena.getAltura(); i++) {
                for (int j = 1; j < this.arena.getLargura(); j++) {
                    Ponto ponto = new Ponto(j, i);
                    boolean printed = false;
                    if (arena.getCobra().getCobra().get(0).pointOnSegment(ponto)) {
                        System.out.print("H");
                        printed = true;
                    } else {
                        for (Quadrado corpo : this.corpoCobra) {
                            if (corpo.pointOnSegment(ponto)) {
                                System.out.print("T");
                                printed = true;
                                break;
                            }
                        }

                        if (arena.getComida().getFormaComida() instanceof Circulo) {
                            Circulo circulo = new Circulo(arena.getComida().getDimensao(), arena.getComida().getPosicaoComida());
                            if (circulo.containsPonto(ponto) && !ponto.equals(circulo.getCentro())) {
                                System.out.print("F");
                                printed = true;
                            }
                        } else {
                            Quadrado quadrado = new Quadrado(arena.getComida().getVerticesFromCentroid(arena.getComida().getPosicaoComida()));
                            if (quadrado.pointOnSegment(ponto)) {
                                System.out.print("F");
                                printed = true;
                            }
                        }
                    /*
                    // Check if the current point contains an object
                    for (Poligono obstaculo : arena.getObstaculos()) {
                        if (obstaculo.containsPonto(ponto)) {
                            System.out.print("O");
                            printed = true;
                            break;
                        }
                    }
                   */
                        // Check if the current point contains food
                    }
                    if (!printed) {
                        System.out.print(".");
                    }
                }
                System.out.println(); // Move to the next line after printing each row
            }
        }

}

