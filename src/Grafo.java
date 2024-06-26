import java.util.List;
import java.util.Set;

/**
 * Classe que representa um grafo com vértices e arestas.
 */
public class Grafo {
  private Set<String> vertices;
  private List<Aresta> arestas;

  /**
   * Construtor para inicializar o grafo com vértices e arestas.
   *
   * @param vertices Conjunto de vértices do grafo.
   * @param arestas  Lista de arestas do grafo.
   */
  public Grafo(Set<String> vertices, List<Aresta> arestas) {
    this.vertices = vertices;
    this.arestas = arestas;
  }

  /**
   * Verifica se o grafo é ponderado.
   *
   * @return true se pelo menos uma aresta tem peso diferente de 1, false caso
   *         contrário.
   */
  public boolean isPonderado() {
    for (Aresta aresta : arestas) {
      if (aresta.peso != 1) {
        return true;
      }
    }
    return false;
  }

  /**
   * Verifica se o grafo é direcionado.
   *
   * @return true se houver pelo menos uma aresta que não tem a sua inversa
   *         presente, false caso contrário.
   */
  public boolean isDirecionado() {
    for (Aresta aresta : arestas) {
      boolean inversaEncontrada = false;
      for (Aresta outraAresta : arestas) {
        if (aresta.u.equals(outraAresta.v) && aresta.v.equals(outraAresta.u)) {
          inversaEncontrada = true;
          break;
        }
      }
      if (!inversaEncontrada) {
        return true;
      }
    }
    return false;
  }

  public Set<String> getVertices() {
    return vertices;
  }

  public List<Aresta> getArestas() {
    return arestas;
  }
}