package dijkstra;

import graph.*;

public class PriorityQueue {
  private List<DijkstraVertex> prio;

  public PriorityQueue() {
    prio = new List<DijkstraVertex>();
  }

  public void fuegeEin(DijkstraVertex pKnoten) {
    if (prio.isEmpty()) {
      prio.append(pKnoten);
    } else {
      prio.toFirst();
      while (prio.hasAccess()) {
        DijkstraVertex lKnoten = (DijkstraVertex) prio.getContent();
        if (lKnoten.getID().equals(pKnoten.getID())) {
          prio.remove();
        }
        prio.next();
      }

      prio.toFirst();
      DijkstraVertex lKnoten = (DijkstraVertex) prio.getContent();
      while (prio.hasAccess() && lKnoten.gibDistanz() < pKnoten.gibDistanz()) {
        prio.next();
        if (prio.hasAccess())
          lKnoten = (DijkstraVertex) prio.getContent();
      }
      if (!prio.hasAccess())
        prio.append(pKnoten);
      else {
        prio.insert(pKnoten);
      }
    }
  }

  public DijkstraVertex ersterKnoten() {
    prio.toFirst();
    return (DijkstraVertex) prio.getContent();
  }

  public boolean isEmpty() {
    return prio.isEmpty();
  }

  public void toFirst() {
    prio.toFirst();
  }

  public Object getObject() {
    return prio.getContent();
  }

  public void remove() {
    prio.remove();
  }

  public String toString() {
    String schlange = "";
    prio.toFirst();
    while (prio.hasAccess()) {
      DijkstraVertex dijv = (DijkstraVertex) prio.getContent();
      schlange = schlange + " " + dijv.getID();
      prio.next();
    }
    return schlange;
  }

}
