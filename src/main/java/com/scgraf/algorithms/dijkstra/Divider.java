package com.scgraf.algorithms.dijkstra;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import javafx.geometry.Side;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Divider {
    private final Random random = new Random();

    private final Graph dividedGraph;
    private final int maxParallelDivisions;
    private final int maxPerpendicularDivisions;

    public Divider(Graph dividedGraph) {
        this.dividedGraph = dividedGraph;
        this.maxParallelDivisions = dividedGraph.getSize().height() - 1;
        this.maxPerpendicularDivisions = dividedGraph.getSize().width() - 1;
    }

    public void divideGraphStraight(int divideThisManyTimes) {
        if (divideThisManyTimes < 1 || divideThisManyTimes > (maxParallelDivisions + maxPerpendicularDivisions))
            return; //TODO Should throw an error

        int divisionsPerformed;

        for (divisionsPerformed = 0; divisionsPerformed < Math.min(maxPerpendicularDivisions, maxParallelDivisions); divisionsPerformed++) {
            if (divisionsPerformed % 2 == 0)
                singleDivisionParallel(divisionsPerformed / 2);
            else
                singleDivisionPerpendicular(divisionsPerformed / 2);
        }

        if (Math.min(maxPerpendicularDivisions, maxParallelDivisions) == maxParallelDivisions)
            for (; divisionsPerformed < divideThisManyTimes; divisionsPerformed++)
                singleDivisionPerpendicular(divisionsPerformed);
        else
            for (; divisionsPerformed < divideThisManyTimes; divisionsPerformed++)
                singleDivisionParallel(divisionsPerformed);

    }

    public void divideGraphUsingDijkstra(int divideThisManyTimes) {
        for (int i = 0; i < divideThisManyTimes; i++) {
            int y1, y2;
            y1 = random.nextInt(dividedGraph.getSize().height());
            do {
                y2 = random.nextInt(dividedGraph.getSize().height());
            } while (y1 != y2);

            try {
                singleDivisionDijkstra(y1, y2);
            } catch (Graph.InvalidMeshConnection | Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void singleDivisionDijkstra(int y1, int y2) throws Graph.InvalidMeshConnection, Exception {
        Node firstNode = dividedGraph.getNode(y1, 0);
        Node endNode = dividedGraph.getNode(y2, dividedGraph.getSize().width() - 1);

        Dijkstra d = new Dijkstra(dividedGraph);
        d.Solve(endNode, firstNode);
        System.out.println(d.getShortestPathString(endNode, firstNode));

        Node[] nodesToVisit = d.getShortestPath(endNode, firstNode);

        Path.Side [] connectionsFromPrevToCurr = new Path.Side [nodesToVisit.length-1];
        initializeConnections(connectionsFromPrevToCurr, nodesToVisit);
        System.out.println("lengths of arrays: Nodes[ " + nodesToVisit.length + "] | paths [" + connectionsFromPrevToCurr.length + "] ");
        for(int i= 0; i<connectionsFromPrevToCurr.length; i++) {
            System.out.println("i: " + i + " connection in array = " + connectionsFromPrevToCurr[i]);
            System.out.println("i: " + i + " Prev Node = " + nodesToVisit[i].getGraphID() + " Next Node = " + nodesToVisit[i+1].getGraphID());
        }

        int i = 0;
        int directionOfCutNodes= getCuttingDirection(y1, y2, connectionsFromPrevToCurr[i]);

        Path.Side connectionToCut, secondConnectionToCut;
        //startNode
        connectionToCut = connectionsFromPrevToCurr[i].getSideTurnedBy(directionOfCutNodes);
        dividedGraph.deletePathBothWays(nodesToVisit[i], connectionToCut);
        secondConnectionToCut = connectionsFromPrevToCurr[i].getOppositeSide();
        dividedGraph.deletePathBothWays(nodesToVisit[i], secondConnectionToCut);

        for(i = 1; i<nodesToVisit.length && nodesToVisit[i] != endNode && nodesToVisit[i] != null; i++){
            if(isNodeAnEdge(connectionsFromPrevToCurr[i-1], connectionsFromPrevToCurr[i])){

                connectionToCut = connectionsFromPrevToCurr[i-1].getSideTurnedBy(directionOfCutNodes);
                if(connectionToCut != connectionsFromPrevToCurr[i])
                    dividedGraph.deletePathBothWays(nodesToVisit[i], connectionToCut);

                secondConnectionToCut = connectionsFromPrevToCurr[i].getSideTurnedBy(directionOfCutNodes);
                if(secondConnectionToCut != connectionsFromPrevToCurr[i-1].getOppositeSide())
                    dividedGraph.deletePathBothWays(nodesToVisit[i], secondConnectionToCut);
            }else{
                connectionToCut = connectionsFromPrevToCurr[i-1].getSideTurnedBy(directionOfCutNodes);
                dividedGraph.deletePathBothWays(nodesToVisit[i], connectionToCut);
            }
        }
        //EndNode
        if(nodesToVisit[i] == null)
            return;
        connectionToCut = connectionsFromPrevToCurr[i-1].getSideTurnedBy(directionOfCutNodes);
        dividedGraph.deletePathBothWays(nodesToVisit[i], connectionToCut);

        secondConnectionToCut = connectionsFromPrevToCurr[i-2];
        dividedGraph.deletePathBothWays(nodesToVisit[i], secondConnectionToCut);
    }

    private void findEdgeNodes(boolean [] tab, Node [] Node, Path.Side [] Paths){
        for(int i= 0; i < Paths.length - 1; i++){
            if(Paths[i] != Paths[i+1])
                tab[i] = true;
        }
    }

    private void initializeConnections(Path.Side [] tabPath, Node [] tabNode) throws Graph.InvalidMeshConnection {
        for(int i= 1; i< tabNode.length && tabNode[i] != null; i++){
            tabPath[i-1] = dividedGraph.getPathForConnection(tabNode[i-1], tabNode[i]);
        }
    }

    private int getCuttingDirection(int y1, int y2, Path.Side connection){
        int directionOfCutNodes;
        if(y1 != 0 && y1 != dividedGraph.getSize().height() - 1) {
            return directionOfCutNodes = -1;
        }
        else if(y1 == 0){
            if(connection.equals(Path.Side.RIGHT))
                return directionOfCutNodes = 1;
            else
                return directionOfCutNodes = -1;
        }else if(y1 == dividedGraph.getSize().height() - 1){
            if(connection.equals(Path.Side.RIGHT))
                return directionOfCutNodes = -1;
            else
                return directionOfCutNodes = 1;
        }
        return 0;
    }

    private void singleDivisionParallel(int parallel_division_point) {
        if (parallel_division_point < 0 || parallel_division_point > dividedGraph.getSize().height() - 1)
            return; //TODO Should throw an error

        for (int i = 0; i < dividedGraph.getSize().width(); i++) {
            Node currNode = dividedGraph.getNode(parallel_division_point, i);
            dividedGraph.deletePathBothWays(currNode, Path.Side.BOTTOM);
        }

    }

    private void singleDivisionPerpendicular(int perpendicular_division_point) {
        if (perpendicular_division_point < 0 || perpendicular_division_point > dividedGraph.getSize().height() - 1)
            return; //TODO Should throw an error

        for (int i = 0; i < dividedGraph.getSize().height(); i++) {
            Node currNode = dividedGraph.getNode(i, perpendicular_division_point);
            dividedGraph.deletePathBothWays(currNode, Path.Side.RIGHT);
        }
    }

    private boolean isNodeAnEdge (Path.Side PathToNode, Path.Side PathFromNode){
        return PathToNode != PathFromNode;
    }


}
