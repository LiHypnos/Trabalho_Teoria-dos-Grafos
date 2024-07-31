import java.util.HashMap;
import java.util.ArrayList;

/**
 * Classe que representa um grafo com vértices e arestas.
 */
public class Grafo {
    public HashMap<String, Vertice> vertices;
    public ArrayList<Aresta> arestas;

    private final HashMap<Vertice, ArrayList<Vertice>> listaAdjacencia;
    public final boolean naoDirecionado;

    public Grafo(HashMap<String, Vertice> vertices, ArrayList<Aresta> arestas, boolean naoDirecionado) {
        this.vertices = vertices;
        this.arestas = arestas;
        this.listaAdjacencia = new HashMap<>();
        this.naoDirecionado = naoDirecionado;
    }

    public Grafo(boolean naoDirecionado) {
        this.vertices = new HashMap<>();
        this.arestas = new ArrayList<>();
        this.listaAdjacencia = new HashMap<>();
        this.naoDirecionado = naoDirecionado;
    }

    public void adicionarAresta(Aresta aresta) {
        if (this.vertices.get(aresta.getVerticeOrigem().id) == null) {
            this.vertices.put(aresta.getVerticeOrigem().id, aresta.getVerticeOrigem());
        }
        if (this.vertices.get(aresta.getVerticeDestino().id) == null) {
            this.vertices.put(aresta.getVerticeDestino().id, aresta.getVerticeDestino());
        }
        this.arestas.add(aresta);
        this.listaAdjacencia.computeIfAbsent(aresta.getVerticeOrigem(), k -> new ArrayList<>())
                .add(aresta.getVerticeDestino());
    }

    public void adicionarVertice(Vertice vertice) {
        this.vertices.put(vertice.getId(), vertice);
    }

    public ArrayList<Vertice> getVizinhos(Vertice noh) {
        return this.listaAdjacencia.getOrDefault(noh, new ArrayList<>());
    }
}
