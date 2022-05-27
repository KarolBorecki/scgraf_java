package com.scgraf.algorithms.dijkstra;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;

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
            try {
                singleDivisionDijkstra();
            } catch (Graph.InvalidMeshConnection e) {
                e.printStackTrace();
            }
        }
    }

    private void singleDivisionDijkstra() throws Graph.InvalidMeshConnection {
        int y1, y2;

        y1 = random.nextInt(dividedGraph.getSize().height());
        do {
            y2 = random.nextInt(dividedGraph.getSize().height());
        } while (y1 != y2);

        Node firstNode = dividedGraph.getNode(y1, 0);
        Node secondNode = dividedGraph.getNode(y2, dividedGraph.getSize().width() - 1);

        Dijkstra d = new Dijkstra(dividedGraph, firstNode);
        d.Solve(secondNode);
        System.out.println(d.getShortestPathString(secondNode));

        Node[] t = d.getShortestPath(secondNode);
        /*System.out.print("|     |");
        for(int i= 0; i<t.length && t[i] != null; i++){
            System.out.print(" Node " + t[i].getGraphID() + " ---> ");
        }System.out.println("|     |");*/

        int i = 0;
        Node prevNode = t[i];

        Path.Side verticalConnectionToCut;
        Path.Side parallelConnectionToCut;

        if(secondNode.isConnected(Path.Side.TOP) && dividedGraph.getPathForConnection(secondNode, t[1]) != Path.Side.TOP)
            verticalConnectionToCut = Path.Side.TOP;
        else
            verticalConnectionToCut = Path.Side.BOTTOM;

        if(secondNode.isConnected(Path.Side.LEFT) && dividedGraph.getPathForConnection(secondNode, t[1]) != Path.Side.LEFT)
            parallelConnectionToCut = Path.Side.LEFT;
        else
            parallelConnectionToCut = Path.Side.RIGHT;

        for (i = 1; i < t.length && t[i] != null; i++) {
            if( dividedGraph.getPathForConnection(prevNode, t[i]) != parallelConnectionToCut && dividedGraph.getPathForConnection(prevNode, t[i]) != verticalConnectionToCut)
                dividedGraph.deletePathBothWays(prevNode, parallelConnectionToCut);
                dividedGraph.deletePathBothWays(prevNode, verticalConnectionToCut);

            prevNode = t[i];
        }
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


}
