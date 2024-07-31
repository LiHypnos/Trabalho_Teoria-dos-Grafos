import java.util.Objects;

// Aresta
class Aresta {
  public final int id;
  private Vertice origem;
  private Vertice destino;
  private int peso;

  public Aresta(int id, Vertice origem, Vertice destino, int peso) {
    this.id = id;
    this.origem = origem;
    this.destino = destino;
    this.peso = peso;
  }

  public Vertice getVerticeOrigem() {
    return origem;
  }

  public Vertice getVerticeDestino() {
    return destino;
  }

  public int getPeso() {
    return peso;
  }

  @Override
  public String toString() {
    return "(" + origem + ", " + destino + ", " + peso + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Aresta aresta = (Aresta) o;
    return Double.compare(aresta.peso, peso) == 0 &&
        Objects.equals(id, aresta.id) &&
        Objects.equals(origem, aresta.origem) &&
        Objects.equals(destino, aresta.destino);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, origem, destino, peso);
  }
}
