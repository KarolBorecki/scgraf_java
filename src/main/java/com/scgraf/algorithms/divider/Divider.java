package com.scgraf.algorithms.divider;

import com.scgraf.algorithms.Dijkstra;
import com.scgraf.data_structures.graph.Graph;
import com.scgraf.solver.Solver;

public class Divider {

    public enum DividingType {
        DIJKSTRA("Random, dijkstra"),
        PATH_CUT("Path cut"),
        STRAIGHT("Straight (don't)");

        public final String str;

        DividingType(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    public static void Divide(Graph graph, int dividingNumber, DividingType dividingType) throws DijkstraDivider.WrongDivisionsNumber, Exception, Dijkstra.DijkstraNotSolvedException, SimpleDivider.TooManyDividesException, Dijkstra.DijkstraCannotFindPathException, Graph.InvalidMeshConnection {
        if (dividingType == DividingType.DIJKSTRA) DijkstraDivider.divideGraphThisManyTimes(graph, dividingNumber);
        else if (dividingType == DividingType.PATH_CUT) SimpleDivider.pathCut(graph, Solver.getInstance().getChosenNodes().getFirst(), Solver.getInstance().getChosenNodes().getSecond());
        else if (dividingType == DividingType.STRAIGHT) SimpleDivider.divideGraphThisManyTimes(graph, dividingNumber);
    }
}
