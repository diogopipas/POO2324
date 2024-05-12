import java.util.Scanner;

public class Runner {
    private Scanner sc;
    private Simulador sl;
    private InterfaceTextual it;
    public Runner(Simulador sl){
        this.sl = sl;
        this.sc = new Scanner(System.in);
        this.it = new InterfaceTextual(sl);
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Introduza a próxima direção da cobra, ex: L (esquerda), R (direita), U (cima), D (baixo), ou Q para sair:");
            String input = sc.next();
            if ("Q".equals(input)) {
                isRunning = false;
            } else {
                this.sl.proximoPasso(input);
                if(this.sl.getModoRasterizacao().equals("completa")){
                    this.it.printStepCompleta();
                }
                else {
                    this.it.printStepCompleta();
                }

            }
        }
    }




}
