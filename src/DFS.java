import java.util.HashSet;
import java.util.Set;

public class DFS {
  private final Grafo grafo;
  private final Set<Vertice> visitados;

  public DFS(Grafo grafo) {
    this.grafo = grafo;
    this.visitados = new HashSet<>();
  }

  public void executar(Vertice nohInicial) {
    if (!visitados.contains(nohInicial)) {
      dfs(nohInicial);
    }
  }

  /**
   * Realiza uma busca em profundidade (DFS) para a ordenação topológica.
   *
   * @param vertice
   *          O vértice atual.
   * @param visitados
   *          Conjunto de vértices visitados.
   * @param pilha
   *          Pilha para armazenar a ordem topológica.
   */
  private void dfs(Vertice noh) {
    visitados.add(noh);
    processarNoh(noh);
  }

  /**
   * Função placeholder para futuros algoritmos para processar o nó
   *
   * @param noh
   *          Vértice a ser processado
   */
  protected void processarNoh(Vertice noh) {
    // System.out.println("No visitado: " + noh.toString());
  }
}
