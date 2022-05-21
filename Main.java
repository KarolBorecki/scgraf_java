import algorithms.dijkstra.Dijkstra;
import data_handling.FileWriterG;
import data_structures.graph.Graph;
import data_structures.queue.PriorityQueue;
import data_structures.tuples.Size;
import generator.GraphGenerator;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        Graph g = GraphGenerator.GenerateExample();
        RandomGenerator rg = new Random();

        System.out.println(g);

        Dijkstra d = new Dijkstra(g, g.getNode(0, 0));

        d.Solve(g.getNode(1,1));
        System.out.println(d.getDijkstraResult(g.getNode(1,1)));
        System.out.println(d);

        System.out.println(d.getShortestPathLength(g.getNode(2, 2)) + "\n" + d.getShortestPathString(g.getNode(2, 2)));

        FileWriterG fg = new FileWriterG("example");
        fg.writeGraphToFile(g);

    }
}
