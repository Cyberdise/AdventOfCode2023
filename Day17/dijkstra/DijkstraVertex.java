package dijkstra;

import graph.*;

public class DijkstraVertex extends Vertex {
  private double distanz;
  private DijkstraVertex vorgaenger;

  public DijkstraVertex(String pID) {
    super(pID);
    vorgaenger = null;
    distanz = -1;

  }

  public double gibDistanz() {
    return distanz;
  }

  public void setzeDistanz(double pDistanz) {
    distanz = pDistanz;
  }

  public DijkstraVertex gibVorgaenger() {
    return vorgaenger;
  }

  public void setzeVorgaenger(DijkstraVertex pVorgaenger) {
    vorgaenger = pVorgaenger;
  }

}
