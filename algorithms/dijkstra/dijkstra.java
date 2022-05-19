package algorithms.dijkstra;

import data_structures.graph.*;
import data_structures.queue.*;


public class dijkstra {

    private final Graph graphToCheck;
    private final Node startNode;

    private double [] pathLengths;
    private Node [] previousNode;
    private Fifo<Node> queToVisit;
    private int amountOfNodes;

    public dijkstra(Graph g, Node startNode){
        this.graphToCheck = g;
        this.startNode = startNode;

        this.initializeDijkstrasTable();
        this.fillDijkstrasTable();
    }

    private void initializeDijkstrasTable(){
        amountOfNodes = graphToCheck.getSize().height() * graphToCheck.getSize().width();

        queToVisit= initializeQueToVisit(amountOfNodes);

        pathLengths = new double[amountOfNodes];
        previousNode = new Node[amountOfNodes];

        for(int i = 0; i< amountOfNodes; i++){
            previousNode[i] = null;
            if(i == startNode.getId())
                pathLengths[i] = 0;
            else
                pathLengths[i] = Double.MAX_VALUE;
        }
    }

    private void fillDijkstrasTable() {
        while(!queToVisit.IsEmpty()){
            Node currentVertex = queToVisit.pop();
            for(Node.ConnectionSide connection : Node.ConnectionSide.values()){
                if(!currentVertex.isConnectionNull(connection)){
                    double valToCheck = currentVertex.getConnectionWeight(connection) + pathLengths[currentVertex.getId()];
                    if(valToCheck < pathLengths[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] ){
                        pathLengths[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] = valToCheck;
                        previousNode[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] = currentVertex;
                    }
                }
            }
            setFifoHead();
        }

    }

    private void setFifoHead(){
        double minDistance = Double.MAX_VALUE;
        double actualDistance = 0.;
        int minDistanceIndex= queToVisit.fifoHeadIndex();
        int max = queToVisit.getLength();
        for(int i= queToVisit.fifoHeadIndex(); i< max ; i++){
            actualDistance = pathLengths[queToVisit.getAtIndex(i).getId()];
            if(actualDistance < minDistance){
                minDistance = actualDistance;
                minDistanceIndex = i;
            }
        }
        if(queToVisit.fifoHeadIndex() != queToVisit.getLength())
            queToVisit.swapElements(queToVisit.fifoHeadIndex(), minDistanceIndex);
    }

    private Fifo<Node> initializeQueToVisit(int size){
        Fifo<Node> queToVisit = new Fifo<>(size);

        queToVisit.push(startNode);
        int startNodeIndex = startNode.getId(), currentIndex;

        for(int i= 0; i < graphToCheck.getSize().height(); i++){
            for(int j= 0; j < graphToCheck.getSize().width(); j++) {
                currentIndex = i * graphToCheck.getSize().width() + j;
                if (currentIndex != startNodeIndex)
                    queToVisit.push(graphToCheck.getNode(i, j));
            }
        }

        return queToVisit;
    }

    public void printDijkstrasTable(){
        System.out.println("-----Dijkstra table-----");
        System.out.println(queToVisit);
        System.out.println("NODE\t\t\tS. PATH\t\t\tPREV NODE");
        for(int i = 0; i< amountOfNodes; i++){
            System.out.println(graphToCheck.getNode(i).getId() + "\t\t\t" + pathLengths[i] + "\t\t\t" + (previousNode[i] == null ? "-" : previousNode[i].getId()));
        }
    }

    public void printDijkstraResult(Node finishNode){
        System.out.println("-----Dijkstra result-----");
        System.out.println("\tShortest path from: " + this.startNode.getId() + " to " + finishNode.getId() + " = " + this.getShortestPathLength(finishNode));
        System.out.println("\tThe following path:\n\t" + this.getShortestPath(finishNode));
    }

    public double getShortestPathLength(Node finishNode){
        double shortestPath= 0;
        if(finishNode == this.startNode)
            return 0;
        for(int i= finishNode.getId(); previousNode[i] != startNode; i = previousNode[i].getId())
            shortestPath += pathLengths[i];
        return shortestPath;
    }

    public String getShortestPath(Node finishNode){
        String path= "";
        if(finishNode == this.startNode)
            return path;
        path += finishNode.getId();
        for(int i= finishNode.getId(); previousNode[i] != startNode; i = previousNode[i].getId())
            path += " -> " + previousNode[i].getId();
        path += " -> " + startNode.getId();
        return path;
    }

    public Node getStartNode(){
        return this.startNode;
    }

}
