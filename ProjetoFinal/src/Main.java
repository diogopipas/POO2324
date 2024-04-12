import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String capital(String s) {
        if (s == null || s.isEmpty()){
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    public static ArrayList<Ponto> strToPontos(String s){
        ArrayList<Ponto> pontos = new ArrayList<>();
        String[] aos = s.split(" ", s.length());

        for(int i = 0; i < aos.length; i+=2){
            Ponto p = new Ponto(Integer.parseInt(aos[i]), Integer.parseInt(aos[i + 1]));
            pontos.add(p);
        }
        return pontos;
    }



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Constructor<?> constructor;
        Class<?> cl;
        Poligono poligono;
        Poligono translatedPoligono;
        String s1;
        String s2;
        String[] aos1;
        String[] aos2;
        String[] aos3;
        ArrayList<Ponto> translatedPontos;
        ArrayList<Poligono> translatedPoligonos = new ArrayList<>();
        ArrayList<Poligono> poligonos = new ArrayList<>();


        s1 = sc.nextLine();
        s2 = sc.nextLine();
        aos1 = s1.split(" ", 3);


        try {
            cl = Class.forName(capital(aos1[0]));
            constructor = cl.getConstructor(ArrayList.class);
            if(aos1[0].equals("Poligono")){
                poligono = (Poligono) constructor.newInstance(strToPontos(aos1[2]));
                poligonos.add(poligono);
            }
            else{
                aos2 = s1.split(" ", 2);
                poligono = (Poligono) constructor.newInstance(strToPontos(aos2[1]));
                poligonos.add(poligono);
            }

            aos3 = s2.split(" ", 2);
            translatedPontos = poligonos.get(0).translatePolygon(new Ponto(Integer.parseInt(aos3[0]), Integer.parseInt(aos3[1])));

            translatedPoligono = (Poligono) constructor.newInstance(translatedPontos);
            translatedPoligonos.add(translatedPoligono);

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(translatedPoligonos.get(0));

        sc.close();

    }
}