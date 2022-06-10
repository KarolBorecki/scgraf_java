package com.scgraf.algorithms;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.queue.Fifo;

public class BFS extends Thread {
    public static boolean Solve(Graph graph) {
        Fifo<Node> queToCheck = initializeQue(graph);

        while (!queToCheck.isEmpty()) {
            Node currentNode = queToCheck.pop();
            for (Path.Side s : Path.Side.values())
                if (currentNode.isConnected(s)) {
                    Node checkedNode = graph.getNeighbourNode(currentNode, s);
                    if (checkedNode != null && !queToCheck.containsInFilledElements(checkedNode))
                        queToCheck.push(checkedNode);
                }

        }
        return (queToCheck.queueFilledSize() - 1) == graph.getNodesCount();
    }

    private static Fifo<Node> initializeQue(Graph graph) {
        Fifo<Node> queToCheck = new Fifo<>(graph.getNodesCount());
        Node startNode = null;
       startNode = graph.getNode(0, 0);

        queToCheck.push(startNode);
        for (Path.Side s : Path.Side.values()) {
            if (startNode.isConnected(s)) {
                Node neighbourNode = graph.getNeighbourNode(startNode, s);
                if (neighbourNode != null)
                    queToCheck.push(neighbourNode);
            }
        }
        queToCheck.pop();

        return queToCheck;
    }
}
