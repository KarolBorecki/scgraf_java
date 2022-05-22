package com.scgraf.algorithms.dijkstra;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.queue.PriorityQueue;

public class Dijkstra implements Algorithm{

    protected static class DijkstraData implements Comparable<DijkstraData>{
        public double length = Double.MAX_VALUE;
        public Node previousNode = null;
        public Node currentNode;

        public DijkstraData(Node node){
            this.currentNode = node;
        }

        public Node getCurrentNode(){
            return currentNode;
        }

        public Node getPreviousNode(){
            return previousNode;
        }

        @Override
        public int compareTo(DijkstraData o) {
            return this.length < o.length ? 1 : -1;
        }

        @Override
        public String toString(){
            return "\t" + length + "\t" + (previousNode == null ? "-" : previousNode.getGraphID());
        }
    }

    private final Graph graph;
    private final Node startNode;

    private DijkstraData[] dijkstraTable;

    private PriorityQueue<DijkstraData> queToVisit;
    private final static int STARTING_SIZE = 4;

    private boolean isSolved = false;

    public Dijkstra(Graph g, Node startNode){
        this.graph = g;
        this.startNode = startNode;
        this.queToVisit = new PriorityQueue<>(graph.getNextGraphID());

        initializeDijkstraTable();
    }

    private void initializeDijkstraTable(){
        this.dijkstraTable = new DijkstraData [graph.getNodesCount()];
        for(int i = 0; i<graph.getSize().height(); i++)
            for(int j = 0; j<graph.getSize().width(); j++) {
                int indexInGraph = i*graph.getSize().width() + j;
                dijkstraTable[indexInGraph] = new DijkstraData(graph.getNode(i, j));
                queToVisit.push(dijkstraTable[indexInGraph]);
            }

        this.dijkstraTable[startNode.getGraphID()].length = 0;
        this.dijkstraTable[startNode.getGraphID()].previousNode = startNode;
    }

    @Override
    public void Solve(){
        ;
    }

    public void Solve(Node finishNode) { //SOLVE();
        while(!queToVisit.IsEmpty()){
            Node currentNode = queToVisit.pop().getCurrentNode();
            for(Path.Side side : Path.Side.values()){
                if(currentNode.isConnected(side)){

                    double valToCheck = currentNode.getConnectionWeight(side) + dijkstraTable[currentNode.getGraphID()].length;
                    Node Neighbour = graph.getNeighbourNode(currentNode, side);

                    if(Neighbour.getGraphID() >= 0 && Neighbour.getGraphID() < graph.getNodesCount() && valToCheck < dijkstraTable[Neighbour.getGraphID()].length ){
                        dijkstraTable[Neighbour.getGraphID()].length = valToCheck;
                        dijkstraTable[Neighbour.getGraphID()].previousNode = currentNode;
                    }
                }
            }
        }
        isSolved = true;
    }

    public Node [] getShortestPath(Node finishNode){
        Node [] t = new Node [STARTING_SIZE];
        return t;
    }

    public Node getStartNode(){
        return this.startNode;
    }

    public double getShortestPathLength(Node finishNode){
        double shortestPath= 0;
        if(finishNode == this.startNode)
            return 0;
        for(int i = finishNode.getGraphID(); dijkstraTable[i].previousNode != startNode; i = dijkstraTable[i].previousNode.getGraphID())
            shortestPath += dijkstraTable[i].length;
        return shortestPath;
    }

    public String toStringDijkstraTable(){
        StringBuilder s = new StringBuilder();
        s.append("Node index\tPath Length\tPrev Node\n");
        for(int i= 0; i<graph.getNodesCount(); i++)
            s.append("\"Node ").append(i).append("\"").append(dijkstraTable[i]).append("\n");

        return s.toString();
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

    public String getDijkstraResult(Node finishNode){
        String s = "";
        s += ("-----Dijkstra result-----\n");
        s += "\tShortest path from: "+this.startNode.getGraphID()+" to "+finishNode.getGraphID()+" = "+this.getShortestPathLength(finishNode)+"\n";
        s += "\tThe following path:\n\t"+this.getShortestPathString(finishNode)+"\n";
        return s;
    }

    @Override
    public String toString(){
        if(!isSolved) {
            //TODO Should Throw an error
            System.err.println("Table havent been solved yet!");
            return null;
        }

        StringBuilder s = new StringBuilder();
        s.append("-----Dijkstra table-----\n");
        s.append("NODE\t\t\tS. PATH\t\t\tPREV NODE\n");
        for(int i= 0; i<graph.getNodesCount(); i++)
            s.append(dijkstraTable[i].getCurrentNode().getGraphID()).append("\t\t\t").append(dijkstraTable[i].length).append("\t\t\t").append((dijkstraTable[i].previousNode == null ? "-" : dijkstraTable[i].previousNode.getGraphID())).append("\n");
        return s.toString();
    }
}
