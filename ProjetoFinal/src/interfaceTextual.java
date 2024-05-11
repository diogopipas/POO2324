import java.util.ArrayList;

public class interfaceTextual {
    private Simulador sl;
    private Arena arena;
    private ArrayList<Quadrado> partesCobraCopia; //Esta copia é feita para poder remover a cabeça
    // da cobra sem influenciar a verdadeira lista
    private ArrayList<Quadrado> corpoCobra;
    public interfaceTextual(Simulador sl){
        this.sl = sl;
        this.arena = sl.getArena();
        this.partesCobraCopia = arena.getCobra().getPartesCobra();
        this.partesCobraCopia.remove(0);
        this.corpoCobra = this.partesCobraCopia;
    }

    public void printStep(){
        for(int i = 0; i < this.arena.getAltura(); i++){
            for(int j = 0; j < this.arena.getLargura(); j++){
                if(this.arena.getCobra().getPartesCobra().get(0).containsPonto(new Ponto(j, i))){
                    System.out.println("H");
                }
                else if(this.corpoCobra.get(j).containsPonto(new Ponto(j, i))){
                    System.out.println("T");
                }
                else {

                }

            }

        }
    }
}
