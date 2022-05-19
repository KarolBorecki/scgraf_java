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
        g.setHeight(10);
        g.setWidth(20);
        g.buildExample(1.0000000000000000000000000000000000000000000000000000000000000000000000000000000001);

        //g.buildExample();
        System.out.println(g.toStringformatted());
        System.out.println(g);

        dijkstra d = new dijkstra(g, g.getNode(0));
        d.printDijkstrasTable();
    }
}
