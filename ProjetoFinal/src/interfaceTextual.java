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
                if(this.arena.getCobra().getPartesCobra().get(0).containsPonto(new Ponto(j, i))){
                    System.out.println("H");
                }
                else if(this.arena.getCobra().getPartesCobra().get((j + 1) % this.arena.getLargura()).containsPonto(new Ponto(j, i))){
                    System.out.println("T");
                }
                else {

                }

            }

        }
    }
}
