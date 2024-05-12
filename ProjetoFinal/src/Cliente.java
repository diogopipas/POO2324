public class Cliente {
    public static void main(String[] args){
        Simulador sl = new Simulador();
        Runner runner = new Runner(sl);
        runner.run();
    }
}
