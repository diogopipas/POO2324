import java.util.ArrayList;

public class interfaceTextual {
    private Simulador sl;
    private Arena arena;
    public interfaceTextual(Simulador sl){
        this.sl = sl;
        this.arena = sl.getArena();
    }

    public void printStep(){
        for(int i = 0; i < this.arena.getAltura(); i++){
            for(int j = 0; j < this.arena.getLargura(); j++){
                if(this.arena.getCobra().getPosicoesCobra().get(j).equals(new Ponto(j, i))){
                    System.out.println("T");
                }
            }

        }
    }
}
