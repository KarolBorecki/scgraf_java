package com.scgraf.algorithms;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.queue.PriorityQueue;
import com.scgraf.utils.Utils;

public class Dijkstra extends Thread {

    private final static int STARTING_SIZE = 4;

    private static PriorityQueue<DijkstraData> queToVisit;
    private static DijkstraData[] dijkstraTable;

    public static Node[] getShortestPathArray(Graph graph, Node startNode, Node finishNode) throws DijkstraNotSolvedException, DijkstraCannotFindPathException {
        Solve(graph, startNode);
        Node[] t = new Node[STARTING_SIZE];

        Node helpNode = finishNode;

        int i = 0;
        while (!helpNode.equals(startNode)) {
            if (i >= t.length)
                t = Utils.resizeArray(t, 2);
            t[i] = helpNode;
            if (dijkstraTable[helpNode.getGraphID()].previousNode == null)
                throw new DijkstraCannotFindPathException();
            helpNode = dijkstraTable[helpNode.getGraphID()].previousNode;
            i++;
        }
        t = Utils.resizeArrayTo(t, i + 1);
        t[i] = helpNode;

        return t;
    }

    public static double getShortestPathLength(Graph graph, Node startNode, Node finishNode) {
        Solve(graph, startNode);
        double shortestPath = 0;
        if (finishNode == startNode)
            return 0;
        for (int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            shortestPath += dijkstraTable[i].length;
        return shortestPath;
    }

    public static String getShortestPathString(Graph graph, Node startNode, Node finishNode) {
        Solve(graph, startNode);
        StringBuilder path = new StringBuilder();
        if (finishNode == startNode)
            return path.toString();
        path.append(finishNode.getGraphID());
        for (int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            path.append(" -> ").append(dijkstraTable[i].previousNode.getGraphID());
        path.append(" -> ").append(startNode.getGraphID());
        return path.toString();
    }

    private static void Solve(Graph graph, Node startNode) {
        dijkstraTable = initializeDijkstraTable(graph, startNode);

        while (!queToVisit.isEmpty()) {
            Node currentNode = queToVisit.pop().getCurrentNode();
            for (Path.Side side : Path.Side.values()) {
                if (currentNode.isConnected(side)) {
                    double valToCheck = currentNode.getConnectionWeight(side) + dijkstraTable[currentNode.getGraphID()].length;
                    Node Neighbour = graph.getNeighbourNode(currentNode, side);

                    if (Neighbour.getGraphID() >= 0 && Neighbour.getGraphID() < graph.getNodesCount() && valToCheck < dijkstraTable[Neighbour.getGraphID()].length) {
                        dijkstraTable[Neighbour.getGraphID()].length = valToCheck;
                        dijkstraTable[Neighbour.getGraphID()].previousNode = currentNode;
                    }
                }
            }
        }
    }

    private static DijkstraData[] initializeDijkstraTable(Graph graph, Node startNode) {
        DijkstraData[] dijkstraTable = new DijkstraData[graph.getNodesCount()];
        queToVisit = new PriorityQueue<>(graph.getNextGraphID());
        for (int i = 0; i < graph.getSize().height(); i++)
            for (int j = 0; j < graph.getSize().width(); j++) {
                int indexInGraph = i * graph.getSize().width() + j;
                dijkstraTable[indexInGraph] = new DijkstraData(graph.getNode(i, j));
                queToVisit.push(dijkstraTable[indexInGraph]);
            }

        dijkstraTable[startNode.getGraphID()].length = 0;
        dijkstraTable[startNode.getGraphID()].previousNode = startNode;

        return dijkstraTable;
    }

    protected static class DijkstraData implements Comparable<DijkstraData> {
        public double length = Double.MAX_VALUE;
        public Node previousNode = null;
        public Node currentNode;

        public DijkstraData(Node node) {
            this.currentNode = node;
        }

        public Node getCurrentNode() {
            return currentNode;
        }

        public Node getPreviousNode() {
            return previousNode;
        }

        @Override
        public int compareTo(DijkstraData o) {
            return this.length < o.length ? 1 : -1;
        }

        @Override
        public String toString() {
            return "\t" + length + "\t" + (previousNode == null ? "-" : previousNode.getGraphID());
        }
    }

    public static class DijkstraNotSolvedException extends Throwable {

    }

    public static class DijkstraCannotFindPathException extends Throwable {

    }
}