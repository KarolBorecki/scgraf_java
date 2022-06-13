package com.scgraf.algorithms;

import com.scgraf.data_structures.graph.Graph;

public class Divider {

    public enum DividingType {
        DIJKSTRA("Random, dijkstra"),
        STRAIGHT("Straight (don't)");

        public final String str;

        DividingType(String str) {
            this.str = str;
        }

        @Override
        public String toString(){
            return str;
        }
    }

    public static void Divide(Graph graph, int dividingNumber, DividingType dividingType) throws DijkstraDivider.WrongDivisionsNumber, Exception, Dijkstra.DijkstraNotSolvedException, SimpleDivider.TooManyDividesException {
        if(dividingType == DividingType.DIJKSTRA) DijkstraDivider.divideGraphThisManyTimes(graph, dividingNumber);
        else if(dividingType == DividingType.STRAIGHT) SimpleDivider.divideGraphThisManyTimes(graph, dividingNumber);
    }
}
