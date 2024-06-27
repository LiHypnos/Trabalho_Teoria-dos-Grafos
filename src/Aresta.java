// Aresta
class Aresta {
    Vertice u;
    Vertice v;
    int peso;
  
    Aresta(Vertice u, Vertice v, int peso) {
      this.u = u;
      this.v = v;
      this.peso = peso;
    }
  
    @Override
    public String toString() {
      return "(" + u + ", " + v + ", " + peso + ")";
    }
  }
