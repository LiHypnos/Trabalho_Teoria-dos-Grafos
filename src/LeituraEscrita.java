import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeituraEscrita {
  /**
   * Lê um grafo de um arquivo e cria um objeto Grafo.
   *
   * @param arquivo
   *          Caminho do arquivo que contém o grafo.
   * @return Um objeto Grafo com vértices e arestas lidos do arquivo.
   * @throws IOException
   *           Se ocorrer um erro ao ler o arquivo.
   */
  public static Grafo lerGrafo(String arquivo) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(arquivo));
    String linha = br.readLine();
    String[] partes = linha.split(" ");
    int nVertices = Integer.parseInt(partes[0]);
    int nArestas = Integer.parseInt(partes[1]);

    linha = br.readLine();
    boolean naoDirecionado = linha.equals("nao_direcionado");

    Grafo grafo = new Grafo(naoDirecionado);

    for (int i = 0; i < nArestas; i++) {
      linha = br.readLine();
      partes = linha.split(" ");
      int idAresta = Integer.parseInt(partes[0]);
      String u = partes[1];
      String v = partes[2];
      int peso = Integer.parseInt(partes[3]);

      Vertice verticeU = new Vertice(u);
      Vertice verticeV = new Vertice(v);
      Aresta aresta = new Aresta(idAresta, verticeU, verticeV, peso);

      /*
       * atualmente, como nao temos vertices soltos,
       * o metodo adicionarAresta tambem adiciona os vertices
       * caso nao estejam presentes na propriedade vertices do grafo
       */
      grafo.adicionarAresta(aresta);

      if (naoDirecionado) {
        grafo.adicionarAresta(new Aresta(idAresta, verticeV, verticeU, peso));
      }
    }

    br.close();
    return grafo;
  }
}