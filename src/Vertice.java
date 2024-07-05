import java.util.ArrayList;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vertice vertice = (Vertice) o;
    return Objects.equals(valor, vertice.valor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valor);
  }
}