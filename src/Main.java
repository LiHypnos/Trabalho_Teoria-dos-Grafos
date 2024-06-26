import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        String arquivo = "grafo.txt";
        try {
            Grafo grafo = LeituraEscrita.lerGrafo(arquivo);
            System.out.println("vertices: " + grafo.getVertices());
            System.out.println("arestas: " + grafo.getArestas());
            System.out.println("eh ponderado: " + grafo.isPonderado());
            System.out.println("eh direcionado: " + grafo.isDirecionado());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}