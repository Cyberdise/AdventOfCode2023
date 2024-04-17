import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dijkstra.DijkstraVertex;
import graph.Edge;
import graph.Graph;

class Main {
    Graph test_graph;
    List<List<Integer>> map;
    List<List<DijkstraVertex>> vertices;
    Main() {
        test_graph = new Graph();
        map = generateListMap();
        vertices = initVertices();
    }

    public void NICHTSTATIC () {
        System.out.println(this.vertices);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.NICHTSTATIC();
    }

    public List<Edge> initEdges(){
        List<Edge> edges = new ArrayList<Edge>();
        List<List<DijkstraVertex>> vertices = this.vertices;
        for(int y = 0; y < vertices.size(); y++){
            for(int x = 0; x < vertices.get(y).size(); y++) {
                Edge edgeR = new Edge(vertices.get(y).get(x), vertices.get(y).get(x+1), vertices.get(y).get(x).)
                if(vertices)

                /*
                            def add_neighbors():
                x, y = 0, 0
                for line in all_nodes:
                    for col in line:
                        if x < len(line) - 1:
                            all_nodes[x][y].neighbors.append(all_nodes[x + 1][y])
                            all_nodes[x + 1][y].neighbors.append(all_nodes[x][y])
                        if y < len(all_nodes) - 1:
                            all_nodes[x][y].neighbors.append(all_nodes[x][y + 1])
                            all_nodes[x][y + 1].neighbors.append(all_nodes[x][y])
                        x += 1
                    y += 1
                    x = 0


                 */
            }
        }
        return edges;
    }

    public List<List<DijkstraVertex>> initVertices() {
        List<List<DijkstraVertex>> vertices = new ArrayList<List<DijkstraVertex>>();
        for(int y = 0; y < this.map.size(); y++) {
            List<DijkstraVertex> line = new ArrayList<DijkstraVertex>();
            for(int x = 0; x < this.map.get(y).size(); x++){
                DijkstraVertex vertex = new DijkstraVertex(Integer.toString(x));

                line.add(vertex);

            }
            vertices.add(line);
        }
        return vertices;
    }

    public static List<List<Integer>> generateListMap() {
        List<List<Integer>> map = new ArrayList<List<Integer>>();
        Scanner scanner = null;
        File insert = null;

        // Buffer immer eine Zeile der txt-Datei speichert
        String buffer = null;

        // Try_Catch-Block: Falls die File nicht gefunden wurde
        try {
            insert = new File("./testinput.txt");
            scanner = new Scanner(insert);
        } catch (IOException io) {
            io.printStackTrace();
        }
        while (scanner.hasNext()) {
            buffer = scanner.nextLine();
            List<Integer> LINE = new ArrayList<Integer>();
            int line = 0;
            for (int i = 0; i < buffer.length(); i++) {
                String CHAR = buffer.charAt(i) + "";
                LINE.add(Integer.parseInt(CHAR));
            }
            map.add(LINE);
        }
        return map;
    }
}