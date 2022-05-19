import data_structures.graph.Graph;
import data_structures.queue.PriorityQueue;
import data_structures.queue.Fifo;
import data_structures.graph.Node;
import algorithms.dijkstra.*;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> f = new PriorityQueue<Integer>(5);
        f.push(4);
        f.push(2);
        System.out.println(f);
        System.out.println(f.pop());
        System.out.println(f);
        System.out.println(f.pop());
        f.push(10);
        f.push(7);
        f.push(11);
        f.push(9);
        System.out.println(f);
        System.out.println(f.pop());
        System.out.println(f);
        System.out.println(f.peek());
        System.out.println(f);
        System.out.println(f.pop());
        f.push(10);
        f.push(13);


        Graph g = new Graph();
        g.setHeight(30);
        g.setWidth(30);
        g.buildExample();

        //g.buildExample();
        System.out.println(g.toStringformatted());
        System.out.println(g);

        dijkstra d = new dijkstra(g, g.getNode(19));

        System.out.println(d.getShortestPath(g.getNode(3)));
        System.out.println(d.getShortestPathLength(g.getNode(3)));

        d.printDijkstraResult(g.getNode(3));
    }
}
