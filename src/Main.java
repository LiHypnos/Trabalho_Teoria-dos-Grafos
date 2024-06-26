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
            System.out.println("eh conexo: " + grafo.isConexo());
            System.out.println("eh bipartido: " + grafo.isBipartido());
            System.out.println("eh euleriano: " + grafo.isEuleriano());
            System.out.println("eh hamiltoniano: " + grafo.isHamiltoniano());
            System.out.println("eh ciclico: " + grafo.isCiclico());
            System.out.println("eh planar: " + grafo.isPlanar());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}