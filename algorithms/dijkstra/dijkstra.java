package algorithms.dijkstra;

import data_structures.graph.*;
import data_structures.queue.*;


public class dijkstra {

    private final Graph graphToCheck;

    private final Node startNode;

    private double shortestPath;

    private double [] pathLengths;
    private Node [] previousNode;
    private Fifo<Node> queToVisit;
    private int ammountOfNodes;

    /*public dijkstra(Graph g, int startNodeIndex, int endNodeIndex){
        this.graphToCheck = g;
        this.startNode = g.getNode(startNodeIndex);
        this.endNode = endNodeIndex;
    }*/

    public dijkstra(Graph g, Node startNode){
        this.graphToCheck = g;
        this.startNode = startNode;

        this.initializeDijkstrasTable();
        this.printDijkstrasTable();
        this.fillDijkstrasTable();
    }

    private void initializeDijkstrasTable(){
        ammountOfNodes = graphToCheck.getSize().height() * graphToCheck.getSize().width();

        queToVisit= initializeQueToVisit(ammountOfNodes);

        pathLengths = new double[ammountOfNodes];
        previousNode = new Node[ammountOfNodes];

        for(int i= 0; i<ammountOfNodes; i++){
            previousNode[i] = null;
            if(i == startNode.getId())
                pathLengths[i] = 0;
            else
                pathLengths[i] = Double.MAX_VALUE;
        }
    }

    Node t = new Node();

    private void fillDijkstrasTable() {
        while(!queToVisit.IsEmpty()){
            System.out.println(queToVisit);
            Node currentVertex = queToVisit.pop();
            System.out.println("Current vertex: " + currentVertex.getId());
            for(Node.ConnectionSide connection : Node.ConnectionSide.values()){
                if(!currentVertex.isConnectionNull(connection)){

                    System.out.println("\t connection to Node: " + graphToCheck.getIdOfConnectedVertex(currentVertex, connection) + " weight = " + currentVertex.getConnectionWeight(connection));

                    double valToCheck = currentVertex.getConnectionWeight(connection) + pathLengths[currentVertex.getId()];
                    System.out.println("valtocheck = " + valToCheck + "        = currentVertex.getConnectionWeight(connection) + pathLengths[currentVertex.getId()] = " + currentVertex.getConnectionWeight(connection) +  " + " + pathLengths[currentVertex.getId()]);
                    System.out.println("\t\t" + valToCheck + " <(?)< " + pathLengths[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] + " ? ");

                    if(valToCheck < pathLengths[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] ){
                        pathLengths[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] = valToCheck;
                        previousNode[graphToCheck.getIdOfConnectedVertex(currentVertex, connection)] = graphToCheck.getNode(graphToCheck.getIdOfConnectedVertex(currentVertex, connection));
                    }
                }
            }
            this.printDijkstrasTable();
        }
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
        for(int i= 0; i<ammountOfNodes; i++){
            System.out.println(graphToCheck.getNode(i).getId() + "\t\t\t" + pathLengths[i] + "\t\t\t" + (previousNode[i] == null ? "-" : previousNode[i].getId()));
        }
    }

    public double getShortestPath(){
        return this.shortestPath;
    }

}
