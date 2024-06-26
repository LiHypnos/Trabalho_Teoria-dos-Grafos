import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeituraEscrita {
  /**
   * Lê um grafo de um arquivo e cria um objeto Grafo.
   *
   * @param arquivo Caminho do arquivo que contém o grafo.
   * @return Um objeto Grafo com vértices e arestas lidos do arquivo.
   * @throws IOException Se ocorrer um erro ao ler o arquivo.
   */
  public static Grafo lerGrafo(String arquivo) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(arquivo));
    StringBuilder conteudo = new StringBuilder();
    String linha;

    while ((linha = reader.readLine()) != null) {
      conteudo.append(linha);
    }
    reader.close();

    // Removendo espaços
    String conteudoStr = conteudo.toString().replace(" ", "");

    // Encontrando os vértices
    int inicioVertices = conteudoStr.indexOf('{') + 1;
    int fimVertices = conteudoStr.indexOf('}');
    String[] verticesArray = conteudoStr.substring(inicioVertices, fimVertices).split(",");
    Set<String> vertices = new HashSet<>();
    for (String vertice : verticesArray) {
      vertices.add(vertice);
    }

    // Encontrando as arestas
    int inicioArestas = conteudoStr.indexOf('{', fimVertices) + 1;
    int fimArestas = conteudoStr.indexOf('}', inicioArestas);
    String[] arestasArray = conteudoStr.substring(inicioArestas, fimArestas).split("\\),\\(");

    List<Aresta> arestas = new ArrayList<>();
    for (String arestaStr : arestasArray) {
      arestaStr = arestaStr.replace("(", "").replace(")", "");
      String[] partes = arestaStr.split(",");
      String u = partes[0];
      String v = partes[1];
      int peso = (partes.length == 3) ? Integer.parseInt(partes[2]) : 1;
      arestas.add(new Aresta(u, v, peso));
    }

    return new Grafo(vertices, arestas);
  }
}