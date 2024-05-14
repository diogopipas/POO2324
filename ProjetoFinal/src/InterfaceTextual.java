/**
 * Classe responsável pelo tratamento das operações da interface textual, incluindo a impressão em contorno e a impressão completa
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */

import java.util.ArrayList;

public class InterfaceTextual {
    private Arena arena;
    private ArrayList<Quadrado> partesCobraCopia; 
    private ArrayList<Quadrado> corpoCobra;

    public InterfaceTextual(Simulador sl) {
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
                    System.out.print("H ");
                    printed = true;
                } else {
                    for (Quadrado corpo : this.corpoCobra) {
                        if (corpo.containsPonto(ponto)) {
                            System.out.print("T ");
                            printed = true;
                            break;
                        }
                    }
                    if (arena.getComida().getFormaComida() instanceof Circulo) {
                        Circulo circulo = new Circulo(arena.getComida().getDimensao(), arena.getComida().getPosicaoComida());
                        if (circulo.containsPonto(ponto)) {
                            System.out.print("F ");
                            printed = true;
                        }
                    } else {
                        Quadrado quadrado = new Quadrado(arena.getComida().getVerticesFromCentroid(arena.getComida().getPosicaoComida()));
                        if (quadrado.containsPonto(ponto)) {
                            System.out.print("F ");
                            printed = true;
                        }
                    }


                    for (Obstaculo obstaculo : arena.getObstaculos()) {
                        if (obstaculo.getPoligono().containsPonto(ponto)) {
                            System.out.print("O ");
                            printed = true;
                            break;
                        }
                    }

                }
                if (!printed) { 
                    System.out.print(". ");
                }
            }
            System.out.println(); 
        }
        System.out.print("Dir H: " + this.arena.getCobra().getAngulo());
        for(int i = 0; i < this.arena.getLargura(); i+=7){
            System.out.print("\t");
        }
        System.out.println("Pontos:" + this.arena.getPontuacao());
    }

    public void printStepContorno() {
        for (int i = 1; i < this.arena.getAltura(); i++) {
            for (int j = 1; j < this.arena.getLargura(); j++) {
                Ponto ponto = new Ponto(j, i);
                boolean printed = false;
                if (arena.getComida().getFormaComida() instanceof Circulo) {
                    Circulo circulo = new Circulo(arena.getComida().getDimensao(), arena.getComida().getPosicaoComida());
                    if (circulo.isOnBorder(ponto, arena.getComida().getDimensao())) {
                        System.out.print("F ");
                        printed = true;
                    }
                } else {
                    Quadrado quadrado = new Quadrado(arena.getComida().getVerticesFromCentroid(arena.getComida().getPosicaoComida()));
                    if (quadrado.isOnBorder(ponto, arena.getComida().getDimensao())) {
                        System.out.print("F ");
                        printed = true;
                    }
                }
                if (!printed && arena.getCobra().getCobra().get(0).isOnBorder(ponto, arena.getCobra().getDimensao())) {
                    System.out.print("H ");
                    printed = true;
                }
                if (!printed) {
                    for (Quadrado corpo : this.arena.getCobra().getCobra().subList(1, this.arena.getCobra().getCobra().size())) {
                        if (corpo.isOnBorder(ponto, arena.getCobra().getDimensao())) {
                            System.out.print("T ");
                            printed = true;
                            break;
                        }
                    }
                }
                for (Obstaculo obstaculo : arena.getObstaculos()) {
                    if (obstaculo.getPoligono().isOnBorder(ponto, obstaculo.getPoligono().getTamanhoSegmento())) {
                        System.out.print("O ");
                        printed = true;
                        break;
                    }
                }
                if (!printed) {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.print("Dir H: " + this.arena.getCobra().getAngulo());
        for(int i = 0; i < this.arena.getLargura(); i+=7){
            System.out.print("\t");
        }
        System.out.println("Pontos:" + this.arena.getPontuacao());
    }
}

