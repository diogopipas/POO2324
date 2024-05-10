import java.util.ArrayList;

public abstract class Comida{
    public static Object criarComida(String tipo, Object... args) {
        switch (tipo) {
            case "quadrado":
                if (args[0] instanceof ArrayList) {
                    return new Quadrado((ArrayList<Ponto>) args[0]);
                } else {
                    throw new IllegalArgumentException("Argumentos inválidos para Quadrado");
                }
            case "circulo":
                if (args[0] instanceof Double && args[1] instanceof Ponto) {
                    return new Circulo((Double) args[0], (Ponto) args[1]);
                } else {
                    throw new IllegalArgumentException("Argumentos inválidos para Circulo");
                }
            default:
                throw new IllegalArgumentException("Tipo de comida inválido");
        }
    }
}