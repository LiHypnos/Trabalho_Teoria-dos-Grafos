import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String arquivo = "grafo.txt";
        try {
            Grafo grafo = LeituraEscrita.lerGrafo(arquivo);
            grafo.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}