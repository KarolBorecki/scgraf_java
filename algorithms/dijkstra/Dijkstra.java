package algorithms.dijkstra;

import data_structures.graph.*;
import data_structures.queue.*;

public class Dijkstra { //IMPLEMENTS ALGORITHMS
    protected static class DijkstraData implements Comparable<DijkstraData>{
        public double length;
        public Node previousNode;

        @Override
        public int compareTo(DijkstraData o) {
            return this.length - o.length > 0 ? -1 : 1;
        }
    }

    private final Graph graph;
    private final Node startNode;

    private double [] pathLengths; // uzyj nowej klasy DijkstraData - jest wygodniej operowac na obiektach
    private Node [] previousNode;
    private DijkstraData[] dijkstraTable; //O tak lepiej

    private Fifo<Node> queToVisit; // Uzyj priority queue!!!!
    private int amountOfNodes; // Marnujesz pamięć - to samo co graph.getNodesCount();

    public Dijkstra(Graph g, Node startNode){
        graph = g;
        this.startNode = startNode;

        initializeDijkstraTable();
        fillDijkstraTable(); // dla dużych grafów sama inicjalizacja będzie trwać bardzo długo
    }

    private void initializeDijkstraTable(){
        amountOfNodes = graph.getNodesCount();

        queToVisit= initializeQueueToVisit(amountOfNodes);

        pathLengths = new double[amountOfNodes];
        previousNode = new Node[amountOfNodes];

        for(int i = 0; i < amountOfNodes; i++){
            previousNode[i] = null;
            if(i == startNode.getGraphID())
                pathLengths[i] = 0;
            else
                pathLengths[i] = Double.MAX_VALUE;
        }
    }

    private void fillDijkstraTable() { //SOLVE();
        while(!queToVisit.IsEmpty()){
            Node currentVertex = queToVisit.pop();
            for(Path.Side side : Path.Side.values()){
                if(currentVertex.isConnected(side)){
                    double valToCheck = currentVertex.getConnectionWeight(side) + pathLengths[currentVertex.getGraphID()];
                    int index = graph.getIdOfConnectedVertex(currentVertex, side);
                    if(index >= 0 && index < graph.getNodesCount() && valToCheck < pathLengths[index] ){
                        pathLengths[index] = valToCheck;
                        previousNode[index] = currentVertex;
                    }
                }
            }
            setFifoHead();
        }

    }
/* TODO DELETE THIS - REDUNDANT CODE - use priority queue */
    private void setFifoHead(){
        double minDistance = Double.MAX_VALUE;
        double actualDistance = 0.;
        int minDistanceIndex= queToVisit.fifoHeadIndex();
        int max = queToVisit.getLength();
        for(int i= queToVisit.fifoHeadIndex(); i< max ; i++){
            actualDistance = pathLengths[queToVisit.getAtIndex(i).getGraphID()];
            if(actualDistance < minDistance){
                minDistance = actualDistance;
                minDistanceIndex = i;
            }
        }
        if(queToVisit.fifoHeadIndex() != queToVisit.getLength())
            queToVisit.swapElements(queToVisit.fifoHeadIndex(), minDistanceIndex);
    }

    private Fifo<Node> initializeQueueToVisit(int size){
        Fifo<Node> queToVisit = new Fifo<>(size);

        queToVisit.push(startNode);

        for(int y = 0; y < graph.getSize().height(); y++){
            for(int x = 0; x < graph.getSize().width(); x++) {
                Node node = graph.getNode(y, x);
                if (node.compareTo(startNode) != 0)
                    queToVisit.push(node);
            }
        }

        return queToVisit;
    }

    public void printDijkstraTable(){// Może lepiej toString()?
        System.out.println("-----Dijkstra table-----");
        System.out.println(queToVisit);
        System.out.println("NODE\t\t\tS. PATH\t\t\tPREV NODE");
        for(int i = 0; i< amountOfNodes; i++){
            System.out.println(graph.getNode(i).getGraphID() + "\t\t\t" + pathLengths[i] + "\t\t\t" + (previousNode[i] == null ? "-" : previousNode[i].getGraphID()));
        }
    }

    public void printDijkstraResult(Node finishNode){
        System.out.println("-----Dijkstra result-----");
        System.out.println("\tShortest path from: " + this.startNode.getGraphID() + " to " + finishNode.getGraphID() + " = " + this.getShortestPathLength(finishNode));
        System.out.println("\tThe following path:\n\t" + this.getShortestPath(finishNode));
    }

    public double getShortestPathLength(Node finishNode){
        double shortestPath= 0;
        if(finishNode == this.startNode)
            return 0;
        for(int i = finishNode.getGraphID(); previousNode[i] != startNode; i = previousNode[i].getGraphID())
            shortestPath += pathLengths[i];
        return shortestPath;
    }

    public String getShortestPath(Node finishNode){ //TODO RETURN ARRRAY OF NODES???
        String path= "";
        if(finishNode == this.startNode)
            return path;
        path += finishNode.getGraphID();
        for(int i = finishNode.getGraphID(); previousNode[i] != startNode; i = previousNode[i].getGraphID())
            path += " -> " + previousNode[i].getGraphID();
        path += " -> " + startNode.getGraphID();
        return path;
    }

    public Node getStartNode(){
        return this.startNode;
    }

}
