// Aresta
class Aresta {
  Vertice u;
  Vertice v;

  public Vertice getVerticePartida() {
    return u;
  }

  public Vertice getVerticeChegada() {
    return v;
  }

  public int getPeso() {
    return peso;
  }

  int peso;

  public Aresta(Vertice u, Vertice v) {
    this.u = u;
    this.v = v;
    this.peso = 1;
  }

  public Aresta(Vertice u, Vertice v, int peso) {
    this(u, v);
    this.peso = peso;
  }

  @Override
  public String toString() {
    return "(" + u + ", " + v + ", " + peso + ")";
  }
}
