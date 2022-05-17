import data_structures.graph.Graph;
import data_structures.queue.Fifo;

public class Main {
    public static void main(String[] args){

        Graph g = new Graph();
        g.setWidth(3).build();

        System.out.println(g);
    }
}
