// Aresta
class Aresta {
    String u;
    String v;
    public String getU() {
      return u;
    }

    public String getV() {
      return v;
    }

    public int getPeso() {
      return peso;
    }

    int peso;
  
    Aresta(String u, String v, int peso) {
      this.u = u;
      this.v = v;
      this.peso = peso;
    }
  
    @Override
    public String toString() {
      return "(" + u + ", " + v + ", " + peso + ")";
    }
  }
