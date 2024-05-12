/** Classe responsável por instanciar as classes Simulador e Runner, e correr o método run() para começar o jogo
 *
 * @author André Santos, Diogo Porto
 * @version 1.0
 */
public class Cliente {
    public static void main(String[] args){
        Simulador sl = new Simulador();
        Runner runner = new Runner(sl);
        runner.run();
    }
}
