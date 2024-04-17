package dijkstra;

import graph.*;

public class Dijkstra {
	private Graph bspGraph = new Graph();
	private List<DijkstraVertex> route;
	private double wegLaenge = 0;

	public Dijkstra(Graph pGraph) {
		bspGraph = pGraph;
	}

	private void konstruiereRoute(DijkstraVertex knoten) {
		wegLaenge = 0;
		route = new List<DijkstraVertex>();
		do {
			route.toFirst();
			route.insert(knoten);
			DijkstraVertex vorgaenger = knoten.gibVorgaenger();
			if (vorgaenger != null) {
				Edge kante = bspGraph.getEdge(knoten, vorgaenger);
				wegLaenge = wegLaenge + kante.getWeight();
				route.next();
			}
			knoten = vorgaenger;
		} while (knoten != null);
	}

	public List<DijkstraVertex> gibRoute() {
		return route;
	}

	public double gibWeglaenge() {
		return wegLaenge;
	}

	public void zuruecksetzen() {
		List<Vertex> knoten = bspGraph.getVertices();
		while (knoten.hasAccess()) {
			DijkstraVertex djk = (DijkstraVertex) knoten.getContent();
			djk.setzeDistanz(-1);
			((DijkstraVertex) knoten.getContent()).setzeVorgaenger(null);
			((DijkstraVertex) knoten.getContent()).setMark(false);
			knoten.next();
		}
	}

	public void sucheWeg(DijkstraVertex pStart, DijkstraVertex pZiel) {
		if (pStart != null && pZiel != null) {
			PriorityQueue schlange = new PriorityQueue();
			pStart.setzeDistanz(0);
			schlange.fuegeEin(pStart);
			DijkstraVertex nachbarKnoten = null;
			DijkstraVertex ersterKnoten = null;
			while (!schlange.isEmpty() && ersterKnoten != pZiel) {
				schlange.toFirst();
				ersterKnoten = (DijkstraVertex) schlange.getObject();
				schlange.remove();
				if (!ersterKnoten.isMarked()) {
					ersterKnoten.setMark(true);
					List<Vertex> nachbarListe = bspGraph
							.getNeighbours(ersterKnoten);
					nachbarListe.toFirst();
					while (nachbarListe.hasAccess()) {
						nachbarKnoten = (DijkstraVertex) nachbarListe.getContent();
						Edge kante = bspGraph.getEdge(nachbarKnoten,
								ersterKnoten);
						double gewicht = kante.getWeight();
						if (nachbarKnoten.gibDistanz() == -1
								|| nachbarKnoten.gibDistanz() > ersterKnoten
										.gibDistanz() + gewicht) {
							nachbarKnoten.setzeDistanz(ersterKnoten
									.gibDistanz() + gewicht);
							nachbarKnoten.setzeVorgaenger(ersterKnoten);
							schlange.fuegeEin(nachbarKnoten);
						}
						nachbarListe.next();
					}
				}
			}
			konstruiereRoute(pZiel);
		}
	}

}
