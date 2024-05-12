import java.util.ArrayList;

public class InterfaceTextual {
    private Simulador sl;
    private Arena arena;
    private ArrayList<Quadrado> partesCobraCopia; //Esta copia é feita para poder remover a cabeça
    // da cobra sem influenciar a verdadeira lista
    private ArrayList<Quadrado> corpoCobra;
    public InterfaceTextual(Simulador sl){
        this.sl = sl;
        this.arena = sl.getArena();
        this.partesCobraCopia = arena.getCobra().getPartesCobra();
        this.partesCobraCopia.remove(0);
        this.corpoCobra = this.partesCobraCopia;
    }

    public void printStep() {
        for (int i = 1; i < this.arena.getAltura(); i++) {
            for (int j = 1; j < this.arena.getLargura(); j++) {
                Ponto ponto = new Ponto(j, i);
                boolean printed = false;
                if (arena.getCobra().getPartesCobra().get(0).containsPonto(ponto)) {
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
                    /*
                    // Check if the current point contains an object
                    for (Poligono obstaculo : arena.getObstaculos()) {
                        if (obstaculo.containsPonto(ponto)) {
                            System.out.print("O");
                            printed = true;
                            break;
                        }
                    }
                    // Check if the current point contains food
                    if (arena.getComida().getFormaComida().containsPonto(ponto)) {
                        System.out.print("F");
                        printed = true;
                        break;
                    }

                     */
                }
                if (!printed) {
                    System.out.print(".");
                }
            }
            System.out.println(); // Move to the next line after printing each row
            System.out.println("");
        }
    }

}
