import algorithms.dijkstra.Dijkstra;
import data_handling.FileWriterG;
import data_structures.graph.Graph;
import data_structures.tuples.Size;
import generator.GraphGenerator;

public class Main {
    public static void main(String[] args) {
        Graph g = GraphGenerator.GenerateExample();

        System.out.println(g);

        Dijkstra d = new Dijkstra(g, g.getNode(0, 0));

        d.Solve(g.getNode(1,1));
        d.getDijkstraResult(g.getNode(1,1));
        System.out.println(d);

        FileWriterG fg = new FileWriterG("example");
        fg.writeGraphToFile(g);

    }
}
