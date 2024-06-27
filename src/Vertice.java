import java.util.ArrayList;

class Vertice {
  public String valor;
  public ArrayList<Vertice> adjacencia;

  public Vertice(String valor) {
    this.valor = valor;
    this.adjacencia = new ArrayList<>();
  }

  @Override
  public String toString() {
    return valor;
  }

  public String getListaDeAdjacencia() {
    String res = this.toString();

    for (Vertice v : this.adjacencia) {
      res += " -> " + v.toString();
    }
    res += ";";

    return res;
  }
}