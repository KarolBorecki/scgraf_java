import algorithms.dijkstra.Dijkstra;
import data_structures.graph.Graph;
import data_structures.tuples.Size;
import generator.GraphGenerator;

public class Main {
    public static void main(String[] args) {
        Graph g = GraphGenerator.GenerateExample();

        System.out.println(g);

        Dijkstra d = new Dijkstra(g, g.getNode(0, 0));

        System.out.println(d.getResult(g.getNode(1,1)));

    }
}
