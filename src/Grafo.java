import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Classe que representa um grafo com vértices e arestas.
 */
public class Grafo {
  private Set<String> vertices;
  private List<Aresta> arestas;
  private Map<String, List<String>> adjacencia;


  /**
   * Construtor para inicializar o grafo com vértices e arestas.
   *
   * @param vertices Conjunto de vértices do grafo.
   * @param arestas  Lista de arestas do grafo.
   */
  public Grafo(Set<String> vertices, List<Aresta> arestas) {
    this.vertices = vertices;
    this.arestas = arestas;
    this.adjacencia = new HashMap<>();
    construirAdjacencia();
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

      // Mapa de adjacência
      Map<String, Set<String>> adjList = new HashMap<>();
      for (String vertice : vertices) {
          adjList.put(vertice, new HashSet<>());
      }
      for (Aresta aresta : arestas) {
          adjList.get(aresta.u).add(aresta.v);
          adjList.get(aresta.v).add(aresta.u); // Considerando grafo não-direcionado
      }

      // Realiza DFS para verificar conectividade
      Set<String> visitados = new HashSet<>();
      Stack<String> stack = new Stack<>();
      stack.push(vertices.iterator().next());

      while (!stack.isEmpty()) {
          String vertice = stack.pop();
          if (!visitados.contains(vertice)) {
              visitados.add(vertice);
              for (String adjacente : adjList.get(vertice)) {
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

      // Mapa de adjacência
      Map<String, Set<String>> adjList = new HashMap<>();
      for (String vertice : vertices) {
          adjList.put(vertice, new HashSet<>());
      }
      for (Aresta aresta : arestas) {
          adjList.get(aresta.u).add(aresta.v);
          adjList.get(aresta.v).add(aresta.u); // Considerando grafo não-direcionado
      }

      // Mapa para armazenar cores dos vértices
      Map<String, Integer> cores = new HashMap<>();
      for (String vertice : vertices) {
          cores.put(vertice, -1); // -1 representa que o vértice não foi colorido
      }

      // Realiza BFS para verificar bipartição
      Queue<String> queue = new LinkedList<>();
      for (String vertice : vertices) {
          if (cores.get(vertice) == -1) { // Se o vértice não foi visitado
              queue.add(vertice);
              cores.put(vertice, 0); // Inicia colorindo o vértice com a cor 0

              while (!queue.isEmpty()) {
                  String u = queue.poll();
                  int corU = cores.get(u);

                  for (String v : adjList.get(u)) {
                      if (cores.get(v) == -1) {
                          // Colorir com a cor oposta
                          cores.put(v, 1 - corU);
                          queue.add(v);
                      } else if (cores.get(v) == corU) {
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

    Map<String, Integer> grau = new HashMap<>();
    for (String vertice : vertices) {
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
        List<String> caminho = new ArrayList<>();
        Set<String> visitados = new HashSet<>();
        String verticeInicial = vertices.iterator().next(); // Escolhe um vértice inicial
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
    private boolean buscaHamiltoniana(String verticeAtual, List<String> caminho, Set<String> visitados) {
        if (caminho.size() == vertices.size()) {
            // Verifica se há uma aresta de volta para o vértice inicial
            String verticeInicial = caminho.get(0);
            return existeAresta(verticeAtual, verticeInicial);
        }

        for (String vizinho : getVizinhos(verticeAtual)) {
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
    private Set<String> getVizinhos(String vertice) {
        Set<String> vizinhos = new HashSet<>();
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
    private boolean existeAresta(String u, String v) {
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
      Set<String> visitados = new HashSet<>();
      for (String vertice : vertices) {
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
  private boolean dfsCiclico(String verticeAtual, Set<String> visitados, String pai) {
      visitados.add(verticeAtual);

      for (String vizinho : adjacencia.get(verticeAtual)) {
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

  /**
   * Constrói a lista de adjacência para o grafo.
   */
  private void construirAdjacencia() {
      for (String vertice : vertices) {
          adjacencia.put(vertice, new ArrayList<>());
      }
      for (Aresta aresta : arestas) {
          adjacencia.get(aresta.u).add(aresta.v);
          adjacencia.get(aresta.v).add(aresta.u);
      }
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
public List<List<String>> getComponentesConexas() {
  List<List<String>> componentes = new ArrayList<>();
  Set<String> visitados = new HashSet<>();

  for (String vertice : vertices) {
      if (!visitados.contains(vertice)) {
          List<String> componente = new ArrayList<>();
          DFS(vertice, visitados, componente);
          componentes.add(componente);
      }
  }

  return componentes;
}

private void DFS(String vertice, Set<String> visitados, List<String> componente) {
  visitados.add(vertice);
  componente.add(vertice);

  for (String vizinho : adjacencia.get(vertice)) {
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
    public List<String> encontrarCaminhoEuleriano() {
      // Verifica as condições para existência de caminho euleriano
      if (!verificarCondicoesParaEuleriano()) {
          return null;
      }

      // Encontra o vértice inicial para começar a busca
      String inicio = encontrarVerticeInicial();
      List<String> caminho = new ArrayList<>();
      Stack<String> pilha = new Stack<>();
      pilha.push(inicio);

      while (!pilha.isEmpty()) {
          String atual = pilha.peek();
          if (!adjacencia.get(atual).isEmpty()) {
              String proximo = adjacencia.get(atual).remove(0);
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
      for (String v : vertices) {
          if (adjacencia.get(v).size() % 2 != 0) {
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
  private String encontrarVerticeInicial() {
      for (String v : vertices) {
          if (adjacencia.get(v).size() % 2 != 0) {
              return v; // Encontra um vértice de grau ímpar
          }
      }
      // Se não houver vértice de grau ímpar, retorna qualquer vértice
      return vertices.iterator().next();
  }

  /**
   * Remove a aresta do grafo entre os vértices u e v.
   *
   * @param u Vértice u.
   * @param v Vértice v.
   */
  private void removerAresta(String u, String v) {
      adjacencia.get(u).remove(v);
      adjacencia.get(v).remove(u);
  }

   /**
     * Verifica se o grafo tem um caminho hamiltoniano e lista o caminho se existir.
     *
     * @return Lista representando o caminho hamiltoniano, ou null se não existir.
     */
    public List<String> encontrarCaminhoHamiltoniano() {
      List<String> caminho = new ArrayList<>();
      Set<String> visitados = new HashSet<>();

      // Inicia a busca de caminho hamiltoniano a partir de cada vértice
      for (String v : vertices) {
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
  private boolean encontrarCaminhoHamiltonianoRecursivo(String v, List<String> caminho, Set<String> visitados) {
      // Caso base: se o caminho tem todos os vértices, é um caminho hamiltoniano
      if (caminho.size() == vertices.size()) {
          return true;
      }

      // Para todos os vizinhos não visitados de v, tenta incluir no caminho
      for (String vizinho : adjacencia.get(v)) {
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
    /**
 * Retorna a matriz de adjacência do grafo.
 *
 * @return Uma matriz de adjacência representando o grafo.
 */
public int[][] getMatrizAdjacencia() {
    int n = vertices.size();
    List<String> verticesList = new ArrayList<>(vertices);
    int[][] matrizAdjacencia = new int[n][n];

    for (Aresta aresta : arestas) {
        int uIndex = verticesList.indexOf(aresta.getU());
        int vIndex = verticesList.indexOf(aresta.getV());
        matrizAdjacencia[uIndex][vIndex] = aresta.getPeso();
        matrizAdjacencia[vIndex][uIndex] = aresta.getPeso();  // Para grafos não direcionados
    }

    return matrizAdjacencia;
}

/**
 * Método para encontrar os vértices de articulação em um grafo.
 *
 * @return Conjunto de vértices de articulação.
 */
public Set<String> encontrarVerticesArticulacao() {
    Set<String> articulacoes = new HashSet<>();
    Map<String, Integer> discovery = new HashMap<>();
    Map<String, Integer> low = new HashMap<>();
    Map<String, String> parent = new HashMap<>();
    Set<String> visited = new HashSet<>();

    for (String vertice : vertices) {
        discovery.put(vertice, -1);
        low.put(vertice, -1);
        parent.put(vertice, null);
    }

    for (String vertice : vertices) {
        if (!visited.contains(vertice)) {
            dfsArticulacoes(vertice, visited, discovery, low, parent, articulacoes);
        }
    }

    return articulacoes;
}

private void dfsArticulacoes(String u, Set<String> visited, Map<String, Integer> discovery, Map<String, Integer> low,
                                Map<String, String> parent, Set<String> articulacoes) {
    int time = 0;
    visited.add(u);
    discovery.put(u, time);
    low.put(u, time);
    time++;

    int children = 0;
    for (String v : adjacencia.get(u)) {
        if (!visited.contains(v)) {
            parent.put(v, u);
            children++;
            dfsArticulacoes(v, visited, discovery, low, parent, articulacoes);

            low.put(u, Math.min(low.get(u), low.get(v)));

            if (parent.get(u) == null && children > 1) {
                articulacoes.add(u);
            }

            if (parent.get(u) != null && low.get(v) >= discovery.get(u)) {
                articulacoes.add(u);
            }
        } else if (!v.equals(parent.get(u))) {
            low.put(u, Math.min(low.get(u), discovery.get(v)));
        }
    }
    }

  /**
     * Método para encontrar as arestas ponte em um grafo.
     *
     * @return Lista de arestas ponte.
     */
    public List<Aresta> encontrarArestasPonte() {
        List<Aresta> pontes = new ArrayList<>();
        Map<String, Integer> discovery = new HashMap<>();
        Map<String, Integer> low = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String vertice : vertices) {
            discovery.put(vertice, -1);
            low.put(vertice, -1);
            parent.put(vertice, null);
        }

        for (String vertice : vertices) {
            if (!visited.contains(vertice)) {
                dfsPontes(vertice, visited, discovery, low, parent, pontes);
            }
        }

        return pontes;
    }

    private void dfsPontes(String u, Set<String> visited, Map<String, Integer> discovery, Map<String, Integer> low,
                           Map<String, String> parent, List<Aresta> pontes) {
        int time = 0;
        visited.add(u);
        discovery.put(u, time);
        low.put(u, time);
        time++;

        for (String v : adjacencia.get(u)) {
            if (!visited.contains(v)) {
                parent.put(v, u);
                dfsPontes(v, visited, discovery, low, parent, pontes);

                low.put(u, Math.min(low.get(u), low.get(v)));

                if (low.get(v) > discovery.get(u)) {
                    pontes.add(new Aresta(u, v, 0));
                }
            } else if (!v.equals(parent.get(u))) {
                low.put(u, Math.min(low.get(u), discovery.get(v)));
            }
        }
    }

  public Map<String, List<String>> getAdjacencia() {
        return adjacencia;
    }

public Set<String> getVertices() {
    return vertices;
  }

  public List<Aresta> getArestas() {
    return arestas;
  }
}