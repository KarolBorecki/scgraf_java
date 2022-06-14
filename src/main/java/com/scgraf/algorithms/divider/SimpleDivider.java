package com.scgraf.algorithms.divider;

import com.scgraf.algorithms.Dijkstra;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;

public class SimpleDivider implements IDivider {

    public static void pathCut(Graph dividedGraph, Node startNode, Node endNode) throws Dijkstra.DijkstraCannotFindPathException, Dijkstra.DijkstraNotSolvedException, Graph.InvalidMeshConnection {
        Dijkstra.Solve(dividedGraph, startNode);
        Node[] path = Dijkstra.getShortestPathArray(endNode);
        for(int i=0; i<path.length-1; i++){
            dividedGraph.deletePath(path[0], dividedGraph.getPathSideBetween(path[i], path[i+1]));
        }
    }

    private static void singleDivisionParallel(Graph dividedGraph, int parallel_division_point) {
        if (parallel_division_point < 0 || parallel_division_point > dividedGraph.getSize().height() - 1)
            return; //TODO Should throw an error

        for (int i = 0; i < dividedGraph.getSize().width(); i++) {
            Node currNode = dividedGraph.getNode(parallel_division_point, i);
            dividedGraph.deletePath(currNode, Path.Side.BOTTOM);
        }

    }

    private static void singleDivisionPerpendicular(Graph dividedGraph, int perpendicular_division_point) {
        if (perpendicular_division_point < 0 || perpendicular_division_point > dividedGraph.getSize().height() - 1)
            return; //TODO Should throw an error

        for (int i = 0; i < dividedGraph.getSize().height(); i++) {
            Node currNode = dividedGraph.getNode(i, perpendicular_division_point);
            dividedGraph.deletePath(currNode, Path.Side.RIGHT);
        }
    }

    public static void divideGraphThisManyTimes(Graph dividedGraph, int divisionNumber) throws DijkstraDivider.WrongDivisionsNumber {
        int maxParallelDivisions = dividedGraph.getSize().height() - 1;
        int maxPerpendicularDivisions = dividedGraph.getSize().width() - 1;
        if (divisionNumber < 1 || divisionNumber > (maxParallelDivisions + maxPerpendicularDivisions))
            return; //TODO Should throw an error

        int divisionsPerformed;

        for (divisionsPerformed = 0; divisionsPerformed < Math.min(maxPerpendicularDivisions, maxParallelDivisions); divisionsPerformed++) {
            if (divisionsPerformed % 2 == 0)
                singleDivisionParallel(dividedGraph, divisionsPerformed / 2);
            else
                singleDivisionPerpendicular(dividedGraph, divisionsPerformed / 2);
        }

        if (Math.min(maxPerpendicularDivisions, maxParallelDivisions) == maxParallelDivisions)
            for (; divisionsPerformed < divisionNumber; divisionsPerformed++)
                singleDivisionPerpendicular(dividedGraph, divisionsPerformed);
        else
            for (; divisionsPerformed < divisionNumber; divisionsPerformed++)
                singleDivisionParallel(dividedGraph, divisionsPerformed);

    }

    public static class TooManyDividesException extends Throwable {

    }
}
