package algorithms.dijkstra;

import data_structures.graph.*;
import data_structures.queue.*;

public class Dijkstra implements Algorithm{

    protected static class DijkstraData implements Comparable<DijkstraData>{
        public double length = Double.MAX_VALUE;
        public Node previousNode = null;

        @Override
        public int compareTo(DijkstraData o) {
            return this.length < o.length ? 1 : -1;
        }
    }

    private final Graph graph;
    private final Node startNode;

    private DijkstraData[] dijkstraTable;

    private PriorityQueue<Node> queToVisit;
    private final static int STARTING_SIZE = 4;

    public Dijkstra(Graph g, Node startNode){
        this.graph = g;
        this.startNode = startNode;

        initializeDijkstraTable();
    }

    private void initializeDijkstraTable(){
        queToVisit= initializeQueueToVisit(graph.getNodesCount());

        this.dijkstraTable = new DijkstraData [graph.getNodesCount()];
        for(int i = 0; i<graph.getNodesCount(); i++)
            dijkstraTable[i] = new DijkstraData();

        this.dijkstraTable[startNode.getGraphID()].length = 0;
    }

    @Override
    public void Solve(){
        ;
    }

    public void Solve(Node finishNode) { //SOLVE();
        while(!queToVisit.IsEmpty()){
            Node currentNode = queToVisit.pop();
            for(Path.Side side : Path.Side.values()){
                if(currentNode.isConnected(side)){
                    double valToCheck = currentNode.getConnectionWeight(side) + dijkstraTable[currentNode.getGraphID()].length;
                    Node Neighbour = graph.getNeighbourNode(currentNode, side);
                    int index = Neighbour.getGraphID();
                    if(index >= 0 && index < graph.getNodesCount() && valToCheck < dijkstraTable[index].length ){
                        dijkstraTable[index].length = valToCheck;
                        dijkstraTable[index].previousNode = currentNode;
                    }
                }
            }
        }

    }
/* TODO DELETE THIS - REDUNDANT CODE - use priority queue *//*

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
*/

    private PriorityQueue<Node> initializeQueueToVisit(int size){
        PriorityQueue<Node> queToVisit = new PriorityQueue<>(size);

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

    @Override
    public String toString(){// Może lepiej toString()?
        StringBuilder s = new StringBuilder();
        s.append("-----Dijkstra table-----\n");
        s.append(queToVisit).append("\n");
        s.append("NODE\t\t\tS. PATH\t\t\tPREV NODE\n");
        for(int i = 0; i< graph.getSize().height(); i++){
            for(int j= 0; j<graph.getSize().width(); j++)
            s.append(graph.getNode(i, j).getGraphID()).append("\t\t\t").append(dijkstraTable[i].length).append("\t\t\t").append((dijkstraTable[i].previousNode == null ? "-" : dijkstraTable[i].previousNode.getGraphID())).append("\n");
        }
        return s.toString();
    }

    public String getDijkstraResult(Node finishNode){
        String s = "";
        s += ("-----Dijkstra result-----\n");
        s += "\tShortest path from: "+this.startNode.getGraphID()+" to "+finishNode.getGraphID()+" = "+this.getShortestPathLength(finishNode)+"\n";
        s += "\tThe following path:\n\t"+this.getShortestPathString(finishNode)+"\n";
        return s;
    }

    public double getShortestPathLength(Node finishNode){
        double shortestPath= 0;
        if(finishNode == this.startNode)
            return 0;
        for(int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            shortestPath += dijkstraTable[i].length;
        return shortestPath;
    }

    public String getShortestPathString(Node finishNode){ //TODO RETURN ARRRAY OF NODES???
        StringBuilder path = new StringBuilder();
        if(finishNode == this.startNode)
            return path.toString();
        path.append(finishNode.getGraphID());
        for(int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            path.append(" -> ").append(dijkstraTable[i].previousNode.getGraphID());
        path.append(" -> ").append(startNode.getGraphID());
        return path.toString();
    }

    public Node [] getShortestPath(Node finishNode){
        Node [] t = new Node [STARTING_SIZE];
        return t;
    }

    public Node getStartNode(){
        return this.startNode;
    }

}
