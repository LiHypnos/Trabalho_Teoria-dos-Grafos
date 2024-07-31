import java.util.Objects;

class Vertice {
  public final String id;

  public Vertice(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getId();
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
    return Objects.equals(id, vertice.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}