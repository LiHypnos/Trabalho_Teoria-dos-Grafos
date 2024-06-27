import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Classe que representa um grafo com vértices e arestas.
 */
public class Grafo {
  private HashMap<String, Vertice> vertices;
  private List<Aresta> arestas;

  /**
   * Construtor para inicializar o grafo com vértices e arestas.
   *
   * @param vertices Conjunto de vértices do grafo.
   * @param arestas  Lista de arestas do grafo.
   */
  public Grafo(HashMap<String, Vertice> vertices, List<Aresta> arestas) {
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
  }    /**
     * Verifica se o grafo é conexo.
     *
     * @return true se o grafo for conexo, false caso contrário.
     */
  public boolean isConexo() {
      if (vertices.isEmpty()) {
          return true; // Um grafo vazio é considerado conexo
      }

      // Realiza DFS para verificar conectividade
      Set<Vertice> visitados = new HashSet<>();
      Stack<Vertice> stack = new Stack<>();
      stack.push(vertices.values().iterator().next());

      while (!stack.isEmpty()) {
          Vertice vertice = stack.pop();
          if (!visitados.contains(vertice)) {
              visitados.add(vertice);
              for (Vertice adjacente : vertice.adjacencia) {
                  if (!visitados.contains(adjacente)) {
                      stack.push(adjacente);
                  }
              }
          }
      }

      return visitados.size() == vertices.size();
  }

    /**
   * Verifica se o grafo é bipartido.
   *
   * @return true se o grafo for bipartido, false caso contrário.
   */
  public boolean isBipartido() {
      if (vertices.isEmpty()) {
          return true; // Um grafo vazio é considerado bipartido
      }

      // Mapa para armazenar cores dos vértices
      Map<Vertice, Integer> cores = new HashMap<>();
      for (Vertice vertice : vertices.values()) {
          cores.put(vertice, -1); // -1 representa que o vértice não foi colorido
      }

      // Realiza BFS para verificar bipartição
      LinkedList<Vertice> queue = new LinkedList<>();
      for (Vertice vertice : vertices.values()) {
          if (cores.get(vertice) == -1) { // Se o vértice não foi visitado
              queue.add(vertice);
              cores.put(vertice, 0); // Inicia colorindo o vértice com a cor 0

              while (!queue.isEmpty()) {
                  Vertice u = queue.poll();
                  int corU = cores.get(u);

                  for (Vertice v : u.adjacencia) {
                      if (cores.get(v) == -1) {
                          // Colorir com a cor oposta
                          cores.put(v, 1 - corU);
                          queue.add(v);
                      } else if (cores.get(v).equals(corU)) {
                          // Se a cor é a mesma, então o grafo não é bipartido
                          return false;
                      }
                  }
              }
          }
      }

    return true;
  }
  /**
   * Verifica se o grafo é Euleriano.
   *
   * @return true se o grafo for Euleriano, false caso contrário.
   */
  public boolean isEuleriano() {
    if (!isConexo()) {
        return false;
    }

    Map<Vertice, Integer> grau = new HashMap<>();
    for (Vertice vertice : vertices.values()) {
        grau.put(vertice, 0);
    }

    for (Aresta aresta : arestas) {
        grau.put(aresta.u, grau.get(aresta.u) + 1);
        grau.put(aresta.v, grau.get(aresta.v) + 1);
    }

    for (int g : grau.values()) {
        if (g % 2 != 0) {
            return false;
        }
    }

    return true;
  }

    /**
     * Verifica se o grafo é Hamiltoniano.
     *
     * @return true se o grafo for Hamiltoniano, false caso contrário.
     */
    public boolean isHamiltoniano() {
        List<Vertice> caminho = new ArrayList<>();
        Set<Vertice> visitados = new HashSet<>();
        Vertice verticeInicial = vertices.values().iterator().next(); // Escolhe um vértice inicial
        caminho.add(verticeInicial);
        visitados.add(verticeInicial);
        return buscaHamiltoniana(verticeInicial, caminho, visitados);
    }

    /**
     * Método recursivo para busca de ciclo Hamiltoniano.
     *
     * @param verticeAtual O vértice atual no caminho.
     * @param caminho      O caminho percorrido até agora.
     * @param visitados    Conjunto de vértices visitados.
     * @return true se um ciclo Hamiltoniano for encontrado, false caso contrário.
     */
    private boolean buscaHamiltoniana(Vertice verticeAtual, List<Vertice> caminho, Set<Vertice> visitados) {
        if (caminho.size() == vertices.size()) {
            // Verifica se há uma aresta de volta para o vértice inicial
            Vertice verticeInicial = caminho.get(0);
            return existeAresta(verticeAtual, verticeInicial);
        }

        for (Vertice vizinho : getVizinhos(verticeAtual)) {
            if (!visitados.contains(vizinho)) {
                caminho.add(vizinho);
                visitados.add(vizinho);
                if (buscaHamiltoniana(vizinho, caminho, visitados)) {
                    return true;
                }
                caminho.remove(caminho.size() - 1);
                visitados.remove(vizinho);
            }
        }

        return false;
    }

    /**
     * Obtém os vizinhos de um dado vértice.
     *
     * @param vertice O vértice para o qual os vizinhos são buscados.
     * @return Conjunto de vértices vizinhos.
     */
    private Set<Vertice> getVizinhos(Vertice vertice) {
        Set<Vertice> vizinhos = new HashSet<>();
        for (Aresta aresta : arestas) {
            if (aresta.u.equals(vertice)) {
                vizinhos.add(aresta.v);
            } else if (aresta.v.equals(vertice)) {
                vizinhos.add(aresta.u);
            }
        }
        return vizinhos;
    }

    /**
     * Verifica se existe uma aresta entre dois vértices.
     *
     * @param u O primeiro vértice.
     * @param v O segundo vértice.
     * @return true se existir uma aresta entre os vértices, false caso contrário.
     */
    private boolean existeAresta(Vertice u, Vertice v) {
        for (Aresta aresta : arestas) {
            if ((aresta.u.equals(u) && aresta.v.equals(v)) || (aresta.u.equals(v) && aresta.v.equals(u))) {
                return true;
            }
        }
        return false;
    }

 /**
     * Verifica se o grafo é cíclico.
     *
     * @return true se o grafo for cíclico, false caso contrário.
     */
    public boolean isCiclico() {
      Set<Vertice> visitados = new HashSet<>();
      for (Vertice vertice : vertices.values()) {
          if (!visitados.contains(vertice)) {
              if (dfsCiclico(vertice, visitados, null)) {
                  return true;
              }
          }
      }
      return false;
  }

  /**
   * Método recursivo para busca em profundidade para detectar ciclos.
   *
   * @param verticeAtual O vértice atual na busca.
   * @param visitados    Conjunto de vértices visitados.
   * @param pai          O vértice pai no caminho atual.
   * @return true se um ciclo for encontrado, false caso contrário.
   */
  private boolean dfsCiclico(Vertice verticeAtual, Set<Vertice> visitados, Vertice pai) {
      visitados.add(verticeAtual);

      for (Vertice vizinho : verticeAtual.adjacencia) {
          if (!visitados.contains(vizinho)) {
              if (dfsCiclico(vizinho, visitados, verticeAtual)) {
                  return true;
              }
          } else if (!vizinho.equals(pai)) {
              return true;
          }
      }
      return false;
  }

  public boolean isPlanar() {
    int V = vertices.size();
    int E = arestas.size();

    // Verificação básica usando a desigualdade de grafos planares.
    if (E > 3 * V - 6) {
        return false;
    }

    // Verificação adicional para grafos com menos de 5 vértices.
    if (V < 5) {
        return true;
    }

    // Verificação de subgrafos K5 e K3,3
    // (Implementação específica pode ser adicionada aqui para subgrafos específicos)

    return true;
}
public List<List<Vertice>> getComponentesConexas() {
  List<List<Vertice>> componentes = new ArrayList<>();
  Set<Vertice> visitados = new HashSet<>();

  for (Vertice vertice : vertices.values()) {
      if (!visitados.contains(vertice)) {
          List<Vertice> componente = new ArrayList<>();
          DFS(vertice, visitados, componente);
          componentes.add(componente);
      }
  }

  return componentes;
}

private void DFS(Vertice vertice, Set<Vertice> visitados, List<Vertice> componente) {
  visitados.add(vertice);
  componente.add(vertice);

  for (Vertice vizinho : vertice.adjacencia) {
      if (!visitados.contains(vizinho)) {
          DFS(vizinho, visitados, componente);
      }
  }
}

    /**
     * Encontra um caminho euleriano no grafo.
     *
     * @return Lista representando o caminho euleriano, ou null se não existir.
     */
    public List<Vertice> encontrarCaminhoEuleriano() {
      // Verifica as condições para existência de caminho euleriano
      if (!verificarCondicoesParaEuleriano()) {
          return null;
      }

      // Encontra o vértice inicial para começar a busca
      Vertice inicio = encontrarVerticeInicial();
      List<Vertice> caminho = new ArrayList<>();
      Stack<Vertice> pilha = new Stack<>();
      pilha.push(inicio);

      while (!pilha.isEmpty()) {
          Vertice atual = pilha.peek();
          if (!atual.adjacencia.isEmpty()) {
              Vertice proximo = atual.adjacencia.remove(0);
              removerAresta(atual, proximo);
              pilha.push(proximo);
          } else {
              caminho.add(pilha.pop());
          }
      }

      // O caminho deve ser revertido para estar na ordem correta
      Collections.reverse(caminho);
      return caminho;
  }

  /**
   * Verifica as condições iniciais para a existência de um caminho euleriano.
   *
   * @return true se o grafo satisfaz as condições para um caminho euleriano, false caso contrário.
   */
  private boolean verificarCondicoesParaEuleriano() {
      int countOddDegree = 0;
      for (Vertice v : vertices.values()) {
          if (v.adjacencia.size() % 2 != 0) {
              countOddDegree++;
          }
      }
      return countOddDegree == 0 || countOddDegree == 2;
  }

  /**
   * Encontra o vértice inicial para começar a busca por um caminho euleriano.
   *
   * @return Vértice inicial para começar a busca.
   */
  private Vertice encontrarVerticeInicial() {
      for (Vertice v : vertices.values()) {
          if (v.adjacencia.size() % 2 != 0) {
              return v; // Encontra um vértice de grau ímpar
          }
      }
      // Se não houver vértice de grau ímpar, retorna qualquer vértice
      return vertices.values().iterator().next();
  }

  /**
   * Remove a aresta do grafo entre os vértices u e v.
   *
   * @param u Vértice u.
   * @param v Vértice v.
   */
  private void removerAresta(Vertice u, Vertice v) {
        u.adjacencia.remove(v);
        v.adjacencia.remove(u);
  }

   /**
     * Verifica se o grafo tem um caminho hamiltoniano e lista o caminho se existir.
     *
     * @return Lista representando o caminho hamiltoniano, ou null se não existir.
     */
    public List<Vertice> encontrarCaminhoHamiltoniano() {
      List<Vertice> caminho = new ArrayList<>();
      Set<Vertice> visitados = new HashSet<>();

      // Inicia a busca de caminho hamiltoniano a partir de cada vértice
      for (Vertice v : vertices.values()) {
          caminho.clear();
          visitados.clear();
          caminho.add(v);
          visitados.add(v);
          if (encontrarCaminhoHamiltonianoRecursivo(v, caminho, visitados)) {
              return caminho;
          }
      }

      return null; // Não encontrou caminho hamiltoniano
  }

  /**
   * Função recursiva para encontrar caminho hamiltoniano.
   *
   * @param v         Vértice atual na busca.
   * @param caminho   Lista que armazena o caminho hamiltoniano encontrado.
   * @param visitados Conjunto de vértices visitados.
   * @return true se encontrou um caminho hamiltoniano, false caso contrário.
   */
  private boolean encontrarCaminhoHamiltonianoRecursivo(Vertice v, List<Vertice> caminho, Set<Vertice> visitados) {
      // Caso base: se o caminho tem todos os vértices, é um caminho hamiltoniano
      if (caminho.size() == vertices.size()) {
          return true;
      }

      // Para todos os vizinhos não visitados de v, tenta incluir no caminho
      for (Vertice vizinho : v.adjacencia) {
          if (!visitados.contains(vizinho)) {
              caminho.add(vizinho);
              visitados.add(vizinho);

              // Recursivamente tenta encontrar o caminho hamiltoniano
              if (encontrarCaminhoHamiltonianoRecursivo(vizinho, caminho, visitados)) {
                  return true;
              }

              // Backtrack: remove o vértice do caminho e do conjunto de visitados
              caminho.remove(caminho.size() - 1);
              visitados.remove(vizinho);
          }
      }

      return false; // Não encontrou caminho hamiltoniano a partir deste vértice
  }
//encontrar V de articulação
//arestas ponte

  public HashMap<String, Vertice> getVertices() {
    return vertices;
  }

  public List<Aresta> getArestas() {
    return arestas;
  }
}